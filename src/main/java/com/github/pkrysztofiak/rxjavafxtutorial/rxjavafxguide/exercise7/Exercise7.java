package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

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

		ObservableList<Player> players = FXCollections.observableArrayList(
				new Player("David", "Seaman", null),
				new Player("Gary", "Neville", null),
				new Player("Tony", "Adams", null)
//				new Employee("Gareth", "Southgate"),
//				new Employee("Stuart", "Pearce"),
//				new Employee("Steve", "McManaman"),
//				new Employee("David", "Platt"),
//				new Employee("Paul", "Gascoigne"),
//				new Employee("Darren", "Anderton"),
//				new Employee("Alan", "Shearer"),
//				new Employee("Teddy", "Sheringham")
				);

		ListView<Player> employeesListView = new ListView<>(players);
		employeesListView.setCellFactory(listView -> {
			EmployeeListCellWorking employeeListCell = new EmployeeListCellWorking();
			employeeListCell.removeEmployeeObservable().subscribe(players::remove);
			return employeeListCell;
		});

		Button button = new Button("Remove first");

		VBox vBox = new VBox(employeesListView, button);

		stage.setScene(new Scene(vBox));
		stage.show();

		button.setOnAction(action -> players.remove(0));
	}
}