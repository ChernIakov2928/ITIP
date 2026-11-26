package edu.course.lab04;

public class ChainedHashTable {

    private static class Node {

        SampleID key;
        DataSample value;
        Node next;

        Node(SampleID key, DataSample value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node[] buckets;
    private int size;

    public ChainedHashTable() {
        this.buckets = new Node[16];
        this.size = 0;
    }

    public void put(SampleID key, DataSample value) {
        int index = getIndex(key);
        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    public DataSample get(SampleID key) {
        int index = getIndex(key);
        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(SampleID key) {
        return Math.abs(key.hashCode()) & (buckets.length - 1);
    }
}
