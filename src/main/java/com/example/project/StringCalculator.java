package com.example.project;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringCalculator {
	private static final String regexOrCondition = "|";
	private final Pattern pattern = Pattern.compile("\\[(.*?)\\]");

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

	private void validateNumbers(int[] numbers) {
		int[] negativeNumbers = Arrays.stream(numbers).filter(element -> element < 0).toArray();
		if (negativeNumbers.length > 0) {
			String[] formattedNegativeNumbers = Arrays.stream(negativeNumbers).mapToObj(String::valueOf)
					.toArray(String[]::new);
			throw new Error("Negative numbers are not allowed: " + String.join(", ", formattedNegativeNumbers));
		}
	}

	private String[] getNumbers(String input) {
		if (hasCustomDelimiter(input)) {
			return getNumbersWithCustomDelimiters(input);
		}
		return getNumbersWithDefaultDelimiters(input);
	}

	private String[] getNumbersWithDefaultDelimiters(String input) {
		String defaultDelimiters = "," + regexOrCondition + "\n";
		return input.split(defaultDelimiters);
	}

	private String[] getNumbersWithCustomDelimiters(String input) {
		String delimitersAndNumbersSeparator = "\n";
		String customDelimiters = extractCustomDelimiters(input, delimitersAndNumbersSeparator);
		String numbers = input.split(delimitersAndNumbersSeparator)[1];
		return numbers.split(customDelimiters);
	}

	private String extractCustomDelimiters(String input, String delimiterAndNumberSeparator) {
		String specialCharRegexEscape = "\\";
		if (hasMultiCustomDelimiters(input)) {
			StringBuilder delimiters = new StringBuilder();
			delimiters.append(specialCharRegexEscape);
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				delimiters.append(String.join(specialCharRegexEscape, matcher.group(1).split("")) + regexOrCondition);
			}

			delimiters.deleteCharAt(delimiters.lastIndexOf(regexOrCondition));
			return delimiters.toString();
		} else {
			String singleCharCustomDelimitersPrefix = "//";
			return specialCharRegexEscape
					+ input.split(delimiterAndNumberSeparator)[0].replace(singleCharCustomDelimitersPrefix, "");
		}
	}

	private boolean hasMultiCustomDelimiters(String input) {
		return input.startsWith("//[");
	}

	private boolean hasCustomDelimiter(String input) {
		return input.startsWith("//");
	}
}
