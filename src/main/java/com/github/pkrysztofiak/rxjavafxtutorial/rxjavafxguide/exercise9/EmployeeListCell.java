package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise9;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Employee;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class EmployeeListCell extends ListCell<Employee> {

	private final Label label = new Label();

	private final Observable<Employee> itemObservable = JavaFxObservable.valuesOf(itemProperty());
	private final Observable<Boolean> emptyObservable = JavaFxObservable.valuesOf(emptyProperty());

	public EmployeeListCell() {
		itemObservable.subscribe(this::onItemChanged);
		emptyObservable.subscribe(this::onEmptyChanged);
	}

	private void onItemChanged(Employee item) {
//		listCellView.setEmployee(item);
	}

	private void onEmptyChanged(boolean empty) {
		setGraphic(empty ? null : label);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalized " + this);
	}
}