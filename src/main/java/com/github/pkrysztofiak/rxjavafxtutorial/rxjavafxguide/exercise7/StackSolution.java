package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Employee;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class StackSolution extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {


		ObservableList<Employee> employees = FXCollections.observableArrayList(
				new Employee("David", "Seaman"),
				new Employee("Gary", "Neville"),
				new Employee("Tony", "Adams")
//				new Employee("Gareth", "Southgate"),
//				new Employee("Stuart", "Pearce"),
//				new Employee("Steve", "McManaman"),
//				new Employee("David", "Platt"),
//				new Employee("Paul", "Gascoigne"),
//				new Employee("Darren", "Anderton"),
//				new Employee("Alan", "Shearer"),
//				new Employee("Teddy", "Sheringham")
				);

		ListView<Employee> listView = new ListView<>(employees);
		stage.setScene(new Scene(listView));
		stage.show();

		listView.setCellFactory(lv -> {
			EmployeeListCellView cellRoot = new EmployeeListCellView();
			cellRoot.removeEmployeeObservable().subscribe(employees::remove);
		    // create nodes, register listeners on them, populate cellRoot, etc...
		    ListCell<Employee> cell = new ListCell<>();
		    cell.itemProperty().addListener((obs, oldItem, newItem) -> {
		        if (newItem != null) {
		        	cellRoot.setEmployee(newItem);
		            // update cellRoot (or its child nodes' properties) accordingly
		        }
		    });
		    cell.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
		        if (isEmpty) {
		            cell.setGraphic(null);
		        } else {
		            cell.setGraphic(cellRoot);
		        }
		    });
		    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    return cell ;
		});

	}
}