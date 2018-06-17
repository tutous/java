package de.tutous.cuncurency.threads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Lesson04 {

	private Counter counter = new Counter();
	private Lock signal = new Lock();
	private static final int MAX_COUNT = 4;

	public static void main(String[] args) throws InterruptedException {
		new Lesson04().start();
	}

	public void start() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println("run " + Thread.currentThread().getName());
			for (int i = 1; i < MAX_COUNT; i++) {

				try {
					signal.doWait();
					counter.add(1);
					TimeUnit.MILLISECONDS.sleep(10);
					signal.doNotify();
				} catch (InterruptedException e) {
					break;
				}

			}

		});

		Thread t02 = new Thread(() -> {

			System.out.println("run " + Thread.currentThread().getName());
			for (int i = 1; i < MAX_COUNT; i++) {
				try {
					signal.doWait();
					counter.add(1);
					TimeUnit.MILLISECONDS.sleep(10);
					signal.doNotify();
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		Thread t03 = new Thread(() -> {

			System.out.println("run " + Thread.currentThread().getName());
			for (int i = 1; i < MAX_COUNT; i++) {
				try {
					signal.doWait();
					counter.add(1);
					TimeUnit.MILLISECONDS.sleep(10);
					signal.doNotify();
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		t01.start();
		t02.start();
		t03.start();

		TimeUnit.SECONDS.sleep(1);

		System.out.println(counter.getCount());
	}

	static class Lock {

		public Boolean wait = false;

		public void doWait() throws InterruptedException {
			synchronized (this) {
				while (wait) {
					System.out.println("start wait    " + getCurrentThread());
					wait();
				}
				System.out.println("count         " + getCurrentThread());
				wait = true;
			}
		}

		private String getCurrentThread() {
			return Thread.currentThread().getName() + " " + new Date().getTime();
		}

		public void doNotify() {
			synchronized (this) {
				System.out.println("doNotify      " + getCurrentThread());
				wait = false;
				notify();
			}
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
