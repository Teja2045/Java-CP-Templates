package practice;
import java.util.*;

public class MyBitset {
    public static void main(String[] args) {
        int max = 100000;
        BitSet dp = new BitSet(max), mask = new BitSet(max);
        int index = 0;
        int [] arr = {1, 2, 3, 4};

        for(int num : arr) {
            while(index < num) mask.set(index++);
            // stopping here since the bitset here is not good enough to solve the problem:
            // 3181. Maximum Total Reward Using Operations II
            dp.and(mask);
        }
    }
}
