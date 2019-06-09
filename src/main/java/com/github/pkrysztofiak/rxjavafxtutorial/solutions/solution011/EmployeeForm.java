package com.github.pkrysztofiak.rxjavafxtutorial.solutions.solution011;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmployeeForm extends VBox {

	private final ObjectProperty<Employee> employeeProperty = new SimpleObjectProperty<>();
	private final Observable<Employee> employeeObservable = JavaFxObservable.valuesOf(employeeProperty);


	private final Label nameLabel = new Label();
	private final Label surnameLabel = new Label();
	private final HBox hBox = new HBox(surnameLabel, new Label(" "), nameLabel);

	private final TextArea descriptionTextArea = new TextArea();
	private final Observable<String> descriptionTextAreaObservable = JavaFxObservable.valuesOf(descriptionTextArea.textProperty());
	private final Observable<Employee> descriptionObservable = employeeObservable.switchMap(employee -> JavaFxObservable.valuesOf(employee.descriptionProperty()).map(description -> employee));

	{
		getChildren().setAll(hBox, descriptionTextArea);
	}

	public EmployeeForm() {
		employeeObservable.subscribe(this::onEmployeeChanged);
		descriptionTextAreaObservable.subscribe(this::onDescriptionChanged);
	}

	public void setEmployee(Employee employee) {
		employeeProperty.set(employee);
	}

	public Observable<Employee> descriptionObservable() {
		return descriptionObservable;
	}

	private void onEmployeeChanged(Employee employee) {
		nameLabel.setText(employee.getName());
		surnameLabel.setText(employee.getSurname());
		descriptionTextArea.setText(employee.getDescription());
	}

	private void onDescriptionChanged(String description) {
		Optional.ofNullable(employeeProperty.get()).ifPresent(employee -> employee.setDescription(description));
	}
}