package de.tutous.concurrent.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * ArrayBlockingQueue LinkedBlockingQueue
 *
 */
public class Lesson07 extends Base {

	public Lesson07(int countAdd, int countRemove, int countRead) {
		super(countAdd, countRemove, countRead);
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("*** ArrayBlockingQueue ***");
		new Lesson07(2, 1, 1).start01();
		System.out.println("*** LinkedBlockingQueue ***");
		new Lesson07(2, 1, 1).start02();
	}

	public void start01() {
		ArrayBlockingQueue<Task> blockingQueue = new ArrayBlockingQueue<>(2);

		AtomicInteger index = new AtomicInteger(0);

		Runnable add = () -> {

			while (true) {
				if (blockingQueue.offer(new Task(index))) {
					// System.out.println("added task");
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		};

		Runnable remove = () -> {

			while (true) {
				if (!blockingQueue.isEmpty()) {
					Task task = blockingQueue.element();
					task.start();
					blockingQueue.remove(task);
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		};

		Runnable read = () -> {

			while (true) {
				System.out.println("queue size " + blockingQueue.size());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					break;
				}
			}

		};

		start(2, add, remove, read);

	}

	public void start02() {
		LinkedBlockingQueue<Task> blockingQueue = new LinkedBlockingQueue<>(2);

		AtomicInteger index = new AtomicInteger(0);

		Runnable add = () -> {

			while (true) {
				if (blockingQueue.offer(new Task(index))) {
					// System.out.println("added task");
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		};

		Runnable remove = () -> {

			while (true) {
				if (!blockingQueue.isEmpty()) {
					Task task = blockingQueue.element();
					task.start();
					blockingQueue.remove(task);
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		};

		Runnable read = () -> {

			while (true) {
				System.out.println("queue size " + blockingQueue.size());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					break;
				}
			}

		};

		start(2, add, remove, read);

	}

	static class Task {

		private String task = "do task ";
		private AtomicInteger index;

		public Task(AtomicInteger index) {
			this.index = index;
		}

		void start() {
			System.out.println(task + index.getAndIncrement());
		}

	}

}
