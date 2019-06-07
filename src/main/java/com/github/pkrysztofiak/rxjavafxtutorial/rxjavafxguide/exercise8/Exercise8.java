package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Exercise8 extends Application {

	private final ObservableList<Employee> employees = FXCollections.observableArrayList(
			new Employee("David", "Seaman"),
			new Employee("Gary", "Neville"),
			new Employee("Tony", "Adams"),
			new Employee("Gareth", "Southgate"),
			new Employee("Stuart", "Pearce"),
			new Employee("Steve", "McManaman"),
			new Employee("David", "Platt"),
			new Employee("Paul", "Gascoigne"),
			new Employee("Darren", "Anderton"),
			new Employee("Alan", "Shearer"),
			new Employee("Teddy", "Sheringham"));

	private final ListView<Employee> employeesListView = new ListView<>(employees);
	private final Scene scene = new Scene(employeesListView);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		employeesListView.setCellFactory(listView -> {
			EmployeeListCell employeeListCell = new EmployeeListCell();
			employeeListCell.employeeRemoveRequestObservable().subscribe(employees::remove);
			return employeeListCell;
		});
	}
}