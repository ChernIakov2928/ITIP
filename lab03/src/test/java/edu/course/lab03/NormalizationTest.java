package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class NormalizationTest {

    // Тесты для MinMax
    @Test
    void minMaxNormalizesCorrectly() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        double[] input = {10.0, 20.0, 30.0};
        double[] expected = {0.0, 0.5, 1.0};

        assertArrayEquals(expected, processor.process(input), 0.001);
    }

    @Test
    void minMaxDoesNotMutateInputArray() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        double[] input = {10.0, 20.0};
        processor.process(input);
        assertArrayEquals(new double[]{10.0, 20.0}, input, 0.001);
    }

    @Test
    void meanCenteringNormalizesCorrectly() {
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());
        double[] input = {1.0, 2.0, 3.0};
        double[] expected = {-1.0, 0.0, 1.0};

        assertArrayEquals(expected, processor.process(input), 0.001);
    }

    @Test
    void meanCenteringDoesNotMutateInputArray() {
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());
        double[] input = {5.0, 5.0};
        processor.process(input);
        assertArrayEquals(new double[]{5.0, 5.0}, input, 0.001);
    }

    @Test
    void processorSupportsReplacingStrategy() {
        double[] input = {10.0, 20.0, 30.0};

        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor meanCenter = new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, minMax.process(input), 0.001);
        assertArrayEquals(new double[]{-10.0, 0.0, 10.0}, meanCenter.process(input), 0.001);
    }
}
