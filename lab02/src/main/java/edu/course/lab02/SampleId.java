package edu.course.lab02;

public final class SampleId {

    private final String value;

    public SampleId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}
