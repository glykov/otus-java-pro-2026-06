package ru.otus;

import com.google.common.collect.Ordering;

import java.util.List;

public class HelloOtus {

    public static void handeList(List<Integer> numbers) {
        Ordering<Integer> ordering = Ordering.natural();
        System.out.println("Input list:" + numbers);

        System.out.println("=========================");
        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
        System.out.println("Min: " + ordering.min(numbers));
        System.out.println("Max: " + ordering.max(numbers));

        numbers.sort(ordering.reversed());
        System.out.println("Reversed: " + numbers);
        System.out.println("=========================");
    }
}
