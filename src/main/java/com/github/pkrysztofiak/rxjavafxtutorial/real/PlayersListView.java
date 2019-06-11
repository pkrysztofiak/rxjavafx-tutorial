package com.github.pkrysztofiak.rxjavafxtutorial.real;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class PlayersListView extends VBox {

	private final ObservableList<Player> players = FXCollections.observableArrayList();
	private final ListView<Player> playersListView = new ListView<>(players);

	private final Behaviour behaviour = new Behaviour();

	{
		getChildren().add(playersListView);
	}

	public PlayersListView() {
		playersListView.setCellFactory(listView -> {
			PlayerListCell playerListCell = new PlayerListCell();
			playerListCell.removeRequestObservable().subscribe(behaviour::onPlayerRemoveRequest);
			return playerListCell;
		});
	}

	public ObservableList<Player> getPlayers() {
		return players;
	}

	private class Behaviour {

		private void onPlayerRemoveRequest(Player player) {
			players.remove(player);
		}
	}
}