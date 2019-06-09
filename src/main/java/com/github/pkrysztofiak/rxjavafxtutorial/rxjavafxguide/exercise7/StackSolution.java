package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

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

		ListView<Player> listView = new ListView<>(players);
		stage.setScene(new Scene(listView));
		stage.show();

		listView.setCellFactory(lv -> {
			EmployeeListCellView cellRoot = new EmployeeListCellView();
			cellRoot.removeEmployeeObservable().subscribe(players::remove);
		    // create nodes, register listeners on them, populate cellRoot, etc...
		    ListCell<Player> cell = new ListCell<>();
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