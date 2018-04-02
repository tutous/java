package de.tutous.rxjava.s02;

import java.io.ByteArrayInputStream;

import rx.Observable;

public class SampleCreate {

	public static void main(String[] args) throws Exception {

		ByteArrayInputStream in = new ByteArrayInputStream("Hallo \nWorld\n\n".getBytes());

		Observable.unsafeCreate(//
				new OnSubscribeFromInputStream(in))//
				.subscribe(//
						line -> print(line), // onNext
						error -> print(error), // onError
						() -> println()); // onCompleted
	}

	private static void println() {
		System.out.println("!");
	}

	private static void print(Throwable error) {
		error.printStackTrace();
	}

	private static void print(String line) {
		System.out.print(line);
	}

}
