package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        assertTrue(CourseToolkit.isEven(8));
    }

    @Test
    void returnsFalseForOddNumber() {
        assertFalse(CourseToolkit.isEven(7));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void isPrimeReturnsFalseForNumbersLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(-5));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumbers() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(15));
    }

    @Test
    void isPrimeCorrectlyHandlesSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49)); // 7*7
    }

    @Test
    void isPalindromeReturnsTrueForCorrectText() {
        assertTrue(CourseToolkit.isPalindrome("level"));
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void isPalindromeIsCaseAndSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void isPalindromeThrowsExceptionOnNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsCorrectResultForPositiveNumbers() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(3.0, CourseToolkit.average(arr), 0.0001);
    }

    @Test
    void averageReturnsCorrectResultForNegativeNumbers() {
        int[] arr = {-1, -2, -3};
        assertEquals(-2.0, CourseToolkit.average(arr), 0.0001);
    }

    @Test
    void averageThrowsExceptionForNullOrEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }
}
