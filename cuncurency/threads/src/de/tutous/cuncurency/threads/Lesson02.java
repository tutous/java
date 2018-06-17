package de.tutous.cuncurency.threads;

import java.util.concurrent.TimeUnit;

public class Lesson02 {

	private Counter01 counter01 = new Counter01();
	private Counter02 counter02 = new Counter02();
	private Counter03 counter03 = new Counter03();
	private Counter04 counter04 = new Counter04();

	public static void main(String[] args) throws InterruptedException {
		new Lesson02().start01();
		new Lesson02().start02();
		new Lesson02().start03();
		new Lesson02().start04();
	}

	public void start01() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				synchronized (counter01) {
					counter01.add(1);
				}
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
				synchronized (counter01) {
					counter01.add(1);
				}
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

		System.out.println(counter01.getCount());
	}

	public void start02() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter02.add(1);
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
				counter02.add(1);
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

		System.out.println(counter02.getCount());
	}

	public void start03() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter03.add(1);
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
				counter03.add(1);
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

		System.out.println(counter03.getCount());
	}
	
	public void start04() throws InterruptedException {

		Thread t01 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				counter04.add(1);
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
				counter04.add(1);
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

		System.out.println(counter04.getCount());
	}

	static class Counter01 {

		private Integer count = 0;

		public void add(int value) {
			count += value;
		}

		public int getCount() {
			return count;
		}

	}

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

	static class Counter04 {

		private Integer count = 0;

		public synchronized void add(int value) {
			count += value;
		}

		public int getCount() {
			return count;
		}

	}
}
