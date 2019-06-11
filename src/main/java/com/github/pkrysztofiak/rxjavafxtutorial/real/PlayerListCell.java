package com.github.pkrysztofiak.rxjavafxtutorial.real;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.ListCell;

public class PlayerListCell extends ListCell<Player> {

	private final PlayerListCellView playerListCellView = new PlayerListCellView();

	private final Observable<Player> itemObservable = JavaFxObservable.valuesOf(itemProperty());
	private final Observable<Boolean> emptyObservable = JavaFxObservable.valuesOf(emptyProperty());

	private final Behaviour behaviour = new Behaviour();

	public PlayerListCell() {
		itemObservable.subscribe(behaviour::onItemChanged);
		emptyObservable.subscribe(behaviour::onEmptyChagned);
	}

	public Observable<Player> removeRequestObservable() {
		return playerListCellView.removeRequestObservable();
	}

	private class Behaviour {

		private void onItemChanged(Player player) {
			playerListCellView.setPlayer(player);
		}

		private void onEmptyChagned(boolean empty) {
			setGraphic(empty ? null : playerListCellView);
		}
	}
}
