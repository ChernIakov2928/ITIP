package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public class SampleRegistry {

    private final Map<SampleID, DataSample> registry = new HashMap<>();

    public void addOrReplace(SampleID id, DataSample sample) {
        registry.put(id, sample);
    }

    public DataSample get(SampleID id) {
        return registry.get(id);
    }

    public void remove(SampleID id) {
        registry.remove(id);
    }

    public boolean containsKey(SampleID id) {
        return registry.containsKey(id);
    }

    public int size() {
        return registry.size();
    }
}
