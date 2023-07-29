package JavaGenerics;

public class Example {
    void print() {

    }

    public static void main(String[] args) {
        Sorter<Integer> sr = new Sorter<Integer>(new Integer[] { 1, 2, 3, 4, 5 });
        sr.sort();
    }
}
