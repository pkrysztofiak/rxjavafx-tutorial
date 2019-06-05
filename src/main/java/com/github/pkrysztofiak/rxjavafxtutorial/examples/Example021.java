package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Example021 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button button = new Button("+");
		Label label = new Label();
		Button unsubscribeButton = new Button("Unsubscribe!");
		HBox hBox = new HBox(button, label, unsubscribeButton);
		stage.setScene(new Scene(hBox));
		stage.show();

		Disposable disposable = JavaFxObservable.actionEventsOf(button)
		.map(actionEvent -> 1)
		.scan(0, (current, next) -> current + next)
		.map(String::valueOf)
		.subscribe(
				label::setText,
				Throwable::printStackTrace,
				() -> System.out.println("completed!"));

		JavaFxObservable.actionEventsOf(unsubscribeButton).subscribe(next -> disposable.dispose());
	}
}