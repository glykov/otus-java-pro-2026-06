package ru.otus;

import ru.otus.runner.TestRunner;
import ru.otus.tests.Testing;

public class Application {
    public static void main(String[] args) {
        TestRunner.run(Testing.class);
    }
}
