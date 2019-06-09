package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise7;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ChangesOfApp {

	public static void main(String[] args) {
		ObjectProperty<Player> employeeProperty = new SimpleObjectProperty<>();

//		JavaFxObservable.changesOf(employeeProperty).subscribe(change -> {
//			System.out.println("change.old=" + change.getOldVal());
//			System.out.println("change.old=" + change.getNewVal());
//		});

		employeeProperty.set(new Player("Jack", "Bowler", null));
		employeeProperty.set(null);

	}

}
