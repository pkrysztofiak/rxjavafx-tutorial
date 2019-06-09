package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import io.reactivex.Observable;
import javafx.scene.control.ListCell;

public class EmployeeListCellWorking extends ListCell<Player> {

	private final EmployeeListCellView cellView = new EmployeeListCellView();

	@Override
	protected void updateItem(Player player, boolean empty) {
		super.updateItem(player, empty);
		if ((player == null) || empty) {
			setGraphic(null);
		} else {
			System.out.println(this + "\t updateItem(" + player.surnameProperty().get() + ")");
			cellView.setEmployee(player);
			setGraphic(cellView);
		}
	}

	public Observable<Player> removeEmployeeObservable() {
		return cellView.removeEmployeeObservable();
	}
}