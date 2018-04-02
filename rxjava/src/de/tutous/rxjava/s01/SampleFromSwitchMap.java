package de.tutous.rxjava.s01;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.TestScheduler;

public class SampleFromSwitchMap {

	/**
	 * SwitchMap
	 * 
	 * The same test of FlatMap, the same conditions, but with operator switchMap
	 * instead of flatMap.
	 * 
	 * Whenever a new item is emitted by the source Observable, it will unsubscribe
	 * to and stop mirroring the Observable that was generated from the
	 * previously-emitted item, and begin only mirroring the current one.
	 * 
	 */
	public static void main(String[] args) {

		final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

		final TestScheduler scheduler = new TestScheduler();

		Observable.from(items)//
				.switchMap(s -> {
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
