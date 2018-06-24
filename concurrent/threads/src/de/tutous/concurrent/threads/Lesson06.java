package de.tutous.concurrent.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * Semaphore
 *
 */
public class Lesson06 extends Base {

	public Lesson06(int countThreads) {
		super(countThreads);
	}

	public static void main(String[] args) throws InterruptedException {
		new Lesson06(3).start();
	}

	public void start() throws InterruptedException {

		Counter counter = new Counter();
		Semaphore semaphore = new Semaphore(1);
		AtomicInteger indexWrite = new AtomicInteger(0);

		Runnable counting = () -> {

			int index = indexWrite.getAndIncrement();

			for (int i = 1; i < 21; i++) {

				try {
					semaphore.acquire();
					counter.add(1);
					System.out.println(index + " write " + counter.getCount());
				} catch (InterruptedException e) {
					break;
				} finally {
					semaphore.release();
				}

				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					break;
				}

			}

		};

		start(counting);
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
