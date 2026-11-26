package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataSampleTest {

    @Test
    void createsValidSample() {
        double[] features = {1.0, 2.0, 3.0};
        DataSample sample = new DataSample("id-1", "test-label", features);

        assertEquals("id-1", sample.getId());
        assertEquals("test-label", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertFalse(sample.isReady());
        assertEquals(2.0, sample.averageFeature(), 0.001);
    }

    @Test
    void throwsExceptionForInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new DataSample(null, "label", new double[]{1.0}));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("id", "", new double[]{1.0}));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("id", "label", null));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("id", "label", new double[0]));
    }

    @Test
    void changeStatusUpdatesState() {
        DataSample sample = new DataSample("id", "label", new double[]{1.0});
        sample.changeStatus(SampleStatus.READY);

        assertTrue(sample.isReady());
        assertEquals(SampleStatus.READY, sample.getStatus());

        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
    }

    @Test
    void constructorAndGetterCopyArray() {
        double[] originalFeatures = {1.0, 2.0};
        DataSample sample = new DataSample("id", "label", originalFeatures);

        originalFeatures[0] = 99.0; // Меняем снаружи
        assertEquals(1.0, sample.getFeatures()[0]); // Внутри не поменялось

        double[] returnedFeatures = sample.getFeatures();
        returnedFeatures[0] = 99.0; // Меняем полученный массив
        assertEquals(1.0, sample.getFeatures()[0]); // Внутри не поменялось
    }

    @Test
    void normalizesFeaturesCorrectlyWithoutMutatingOriginal() {
        double[] features = {10.0, 20.0, 30.0}; // min=10, max=30
        DataSample sample = new DataSample("id", "label", features);

        double[] normalized = sample.getNormalizedFeatures();

        // Ожидаем: (10-10)/20=0.0, (20-10)/20=0.5, (30-10)/20=1.0
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, normalized, 0.001);

        // Проверяем, что исходный массив внутри не изменился
        assertArrayEquals(new double[]{10.0, 20.0, 30.0}, sample.getFeatures(), 0.001);
    }
}
