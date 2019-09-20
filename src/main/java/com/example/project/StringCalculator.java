package com.example.project;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator {

	public int sum(String input) {
		if(input.isEmpty()) {
			return 0;
		}

		String[] numbers = getNumbers(input);
		int[] parsedNumbers = parseNumbers(numbers);
		return IntStream.of(parsedNumbers).sum();
	}

	private int[] parseNumbers(String[] numbers) {
		return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
	}

	private String[] getNumbers(String input) {
		return input.split(",|\n");
	}
}
