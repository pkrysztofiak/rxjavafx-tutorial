package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Example038 extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Label label = new Label();
		StackPane hBox = new StackPane(label);
		Scene scene = new Scene(hBox, 100, 100);
		stage.setScene(scene);
		stage.show();
		Observable.interval(1, TimeUnit.SECONDS, JavaFxScheduler.platform()).map(String::valueOf).subscribe(label::setText);
	}
}