package com.example.filefilter;

public class FileFilterUtility {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        WorkWithFiles dataProcessor = new WorkWithFiles(argumentParser);

        dataProcessor.processFiles();
        dataProcessor.writeToFiles();

        Statistics stats = new Statistics(dataProcessor);
        if (argumentParser.isStats()) stats.printStatistics();
        if (argumentParser.isFullStats()) stats.printFullStatistics();
    }
}