package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Employee;

import io.reactivex.Observable;
import javafx.scene.control.ListCell;

public class EmployeeListCellWorking extends ListCell<Employee> {

	private final EmployeeListCellView cellView = new EmployeeListCellView();

	@Override
	protected void updateItem(Employee employee, boolean empty) {
		super.updateItem(employee, empty);
		if ((employee == null) || empty) {
			setGraphic(null);
		} else {
			System.out.println(this + "\t updateItem(" + employee.surnameProperty().get() + ")");
			cellView.setEmployee(employee);
			setGraphic(cellView);
		}
	}

	public Observable<Employee> removeEmployeeObservable() {
		return cellView.removeEmployeeObservable();
	}
}