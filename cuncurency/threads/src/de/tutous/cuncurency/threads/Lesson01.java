package de.tutous.cuncurency.threads;

import java.util.concurrent.TimeUnit;

public class Lesson01 {
	
	private Counter counter = new Counter();

	public static void main(String[] args) throws InterruptedException {
		new Lesson01().start();
	}

	public void start() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter.add(1);
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		Thread t02 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter.add(1);
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		t01.start();
		t02.start();

		TimeUnit.SECONDS.sleep(1);

		System.out.println(counter.getCount());
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
