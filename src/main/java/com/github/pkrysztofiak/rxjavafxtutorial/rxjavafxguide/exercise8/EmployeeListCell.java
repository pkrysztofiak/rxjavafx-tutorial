package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.ListCell;

public class EmployeeListCell extends ListCell<Employee> {

	private final EmployeeListCellView listCellView = new EmployeeListCellView();

	private final Observable<Optional<Employee>> itemObservable = JavaFxObservable.nullableValuesOf(itemProperty());
	private final Observable<Boolean> emptyObservable = JavaFxObservable.valuesOf(emptyProperty());

	public EmployeeListCell() {
		itemObservable.subscribe(this::onItemChanged);
		emptyObservable.subscribe(this::onEmptyChanged);
	}

	private void onItemChanged(Optional<Employee> employee) {
		listCellView.setEmployee(employee.orElse(null));
	}

	private void onEmptyChanged(boolean empty) {
		setGraphic(empty ? null : listCellView);
	}

	public Observable<Employee> employeeRemoveRequestObservable() {
		return listCellView.employeeRemoveRequestObservable();
	}
}