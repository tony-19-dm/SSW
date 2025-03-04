package com.example.filefilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ArgumentParserTest {
    @Test
    void testOutputPath() {
        String[] args = {"-o", "outputDir"};
        ArgumentParser parser = new ArgumentParser(args);
        assertEquals("outputDir", parser.getOutputPath());
    }

    @Test
    void testPrefix() {
        String[] args = {"-p", "test_"};
        ArgumentParser parser = new ArgumentParser(args);
        assertEquals("test_", parser.getPrefix());
    }

    @Test
    void testAppendMode() {
        String[] args = {"-a"};
        ArgumentParser parser = new ArgumentParser(args);
        assertTrue(parser.isAppendMode());
    }

    @Test
    void testStats() {
        String[] args = {"-s"};
        ArgumentParser parser = new ArgumentParser(args);
        assertTrue(parser.isStats());
    }

    @Test
    void testFullStats() {
        String[] args = {"-f"};
        ArgumentParser parser = new ArgumentParser(args);
        assertTrue(parser.isFullStats());
    }

    @Test
    void testInputFiles() {
        String[] args = {"file1.txt", "file2.txt"};
        ArgumentParser parser = new ArgumentParser(args);
        assertEquals(2, parser.getInputFiles().size());
        assertTrue(parser.getInputFiles().contains("file1.txt"));
        assertTrue(parser.getInputFiles().contains("file2.txt"));
    }
}