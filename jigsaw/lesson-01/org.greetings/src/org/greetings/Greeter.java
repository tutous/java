package org.greetings;

public interface Greeter {
	public default String greet() {
		return "hello world";
	}
}
