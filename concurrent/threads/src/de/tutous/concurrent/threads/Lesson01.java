package de.tutous.concurrent.threads;

import java.util.concurrent.TimeUnit;

public class Lesson01 extends Base {

	public Lesson01(int countThreads) {
		super(countThreads);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {
		new Lesson01(3).start();
	}

	public void start() throws InterruptedException {

		Counter counter = new Counter();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter.add(1);
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}

		};

		start(counting);
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
