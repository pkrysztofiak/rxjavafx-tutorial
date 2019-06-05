package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Solution009 extends Application {

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

		Observable.combineLatest(
				stageXObservable.map(Number::doubleValue),
				stageYObservable.map(Number::doubleValue),
				stageWidthObservable.map(Number::doubleValue),
				stageHeightObservable.map(Number::doubleValue),
				BoundingBox::new)
		.subscribe(bounds -> label.setText("x=" + bounds.getMinX() + ", y=" + bounds.getMinY() + ", width=" + bounds.getWidth() + ", height=" + bounds.getHeight()));
	}
}