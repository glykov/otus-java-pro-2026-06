package ru.otus.runner;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exceptions.TestFailedException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunner {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final List<Method> testMethods = new ArrayList<>();
    private static final Map<String, String> failedTests = new HashMap<>();
    private static int testsRun = 0;
    private static int testsFailed = 0;

    public static void run(Class<?> testing) {
        Method[] methods = testing.getDeclaredMethods();
        Method beforeMethod = null;
        Method afterMethod = null;

        for (Method m : methods) {
            if (m.getAnnotation(Before.class) != null) {
                beforeMethod = m;
            } else if (m.getAnnotation(After.class) != null) {
                afterMethod = m;
            } else if (m.getAnnotation(Test.class) != null) {
                testMethods.add(m);
            }
        }

        try {
            for (Method m : testMethods) {
                var out = testing.getConstructor().newInstance();
                try {
                    if (beforeMethod != null) {
                        beforeMethod.invoke(out);
                    }
                    testsRun++;
                    m.invoke(out);
                } catch (Exception e) {
                    if (e.getCause() instanceof TestFailedException tfe) {
                        failedTests.put(m.getName(), tfe.getMessage());
                        testsFailed++;
                    } else {
                        throw new RuntimeException(e);
                    }
                } finally {
                    if (afterMethod != null) {
                        afterMethod.invoke(out);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        showResults();
    }

    public static void showResults() {
        System.out.println("\n\nTotal of " + testsRun + " tests were run");
        System.out.println(ANSI_GREEN + "OK (" + (testsRun - testsFailed) + " tests)" + ANSI_RESET);
        if (testsFailed != 0) {
            System.out.println(ANSI_RED + "\n>>> " + testsFailed + " tests failed <<<" + ANSI_RESET);
            for (var entry : failedTests.entrySet()) {
                System.out.println(entry.getKey() + " failed because " + entry.getValue());
            }
        }
    }
}
