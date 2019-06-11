package com.github.pkrysztofiak.rxjavafxtutorial.real;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayersApp extends Application {

	private final ObservableList<Player> players = FXCollections.observableArrayList(
			new Player("David", "Seaman", 0),
			new Player("Gary", "Neville", 0),
			new Player("Tony", "Adams", 2),
			new Player("Gareth", "Southgate", 0),
			new Player("Stuart", "Pearce", 0),
			new Player("Steve", "McManaman", 0));

	private final PlayersListView playersListView = new PlayersListView();
	private final Scene scene = new Scene(playersListView);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Bindings.bindContentBidirectional(playersListView.getPlayers(), players);

		stage.setScene(scene);
		stage.show();
	}
}