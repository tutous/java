package de.tutous.rxjava.s01;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.TestScheduler;

public class SampleFromFlatMap {

	/**
	 * FlatMap
	 * 
	 * Lets start with a simple Java unit test that is testing flatMap operator.
	 * First we create list of Strings, then transform each object of this list into
	 * an Observable using operator from. Then each item is flapMapped into an
	 * observable that adds “x” letter at the end of the string. Next the observable
	 * is delayed by na random number of seconds (line 9). Last thing is advancing
	 * in time by 1 minute (line 17), just to be sure that everything will have the
	 * time to emit. (If we would not do this, the test would end before any
	 * emission).
	 * 
	 * So what actually happened here is operator flatMap does not care about the
	 * order of the items. It creates a new observable for each item and that
	 * observable lives its own life. Some of them will emit faster, and others
	 * slower because we delay each of them for a random amount of seconds. Similar
	 * effect will happen without delaying, but it was added for the sake of an
	 * argument.
	 */
	public static void main(String[] args) {

		final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

		final TestScheduler scheduler = new TestScheduler();

		Observable.from(items)//
				.flatMap(s -> {
					return Observable.just(s)//
							.delay(new Random().nextInt(10), TimeUnit.SECONDS, scheduler) //
							.doOnEach(a -> {
								System.out.println(a.getKind() + " " + a.getValue());
							});
				})//
				.toList()//
				.doOnNext(a -> {
					System.out.println(a);
				})//
				.subscribe();

		scheduler.advanceTimeBy(1, TimeUnit.MINUTES);

	}

}
