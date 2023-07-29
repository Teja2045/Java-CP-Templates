package JavaGenerics;

// extra example
class E<T> {
    T t;

    public E(T t) {
        this.t = t;
    }
}

public class Example {
    <T> void print(T t) {
    }

    public static void main(String[] args) {
        Sorter<Integer> sr = new Sorter<Integer>(new Integer[] { 1, 2, 3, 4, 5 });
        sr.sort();

        // extra example
        E<int[]> e = new E<int[]>(new int[] { 1, 2, 3 });
        e.t[0] = 10;
    }
}
