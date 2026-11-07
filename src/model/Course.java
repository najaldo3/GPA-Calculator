
package model;

import java.util.Objects;
/*
 * Immutable ADT representing a university course.
 *
 * Representation Invariant (RI):
 * - Course name must not be empty.
 * - Credit hours must be greater than zero.
 * - Grade must be one of the allowed letter grades.
 *
 * Abstraction Function (AF):
 * - name represents the course name.
 * - creditHours represents the course credit hours.
 * - grade represents the student's letter grade.
 */
public final class Course {
    private final String name;
    private final int creditHours;
    private final String grade;

    public Course(String name, int creditHours, String grade) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter a course name.");
        }
        if (creditHours <= 0) {
            throw new IllegalArgumentException("Credit hours must be positive.");
        }
       if (grade == null ||
    !java.util.Arrays.asList(
        "A+", "A", "B+", "B", "C+", "C", "D+", "D", "F"
    ).contains(grade.trim().toUpperCase())) {
    throw new IllegalArgumentException("Select a valid grade.");
}

        this.name = name.trim();
        this.creditHours = creditHours;
        this.grade = grade.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(java.util.Locale.ROOT));
    }
}
