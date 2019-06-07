package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Employee;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise7 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		ObservableList<Employee> employees = FXCollections.observableArrayList(
				new Employee("David", "Seaman", null),
				new Employee("Gary", "Neville", null),
				new Employee("Tony", "Adams", null)
//				new Employee("Gareth", "Southgate"),
//				new Employee("Stuart", "Pearce"),
//				new Employee("Steve", "McManaman"),
//				new Employee("David", "Platt"),
//				new Employee("Paul", "Gascoigne"),
//				new Employee("Darren", "Anderton"),
//				new Employee("Alan", "Shearer"),
//				new Employee("Teddy", "Sheringham")
				);

		ListView<Employee> employeesListView = new ListView<>(employees);
		employeesListView.setCellFactory(listView -> {
			EmployeeListCellWorking employeeListCell = new EmployeeListCellWorking();
			employeeListCell.removeEmployeeObservable().subscribe(employees::remove);
			return employeeListCell;
		});

		Button button = new Button("Remove first");

		VBox vBox = new VBox(employeesListView, button);

		stage.setScene(new Scene(vBox));
		stage.show();

		button.setOnAction(action -> employees.remove(0));
	}
}