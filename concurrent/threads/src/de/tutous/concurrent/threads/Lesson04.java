package de.tutous.concurrent.threads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 
 * wait notify
 *
 */
public class Lesson04 extends Base {

	public Lesson04(int countThreads) {
		super(countThreads);
	}

	private static final int MAX_COUNT = 4;

	public static void main(String[] args) throws InterruptedException {
		new Lesson04(3).start();
	}

	public void start() throws InterruptedException {

		Counter counter = new Counter();
		Lock lock = new Lock();

		Runnable counting = () -> {

			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i < MAX_COUNT; i++) {
				try {
					lock.lock();
					counter.add(1);
				} catch (InterruptedException e1) {
					break;
				} finally {
					lock.unlock();
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

	static class Lock {

		public Boolean wait = false;

		public void lock() throws InterruptedException {
			synchronized (this) {
				// while (wait) { is also possible
				if (wait) {
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

		public void unlock() {
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
