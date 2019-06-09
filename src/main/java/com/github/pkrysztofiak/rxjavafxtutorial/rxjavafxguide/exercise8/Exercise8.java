package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Exercise8 extends Application {

	private final ObservableList<Player> players = FXCollections.observableArrayList(
			new Player("David", "Seaman", Position.G),
			new Player("Gary", "Neville", Position.CB),
			new Player("Tony", "Adams", Position.CB),
			new Player("Gareth", "Southgate", Position.LF),
			new Player("Stuart", "Pearce", Position.RF),
			new Player("Steve", "McManaman", Position.LMF),
			new Player("David", "Platt", Position.CMF),
			new Player("Paul", "Gascoigne", Position.CMF),
			new Player("Darren", "Anderton", Position.RMF),
			new Player("Alan", "Shearer", Position.CF),
			new Player("Teddy", "Sheringham", Position.CF));

	private final ListView<Player> playersListView = new ListView<>(players);
	private final Scene scene = new Scene(playersListView);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		playersListView.setCellFactory(listView -> {
			PlayerListCell playerListCell = new PlayerListCell();
			playerListCell.employeeRemoveRequestObservable().subscribe(players::remove);
			return playerListCell;
		});
	}
}