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

public class EmployeeListCellView extends HBox {

	private final Label surnameLabel = new Label();
	private final Label nameLabel = new Label();
	private final Pane placeholderPane = new Pane();
	private final ComboBox<Position> positionComboBox = new ComboBox<>(FXCollections.observableArrayList(Position.values()));
	private final Button removeButton = new Button("Remove");


	private final Observable<ActionEvent> removeActionObservable = JavaFxObservable.actionEventsOf(removeButton);

	private final ObjectProperty<Employee> employeeProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Optional<Employee>> employeeOptionalProperty = new SimpleObjectProperty<>();

	private final Observable<Optional<Employee>> employeeOptionalObservable = JavaFxObservable.nullableValuesOf(employeeProperty);
	private final Observable<Change<Optional<Employee>>> employeeChangeObservable = JavaFxObservable.changesOf(employeeOptionalProperty);

	private final Behaviour behaviour = new Behaviour();

	{
		HBox.setHgrow(placeholderPane, Priority.ALWAYS);
		getChildren().setAll(surnameLabel, new Label(", "), nameLabel, placeholderPane, positionComboBox, removeButton);
	}

	public EmployeeListCellView() {
		employeeOptionalObservable.subscribe(employeeOptionalProperty::set);
		employeeChangeObservable.subscribe(behaviour::onEmployeeChanged);
	}

	public void setEmployee(Employee employee) {
		employeeProperty.set(employee);
	}

	public Observable<Employee> employeeRemoveRequestObservable() {
		return removeActionObservable.map(removeAction -> employeeProperty.get());
	}

	private class Behaviour {

		private void onEmployeeChanged(Change<Optional<Employee>> change) {
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