package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    @Test
    void noDiscountReturnsSamePrice() {
        PriceCalculator calc = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calc.calculate(100.0), 0.001);
    }

    @Test
    void percentDiscountAppliesCorrectly() {
        PriceCalculator calc = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, calc.calculate(100.0), 0.001);
    }

    @Test
    void calculatorSupportsReplacingStrategy() {
        PriceCalculator calc = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calc.calculate(100.0), 0.001);

        PriceCalculator calcWithDiscount = new PriceCalculator(new PercentDiscount(50));
        assertEquals(50.0, calcWithDiscount.calculate(100.0), 0.001);
    }
}
