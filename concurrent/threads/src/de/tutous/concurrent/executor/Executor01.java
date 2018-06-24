package de.tutous.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Executor01 {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		new Executor01().start01();
		new Executor01().start02();
		new Executor01().start03();
	}

	/*
	 * submit
	 */
	public void start01() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Callable<Task> callable1 = () -> {
			TimeUnit.SECONDS.sleep(1);
			return new Task("task 01");
		};
		Callable<Task> callable2 = () -> {
			TimeUnit.SECONDS.sleep(1);
			return new Task("task 02");
		};
		Callable<Task> callable3 = () -> {
			TimeUnit.SECONDS.sleep(1);
			return new Task("task 03");
		};

		Future<Task> future1 = executorService.submit(callable1);
		Future<Task> future2 = executorService.submit(callable2);
		Future<Task> future3 = executorService.submit(callable3);

		Task task1 = future1.get();
		System.out.println(task1.getTask());

		Task task2 = future2.get();
		System.out.println(task2.getTask());

		Task task3 = future3.get();
		System.out.println(task3.getTask());

		executorService.shutdown();

	}

	/*
	 * invokeAll
	 */
	public void start02() throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		List<Callable<Task>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String task = "task " + i;
			int timeout = 1;
			tasks.add(() -> {
				TimeUnit.SECONDS.sleep(timeout);
				return new Task(task);
			});
		}

		List<Future<Task>> futures = executorService.invokeAll(tasks);

		for (Future<Task> future : futures) {
			System.out.println(future.get().getTask());
		}

		executorService.shutdown();

	}

	/*
	 * invokeAny
	 */
	public void start03() throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		List<Callable<Task>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String task = "task " + i;
			int timeout = 1;
			tasks.add(() -> {
				TimeUnit.SECONDS.sleep(timeout);
				return new Task(task);
			});
		}

		Task task = executorService.invokeAny(tasks);

		System.out.println(task.getTask());

		executorService.shutdown();

	}

	static class Task {
		private String task;

		public Task(String task) {
			this.task = task;
		}

		public String getTask() {
			return task;
		}
	}

}
