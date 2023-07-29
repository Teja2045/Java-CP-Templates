import java.util.*;
import java.io.*;

// -------------------------Main class-------------------------

public class Template
{


    static long MOD=1000000007;

    static long power(long x, long y, long p)
	{

		// Initialize result
		long res = 1;

		// Update x if it is more than or
		// equal to p
		x = x % p;

		while (y > 0) {

			// If y is odd, multiply x
			// with result
			if (y % 2 == 1)
				res = (res * x) % p;

			// y must be even now
			y = y >> 1; // y = y/2
			x = (x * x) % p;
		}

		return res;
	}

	// Returns n^(-1) mod p
	static long modInverse(long n, long p)
	{
		return power(n, p - 2, p);
	}

	// Returns nCr % p using Fermat's
	// little theorem.
	static long nCrModPFermat(long n, long r,
							long p,long[] fac)
	{

		if (n<r)
			return 0;
	// Base case
		if (r == 0)
			return 1;

		return (fac[(int)n] * modInverse(fac[(int)r], p)
				% p * modInverse(fac[(int)n -(int) r], p)
				% p)
			% p;
	}
    // -------------------------Main function-------------------------
    
    static FastReader sc;
    public static void main(String args[]) throws IOException
    {
        sc=new FastReader();
        PrintWriter pw=new PrintWriter(System.out);
        int t=sc.nextInt();
    
        for(int i=0;i<t;i++)
        {
            solve();
        }
        pw.flush();
        pw.close();
    }

    static void solve() {
        int n = sc.nextInt();
        System.out.println(n);
    }

    static int msb(int n)
    {
        if(n==0)
        return 0;
        int msb=0;
        n/=2;
        while(n!=0)
        {
            n/=2;
            msb++;
        }
        return msb;
    }

    static long func1(long j)
    {
        if(j==1)
        return 0;
        else
        {
            if(j%2==0)
            return func1(j/2)+1;
            else
            return func1(j/2);
        }
    }

    static long func2(long j)
    {
        if(j==1)
        return 1;
        else
        {
            if(j%2==0)
            return 2*func2(j/2)+1;
            else
            return 2*func2(j/2);
        }
    }

    static long highest_pow(long n)
    {
        if(n<1)
        return 0;
        long res=1;
        for(long j=0;j<8*Integer.BYTES;j++)
        {
            long curr=1<<j;
            if(curr>n)
            break;
            res=curr;
        }
        return res;
    }

    // -------------------------Other functions-------------------------

    // Time Complexity : log(n)
    static long fast_power_mod(long n, long mod)
    {
        long pow2=2;
        long res=1;
        while(n>0)
        {
            if(n%2==1)
            res=(res%mod*pow2%mod)%mod;
            pow2=(pow2%mod*pow2%mod)%mod;
            n>>=1;
        }
        return res;
    }

    // Time Complexity : n*r
    static long nCrModpDP(long n, long r, long p)
    {
        long c[]=new long[(int)r+1];
        c[0]=1;
        for(long j=1;j<=n;j++)
        {
            for(long k=Math.min(j,r);k>0;k--)
            c[(int)k]=(c[(int)k]%p+c[(int)k-1]%p)%p;
        }
        return c[(int)r];
    }

    // Time Complexity : log(n)
    static long nCrModpLucas(long n, long r, long p)
    {
        if(r==0)
        return 1;
        long ni=n%p;
        long ri=r%p;
        return (nCrModpLucas(n/p, r/p, p)%p*nCrModpDP(ni, ri, p)%p)%p;
    }

    // Time Complexity : log(mod)
    static long inverseMOD(long x, long mod) {
        if(mod==0)
        return 1;
        if(mod%2==1)
        return(x*inverseMOD(x, mod - 1))%MOD;
        else
        return inverseMOD((x * x) % MOD, mod/2);
    }

    // Time Complexity : log(max(a,b))
    static long bitwiseAND(long a, long b)
    {
        long shiftcount=0;
        while(a!=b&&a>0)
        {
            shiftcount++;
            a=a>>1;
            b=b>>1;
        }
        return (a<<shiftcount);
    }

    // Time Complexity : n*m
    static void dfs(int j, ArrayList<ArrayList<Integer>> al, boolean[] visited)
    {
        visited[j]=true;
        for(int x=0;x<al.get(j).size();x++)
        {
            if(!visited[al.get(j).get(x)])
            dfs(al.get(j).get(x),al,visited);
        }
    }

    // Time Complexity : log(n) for composite numbers, n for prime numbers
    static long getPrimeFactors(long n)
    {
        int x=2;
        long count=0;
        // ArrayList<Integer> al=new ArrayList<>();
        while(n>1)
        {
            if(n%x==0)
            {
                // if(!al.contains(x))
                // al.add(x);
                count++;
                n/=x;
            }
            else
            x++;
        }
        return count;
    }

    // Time Complexity : log(n)
    static ArrayList<Integer> primeFactorization(int x, int[] spf)
    {
        HashMap<Integer, Integer> map=new HashMap<>();
        ArrayList<Integer> al=new ArrayList<>();
        while(x!=1)
        {
            if(!al.contains(spf[x]))
            al.add(spf[x]);
            map.put(spf[x],map.getOrDefault(spf[x],0)+1);
            x/=spf[x];
        }
        return al;
        // return map;
    }

    // Time Complexity : n*10
    static int[][] getBitMap(int[] a)
    {
        int n=a.length;
        int[][] bit_map=new int[n][32];
        for(int j=0;j<n;j++)
        Arrays.fill(bit_map[j],0);
        for(int j=0;j<n;j++)
        {
            int counter=0;
            while(a[j]!=0)
            {
                bit_map[j][counter]=a[j]%2;
                a[j]/=2;
                counter++;
            }
        }
        return bit_map;
    }

    // Time Complexity : n*log(log(n))
    static ArrayList<Integer> sieveOfEratosthenes(int n)
    {
        boolean prime[]=new boolean[n+1];
        for(int j=0;j<=n;j++)
        prime[j]=true;
        for(long p=2;p*p<=n;p++)
        {
            if(prime[(int)p])
            {
                for(long j=p*p;j<=n;j+=p)
                prime[(int)j]=false;
            }
        }
        ArrayList<Integer> al=new ArrayList<>();
        for(long j=2;j<=n;j++)
        {
            if(prime[(int)j])
            al.add((int)j);
        }
        return al;
    }

    // Time Complexity : n
    static boolean sortedIncreasing(int[] a)
    {
        int f=0;
        for(int j=1;j<a.length;j++)
        {
            if(a[j]<a[j-1])
            f=1;
        }
        return f==0?true:false;
    }

    // Time Complexity : n
    static boolean sortedDecreasing(int[] a)
    {
        int f=0;
        for(int j=1;j<a.length;j++)
        {
            if(a[j]>a[j-1])
            f=1;
        }
        return f==0?true:false;
    }

    // Time Complexity : sqrt(n)
    static ArrayList<Long> getFactors(long n)
    {
        ArrayList<Long> al=new ArrayList<>();
        // int count=0;
        for(long i=1;i*i<=n;i++)
        {
            if(n%i==0)
            {
                al.add(i);
                // count++;
                if(n/i!=i)
                {
                    al.add(n/i);
                    // count++;
                }
            }
        }
        return al;
        // return count;
    }

    // Time Complexity : n*log(n)
    static void sort(int[] a)
    {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a)
        l.add(i);
		Collections.sort(l);
		for(int i=0;i<a.length;i++)
        a[i]=l.get(i);
    }

    // Time Complexity : n*log(n)
    static void sort(long[] a)
    {
		ArrayList<Long> l=new ArrayList<>();
		for (long i:a)
        l.add(i);
		Collections.sort(l);
		for(int i=0;i<a.length;i++)
        a[i]=l.get(i);
    }

    // Time Complexity : log(min(a,b))
    static long gcd(long a, long b) {
        return b==0 ? a: gcd(b, a%b);
    }
    

    // Time Complexity : log(min(a,b))
    static long lcm(long a, long b)
    {
        return ((a/gcd(a,b))*b);
    }

    // Time Complexity : log(n)
    static long floorSqrt(long x)
    {
        if(x==0||x==1)
        return x;
        long l=1;
        long r=(long)Math.sqrt(x)+1;
        long ans=0;
        while(l<=r)
        {
            long mid=l+(r-l)/2;
            long curr=mid*mid;
            if(curr==x)
            return mid;
            else if(curr>0&&curr<=x)
            {
                ans=mid;
                l=mid+1;
            }
            else
            r=mid-1;
        }
        return ans;
    }

    // Time Complexity : log(n*logn)
    static long getRemainderSum(long[] a, long totalsum, int x)
    {
        long k=0;
        outer :
        for(int mul=x;;mul+=x)
        {
            int l=0;
            int r=a.length-1;
            int index=-1;
            while(l<=r)
            {
                int mid=l+(r-l)/2;
                if(a[mid]>=mul)
                {
                    index=mid;
                    r=mid-1;
                }
                else
                l=mid+1;
            }
            if(index==-1)
            break outer;
            else
            k+=a.length-index;
        }
        return (totalsum-k*x);
    }

    // -------------------------Input class-------------------------

    static class FastReader
    {
        BufferedReader br; 
        StringTokenizer st;
        public FastReader()
        {
            br=new BufferedReader(new InputStreamReader(System.in)); 
        } 
        String next()
        {
            while(st==null||!st.hasMoreElements())
            {
                try
                {
                    st=new StringTokenizer(br.readLine());
                }
                catch(IOException  e)
                {
                    e.printStackTrace();
                }
            } 
            return st.nextToken(); 
        }
        int nextInt()
        {
            return Integer.parseInt(next());
        }
        long nextLong()
        {
            return Long.parseLong(next());
        }
        double nextDouble()
        {
            return Double.parseDouble(next());
        } 
        String nextLine()
        {
            String str = ""; 
            try
            {
                str = br.readLine();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            } 
            return str; 
        }
    }

}

// -------------------------Other classes-------------------------

class Pair
{
    int first=0;
    int second=0;
    char ch='a';
    int index=-1;
    Pair(int x, int y)
    {
        first=x;
        second=y;
    }
    Pair(char x, int y)
    {
        ch=x;
        index=y;
    }
}

class NewComparator implements Comparator<Pair>
{
    public int compare(Pair p1, Pair p2)
    {
        return Long.compare(p2.second, p1.second);
    }
}
