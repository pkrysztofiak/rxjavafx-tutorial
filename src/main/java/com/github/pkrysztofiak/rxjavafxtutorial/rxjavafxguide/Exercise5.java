package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.stage.Stage;

public class Exercise5 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Observable.just("one", "two", "three")
		.subscribeOn(Schedulers.io())
		.doOnNext(next -> System.out.println("onNext [" + Thread.currentThread().getName() + "] next=" + next))
		.toList()
		.toObservable()
		.flatMap(list -> Observable.fromIterable(list))
		.observeOn(JavaFxScheduler.platform())
		.doOnNext(next -> System.out.println("onNext2 [" + Thread.currentThread().getName() + "] next=" + next))
		.subscribe(next -> System.out.println("observer [" + Thread.currentThread().getName() + "] next=" + next));
	}
}