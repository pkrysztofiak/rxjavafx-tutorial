package com.github.pkrysztofiak.rxjavafxtutorial.tasks.task011;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Example037 extends Application {

	private final EmployeeChooser employeeChooser = new EmployeeChooser();
	private final EmployeeForm employeeForm = new EmployeeForm();
	private final HBox hBox = new HBox(employeeChooser, employeeForm);
	private final Scene scene = new Scene(hBox);

	private final ExternalSystem externalSystem = new ExternalSystem();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		employeeChooser.selectedEmployeeObservable().subscribe(employeeForm::setEmployee);
		employeeForm.descriptionObservable().subscribe(externalSystem::updateDescription);
	}
}