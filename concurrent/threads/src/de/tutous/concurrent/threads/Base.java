package de.tutous.concurrent.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Base {

	private Consumer<List<Thread>> execute;
	private List<BiFunction<ThreadGroup, Runnable, List<Thread>>> creates;

	public Base(Integer... countThreads) {
		execute = (threads) -> threads.parallelStream().forEach(t -> t.start());
		creates = Arrays.asList(countThreads).stream().map(count -> listOfThreads(count)).collect(Collectors.toList());
	}

	protected void start(int timeout, Runnable... runnables) {
		ThreadGroup threadGroup = new ThreadGroup("concurrent");
		threadGroup.setDaemon(true);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				threadGroup.interrupt();
			}
		}, timeout * 1000);
		AtomicInteger index = new AtomicInteger(0);
		Arrays.asList(runnables).stream() //
				.map(runnable -> creates.get(index.getAndIncrement()) //
						.apply(threadGroup, runnable)) //
				.forEach(threads -> execute.accept(threads));
		while (threadGroup.activeCount() != 0) {
			try {
				TimeUnit.MICROSECONDS.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	protected void start(Runnable... runnables) {
		ThreadGroup threadGroup = new ThreadGroup("concurrent");
		threadGroup.setDaemon(true);
		AtomicInteger index = new AtomicInteger(0);
		Arrays.asList(runnables).stream() //
				.map(runnable -> creates.get(index.getAndIncrement()) //
						.apply(threadGroup, runnable)) //
				.forEach(threads -> execute.accept(threads));
		while (threadGroup.activeCount() != 0) {
			try {
				TimeUnit.MICROSECONDS.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private BiFunction<ThreadGroup, Runnable, List<Thread>> listOfThreads(int countThreads) {
		return (group, runnable) -> {
			List<Thread> threads = new ArrayList<>(countThreads);
			for (int i = 0; i < countThreads; i++) {
				threads.add(new Thread(group, runnable));
			}
			return threads;
		};
	}

}
