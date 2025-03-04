package com.example.filefilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFiles {
    private List<Integer> integers = new ArrayList<>();
    private List<Double> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private ArgumentParser args;
    private ErrorHandler errorHandler; // Исправлено имя класса

    public WorkWithFiles(ArgumentParser args) {
        this.args = args;
        this.errorHandler = new ErrorHandler(); // Инициализация объекта
    }

    public void processFiles() {
        for (String file : args.getInputFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            } catch (IOException e) {
                errorHandler.handleError("Error reading file: " + file + " - " + e.getMessage()); // Используем errorHandler
            }
        }
    }

    public void processLine(String line) {
        try {
            integers.add(Integer.parseInt(line));
        } catch (NumberFormatException e1) {
            try {
                floats.add(Double.parseDouble(line));
            } catch (NumberFormatException e2) {
                strings.add(line);
            }
        }
    }

    public void writeToFiles() {
        writeListToFile(args.getPrefix() + "integers.txt", integers);
        writeListToFile(args.getPrefix() + "floats.txt", floats);
        writeListToFile(args.getPrefix() + "strings.txt", strings);
    }

    private <T> void writeListToFile(String fileName, List<T> list) {
        if (list.isEmpty()) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, args.isAppendMode()))) {
            for (T item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException ex) {
            errorHandler.handleError("Error writing to file: " + fileName + " - " + ex.getMessage());
        }
    }

    public List<Integer> getIntegers() { return integers; }
    public List<Double> getFloats() { return floats; }
    public List<String> getStrings() { return strings; }
}
