package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Exercise6 extends Application {

	private final Button button = new Button("click me");
	private final StackPane stackPane = new StackPane(button);
//	private final VBox vBox = new VBox(button, stackPane);
	private final Scene scene = new Scene(stackPane, 400, 400);

	private final Observable<MouseEvent> mouseClickedObservable = JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_CLICKED).take(3);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		mouseClickedObservable.subscribe(next -> System.out.println("Obs1 next=" + next));

		JavaFxObservable.actionEventsOf(button).subscribe(next -> {
			mouseClickedObservable.subscribe(next2 -> System.out.println("Obs2 next=" + next2));
		});

	}
}
