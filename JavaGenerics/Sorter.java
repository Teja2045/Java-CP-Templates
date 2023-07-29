package JavaGenerics;

public class Sorter<T extends Comparable<T>> {
    T[] objs;

    public Sorter(T[] objs) {
        this.objs = objs;
    }

    public void sort() {
        System.out.println("in sort");
        for (int i = 0; i < objs.length; i++) {
            for (int j = 1; j < objs.length; j++) {
                if (objs[j - 1].compareTo(objs[j]) > 0) {
                    T temp = objs[j - 1];
                    objs[j - 1] = objs[j];
                    objs[j] = temp;
                }
            }
        }

        for (int i = 0; i < objs.length; i++) {
            System.out.print(objs[i] + " ");
        }
    }

}
