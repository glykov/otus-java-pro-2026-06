package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i < 20; i++) {
            numbers.add(random.nextInt(100));
        }

        HelloOtus.handeList(numbers);
    }
}
