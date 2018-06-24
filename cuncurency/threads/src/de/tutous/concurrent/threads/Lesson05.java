package de.tutous.cuncurency.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lesson05 {

	private Counter counter = new Counter();
	private ReadWriteLock lock = new ReentrantReadWriteLock(true);

	public static void main(String[] args) throws InterruptedException {
		new Lesson05().start();
	}

	public void start() throws InterruptedException {

		ThreadGroup group = new ThreadGroup("test");

		for (int i = 0; i < 20; i++) {
			Thread read = new Thread(group, () -> {
				for (int j = 1; j < 71; j++) {
					lock.readLock().lock();
					System.out.println(j + " read " + counter.getCount());
					lock.readLock().unlock();
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						break;
					}
				}
			});
			read.start();
		}

		Thread write01 = new Thread(group, () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				lock.writeLock().lock();
				counter.add(1);
				System.out.println(1 + " write " + counter.getCount());
				lock.writeLock().unlock();
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		write01.start();

		Thread write02 = new Thread(group, () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < 21; i++) {
				lock.writeLock().lock();
				counter.add(1);
				System.out.println(2 + " write " + counter.getCount());
				lock.writeLock().unlock();
				try {
					TimeUnit.MILLISECONDS.sleep(150);
				} catch (InterruptedException e) {
					break;
				}
			}

		});

		write02.start();

		while (write01.isAlive() || write02.isAlive()) {
			TimeUnit.MILLISECONDS.sleep(500);
		}

		System.out.println("counter is " + counter.getCount());
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
