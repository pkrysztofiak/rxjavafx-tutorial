This work, "RxJavaFx tutorial", is a derivative of "[Learning RxJava with JavaFx]([https://thomasnield.gitbooks.io/rxjavafx-guide/content/](https://thomasnield.gitbooks.io/rxjavafx-guide/content/))" by [Thomas Nield](http://tomstechnicalblog.blogspot.com/), used under [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/2.0/) / material modified. "RxJavaFx tutorial" is licensed under [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/2.0/) by Przemysław Krysztofiak.


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
Where possible I will try to yield Stream counterpart to emphasize that we already know the external mechnics of basic Observable operators.

**Observer** is composed out of three methods:
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
	emissionsSource.subscribe(number -> System.out.println("number=" + number));
Example001
Produces an output:

    number=1    
    number=2    
    number=3    
    number=4    
    number=5

After subscribing to an Observable it starts to produce an emissions which are handled by the Observer. The Observer we deal with is called finite or cold. In oposition to hot Observables it has predefined number of emissions and in consequence will call Observer onComplete() method when all emissions are passed.
For now just note RxJava has no notion of parallelization, and when you subscribe to a factory like Observable.just(1, 2, 3, 4, 5) , you will always get those emissions serially, in that exact order, and on a single thread.

### Operators
Let us do something a little more useful than just connecting a source
Observable and an Observer . Let's put some operators between them to
actually transform emissions and do work. In RxJava, you can use hundreds of operators to transform emissions and create new Observables with those transformations.
#### map()
 For instance, you can create an Observable\<Integer> off an Observable<String> by using the map() operator, and use it to emit each String's length. The *lettersObservable* Observable pushes each String to the map() operator where it is mapped to its length . That length is then pushed from the map() operator to the Observer where it is printed.
```java
Observable<String> lettersObservable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

lettersObservable
.map(letter -> letter.length())
.subscribe(length -> System.out.println("length=" + length));
```
Example002
Produces output:

    length=5    
    length=4    
    length=5    
    length=5    
    length=7

#### doOnNext()
Is a debug operator. Passes emission but does nothing with it.
```java
Observable<String> numbersObservable = Observable.just("one", "two", "three");
numbersObservable
.doOnNext(next -> System.out.println("next=" + next))
.map(String::length)
.subscribe(length -> System.out.println("length=" + length));
```
Example003
Produces output:

    next=one
    length=3
    next=two
    length=3
    next=three
    length=5

#### filter()
Another common operator is filter() , which suppresses emissions that fail to
meet a certain criteria, and pushes the ones that do forward.
```java
Observable.just("one", "two", "three", "four", "five")
.filter(next -> next.length() > 3)
.subscribe(next -> System.out.println("next=" + next));
```
Example004
Produces output:

    next=three
    next=four
    next=five
#### distinct()
There are also operators like distinct() , which will suppress emissions that
have previously been emitted to prevent duplicate emissions (based on each
emission's hashcode() / equals() implementation).
```java
Observable.just("one", "two", "three", "four", "five")
.map(String::length)
.distinct()
.subscribe(System.out::println);
```
Example005
Produces output:

    3
    5
    4
You can also provide a lambda specifying an attribute of each emitted item to distinct on, rather than the item itself.
```java
Observable.just("one", "two", "three", "four", "five")
.distinct(String::length)
.subscribe(System.out::println);
```
Exmaple006
Produces output:

    one
    three
    four
#### distinctUntilChanged()
Will not supress only if current emission is different from the last one.
```java
Observable.just("one", "one", "two", "one", "seven", "eight", "eight", "nine")
.distinctUntilChanged()
.subscribe(System.out::println);
```
Example007
Produces output:

    one
    two
    one
    seven
    eight
    nine
#### take()
The take() operator will cut off at a fixed number of emissions and then unsubscribe from the source. Afterwards, it will call onComplete() downstream to the final Observer.
```java
Observable.just("one", "two", "three", "four", "five")
.take(3)
.subscribe(
		next -> System.out.println("next=" + next), //onNext
		throwable -> throwable.printStackTrace(),	//onError
		() -> System.out.println("completed!")		//onComplete
		);
```
Example008
Produces output:

    next=one
    next=two
    next=three
    completed!
#### takeWhile()
takeWhile() will do something similar to take() , but specifies a lambda condition which, if not satisfied will unsubscribe from the Observer.
```java
Observable.just("one", "two", "three", "four", "five")
.takeWhile(number -> number.length() == 3)
.subscribe(
		next -> System.out.println("next=" + next), //onNext
		throwable -> throwable.printStackTrace(),	//onError
		() -> System.out.println("completed!")		//onComplete
		);
```
Exmaple009
Produces output:

    next=one
    next=two
    completed!
#### takeUntil()
takeUntil() will do something similar to take() , but specifies a lambda condition which, if satisfied will unsubscribe from the Observer after pushing the emission which caused unsubscribing.
```java
Observable.just("one", "two", "three", "four", "five")
.takeUntil((String number) -> number.startsWith("f"))
.subscribe(
		next -> System.out.println("next=" + next), //onNext
		throwable -> throwable.printStackTrace(),	//onError
		() -> System.out.println("completed!")		//onComplete
		);
```
Example010
Produces output:

    next=one
    next=two
    next=three
    next=four
    completed!
#### count()
Some operators will aggregate the emissions in some form, and then push that aggregation as a single emission to the Observer. Obviously, this requires the onComplete() to be called so that the aggregation can be finalized and pushed to the Observer.
The count() actually returns a Single , which is a specialized Observable type that only emits one item. The Single does not have an onNext or onComplete , but rather an onSuccess event which passes the single item. If you ever need to turn a Single back into an Observable (so it works with certain Observable operators), just call its toObservable() method.
```java
Observable.just("one", "two", "three", "four", "five")
.count()
.subscribe(
		result -> System.out.println("onSucces=" + result), //onSuccess
		throwable -> throwable.printStackTrace()			//onError
		);
```
Example011
Produces output:

    onSucces=5
#### toList()
The toList() is similar to the count() , and it also will yield a Single
rather than an Observable . It will collect the emissions until its onComplete()
is called. After that it will push an entire List containing all the emissions to the
Observer .
```java
Observable.just("one", "two", "three", "four", "five")
.toList()
.subscribe(
		list -> System.out.println("onSucces=" + list), //onSuccess
		throwable -> throwable.printStackTrace()		//onError
		);
```
Example012
Produces output:

    onSucces=[one, two, three, four, five]
#### reduce()
When you need to do a custom aggregation or reduction, you can use reduce() to achieve this in most cases (to aggregate into collections and other mutable structures, you can use its cousin collect() ). This will return a Single (if a "seed" value is provided) or a Maybe (if no "seed" value is provided). But say we wanted the sum of all lengths for all emissions. Starting with a seed value of zero, we can use a lambda specifying how to "fold" the emissions into a single value.
```java
Observable.just("one", "two", "three")
.map(String::length)
.reduce(0, (current, next) -> current + next)
.subscribe(new SingleObserver<Integer>() {

	@Override
	public void onSubscribe(Disposable disposable) {
	}

	@Override
	public void onSuccess(Integer single) {
		System.out.println("sum=" + single);
	}

	@Override
	public void onError(Throwable throwable) {
	}
});
```
Example013
Produces output:

    sum=11
```java    
Observable.just("one", "two", "three")
.map(String::length)
.reduce((current, next) -> current + next)
.subscribe(new MaybeObserver<Integer>() {

	@Override
	public void onSubscribe(Disposable disposable) {
	}

	@Override
	public void onSuccess(Integer single) {
		System.out.println("sum=" + single);
	}

	@Override
	public void onError(Throwable e) {
	}

	@Override
	public void onComplete() {
		System.out.println("completed!");
	}
});
```
Example014
Produces output:

    sum=11
As we can see onComplete() method was not called. It would be called if Observable does not produce any emission.
```java
Observable.<String>empty()
.map(String::length)
.reduce((current, next) -> current + next)
.subscribe(new MaybeObserver<Integer>() {

	@Override
	public void onSubscribe(Disposable disposable) {
	}

	@Override
	public void onSuccess(Integer single) {
		System.out.println("sum=" + single);
	}

	@Override
	public void onError(Throwable e) {
	}

	@Override
	public void onComplete() {
		System.out.println("completed!");
	}
});
```
Example015
Produces output:

    completed!
#### scan()
The reduce() will push a single aggregated value derived from all the emissions. If you want to push the "running total" for each emission, you can use scan() instead. This can work with infinite Observables since it will push each accumulation for each emission, rather than waiting for all emissions to be accumulated.
```java
Observable.just("one", "two", "three")
.map(String::length)
.scan(0, (current, next) -> current + next)
.subscribe(System.out::println);
```
Example016
Produces output:

    0
    3
    6
    11
#### flatMap()
The flatMap() is similar to map() , but will map the emission to another set of emissions via another Observable . This is one of the most powerful operators in RxJava and is full of use cases, but for now we will just stick with a simple example.
Say we have some String emissions where each contains concatenated numbers separated by semicolon. We want to break up these numbers into separate emissions (and omit the semicolons). You can call split() on each String and specify splitting on the semicolons, and this will return an array of the separated String values. Then you can turn that array into an Observable inside the flatMap().
```java
Observable.just("1;2;3", "1;1", "4;4;1;2")
.flatMap(numbers -> Observable.fromArray(numbers.split(";")))
.map(Integer::valueOf)
.reduce((current, next) -> current + next)
.subscribe(System.out::println);
```
Example017
Produces output:

    19
#### flatMapSingle()
flatMapSingle() can be used to flatMap() to a Single, and flatMapMaybe() to a Maybe. This saves you the step of having to call toObservable() on each resulting Maybe or Single. If we wanted to sum each set of numbers, we would do it like this since this reduce() will yield a Single.
```java
Observable.just("1;2;3", "1;1", "4;4;1;2")
.flatMapSingle(numbers ->
	Observable.fromArray(numbers.split(";"))
	.map(Integer::valueOf)
	.reduce(0, (current, next) -> current + next))
.subscribe(System.out::println);
```
Example018
Produces output:

    6
    2
    11

#### Tasks
Let's say we run a company with clients spread around the Poland. Our reps visit clients and sell them our product. Every trip made by the rep is logged as route.
```java
public class Client {
	private final String name;
	private final String city;
	private final int itemsSold;

	public Client(String name, String city, int itemsSold) {
		this.name = name;
		this.city = city;
		this.itemsSold = itemsSold;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public int getItemsSold() {
		return itemsSold;
	}
}
public class Route {
	private final List<Client> clients = new ArrayList<>();

	public Route(Client... clients) {
		this.clients.addAll(Arrays.asList(clients));
	}
	public List<Client> getClients() {
		return clients;
	}
}    
```
Task1
Having last two routes of company representative, print the number of different cities he/she visited.
Expected output:

    4
Task2
Having last two routes of company representative, print subsequent cities he/she visited. Each route should start with "\==route==" line.
Expected output:

    ==route==
    Lodz
    Warsaw
    Poznan
    ==route==
    Lodz
    Poznan
    Gdansk
    Poznan
Task3
Having last two routes of company representative, print the total number of items sold for each route.
Expected output:

    215
    240
### Events Observable    
So far we just pushed data out of Observables. But did you know you can push events too? As stated earlier, data and events are basically the same thing in RxJava. Let's take a simple JavaFX Application with a single Button.
```java
Button button = new Button("Click me!");
stage.setScene(new Scene(button));
stage.show();

JavaFxObservable.actionEventsOf(button).subscribe(actionEvent -> {
	System.out.println(actionEvent.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(actionEvent)));
});
button.setOnAction(actionEvent -> {
	System.out.println(actionEvent.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(actionEvent)));
});
```
Example019
On every button click produces output:

    javafx.event.ActionEvent@2a2e1055
    javafx.event.ActionEvent@2a2e1055
As you can see the event object is the same.

