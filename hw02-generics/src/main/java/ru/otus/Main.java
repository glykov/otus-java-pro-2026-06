package ru.otus;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Run Tests");
        // проверка работоспособности методов в тестах
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> List<T> fromArray(T[] array) {
        // не совсем понял смысл задания, такие методы уже есть у библиотечных классов
        // return Arrays.asList(array);
        // return List.of(array);
        // или если нужне изменяемый список, то
        // return new ArrayList<>(List.of(array));

        // если совсем вручную это делать, то
        List<T> result = new ArrayList<>();
        for (T element : array) {
            result.add(element);
        }
        return result;
        // или через stream api
        // return Arrays.stream(array).toList();
    }

    public static void analyzeWords(List<String> words) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            Integer count = result.getOrDefault(word, 0);
            result.put(word, count + 1);
        }
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
