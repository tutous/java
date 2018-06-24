package de.tutous.concurrent.threads;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Exchanger
 * 
 *
 */
public class Lesson08 extends Base {

	public static void main(String[] args) {
		new Lesson08(2, 2).start();
	}

	public Lesson08(int countExchangerA, int countExchangerB) {
		super(countExchangerA, countExchangerB);
	}

	public void start() {

		Exchanger<Order> exchanger = new Exchanger<>();

		AtomicInteger indexA = new AtomicInteger(0);

		Runnable exchangerRunnableA = () -> {

			while (true) {

				try {
					Order order = new Order("A", indexA.getAndIncrement());
					Order exchangedOrder = exchanger.exchange(order);
					System.out.println(Thread.currentThread().getName() + " exchanged " + order.getContext()
							+ order.getIndex() + " for " + exchangedOrder.getContext() + exchangedOrder.getIndex());
				} catch (InterruptedException e) {
					break;
				}

				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					break;
				}

			}

		};

		AtomicInteger indexB = new AtomicInteger(0);

		Runnable exchangerRunnableB = () -> {

			while (true) {

				try {
					Order order = new Order("B", indexB.getAndIncrement());
					Order exchangedOrder = exchanger.exchange(order);
					System.out.println(Thread.currentThread().getName() + " exchanged " + order.getContext()
							+ order.getIndex() + " for " + exchangedOrder.getContext() + exchangedOrder.getIndex());
				} catch (InterruptedException e) {
					break;
				}

				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					break;
				}

			}

		};

		start(2, exchangerRunnableA, exchangerRunnableB);

	}

	static class Order {

		private Integer index;
		private String context;

		public Order(String context, Integer index) {
			this.index = index;
			this.context = context;
		}

		public Integer getIndex() {
			return index;
		}

		public String getContext() {
			return context;
		}

	}

}
