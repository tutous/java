package de.tutous.concurrent.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * ReadWriteLock
 *
 */
public class Lesson05 extends Base {

	public Lesson05(int countThreadsRead, int countThreadsWrite) {
		super(countThreadsRead, countThreadsWrite);
	}

	public static void main(String[] args) throws InterruptedException {
		new Lesson05(50, 3).start();
	}

	public void start() throws InterruptedException {

		Counter counter = new Counter();
		ReadWriteLock lock = new ReentrantReadWriteLock(true);
		AtomicInteger indexRead = new AtomicInteger(0);

		Runnable read = () -> {

			int index = indexRead.getAndIncrement();

			while (true) {

				int count = 0;

				try {
					lock.readLock().lock();
					count = counter.getCount();
					System.out.println(index + " read " + count);
				} finally {
					lock.readLock().unlock();
				}

				// 3 threads * 20
				if (count == 60) {
					break;
				}

				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					break;
				}

			}

		};

		AtomicInteger indexWrite = new AtomicInteger(0);
		Runnable write = () -> {

			int index = indexWrite.getAndIncrement();

			for (int i = 1; i < 21; i++) {
				try {
					lock.writeLock().lock();
					counter.add(1);
					System.out.println(index + " write " + counter.getCount());
				} finally {
					lock.writeLock().unlock();
				}

				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					break;
				}
			}

		};

		start(read, write);
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
