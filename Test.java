public class Test {
    
    static int power(long a, long b, long mod) {
        long ans = 1;
        while(b > 0) {
            if((b&1)==1) ans *= a;
            ans %= mod;
            b >>= 1;
            a *= a;
            a %= mod;
        }
        return (int)ans;
    }
    public static void main(String[] args) {
        int ans = power(9, 29, 31);
        System.out.println(ans);
    }
    // if  a ^ p-1 mod p = 1 mod p;
    // a * a ^ p - 2 mod p = 1 mod p
    // so 1 / a mod p = a ^ p - 2 mod p
}
