package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise37 extends Application {

	private final ObservableList<Player> players = PlayersFactory.createEnglandNationlTeam();

	private final PlayersListView playersListView = new PlayersListView();
	private final PlayerStatsView playerStatsView = new PlayerStatsView();
	private final HBox hBox = new HBox(playersListView, playerStatsView);
	private final Scene scene = new Scene(hBox);



	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		Bindings.bindContentBidirectional(playersListView.getPlayers(), players);
		playerStatsView.playerProperty().bind(playersListView.getSelectionModel().selectedItemProperty());
	}
}