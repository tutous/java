package org.astro;

import org.astro.internal.WorldGreeter;
import org.greetings.Greeter;

public class GreeterFactory {
	public static Greeter create() {
		return new WorldGreeter();
	}
}
