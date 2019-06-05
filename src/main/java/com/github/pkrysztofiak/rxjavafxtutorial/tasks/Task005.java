package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Task005 extends Application {
	private final Label xLabel = new Label();
	private final Label yLabel = new Label();
	private final HBox hBox = new HBox(new Label("x="), xLabel, new Label(", y="), yLabel);
	private final StackPane stackPane = new StackPane(hBox);
	private final Scene scene = new Scene(stackPane);

	{
		hBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		stackPane.setPrefSize(400, 400);
		hBox.setMouseTransparent(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		//your code here
	}
}