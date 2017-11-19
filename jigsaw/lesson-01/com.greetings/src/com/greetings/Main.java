package com.greetings;

import org.astro.GreeterFactory;

public class Main {

	public static void main(String[] args) {
		System.out.println(GreeterFactory.create().greet());
	}

}
