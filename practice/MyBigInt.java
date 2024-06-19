package practice;

import java.math.BigInteger;

public class MyBigInt {
    public static void main(String[] args) {
        BigInteger dp = BigInteger.ONE;

        int nums[] = { 1, 2, 3, 5 };

        for (int i : nums) {
            BigInteger mask = BigInteger.ONE.shiftLeft(i).subtract(BigInteger.ONE);
            BigInteger allowed = mask.and(dp);
            System.out.println("allowed: "+allowed.bitCount());
            allowed = allowed.shiftLeft(i);

            dp = dp.or(allowed);
            System.out.println("dp: "+dp);
        }

        System.out.println(dp.bitLength());
    }
}
