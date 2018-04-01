package de.tutous.rxjava.s01;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.TestScheduler;

public class SampleConcatMap {

	/**
	 * ConcatMap
	 * 
	 * The same test, now with concatMap operator
	 * 
	 * ConcatMap works almost the same as flatMap, but preserves the order of items.
	 * But concatMap has one big flaw: it waits for each observable to finish all
	 * the work until next one is processed. Lets see how it works in example.
	 */
	public static void main(String[] args) {

		final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

		final TestScheduler scheduler = new TestScheduler();

		Observable.from(items)//
				.concatMap(s -> {
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
