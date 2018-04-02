package de.tutous.rxjava.s01;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class SampleFromMap {
	
	/**
	 * Observable ist Datenmengen async:
	 *  kontrollieren
	 *  filtern
	 *  aggregieren
	 *  transformieren
	 *  orchestrieren
	 * 
	 */
	public static void main(String[] args) {
		
		List<String> source = Arrays.asList("Hello", "World", "Test");
		
		Observable.from(source)
		  .map(s -> s.toUpperCase() + " ")
		  .filter(s -> s.contains("E"))
		  .reduce(new StringBuilder(), StringBuilder::append)
		  .subscribe(System.out::print, e -> {}, () -> System.out.println("!"));
		
	}

}
