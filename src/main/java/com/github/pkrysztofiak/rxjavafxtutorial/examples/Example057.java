package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Example057 extends Application {

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
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.delay(3, TimeUnit.SECONDS, Schedulers.io())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.toList()
		.observeOn(JavaFxScheduler.platform())
		.doOnSuccess(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.subscribe(listView.getItems()::setAll);
	}
}