package com.github.pkrysztofiak.rxjavafxtutorial.examples;


import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Example055 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		StackPane stackPane = new StackPane();
		Scene scene = new Scene(stackPane, 400, 400);
		stage.setScene(scene);
		stage.show();
		JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_MOVED)
		.doOnNext(moveEvent -> System.out.println("[" + Thread.currentThread().getName() + "] next=[x" + moveEvent.getX() + ", y=" + moveEvent.getY() + "]"))
		.observeOn(Schedulers.computation())
		.subscribe(moveEvent -> System.out.println("Observer[" + Thread.currentThread().getName() + "] next=[x" + moveEvent.getX() + ", y=" + moveEvent.getY() + "]"));
	}
}