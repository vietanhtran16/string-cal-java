package com.example.project;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator {

	public int Add(String input) {
		if (input.isEmpty()) {
			return 0;
		}

		String[] numbers = getNumbers(input);
		int[] parsedNumbers = parseNumbers(numbers);
		validateNumbers(parsedNumbers);
		return IntStream.of(parsedNumbers).filter(number -> number < 1000).sum();
	}

	private int[] parseNumbers(String[] numbers) {
		return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
	}

	private void validateNumbers(int[] numbers){
		 int[] negativeNumbers = Arrays.stream(numbers).filter(element -> element < 0).toArray();
		 if(negativeNumbers.length > 0){
			String[] formattedNegativeNumbers = Arrays.stream(negativeNumbers).mapToObj(String::valueOf).toArray(String[]::new);
			throw new Error("Negative numbers are not allowed: " + String.join(", ", formattedNegativeNumbers));
		 }
	}

	private String[] getNumbers(String input) {
		if (hasCustomDelimiters(input)) {
			return getNumbersWithCustomDelimiter(input);
		}
		return getNumbersWithDefaultDelimiters(input);
	}

	private String[] getNumbersWithDefaultDelimiters(String input) {
		return input.split(",|\n");
	}

	private String[] getNumbersWithCustomDelimiter(String input) {
		String delimiterAndNumberSeparator = "\n";
		String customDelimiter = getCustomDelimiters(input, delimiterAndNumberSeparator);
		String numbers = input.split(delimiterAndNumberSeparator)[1];
		return numbers.split("\\" + customDelimiter);
	}

	private String getCustomDelimiters(String input, String delimiterAndNumberSeparator) {
		String customDelimitersPrefix = "//";
		return input.split(delimiterAndNumberSeparator)[0].replace(customDelimitersPrefix, "");
	}

	private boolean hasCustomDelimiters(String input) {
		return input.startsWith("//");
	}
}
