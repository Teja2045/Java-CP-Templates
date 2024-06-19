package sparseTable;

public class SparseTable {
    int table[][];
    int table2[][];
    int size = 31;

    public SparseTable(int a[]) {
        table = new int[a.length][size];
        table2 = new int[a.length][size];
        fill(a);
        flipFill(a);
    }

    public void fill(int[] a) {
        int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            table[i][0] = a[i];
            for (int j = 1; j < size; j++) {
                int next = i + (1 << (j - 1));
                table[i][j] = table[i][j - 1] | (next < n ? table[next][j - 1] : 0);
            }
        }
    }

    public void flipFill(int[] a) {
        int n = a.length;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < n; i++) {
                if (j == 0) {
                    table2[i][j] = a[i];
                    continue;
                }
                int next = Math.min(i + (1 << (j - 1)), n-1);
                table[i][j] = table[i][j - 1] | table[next][j - 1];
            }
        }
    }

    private int getFloorLog(int x) {
        int count = 0;
        while ((1 << count) <= x) {
            count++;
        }
        return count - 1;
    }

    public int query(int l, int r) {
        int logsize = getFloorLog(r - l + 1);
        return table[l][logsize] | table[r - (1 << logsize) + 1][logsize];
    }

    boolean checkEquality() {
        int n = table.length, m = table[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (table[i][j] != table2[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a[] = new int[] { 1, 2, 3, 4 };
        SparseTable sp = new SparseTable(a);
        System.out.println(sp.checkEquality());

    }
}
