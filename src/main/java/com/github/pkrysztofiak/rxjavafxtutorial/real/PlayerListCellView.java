package com.github.pkrysztofiak.rxjavafxtutorial.real;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class PlayerListCellView extends HBox {

	private final ObjectProperty<Player> playerProperty = new SimpleObjectProperty<>();
	private final Observable<Player> playerObservable = JavaFxObservable.valuesOf(playerProperty);
	private final Observable<String> nameObservable = playerObservable.map(Player::nameProperty).switchMap(JavaFxObservable::valuesOf);
	private final Observable<String> surnameObservable = playerObservable.map(Player::surnameProperty).switchMap(JavaFxObservable::valuesOf);

	private final Label nameLabel = new Label();
	private final Label surnameLabel = new Label();
	private final HBox hBox = new HBox(nameLabel, surnameLabel);

	private final Pane placeholderPane = new Pane();

	private final Button removeButton = new Button("Remove");
	private final Observable<ActionEvent> removeAction = JavaFxObservable.actionEventsOf(removeButton);

	private final Behaviour behaviour = new Behaviour();

	{
		getChildren().setAll(hBox, placeholderPane, removeButton);
		HBox.setHgrow(placeholderPane, Priority.ALWAYS);
		hBox.setSpacing(4);
	}

	public PlayerListCellView() {
		nameObservable.subscribe(behaviour::onPlayerNameChanged);
		surnameObservable.subscribe(behaviour::onPlayerSurnameChanged);
	}

	public void setPlayer(Player player) {
		playerProperty.set(player);
	}

	public Observable<Player> removeRequestObservable() {
		return removeAction.withLatestFrom(playerObservable, (removeAction, player) -> player);
	}

	private class Behaviour {

		void onPlayerNameChanged(String name) {
			nameLabel.setText(name);
		}

		void onPlayerSurnameChanged(String surname) {
			surnameLabel.setText(surname);
		}
	}
}
