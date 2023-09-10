package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class TestClass {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Map<Integer, List<Integer>> map;
    static int parent[][];
    static int dep[];

    static void dfs(int node, int par, int height) {
        parent[node][0] = par == -1 ? 0 : par;
        dep[node] = height;
        for (int i = 1; i < 31; i++) {
            parent[node][i] = parent[parent[node][i - 1]][i - 1];
        }
        for (int child : map.get(node)) {
            if (child != par)
                dfs(child, node, height + 1);
        }
    }

    static int lca(int x, int y) {
        if (dep[x] > dep[y])
            return lca(y, x);
        y = findNth(y, dep[y] - dep[x]);
        if (x == y) {
            return x;
        }
        for (int i = 30; i >= 0; i--) {
            if (parent[x][i] == parent[y][i])
                continue;
            x = parent[x][i];
            y = parent[y][i];
        }
        return parent[x][0];
    }

    static int findNth(int node, int ancestorOffset) {
        if (ancestorOffset <= 0)
            return node;
        for (int i = 0; i < 31; i++) {
            if ((ancestorOffset & 1 << i) == 0)
                continue;
            node = parent[node][i];
        }
        return node;
    }

    static boolean check(int x, int y, int z) {
        int lca = lca(x, y);
        if (dep[z] > Math.max(dep[x], dep[y]) || dep[z] < dep[lca])
            return false;
        x = findNth(x, dep[x] - dep[z]);
        y = findNth(y, dep[y] - dep[z]);
        if (x == z || y == z)
            return true;
        return false;
    }

    static boolean isSimple(int x, int y, int z) {
        return check(x, y, z) || check(y, z, x) || check(x, z, y);
    }

    static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static void solve() throws IOException {
        int n = nextInt();
        parent = new int[n][31];
        dep = new int[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            map.get(u).add(v);
            map.get(v).add(u);
        }
        dfs(0, -1, 0);

        int m = nextInt();

        for (int i = 0; i < m; i++) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int z = nextInt() - 1;
            bw.write(isSimple(x, y, z) ? "Yes\n" : "No\n");
        }
    }

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = nextInt();
        while (t-- > 0) {
            solve();
        }
        bw.flush();
        bw.close();
    }
}
