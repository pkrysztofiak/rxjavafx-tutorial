package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Exercise8 extends Application {

	private final ObservableList<Employee> employees = FXCollections.observableArrayList(
			new Employee("David", "Seaman", Position.G),
			new Employee("Gary", "Neville", Position.CB),
			new Employee("Tony", "Adams", Position.CB),
			new Employee("Gareth", "Southgate", Position.LF),
			new Employee("Stuart", "Pearce", Position.RF),
			new Employee("Steve", "McManaman", Position.LMF),
			new Employee("David", "Platt", Position.CMF),
			new Employee("Paul", "Gascoigne", Position.CMF),
			new Employee("Darren", "Anderton", Position.RMF),
			new Employee("Alan", "Shearer", Position.CF),
			new Employee("Teddy", "Sheringham", Position.CF));

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