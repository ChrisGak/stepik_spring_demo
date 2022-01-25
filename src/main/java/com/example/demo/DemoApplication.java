package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public static List<Integer> getModes(final List<Integer> numbers) {
		final Map<Integer, Long> countFrequencies = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("countFrequencies: " + countFrequencies);

		final long maxFrequency = countFrequencies.values().stream().mapToLong(count -> count).max().orElse(-1);
		System.out.println("maxFrequency: " + maxFrequency);
		return countFrequencies.entrySet().stream().filter(tuple -> tuple.getValue() == maxFrequency).map(Map.Entry::getKey).collect(Collectors.toList());
	}

}
