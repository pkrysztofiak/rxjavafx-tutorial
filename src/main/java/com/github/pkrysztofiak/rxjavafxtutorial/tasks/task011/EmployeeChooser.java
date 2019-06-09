package com.github.pkrysztofiak.rxjavafxtutorial.tasks.task011;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EmployeeChooser extends VBox {

	private final Employee smithEmployee = new Employee("Jimmy", "Smith", "Easygoing");
	private final Employee doeEmployee = new Employee("John", "Doe", "Lazy");
	private final Employee johnsonEmployee = new Employee("Mark", "Johnson", "Swift");

	private final Button smithButton = new Button(smithEmployee.getSurname() + " " + smithEmployee.getName());
	private final Button doeButton = new Button(doeEmployee.getSurname() + " " + doeEmployee.getName());
	private final Button johnsonButton = new Button(johnsonEmployee.getSurname() + " " + johnsonEmployee.getName());

	private final Observable<Employee> smithObservable = JavaFxObservable.actionEventsOf(smithButton).map(action -> smithEmployee);
	private final Observable<Employee> doeObservable = JavaFxObservable.actionEventsOf(doeButton).map(action -> doeEmployee);
	private final Observable<Employee> johnsonObservable = JavaFxObservable.actionEventsOf(johnsonButton).map(action -> johnsonEmployee);

	private final Observable<Employee> employeeObservable = Observable.merge(smithObservable, doeObservable, johnsonObservable);

	{
		getChildren().setAll(smithButton, doeButton, johnsonButton);
	}

	public Observable<Employee> selectedEmployeeObservable() {
		return employeeObservable;
	}
}