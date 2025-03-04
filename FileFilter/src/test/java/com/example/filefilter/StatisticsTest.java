package com.example.filefilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatisticsTest {
    private ArgumentParser parser;
    private WorkWithFiles workWithFiles;
    private Statistics stats;

    @BeforeEach
    void setUp() {
        String[] args = {"-s", "/Users/antondmitriev/IdeaProjects/FileFilter/src/test/resources/testFile.txt"};
        parser = new ArgumentParser(args);
        workWithFiles = new WorkWithFiles(parser);
        workWithFiles.processFiles();

        stats = new Statistics(workWithFiles);
    }

    @Test
    void testPrintStatistics(){
        stats.printStatistics();
    }

    @Test
    void testPrintFullStatistics(){
        stats.printFullStatistics();
    }
}