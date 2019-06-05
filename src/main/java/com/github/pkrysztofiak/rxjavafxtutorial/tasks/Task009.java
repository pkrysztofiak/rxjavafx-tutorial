package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Task009 extends Application {

	private final Label label = new Label();
	private final StackPane stackPane = new StackPane(label);
	private final Scene scene = new Scene(stackPane, 600, 400);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		Observable<Number> stageXObservable = JavaFxObservable.valuesOf(stage.xProperty());
		Observable<Number> stageYObservable = JavaFxObservable.valuesOf(stage.yProperty());
		Observable<Number> stageWidthObservable = JavaFxObservable.valuesOf(stage.widthProperty());
		Observable<Number> stageHeightObservable = JavaFxObservable.valuesOf(stage.heightProperty());

		//your code here
	}
}