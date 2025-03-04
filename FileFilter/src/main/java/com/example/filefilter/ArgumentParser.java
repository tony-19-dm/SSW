package com.example.filefilter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    private String outputPath = null;
    private String prefix = null;
    private boolean appendMode = false;
    private boolean stats = false;
    private boolean fullStats = false;
    private List<String> inputFiles = new ArrayList<>();
    private ErrorHandler errorHandler = new ErrorHandler();

    public ArgumentParser(String[] args) {
        parseArguments(args);
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 >= args.length) {
                        errorHandler.handleError("Missing value for -o (output path).");
                        return;
                    }
                    outputPath = args[++i];
                    break;
                case "-p":
                    if (i + 1 >= args.length) {
                        errorHandler.handleError("Missing value for -p (prefix).");
                        return;
                    }
                    prefix = args[++i];
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    stats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                default:
                    if (args[i].startsWith("-")) { // Проверка неизвестного флага
                        errorHandler.handleError("Unknown flag: " + args[i]);
                    } else {
                        inputFiles.add(args[i]);
                    }
                    break;
            }
        }
    }

    public String getOutputPath() { return outputPath; }
    public String getPrefix() { return prefix != null ? prefix : ""; }
    public boolean isAppendMode() { return appendMode; }
    public boolean isStats() { return stats; }
    public boolean isFullStats() { return fullStats; }
    public List<String> getInputFiles() { return inputFiles; }
}
