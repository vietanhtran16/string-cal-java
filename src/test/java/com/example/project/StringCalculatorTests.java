package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringCalculatorTests {

	@Test
	void return0ForEmptyString() {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add("");

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

		int result = calculator.Add(input);

		assertEquals(expectedResult, result);
	}

	@ParameterizedTest()
	@CsvSource({
			"'7,4,5', 16",
			"'9,5,1,4', 19",
	})
	void returnSumOfAnyNumbers(String input, int expectedResult) {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add(input);

		assertEquals(expectedResult, result);
	}

	@Test
	void handleCommaAndNewLineAsDelimiters() {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add("1\n2,3");

		assertEquals(6, result);
	}

	@ParameterizedTest()
	@CsvSource({
			"'//;\n1;2', 3",
			"'//*\n4*2*8', 14",
	})
	void handleCustomDelimitersWithSingleChar(String input, int expectedResult) {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add(input);

		assertEquals(expectedResult, result);
	}

	@Test
	void throwErrorForNegativeNumbers(){
		StringCalculator calculator = new StringCalculator();

		Error negativeNumberException = assertThrows(Error.class, () -> calculator.Add("-1,2,-3"));

		assertTrue(negativeNumberException.getMessage().contains("Negative numbers are not allowed: -1, -3"));
	}

	@Test
	void ignoreNumberMoreThan1000(){
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add("1000,2,3");

		assertEquals(5, result);
	}

	@ParameterizedTest()
	@CsvSource({
			"'//[***]\n1***2***3', 6"
	})
	void handleCustomDelimitersWithMultiChars(String input, int expectedResult) {
		StringCalculator calculator = new StringCalculator();

		int result = calculator.Add(input);

		assertEquals(expectedResult, result);
	}
}
