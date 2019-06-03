# RxJavaFx tutorial
RxJavaFx is merely a layer between JavaFx and RxJava technologies.
### Preface
JavaFx brought a new concepts of **Binding**s and **ObservableValue**s. The idea of events triggering other events, and a value notifying another value of its changes seems to be very promising.
But after studying JavaFx deeper one may become discontent. JavaFx does have **Binding** functionality that can synchronize properties and events of different controls, but the ability to express transformations is pretty limited.

Reactive programming brings means for simple and intuitive data processing between synchronized properties. And goes far beyond that. It extends the observer pattern to support sequences of data and/or events and allows you to compose sequences together while abstracting away concerns about things like low-level threading, synchronization, thread-safety, concurrent data structures, and non-blocking I/O.
### RxJava
As mentioned before RxJavaFx is just a bridge between RxJava and JavaFx. To get the idea behind the technology we need to start from Rx.

RxJava has two core types: **Observable** and **Observer**. 
In the simplest definition **Observable** pushes things, a given **Observable\<T>** will push items of type **T** through a series of operators that form other **Observable**s and finally the terminal **Observer** is what consumes the items at the end of the chain. Each pushed T item is known as an emission.

    Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
As you may have noticed the form of the factory is Stream alike.

    Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5);
Where possible I will try to bring Stream counterpart to emphasize that we already know the external mechnics of basic Observable operators.
Observer is composed out of three methods that handle every received emission origineted from Observables.
```java
	new ResourceObserver<T>() {

		@Override
		public void onNext(T t) {
			//is called to pass the emission
		}

		@Override
		public void onError(Throwable e) {
			//is called when exception occured
		}

		@Override
		public void onComplete() {
			//is called when there are no more emissions
		}
	};
```
Lets get one with some examples.

    Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
	emissionsSource.subscribe(number -> System.out.println("emission=" + number));
Produces an output:

    emission=1    
    emission=2    
    emission=3    
    emission=4    
    emission=5
After subscribing to an Observable it starts to produce an emissions which are handled by the Observer. The Observer we deal with is called finite or cold. In oposition to hot Observables it has predefined number of emissions and in consequence will call Observer onComplete() method when all emissions are passed.
For now just note RxJava has no notion of parallelization, and when you subscribe to a factory like Observable.just(1, 2, 3, 4, 5) , you will always get those emissions serially, in that exact order, and on a single thread.
Example001
### Using operators
Let us do something a little more useful than just connecting a source
Observable and an Observer . Let's put some operators between them to
actually transform emissions and do work.
#### map()
