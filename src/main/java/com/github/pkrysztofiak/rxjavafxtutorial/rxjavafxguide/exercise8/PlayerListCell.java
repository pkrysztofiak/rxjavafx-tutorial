package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.ListCell;

public class PlayerListCell extends ListCell<Player> {

	private final PlayerListCellView listCellView = new PlayerListCellView();

	private final Observable<Optional<Player>> itemObservable = JavaFxObservable.nullableValuesOf(itemProperty());
	private final Observable<Boolean> emptyObservable = JavaFxObservable.valuesOf(emptyProperty());

	public PlayerListCell() {
		itemObservable.subscribe(this::onItemChanged);
		emptyObservable.subscribe(this::onEmptyChanged);
	}

	private void onItemChanged(Optional<Player> player) {
		listCellView.setPlayer(player.orElse(null));
	}

	private void onEmptyChanged(boolean empty) {
		setGraphic(empty ? null : listCellView);
	}

	public Observable<Player> employeeRemoveRequestObservable() {
		return listCellView.playerRemoveRequestObservable();
	}
}