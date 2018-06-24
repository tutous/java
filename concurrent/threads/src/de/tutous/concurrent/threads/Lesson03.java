package de.tutous.concurrent.threads;

import java.util.concurrent.TimeUnit;

/**
 * 
 * ThreadLocal
 *
 */
public class Lesson03 extends Base {

	public Lesson03(int countThreads) {
		super(countThreads);
	}

	public static void main(String[] args) throws InterruptedException {
		new Lesson03(2).start();
	}

	public void start() throws InterruptedException {

		ThreadLocal<Counter> counter = new ThreadLocal<>();

		Runnable counting = () -> {
			// each thread has its own counter
			counter.set(new Counter());
			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter.get().add(1);
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}
			System.out.println(counter.get().getCount());
		};

		start(counting);

	}

	static class Counter {

		private int count = 0;

		public void add(int value) {
			count += value;
		}

		public int getCount() {
			return count;
		}

	}

}
