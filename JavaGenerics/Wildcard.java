package JavaGenerics;

import java.util.ArrayList;
import java.util.List;

class Test {
    String identifier;

    public Test(String identifier) {
        this.identifier = identifier;
    }

    public void test() {
        System.out.println("testing " + identifier + " ...");
    }

    @Override
    public String toString() {
        return "toString = " + identifier;
    }
}

class X_Test extends Test {
    String identifier = "check";

    X_Test(String identifier) {
        super(identifier);
    }

    @Override
    public void test() {
        System.out.println(super.identifier);
    }
}

class XX_Test extends X_Test {
    XX_Test(String identifier) {
        super(identifier);
    }

    public void test() {
        System.out.println(super.toString());
    }

    @Override
    public String toString() {
        return "XX toString = " + identifier;
    }
}

public class Wildcard<T extends Test & Iterable<T> & Comparable<T>> {
    public static void main(String[] args) {
        X_Test test = new XX_Test("cc");
        test.test();
        System.out.println(test);
        List<X_Test> tests = new ArrayList<>();
        tests.add(test);

        genericsCheck(tests);
    }

    public static <T> String genericsCheck(List<T> li) {
        return li.toString();
    }
}
