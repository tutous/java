package de.tutous.concurrent.threads;

import java.util.concurrent.TimeUnit;

public class Lesson02 extends Base {

	public Lesson02(int countThreads) {
		super(countThreads);
	}

	public static void main(String[] args) throws InterruptedException {
		new Lesson02(3).start01();
		new Lesson02(3).start02();
		new Lesson02(3).start03();
		new Lesson02(3).start04();
	}

	/**
	 * sample 01
	 */

	static class Counter01 {

		private Integer count = 0;

		public void add(int value) {
			count += value;
		}

		public int getCount() {
			return count;
		}

	}

	public void start01() throws InterruptedException {

		Counter01 counter = new Counter01();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				// synchronized counter
				synchronized (counter) {
					counter.add(1);
				}
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

	/**
	 * sample 02
	 */

	static class Counter02 {

		private Integer count = 0;

		public void add(int value) {
			synchronized (count) {
				count += value;
			}
		}

		public int getCount() {
			return count;
		}

	}

	public void start02() throws InterruptedException {

		Counter02 counter = new Counter02();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				// synchronized count
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

	/**
	 * sample 03
	 */

	static class Counter03 {

		private Integer count = 0;

		public void add(int value) {
			synchronized (this) {
				count += value;
			}
		}

		public int getCount() {
			return count;
		}

	}

	public void start03() throws InterruptedException {

		Counter03 counter = new Counter03();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				// synchronized this
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

	/**
	 * sample 04
	 */

	static class Counter04 {

		private Integer count = 0;

		public synchronized void add(int value) {
			count += value;
		}

		public int getCount() {
			return count;
		}

	}

	public void start04() throws InterruptedException {

		Counter04 counter = new Counter04();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				// synchronized method
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

}
