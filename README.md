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
Moreover, did we just treat the ActionEvent like any other emission and push it through the Observable? Yes we did! As said earlier, this is the powerful part of RxJava. It treats events and data the same way, and you can use all the operators we covered earlier. 
Task4
For example, we can use scan() to push how many times the Button was pressed, and push that into a Label . Just map() each ActionEvent to a 1 to drive increments.
#### Cold vs. hot observables
The Observable\<ActionEvent> we created off a Button is an example of a hot Observable . Earlier, all of our examples emitting Integer and String items are cold Observables. So what is the difference?
Remember this source Observable that simply pushes five String emissions?

    Observable.just("one", "two", "three", "four", "five")
What do you think will happen if we subscribe() to it twice? Let's try it out.
```java
	Observable<String> source = Observable.just("one", "two", "three", "four", "five");
	source.subscribe(next -> System.out.println("[Observer1] next=" + next));
	source.subscribe(next -> System.out.println("[Observer2] next=" + next))
```
Produces output:

    [Observer1] next=one
    [Observer1] next=two
    [Observer1] next=three
    [Observer1] next=four
    [Observer1] next=five
    [Observer2] next=one
    [Observer2] next=two
    [Observer2] next=three
    [Observer2] next=four
    [Observer2] next=five
Emissions are replayed for each Observer .	
With a Cold Observable, every Observer independently receives all the emissions regardless of when they Subscribe. There is no notion of timing making an impact to which emissions they receive. Cold Observables are often used to "play" data independently to each Observer . This is like giving every Observer a music CD to play, and they can independently play all the tracks. Hot Observables, however, will simultaneously push emissions to all Observers at the same time. Logically, an effect of this is Observers that come later and have missed previous emissions will not receive them. They will only get emissions going forward from the time they subscribe() . Instead of a music CD, Hot Observables are more like radio stations. They will broadcast a given song (emission) to all listeners (Observers) at the same time. If a listener misses a song, they missed it. While data and events are the same in RxJava, Hot Observables are often used to represent events, such as an Observable\<ActionEvent> built off a Button.
#### Unsubscribing
There is one last operation we need to cover: unsubscribing. Unsubscription should happen automatically for finite Observables once onComplete() is called. But for infinite or long-running Observables, there will be times you want to stop the emissions and cancel the entire operation. This will also free up resources in the Observable chain and clean up any resources it was using.
If you want to disconnect an Observer from an Observable so it stops receiving emissions, there are a couple ways to do this. The easiest way is to note the subscribe() method returns a Disposable object. This represents the connection between the Observable and the Observer , and you can call dispose() on it at any time to dispose the connections so no more emissions are pushed.
For instance, let's take our incrementing Button example from earlier and add another Button that will unsubscribe the emissions. We need to save the Disposable returned from the subscribe() method, and then we can refer to it later to call dispose() and stop emissions.
Example16
```java
	Button button = new Button("+");
	Label label = new Label();
	Button unsubscribeButton = new Button("Unsubscribe!");
	HBox hBox = new HBox(button, label, unsubscribeButton);
	stage.setScene(new Scene(hBox));
	stage.show();

	Disposable disposable = JavaFxObservable.actionEventsOf(button)
	.map(actionEvent -> 1)
	.scan(0, (current, next) -> current + next)
	.map(String::valueOf)
	.subscribe(
			label::setText,
			Throwable::printStackTrace,
			() -> System.out.println("completed!"));

	JavaFxObservable.actionEventsOf(unsubscribeButton).subscribe(next -> disposable.dispose());
```
Note that when you press the "Unsubscribe" Button , the increments stop because the Observer was disposed, and it instructed the Observable to stop sending emissions. Disposal automatically happens with finite Observables once onComplete() is called. But with infinite or long-running Observables, you need to manage their disposal if you intend to terminate them at some point. When you have infinite Observables that need to be disposed, it is very critical to call dispose() on any Disposables when you are done with them. If you do not do this, you will run into memory leak problems and the garbage collector will not be able to free those resources.
#### takeUntil()
Using line
```java
    JavaFxObservable.actionEventsOf(unsubscribeButton).subscribe(next -> disposable.dispose());
```
we called dispose() method to cancel a subscription to our Observable. But we can also unsubscribe in more reactive way.
		Button button = new Button("+");
		Label label = new Label();
		Button unsubscribeButton = new Button("Unsubscribe!");
		HBox hBox = new HBox(button, label, unsubscribeButton);
		stage.setScene(new Scene(hBox));
		stage.show();
```java
Observable<ActionEvent> unsubscribeButtonActionObservable = JavaFxObservable.actionEventsOf(unsubscribeButton).take(1);
Observable<ActionEvent> incrementButtonActionObservable = JavaFxObservable.actionEventsOf(button).takeUntil(unsubscribeButtonActionObservable);

incrementButtonActionObservable
.map(actionEvent -> 1)
.scan(0, (current, next) -> current + next)
.map(String::valueOf)
.subscribe(
		label::setText,
		Throwable::printStackTrace,
		() -> System.out.println("completed!"));
```
Example017
Notice that this time onComplete() method was called.
### Events and value changes
In the previous section, we got a brief introduction to handling events reactively. But RxJavaFX is equipped to handle almost any event type for various Node controls. JavaFX also utilizes the ObservableValue, and its value changes can be turned into Observables as well.
To create an Observable for any event on any Node , you can target the Node 's events using a JavaFxObservable.eventsOf(). You can pass the EventType you are targeting as a parameter, and an Observable emitting that EventType will be returned.
Here is an example with a ListView containing String items representing the integers 0 through 9. Whenever a numeric key is pressed on your keyboard, it will select that item in the ListView.
```java
ListView<String> listView = new ListView<>(FXCollections.observableArrayList(IntStream.range(0, 10).boxed().map(String::valueOf).collect(Collectors.toList())));
Scene scene = new Scene(listView);
stage.setScene(scene);
stage.show();

listView.requestFocus();

JavaFxObservable.eventsOf(listView, KeyEvent.KEY_TYPED)
.map(KeyEvent::getCharacter)
.filter(character -> character.matches("[0-9]"))
.subscribe(listView.getSelectionModel()::select);
```
Example018
Task005
Write an app which prints x and y coordinates of mouse clicked to appropriate labels.
### ObservableValue changes
Up to this point we only have worked with events. There is some metadata on event emissions that can be useful, but we are not quite working with data in the traditional sense.
JavaFX has many implementations of its ObservableValue<T> type. This is essentially a wrapper around a mutable value of a type T , and it notifies any listeners when the value changes. This provides a perfect opportunity to hook a listener onto it and make a reactive stream of value changes.
Let's create a simple ComboBox and hoop up to it's value property.
```java
	ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
	StackPane stackPane = new StackPane(comboBox);
	Scene scene = new Scene(stackPane, 400, 400);
	stage.setScene(scene);
	stage.show();
	JavaFxObservable.valuesOf(comboBox.valueProperty()).subscribe(System.out::println);
```
Example019
Every selection change should produce output in a from of new value printed to console.
Let's modify our example a little and selected some value before running our subscription mechanism.
```java
	ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
	StackPane stackPane = new StackPane(comboBox);
	Scene scene = new Scene(stackPane, 400, 400);
	stage.setScene(scene);
	stage.show();
	comboBox.setValue("Gamma");
	JavaFxObservable.valuesOf(comboBox.valueProperty()).subscribe(System.out::println);
```
Example020
After running the app the output is:

    Gamma
Rx emmited current value as a first thing after the subscription started.
Notice that the Example019 Rx did not push the initial null value. This is because RxJava 2 does not emit null values. However, you can use nullableValuesOf() to get the null values wrapped within Optional object.
```java
	ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
	StackPane stackPane = new StackPane(comboBox);
	Scene scene = new Scene(stackPane, 400, 400);
	stage.setScene(scene);
	stage.show();
	JavaFxObservable.nullableValuesOf(comboBox.valueProperty()).subscribe(optional -> System.out.println(optional.orElse("N/A")));
```
Task006
Let's get a little bit creative. Sum every selected value length and show it to the user in the label.
This example may be a bit contrived, but hopefully you are starting to see some of the possibilities when you have a chain of operators "reacting" to a change in a ComboBox . Pushing each value every time it is selected in a ComboBox allows you to quickly tell other parts of the UI to update accordingly.
Again, you can use this factory on any ObservableValue . This means you can hook into any JavaFX component property and track its changes reactively. The possibilities are quite vast.
#### cahangesOf()
You also have the option of pushing the old and new value in a Change item through the changesOf() factory. This can be helpful for validation, and you can restore that old value back into the control if the new value fails to meet a condition.
```java
	TextField textField = new TextField();
	Scene scene = new Scene(textField);
	stage.setScene(scene);
	stage.show();
	JavaFxObservable.changesOf(textField.textProperty())
	.map(change -> change.getNewVal().matches("[0-9]+") ? change.getNewVal() : change.getOldVal())
	.subscribe(textField::setText);
```
Example022
### Collections and data
Any sizable application needs to work with data and collections of items. One of the greatest utilities to come out of JavaFX are ObservableCollections such as ObservableList , ObservableSet, and ObservableMap . These implementations of List , Set , and Map are built specifically for JavaFX to notify the UI when it has been modified, and any control built off it will visually update accordingly.
However, these ObservableCollections can have custom listeners added to them. This creates an opportunity to reactively work with data through collections. The idea of emitting a collection every time it changes allows some surprisingly useful reactive transformations, and we will see plenty of examples in this section.
####emitOnChanged()
Let's create a simple application backed by an ObservableList of Strings. There will be a ListView\<String> to display these values, and another ListView\<Integer> that will hold their lengths. We will use a TextField and a Button to add Strings to the ObservableList , and both ListViews should update accordingly with each addition.
```java
	ListView<String> listView = new ListView<>();
	ListView<Integer> lenghtsListView = new ListView<>();
	HBox hBox = new HBox(listView, lenghtsListView);
	TextField textField = new TextField();
	Button button = new Button("Add");
	HBox hBox2 = new HBox(textField, button);
	VBox vBox = new VBox(hBox, hBox2);
	Scene scene = new Scene(vBox);
	stage.setScene(scene);
	stage.show();
	
	JavaFxObservable.actionEventsOf(button).map(event -> textField.getText()).subscribe(listView.getItems()::add);
	
	JavaFxObservable.emitOnChanged(listView.getItems())
	.flatMapSingle(items -> Observable.fromIterable(items).map(String::length).toList())
	.subscribe(lenghtsListView.getItems()::setAll);
```
Example023
Go ahead, type in "Detla", click Add button, type in "Beta", click Add button. As the list of items was changed twice there were two emissions already. Let's focus on the latter. The emisson contains all items that currently are held in the String ListView. Inside flatMapSingle() we transform it Observable.fromIterable() which creates a finite (cold) Observable. Values "Delta" and "Beta" are emitted, mapped to its lenghts and grouped toList(). After that the Observable is done(), we can say it's onSucccess() method was call and it emitted list of lengths as its single and final emission.
What we have really done it this example is combining the features of hot and Observables to achive desired behaviour.
Task007
Modify the previous example to show in the ListView\<Integer> only distinct lenghts in the ascending order.
### Add, remove, ad update events
There are factories for ObservableList , ObservableSet , and ObservableMap to emit specific change events against those collections.
#### additionsOf()
```java
ObservableList<String> numbers = FXCollections.observableArrayList();
JavaFxObservable.additionsOf(numbers)
.subscribe(System.out::println);
numbers.add("one");
numbers.addAll("two", "three");
```	
Example024
Produces output:

    one
    two
    three
Note that this factory has no initial emission. It will only emit additions going forward after subscription. The idea of removalsOf() is pretty the same.
#### updatesOf()
An UPDATED emission occurs when an ObservableValue property of a T item in an ObservableList\<T> changes. Consider a User class with an updateable Property called name.
```java
    class User {
    	private final StringProperty nameProperty = new SimpleStringProperty();
    
    	public User(String name) {
    		nameProperty.set(name);
    	}
    	public void setName(String name) {
    		nameProperty.set(name);
    	}
    	public StringProperty nameProperty() {
    		return nameProperty;
    	}
    	@Override
    	public String toString() {
    		return "User[name=" + nameProperty.get() + "]";
    	}
    }
```
```java
	User john = new User("John");
	User lucy = new User("Lucy");

	ObservableList<User> users = FXCollections.observableArrayList(user -> new ObservableValue[] {user.nameProperty()});
	users.addAll(john, lucy);

	JavaFxObservable.updatesOf(users).subscribe(System.out::println);

	john.setName("Johnny");
	lucy.setName("Lucinda");
```
Exmaple025
Produces output:

    User[name=Johnny]
    User[name=Lucinda]
Whenever this name property for any User changes, this change will be
pushed as an emission.
## Combining Observables
There are several ways to combine emissions from multiple Observables, and we will cover many of these combine operators.
#### concat()
One of the simplest ways to combine Observables is to use the concat() operators. You can specify two or more Observables emitting the same type T and it will fire emissions from each one in order.
```java
Observable<String> source1 = Observable.just("one", "two");
Observable<String> source2 = Observable.just("three", "four", "five");

Observable.concat(source1, source2)
.map(String::length)
.toList()
.subscribe(System.out::println);
```
Example026
Produces output:

    [3, 3, 5, 4, 4]
It is very critical to note that onComplete() must be called by each Observable so it moves on to the next one. If you have an infinite Observable in a concatenated operation, it will hold up the line by infinitely emitting items, forever keeping any Observables after it from getting fired. Concatentation is also available as an operator and not just a factory, and it should yield the same output.
```java
Observable<String> source1 = Observable.just("one", "two");
Observable<String> source2 = Observable.just("three", "four", "five");

source1.concatWith(source2)
.map(String::length)
.toList()
.subscribe(System.out::println);
```
Example027
Produces output:

    [3, 3, 5, 4, 4]
#### startWith()
If you want to do a concenation but put another Observable in front rather than after, you can use startWith() instead.
```java
Observable<String> source1 = Observable.just("one", "two");
Observable<String> source2 = Observable.just("three", "four", "five");

source2.startWith(source1)
.map(String::length)
.toList()
.subscribe(System.out::println);
```
Example028
Produces output:

    [3, 3, 5, 4, 4]
But we can also use startWith() to force the first emssion without latter Observable involved.
```java
	Observable<String> source1 = Observable.just("two", "three");

	source1.startWith("one")
	.map(String::length)
	.toList()
	.subscribe(System.out::println);
```
Example029
#### merge()
Merging is almost like concatenation but with one important difference: it will combine all Observables of a given emission type T simultaneously. This means all emissions from all Observables are merged together at once into a single stream without any regard for order or completion.
Task008
Create app with label, decrement button and increment button. Every decrement button click should decrement the label value, same but in opposite direction with increment button. Use merge() and predefined observables.
#### combineLatest()
Pushes first emission when every of combined Observables sent an initial emission. After that pushes on any of Observables pushed new emission so it always reflects the contents of every Observable combined.
Task009
Create app with label showing current position of stage (x, y, width, height). Use combineLatest() and predefined Observables.
#### withLatestFrom()
Is an operator counterpart of combineLatest() factory.

    observable1.withLatestFrom(observable2)

is triggerd only by observable1 emission reflecting the state of both.
Task010
Write an app which reflects state of delta (x, y) between mouse pressed an mouse dragged showing it in the label combined of xLabel, yLabel. On mouse released labels should be cleared.
## Concurrency
#### subscribeOn()
By default, for a given Observable chain, the thread that calls the subscribe() method is the thread the Observable sends emissions on. For instance, a simple subscription to an Observable inside a main() method will fire the emissions on the main daemon thread.
```java
Observable.just("one", "two", "three")
.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
```
Example030
Produces output:

    [main] next=one
    [main] next=two
    [main] next=three
However, we can easily switch these emissions to happen on another thread using subscribeOn() . We can pass a Scheduler as an argument, which specifies where it gets a thread from. In this case we can pass subscribeOn() an argument of Schedulers.newThread() , so it will execute on a new thread for each Observer.
```java
Observable.just("one", "two", "three")
.subscribeOn(Schedulers.newThread())
.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
TimeUnit.SECONDS.sleep(1);
```	
Example031
Produces output:

    [RxNewThreadScheduler-1] next=one
    [RxNewThreadScheduler-1] next=two
    [RxNewThreadScheduler-1] next=three
This way we can declare our Observable chain and an Observer , but then immediately move on without waiting for the emissions to finish. Those are now happening on a new thread named RxNewThreadScheduler-1 . Notice too we have to call TimUnit.SECONDS.sleep(3) afterwards to make the main thread sleep for 3 seconds. This gives our Observable a chance to fire all emissions before the program exits.
A critical behavior to note here is that all emissions are happening sequentially on a single RxNewThreadScheduler-1 thread. Emissions are strictly happening one-at-a-time on a single thread. There is no parallelization or racing to call onNext() throughout the chain. If this did occur, it would break the Observable contract.
subscribeOn() can be declared anywhere in the Observable chain, and it will communicate all the way up to the source what thread to fire emissions on. If you pointlessly declare multiple subscribeOn() operators in a chain, the leftmost one (closest to the source) will win.
In reality, you should be conservative about using Schedulers.newThread() as it creates a new thread for each Observer. You will notice that if we attach multiple Observers to this Observable, we are going to create a new thread for each Observer.
```java
Observable<String> sourceObservable = Observable.just("one", "two", "three", "four", "five")
.subscribeOn(Schedulers.newThread());
sourceObservable.subscribe(next -> System.out.println("Obserer1 [" + Thread.currentThread().getName() + "] next=" + next));
sourceObservable.subscribe(next -> System.out.println("Obserer2 [" + Thread.currentThread().getName() + "] next=" + next));
TimeUnit.SECONDS.sleep(2);
```
Example032
Produces output:

    Obserer1 [RxNewThreadScheduler-1] next=one
    Obserer2 [RxNewThreadScheduler-2] next=one
    Obserer2 [RxNewThreadScheduler-2] next=two
    Obserer1 [RxNewThreadScheduler-1] next=two
    Obserer2 [RxNewThreadScheduler-2] next=three
    Obserer1 [RxNewThreadScheduler-1] next=three
    Obserer2 [RxNewThreadScheduler-2] next=four
    Obserer1 [RxNewThreadScheduler-1] next=four
    Obserer2 [RxNewThreadScheduler-2] next=five
    Obserer1 [RxNewThreadScheduler-1] next=five
or output:

    Obserer1 [RxNewThreadScheduler-1] next=one
    Obserer1 [RxNewThreadScheduler-1] next=two
    Obserer2 [RxNewThreadScheduler-2] next=one
    Obserer1 [RxNewThreadScheduler-1] next=three
    Obserer2 [RxNewThreadScheduler-2] next=two
    Obserer1 [RxNewThreadScheduler-1] next=four
    Obserer2 [RxNewThreadScheduler-2] next=three
    Obserer1 [RxNewThreadScheduler-1] next=five
    Obserer2 [RxNewThreadScheduler-2] next=four
    Obserer2 [RxNewThreadScheduler-2] next=five   
or many more, depends on how thread interleaves
Now we have two threads, RxNewThreadScheduler-1 and RxNewThreadScheduler-2 . What if we had 100, or even 1000 Observers? This can easily happen if you are flatMapping to hundreds or thousands of Observables each with their own subscribeOn(Schedulers.newThread()) . Threads are very expensive and can tax your machine, so we want to constrain the number of threads that can be used at a time.
The most effective way to keep thread creation under control is to "reuse" threads. You can do this with the different Schedulers . A Scheduler is RxJava's equivalent to Java's standard Executor. You can create your own Scheduler by passing an Executor to the Schedulers.from() factory. But for most cases, it is better to use RxJava's standard Schedulers as they are optimized to be conservative and efficient for most cases.
#### Computation
If you are doing computation-intensive operations, you will likely want to use Schedulers.computation().
#### IO
If you are doing a lot of IO-related tasks, like sending web requests or database queries, these are much less taxing on the CPU and threads can be created more liberally. Schedulers.io() is suited for this kind of work.
#### Single
Single Thread Executor
#### JavaFX Scheduler
Finally, the JavaFxScheduler is packaged with the RxJavaFX library. It executes the emissions on the JavaFX thread so they can safely make modifications to a UI.
All RxJavaFX factories already emit on the JavaFxScheduler . Therefore, declaring a subscribeOn() against these sources will have no affect.
### Intervals
While we are talking about concurrency, it is worth mentioning there are other factories that already emit on a specific Scheduler . For instance, there are factories in both RxJava and RxJavaFX to emit at a specified time interval.
In RxJava, there is an Observable.interval() that will emit a consecutive Long at every specified time interval. By default, this runs on the Schedulers.computation() unless you specify a different one as a third argument.
Here is an application that will increment a Label every second.
```java
Label label = new Label();
StackPane hBox = new StackPane(label);
Scene scene = new Scene(hBox, 100, 100);
stage.setScene(scene);
stage.show();
Observable.interval(1, TimeUnit.SECONDS, JavaFxScheduler.platform()).map(String::valueOf).subscribe(label::setText);
```	
Example033
### observeOn()
A lot of people get confused by the difference between subscribeOn() and observeOn(), but the distinction is quite simple. A subsribeOn() instructs the source Observable what thread to emit  items on. However, the observeOn() switches emissions to a different thread at that point in the chain.
In JavaFX, the most common useage of observeOn() is to put items back on the JavaFX thread after a compution or IO operation finishes from another thread. Say you wanted to import some expensive data on Schedulers.io() and collect it in a List . Once it is ready, you want to move that List emission to the JavaFX thread to feed a ListView . That is perfectly doable with an observeOn().
