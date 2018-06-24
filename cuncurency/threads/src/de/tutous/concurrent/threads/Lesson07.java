package de.tutous.cuncurency.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Lesson07 {

	public static void main(String[] args) throws InterruptedException {

		ArrayBlockingQueue<Task> blockingQueue = new ArrayBlockingQueue<>(2);

		Thread remove = new Thread(() -> {

			while (true) {
				if (!blockingQueue.isEmpty()) {
					Task task = blockingQueue.element();
					task.start();
					blockingQueue.remove(task);
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		Thread add = new Thread(() -> {

			int index = 0;

			while (true) {
				if (blockingQueue.offer(new Task("do task " + index++))) {
					//System.out.println("added task");
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		add.setDaemon(true);
		add.start();
		remove.setDaemon(true);
		remove.start();

		TimeUnit.SECONDS.sleep(10);

	}

	static class Task {

		private String task;

		public Task(String task) {
			this.task = task;
		}

		void start() {
			System.out.println(task);
		}

	}

}
