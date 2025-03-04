package com.example.filefilter;

import java.util.List;

public class Statistics {
    double sum;
    double max;
    double min;
    double average;
    private WorkWithFiles dataProcessor;

    public Statistics(WorkWithFiles dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public void printStatistics() {
        System.out.println("Statistics:");
        System.out.println("Integers: " + dataProcessor.getIntegers().size());
        System.out.println("Floats: " + dataProcessor.getFloats().size());
        System.out.println("Strings: " + dataProcessor.getStrings().size());
    }

    public void printFullStatistics() {
        System.out.println("Full Statistics:");
        printNumberStatistics("Integers", dataProcessor.getIntegers());
        printNumberStatistics("Floats", dataProcessor.getFloats());
        printStringStatistics();
    }

    private <T extends Number> void printNumberStatistics(String type, List<T> list) {
        if (list.isEmpty()) return;
        sum = list.stream().mapToDouble(Number::doubleValue).sum();
        max = list.stream().mapToDouble(Number::doubleValue).max().orElse(0);
        min = list.stream().mapToDouble(Number::doubleValue).min().orElse(0);
        average = sum / list.size();

        System.out.println(type + ":");
        System.out.println("  Count: " + list.size());
        System.out.println("  Sum: " + sum);
        System.out.println("  Max: " + max);
        System.out.println("  Min: " + min);
        System.out.println("  Average: " + average);
    }

    private void printStringStatistics() {
        List<String> strings = dataProcessor.getStrings();
        if (strings.isEmpty()) return;
        int maxLen = strings.stream().mapToInt(String::length).max().orElse(0);
        int minLen = strings.stream().mapToInt(String::length).min().orElse(0);

        System.out.println("Strings:");
        System.out.println("  Count: " + strings.size());
        System.out.println("  Longest string length: " + maxLen);
        System.out.println("  Shortest string length: " + minLen);
    }

    public Integer getIntegerCount() { return dataProcessor.getIntegers().size(); }
    public Integer getFloatCount() { return dataProcessor.getFloats().size(); }
    public Integer getStringCount() { return  dataProcessor.getStrings().size(); }
    public Double getSum() { return sum; }
    public Double getMax() { return max; }
    public Double getMin() { return min; }
    public Double getAverage() { return average; }
}
