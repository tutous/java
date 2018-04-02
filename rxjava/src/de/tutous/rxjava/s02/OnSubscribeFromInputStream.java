package de.tutous.rxjava.s02;

import rx.Subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rx.Observable.OnSubscribe;

public class OnSubscribeFromInputStream implements OnSubscribe<String> {

	private final BufferedReader reader;

	public OnSubscribeFromInputStream(InputStream is) {
		this.reader = new BufferedReader(new InputStreamReader(is));
	}

	@Override
	public void call(Subscriber<? super String> subscriber) {
		try {
			String line;
			while (!subscriber.isUnsubscribed() && //
					(line = reader.readLine()) != null && !line.isEmpty()) {
				subscriber.onNext(line);
			}
		} catch (IOException ex) {
			subscriber.onError(ex);
		}
		if (!subscriber.isUnsubscribed()) {
			subscriber.onCompleted();
		}
	}

}
