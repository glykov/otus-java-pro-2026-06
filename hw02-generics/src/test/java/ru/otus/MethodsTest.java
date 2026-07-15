package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MethodsTest {

    private String[] stringArray;
    private Integer[] intArray;

    @BeforeEach
    public void setUp() {
        stringArray = new String[] {"apple", "banana", "cherry", "orange", "strawberry"};
        intArray = new Integer[] {1, 2, 3, 4, 5};
    }

    // test swap
    @Test
    void testSwap_ValidIndices() {
        String[] array = new String[]{"one", "two", "three", "four"};

        Main.swap(array, 1, 2);

        assertArrayEquals(new String[]{"one", "three", "two", "four"}, array);
    }

    @Test
    void testSwap_SameIndices() {
        Integer[] array = new Integer[]{1, 2, 3};

        Main.swap(array, 1, 1);

        assertArrayEquals(new Integer[]{1, 2, 3}, array);
    }

    @Test
    void testSwap_FirstAndLast() {
        String[] array = new String[]{"first", "second", "third", "last"};

        Main.swap(array, 0, array.length - 1);

        assertArrayEquals(new String[]{"last", "second", "third", "first"}, array);
    }

    // test fromArray
    @Test
    void testFromArray_stringArray() {
        List<String> list = Main.fromArray(stringArray);

        assertEquals(list.size(), stringArray.length);
        assertEquals(Arrays.asList(stringArray), list);
    }

    @Test
    void testFromArray_intArray() {
        List<Integer> list = Main.fromArray(intArray);

        assertEquals(list.size(), intArray.length);
        assertEquals(Arrays.asList(intArray), list);
    }

    @Test
    void testFromArray_emptyArray() {
        String[] empty = {};

        List<String> list = Main.fromArray(empty);

        assertTrue(list.isEmpty());
    }

    @Test
    void testFromArray_modifiableList() {
        List<String> list = Main.fromArray(stringArray);
        list.add("apricot");
        list.set(0, "plum");

        assertEquals(stringArray.length + 1, list.size());
        assertEquals("plum", list.getFirst());
        assertEquals("apricot", list.getLast());
    }

    // analyzeWords
    @Test
    void testAnalyzeWords_withDuplicates() {
        List<String> words = new ArrayList<>(List.of("apple", "banana", "cherry", "orange", "strawberry",
            "banana", "cherry", "apple", "apple", "orange"));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.analyzeWords(words);
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("banana: 2"));
        assertTrue(output.contains("orange: 2"));
        assertTrue(output.contains("apple: 3"));
        assertTrue(output.contains("cherry: 2"));
        assertTrue(output.contains("strawberry: 1"));
    }

    @Test
    void testAnalyzeWords_allDuplicates() {
        List<String> words = Arrays.asList("java", "java", "java", "java");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.analyzeWords(words);
        System.setOut(System.out);

        assertEquals("java: 4", outContent.toString().trim());
    }

    @Test
    void testAnalyzeWords_allUnique() {
        List<String> words = Arrays.asList("one", "two", "three");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.analyzeWords(words);
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("one: 1"));
        assertTrue(output.contains("two: 1"));
        assertTrue(output.contains("three: 1"));
    }
}
