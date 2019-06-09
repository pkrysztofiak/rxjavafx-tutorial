package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class EmployeeListCellView extends HBox {

	private final ObjectProperty<Player> employeeProperty = new SimpleObjectProperty<>();
	private final Observable<Change<Player>> employeeChangeObservable = JavaFxObservable.changesOf(employeeProperty);

	private final Label nameLabel = new Label();
	private final Label surnameLabel = new Label();
	private final Pane placeholderPane = new Pane();
	private final Button removeButton = new Button("Remove");

	private final Observable<ActionEvent> removeButtonActionObservable = JavaFxObservable.actionEventsOf(removeButton);

//	private final Behaviour behaviour = new Behaviour();

	{
		getChildren().setAll(surnameLabel, placeholderPane,  removeButton);
		setHgrow(placeholderPane, Priority.ALWAYS);
	}

	public EmployeeListCellView() {
		employeeChangeObservable.subscribe(change -> {
			Player oldValue = change.getOldVal();
			if (oldValue != null) {
				Bindings.unbindBidirectional(surnameLabel.textProperty(), oldValue.surnameProperty());
			}

			Player newValue = change.getNewVal();
			if (newValue != null) {
				Bindings.bindBidirectional(surnameLabel.textProperty(), newValue.surnameProperty());
			}

		});
//		employeeObservable.subscribe(this::onEmployeeChanged);
//		removeEmployeeObservable().subscribe(behaviour::onEmployeeRemoved);
	}

	public void setEmployee(Player player) {
		System.out.println("setEmployee(" + player.surnameProperty().get() + ")");
//		surnameLabel.setText(employee.surnameProperty().get());
		employeeProperty.set(player);
//		Bindings.bindBidirectional(surnameLabel.textProperty(), employee.surnameProperty());
//		Bindings.bindBidirectional(nameLabel.textProperty(), employee.nameProperty());
	}

	private void onEmployeeChanged(Player player) {
		Bindings.bindBidirectional(surnameLabel.textProperty(), player.surnameProperty());
		Bindings.bindBidirectional(nameLabel.textProperty(), player.nameProperty());
	}

	public Observable<Player> removeEmployeeObservable() {
		return removeButtonActionObservable.map(removeAction -> employeeProperty.get());
	}

//	private class Behaviour {
//
//		private void onEmployeeRemoved(Employee employee) {
//			Bindings.unbindBidirectional(surnameLabel.textProperty(), employee.surnameProperty());
//			Bindings.unbindBidirectional(nameLabel.textProperty(), employee.nameProperty());
//		}
//	}
}