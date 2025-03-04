package com.example.filefilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class WorkWithFilesTest {

    private WorkWithFiles workWithFiles;
    private ArgumentParser parser;

    @BeforeEach
    void setUp() {
        String[] args = {"-p", "test_","/Users/antondmitriev/IdeaProjects/FileFilter/src/test/resources/testFile.txt"};
        parser = new ArgumentParser(args);
        workWithFiles = new WorkWithFiles(parser);
    }

    @Test
    void testProcessLine_Int(){
        workWithFiles.processLine("123");
        List<Integer> integers = workWithFiles.getIntegers();
        assertEquals(1, integers.size());
        assertEquals(123, integers.get(0));
    }

    @Test
    void testProcessLine_Float(){
        workWithFiles.processLine("9.3");
        List<Double> floats = workWithFiles.getFloats();
        assertEquals(1, floats.size());
        assertEquals(9.3, floats.get(0));
    }

    @Test
    void testProcessLine_Str(){
        workWithFiles.processLine("word");
        List<String> strings = workWithFiles.getStrings();
        assertEquals(1, strings.size());
        assertEquals("word", strings.get(0));
    }

    @Test
    void testProcessFiles() {
        workWithFiles.processFiles();

        List<Integer> integers = workWithFiles.getIntegers();
        List<Double> floats = workWithFiles.getFloats();
        List<String> strings = workWithFiles.getStrings();

        assertEquals(1, integers.size());
        assertEquals(1, floats.size());
        assertEquals(1, strings.size());
    }

    @Test
    void testWriteToFiles() throws IOException {
        workWithFiles.processFiles();
        workWithFiles.writeToFiles();
        String pref = parser.getPrefix();


        Path intFile = Paths.get(pref + "integers.txt");
        Path floatFile = Paths.get(pref + "floats.txt");
        Path stringFile = Paths.get(pref + "strings.txt");

        assertTrue(Files.exists(intFile));
        assertTrue(Files.exists(floatFile));
        assertTrue(Files.exists(stringFile));

        Files.deleteIfExists(intFile);
        Files.deleteIfExists(floatFile);
        Files.deleteIfExists(stringFile);
    }
}