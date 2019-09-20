package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringCalculatorTests {

	@Test
	void return0ForEmptyString() {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.sum("");

		assertEquals(0, result);
	}

	@ParameterizedTest()
	@CsvSource({
			"'1,2', 3",
			"'3,7', 10",
			"'100,401', 501",
	})
	void returnSumOfTwoNumbers(String input, int expectedResult) {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.sum(input);

		assertEquals(expectedResult, result);
	}

	@ParameterizedTest()
	@CsvSource({
			"'7,4,5', 16",
			"'9,5,1,4', 19",
	})
	void returnSumOfAnyNumbers(String input, int expectedResult) {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.sum(input);

		assertEquals(expectedResult, result);
	}

	@Test
	void handleCommaAndNewLineAsDelimiters() {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.sum("1\n2,3");

		assertEquals(6, result);
	}
}
