package de.tutous.cuncurency.threads;

import java.util.concurrent.TimeUnit;

public class Lesson03 {

	public static void main(String[] args) throws InterruptedException {
		new Lesson03().start();
	}

	public void start() throws InterruptedException {

		SharedRunnable sharedRunnable = new SharedRunnable();

		Thread t01 = new Thread(sharedRunnable);
		Thread t02 = new Thread(sharedRunnable);

		t01.start();
		t02.start();

		TimeUnit.SECONDS.sleep(1);

	}

	static class SharedRunnable implements Runnable {

		private ThreadLocal<Counter> counter = new ThreadLocal<>();

		@Override
		public void run() {
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
		}

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
