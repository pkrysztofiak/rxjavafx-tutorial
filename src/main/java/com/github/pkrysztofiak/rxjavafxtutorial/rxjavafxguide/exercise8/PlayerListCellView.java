package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class PlayerListCellView extends HBox {

	private final Label surnameLabel = new Label();
	private final Label nameLabel = new Label();
	private final Pane placeholderPane = new Pane();
	private final ComboBox<Position> positionComboBox = new ComboBox<>(FXCollections.observableArrayList(Position.values()));
	private final Button removeButton = new Button("Remove");


	private final Observable<ActionEvent> removeActionObservable = JavaFxObservable.actionEventsOf(removeButton);

	private final ObjectProperty<Player> employeeProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Optional<Player>> employeeOptionalProperty = new SimpleObjectProperty<>();

	private final Observable<Optional<Player>> employeeOptionalObservable = JavaFxObservable.nullableValuesOf(employeeProperty);
	private final Observable<Change<Optional<Player>>> employeeChangeObservable = JavaFxObservable.changesOf(employeeOptionalProperty);

	private final Behaviour behaviour = new Behaviour();

	{
		HBox.setHgrow(placeholderPane, Priority.ALWAYS);
		getChildren().setAll(surnameLabel, new Label(", "), nameLabel, placeholderPane, positionComboBox, removeButton);
	}

	public PlayerListCellView() {
		employeeOptionalObservable.subscribe(employeeOptionalProperty::set);
		employeeChangeObservable.subscribe(behaviour::onEmployeeChanged);
	}

	public void setEmployee(Player player) {
		employeeProperty.set(player);
	}

	public Observable<Player> employeeRemoveRequestObservable() {
		return removeActionObservable.map(removeAction -> employeeProperty.get());
	}

	private class Behaviour {

		private void onEmployeeChanged(Change<Optional<Player>> change) {
			change.getOldVal().ifPresent(employee -> {
				Bindings.unbindBidirectional(nameLabel.textProperty(), employee.nameProperty());
				Bindings.unbindBidirectional(surnameLabel.textProperty(), employee.surnameProperty());
				Bindings.unbindBidirectional(positionComboBox.valueProperty(), employee.positionProperty());
			});
			change.getNewVal().ifPresent(employee -> {
				Bindings.bindBidirectional(nameLabel.textProperty(), employee.nameProperty());
				Bindings.bindBidirectional(surnameLabel.textProperty(), employee.surnameProperty());
				Bindings.bindBidirectional(positionComboBox.valueProperty(), employee.positionProperty());
			});
		}
	}
}