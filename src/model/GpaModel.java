
package model;

import java.util.ArrayList;
import java.util.List;

public class GpaModel {

    // Singleton pattern
    private static final GpaModel instance = new GpaModel();

    private final List<Course> courses = new ArrayList<>();
    private final List<Runnable> observers = new ArrayList<>();
    private int scale = 5;

    private GpaModel() {
    }

    public static GpaModel getInstance() {
        return instance;
    }

    // Observer pattern
    public void addObserver(Runnable observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Runnable observer : observers) {
            observer.run();
        }
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public void addCourse(Course course) {
        if (courses.contains(course)) {
            throw new IllegalArgumentException("Course already exists.");
        }
        courses.add(course);
        notifyObservers();
    }

    public void editCourse(int index, Course course) {
        checkIndex(index);

        for (int i = 0; i < courses.size(); i++) {
            if (i != index && courses.get(i).equals(course)) {
                throw new IllegalArgumentException("Course already exists.");
            }
        }

        courses.set(index, course);
        notifyObservers();
    }

    public void deleteCourse(int index) {
        checkIndex(index);
        courses.remove(index);
        notifyObservers();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= courses.size()) {
            throw new IllegalArgumentException("Select a course first.");
        }
    }

    public void setScale(int scale) {
        if (scale != 4 && scale != 5) {
            throw new IllegalArgumentException("Invalid GPA scale.");
        }
        this.scale = scale;
        notifyObservers();
    }

    public int getScale() {
        return scale;
    }

    public int getTotalCreditHours() {
        int total = 0;
        for (Course course : courses) {
            total += course.getCreditHours();
        }
        return total;
    }

    public double calculateGpa() {
        int totalHours = getTotalCreditHours();

        if (totalHours == 0) {
            return 0;
        }

        double totalPoints = 0;

        for (Course course : courses) {
            double points = getGradePoints(course.getGrade());
            totalPoints += points * course.getCreditHours();
        }

        return totalPoints / totalHours;
    }

    private double getGradePoints(String grade) {
        if (scale == 5) {
            switch (grade) {
                case "A+": return 5.0;
                case "A": return 4.75;
                case "B+": return 4.5;
                case "B": return 4.0;
                case "C+": return 3.5;
                case "C": return 3.0;
                case "D+": return 2.5;
                case "D": return 2.0;
                default: return 1.0;
            }
        } else {
            switch (grade) {
                case "A+": return 4.0;
                case "A": return 3.75;
                case "B+": return 3.5;
                case "B": return 3.0;
                case "C+": return 2.5;
                case "C": return 2.0;
                case "D+": return 1.5;
                case "D": return 1.0;
                default: return 0.0;
            }
        }
    }
}
