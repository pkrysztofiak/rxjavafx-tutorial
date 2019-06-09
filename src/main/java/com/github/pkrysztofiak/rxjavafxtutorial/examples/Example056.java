package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Example056 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ListView<String> listView = new ListView<>();
		Scene scene = new Scene(listView);
		stage.setScene(scene);
		stage.show();

		Observable.just("one", "two", "three")
		.observeOn(Schedulers.io())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.observeOn(JavaFxScheduler.platform())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.subscribe(listView.getItems()::add);
	}
}