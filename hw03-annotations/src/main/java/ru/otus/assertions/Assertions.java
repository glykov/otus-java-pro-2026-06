package ru.otus.assertions;

import ru.otus.exceptions.TestFailedException;

import java.util.Objects;

public class Assertions {
    public static boolean assertEquals(Object a, Object b) {
        if (!Objects.equals(a, b)) {
            throw new TestFailedException(a.getClass().getCanonicalName()
                + " is not equal to " + b.getClass().getCanonicalName());
        }
        return true;
    }

    public static boolean assertEquals(int a, int b) {
        if (a != b) {
            throw new TestFailedException(a + " is not equal to " + b);
        }
        return true;
    }

    public static boolean assertEquals(double a, double b) {
        if (Double.compare(a, b) != 0) {
            throw new TestFailedException(a + " is not equal to " + b);
        }
        return true;
    }

    public static boolean assertEquals(String a, String b) {
        if (!Objects.equals(a, b)) {
            throw new TestFailedException(a + " is not equal to " + b);
        }
        return true;
    }

    public static boolean assertNotNull(Object o) {
        if (Objects.isNull(o)) {
            throw new TestFailedException("Object is null");
        }
        return true;
    }
}
