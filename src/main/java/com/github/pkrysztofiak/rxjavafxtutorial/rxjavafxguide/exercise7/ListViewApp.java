package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ListViewApp extends Application {

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

		ListView<Player> employessListView = new ListView<>(players);
		stage.setScene(new Scene(employessListView));
		stage.show();

//		employessListView.setCellFactory(listView -> new ListCell<Employee>() {
//
//			@Override
//			protected void updateItem(Employee employee, boolean empty) {
//				super.updateItem(employee, empty);
//				if ((employee == null) || empty) {
//					setGraphic(null);
//				} else {
//					System.out.println(this + "\t update(" + employee.surnameProperty().get() + ")");
//					EmployeeListCellView cellView = new EmployeeListCellView();
//					cellView.removeEmployeeObservable().subscribe(employees::remove);
//					cellView.setEmployee(employee);
//					setGraphic(cellView);
//				}
//			}
//		});

		employessListView.setCellFactory(listView -> {
			EmployeeListCellWorking listCell = new EmployeeListCellWorking();
			listCell.removeEmployeeObservable().subscribe(players::remove);
			return listCell;
		});
	}
}