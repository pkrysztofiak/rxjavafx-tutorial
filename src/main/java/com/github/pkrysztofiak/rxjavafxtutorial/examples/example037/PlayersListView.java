package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class PlayersListView extends ListView<Player> {

	private final ObservableList<Player> players = FXCollections.observableArrayList();

	public PlayersListView() {
		setItems(players);

		setCellFactory(listView -> {
			PlayerListCell playerListCell = new PlayerListCell();
			playerListCell.employeeRemoveRequestObservable().subscribe(players::remove);
			return playerListCell;
		});


	}

	public ObservableList<Player> getPlayers() {
		return players;
	}
}