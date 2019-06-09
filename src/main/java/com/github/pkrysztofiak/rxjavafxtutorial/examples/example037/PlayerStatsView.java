package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.binding.Binding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerStatsView extends VBox {

	private final ObjectProperty<Player> playerProperty = new SimpleObjectProperty<>();
	private final Observable<Optional<Player>> optionalPlayerObservable = JavaFxObservable.nullableValuesOf(playerProperty);

	private final ObjectProperty<Optional<Player>> optionalPlayerProperty = new SimpleObjectProperty<>();
	private final Observable<Change<Optional<Player>>> optionalPlayerChangeObservable = JavaFxObservable.changesOf(optionalPlayerProperty);

	private final Label nameLabel = new Label();
	private final Label surnameLabel = new Label();
	private final Label positionLabel = new Label();
	private final HBox hBox1 = new HBox(nameLabel, new Label(" "), surnameLabel, new Label(" "), positionLabel);

	private final Label goalsLabel = new Label("Goals:");
	private final TextField goalsTextField = new TextField();
	private final HBox hBox2 = new HBox(goalsLabel, goalsTextField);


	private final Observable<String> goalsTextObservable = JavaFxObservable.valuesOf(goalsTextField.textProperty());
//
	private final IntegerProperty goalsProperty = new SimpleIntegerProperty();
	private final Observable<Integer> goalsObservable = JavaFxObservable.valuesOf(goalsProperty).map(Number::intValue);

	private final Button saveButton = new Button("Save");

	{
		getChildren().setAll(hBox1, hBox2);
	}

	public PlayerStatsView() {
		optionalPlayerObservable.subscribe(optionalPlayerProperty::set);
		optionalPlayerChangeObservable.subscribe(this::onPlayerChanged);

		Binding<String> goalsBinding = JavaFxObserver.toBinding(goalsTextObservable);
//		StringBinding stringBinding = new SimpleStringProperty();
//		Bindings.bindBidirectional(playerProperty.get().nameProperty(), goalsBinding);
//		playerProperty.get().nameProperty().bind(goalsBinding);
	}

	public ObjectProperty<Player> playerProperty() {
		return playerProperty;
	}

	private void onGoalsChanged(Integer goals) {
		goalsTextField.setText(String.valueOf(goals));
	}

	private void onGoalsChanged(String goals) {
		if (goals.matches("[0-9]+")) {
			goalsProperty.set(Integer.valueOf(goals));
		}
	}

	private void onPlayerChanged(Change<Optional<Player>> change) {
		change.getOldVal().ifPresent(player -> {
			nameLabel.textProperty().unbindBidirectional(player.nameProperty());
			surnameLabel.textProperty().unbindBidirectional(player.surnameProperty());
			goalsProperty.unbindBidirectional(player.getStats().goalsProperty());
		});
		change.getNewVal().ifPresent(player -> {
			Player playerCopy = new Player(player);

			nameLabel.textProperty().bindBidirectional(player.nameProperty());
			surnameLabel.textProperty().bindBidirectional(player.surnameProperty());
			goalsProperty.bindBidirectional(player.getStats().goalsProperty());
		});
	}
}