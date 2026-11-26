package edu.course.lab02;

public class DataSample {

    private final SampleId id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, double[] features) {
        // Создаем ID (внутри SampleId уже есть проверка на null и пустоту)
        this.id = new SampleId(id);

        // Проверка label
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Label cannot be null or empty");
        }

        // Проверка features
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("Features cannot be null or empty");
        }

        this.label = label;
        this.status = SampleStatus.NEW; // По умолчанию
        this.features = features.clone(); // Защитное копирование (Инвариант 5)
    }

    public String getId() {
        return id.value(); // Берем значение из нашего класса SampleId
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone(); // Защитное копирование при возврате
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return this.status == SampleStatus.READY;
    }

    public double averageFeature() {
        double sum = 0;
        for (double feature : features) {
            sum += feature;
        }
        return sum / features.length;
    }

    // ДОПОЛНИТЕЛЬНАЯ ЧАСТЬ: Нормализация (Min-Max) без изменения исходного массива
    public double[] getNormalizedFeatures() {
        double min = features[0];
        double max = features[0];

        for (double f : features) {
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
        }

        double[] normalized = new double[features.length];

        if (max == min) {
            return normalized; // Защита от деления на ноль
        }

        for (int i = 0; i < features.length; i++) {
            normalized[i] = (features[i] - min) / (max - min);
        }

        return normalized;
    }
}
