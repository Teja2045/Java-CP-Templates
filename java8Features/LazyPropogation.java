
import java.util.*;

public class LazyPropogation {

    static int a[];
    static int tree[][];
    static int lazy[];

    static void buildTree(int node, int start, int end) {
        if (start == end) {
            for(int i=0;i<32;i++) {
                tree[node][i] += (a[start]&(1<<i))>0 ? 1 : 0;
            }
        } else {
            int mid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            buildTree(leftChild, start, mid);
            buildTree(rightChild, mid + 1, end);
            for (int i = 0; i < 32; i++) {
                tree[node][i] = tree[leftChild][i] + tree[rightChild][i];
            }
        }
    }
    static void updateLazy(int node, int start, int end, int rangeStart, int rangeEnd, int value) {
        if (lazy[node] != 0) {
            int laze = lazy[node];
            for(int i=0;i<32;i++) {
                if(((1<<i)&laze)>0) {
                    tree[node][i] = end-start+1 - tree[node][i];
                }
            }
            if (start != end) {
                lazy[2 * node + 1] ^= laze;
                lazy[2 * node + 2] ^= laze;
            }
            lazy[node] = 0;
        }
        if (start > rangeEnd || end < rangeStart) {
            return;
        }
        if (start >= rangeStart && end <= rangeEnd) {
            int laze = value;
            for(int i=0;i<32;i++) {
                if(((1<<i)&laze)>0) {
                    tree[node][i] = end-start+1 - tree[node][i];
                }
            }
            if (start != end) {
                lazy[2 * node + 1] ^= laze;
                lazy[2 * node + 2] ^= laze;
            }
            return;
        }
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        updateLazy(2 * node + 1, start, mid, rangeStart, rangeEnd, value);
        updateLazy(2 * node + 2, mid + 1, end, rangeStart, rangeEnd, value);
        for (int i = 0; i < 32; i++) {
                tree[node][i] = tree[leftChild][i] + tree[rightChild][i];
        }
    }
    static int[] queryTree(int node, int start, int end, int rangeStart, int rangeEnd) {
       if (lazy[node] != 0) {
            int laze = lazy[node];
            for(int i=0;i<32;i++) {
                if(((1<<i)&laze)>0) {
                    tree[node][i] = end-start+1 - tree[node][i];
                }
            }
            if (start != end) {
                lazy[2 * node + 1] ^= laze;
                lazy[2 * node + 2] ^= laze;
            }
            lazy[node] = 0;
        }
        if (start > rangeEnd || end < rangeStart) {
            return new int[32];
        }
        if (start >= rangeStart && end <= rangeEnd) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftSum[] = queryTree(2 * node + 1, start, mid, rangeStart, rangeEnd);
        int rightSum[] = queryTree(2 * node + 2, mid + 1, end, rangeStart, rangeEnd);
        int[]result = new int[32];
        for(int i=0;i<32;i++) {
            result[i] = leftSum[i] + rightSum[i];
        }
        return result;
    }
    static int[] binaryQueries(int n, int[] p, int q, int[][] queries) {
        a = p;
        //for(int i=0;i<n;i++) System.out.print("a"+a[i]+"|");
        tree = new int[4*n][32];
        lazy = new int[4*n];
        buildTree(0,0,n-1);
       // for(int temp[]:tree) for(int i: temp) System.out.print(i+"!");
        int res[] = new int[q];
        int x = 0;
        for(int que[] : queries) {
            int l = que[0];
            int r = que[1];
            int value = que[2];
            updateLazy(0, 0, n-1, l, r, value);
            int cur[] = queryTree(0, 0, n-1, l, r);
        	for(int i:cur) System.out.print(i+"|"); System.out.println();
            int ans = 0;
            for(int i=0;i<32;i++) {
                if(cur[i] > 0) ans = (ans | (1<<i));
            }
            res[x++] = ans;
        }
//        for(int temp[]: tree) {
//        	for(int i:temp) System.out.print(i+"|"); System.out.println();
//        }
        return res;
    }
    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);

        List<List<Integer>> permutations = generatePermutations(elements);
        System.out.println("Permutations: " + permutations);
        int res = 1;
        for(int i=1;i<=13;i++) res *= i;
        System.out.println(res);
    }

    public static List<List<Integer>> generatePermutations(List<Integer> elements) {
        List<List<Integer>> permutations = new ArrayList<>();

        if (elements.size() == 0) {
            permutations.add(new ArrayList<>());
            return permutations;
        }

        Integer firstElement = elements.remove(0);
        List<List<Integer>> subPermutations = generatePermutations(elements);

        for (List<Integer> subPermutation : subPermutations) {
            for (int i = 0; i <= subPermutation.size(); i++) {
                List<Integer> permutation = new ArrayList<>(subPermutation);
                permutation.add(i, firstElement);
                permutations.add(permutation);
            }
        }

        return permutations;
    }
    
}
