package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.ListCell;

public class EmployeeListCell extends ListCell<Player> {

	private final EmployeeListCellView employeeListCellView = new EmployeeListCellView();

	private final Observable<Boolean> emptyObservable = JavaFxObservable.valuesOf(emptyProperty());
	private final Observable<Player> itemObservable = JavaFxObservable.valuesOf(itemProperty());

	public EmployeeListCell() {
		System.out.println("new EmployeeListCell() " + this);
//		itemObservable.subscribe(this::onItemChanged);
//		emptyObservable.subscribe(this::onEmptyChanged);
	}

	private void onItemChanged(Player player) {

		System.out.println("onItemChanged employee=" + player.surnameProperty().get() + " " + this);
		employeeListCellView.setEmployee(player);
	}

	@Override
	protected void updateItem(Player item, boolean empty) {
		super.updateItem(item, empty);
		if ((item == null) || empty) {
			setGraphic(null);
		} else {
			setGraphic(employeeListCellView);
		}
	}

	private void onEmptyChanged(boolean empty) {
		setGraphic(empty ? null : employeeListCellView);
	}

	public Observable<Player> removeEmployeeObservable() {
		return employeeListCellView.removeEmployeeObservable();
	}
}