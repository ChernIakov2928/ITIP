package edu.course.lab04;

import java.util.Objects;

public final class SampleID {

    private final String datasetName;
    private final int sampleNumber;

    public SampleID(String datasetName, int sampleNumber) {
        if (datasetName == null || datasetName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dataset name cannot be empty");
        }
        if (sampleNumber < 0) {
            throw new IllegalArgumentException("Sample number cannot be negative");
        }
        this.datasetName = datasetName;
        this.sampleNumber = sampleNumber;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public int getSampleNumber() {
        return sampleNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampleID sampleId = (SampleID) o;
        return sampleNumber == sampleId.sampleNumber
                && Objects.equals(datasetName, sampleId.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, sampleNumber);
    }
}
