
package controller;

import model.Course;
import model.GpaModel;
import view.GpaView;

public class GpaController {

    private final GpaModel model;
    private final GpaView view;

    public GpaController(GpaModel model, GpaView view) {
        this.model = model;
        this.view = view;

        // Observer pattern
        model.addObserver(() -> view.refresh(model));

        view.getAddButton().addActionListener(e -> addCourse());
        view.getEditButton().addActionListener(e -> editCourse());
        view.getDeleteButton().addActionListener(e -> deleteCourse());

        view.getScaleBox().addActionListener(e ->
            model.setScale(view.getSelectedScale())
        );

        view.onCourseSelected(() -> view.fillFieldsFromSelectedRow());

        view.refresh(model);
    }

    private Course readCourse() {
        String name = view.getCourseName();
        int hours;

        try {
            hours = Integer.parseInt(view.getCreditHours().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Credit hours must be a valid number."
            );
        }

        String grade = view.getSelectedGrade();
        return new Course(name, hours, grade);
    }

    private void addCourse() {
        try {
            model.addCourse(readCourse());
            view.clearFields();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void editCourse() {
        try {
            int index = view.getSelectedRow();
            model.editCourse(index, readCourse());
            view.clearFields();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void deleteCourse() {
        try {
            model.deleteCourse(view.getSelectedRow());
            view.clearFields();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
}
