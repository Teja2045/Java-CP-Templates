package streams;

import java.util.Arrays;
import java.util.List;

public class StreamsExample {
    public static void main(String[] args) {

        List<Integer> arrList = Arrays.asList(1, 2, 4);
        int sum = arrList.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> 100 * num)
                .reduce(0, (accumulator, value) -> accumulator + value);

        System.out.println(sum);
    }
}
