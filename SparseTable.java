    import java.util.*;
     
    
    static class SparseTable {
            int table[][];
            int size = 31;
            public SparseTable(int a[]) {
                table = new int[a.length][size];
                fill(a);
            }
            void fill(int[] a) {
                int n = a.length;
                for(int i=n-1;i>=0;i--) {
                    table[i][0] = a[i];
                    for(int j=1;j<size;j++) {
                        int next = i+(1<<(j-1));
                        table[i][j] = table[i][j-1] | ( next < n ? table[next][j-1] : 0);
                    }
                }
            }
            int getFloorLog(int x) {
                int count = 0;
                while((1<<count) <= x) {
                    count++;
                }
                return count-1;
            }
            int query(int l, int r) {
                int logsize = getFloorLog(r-l+1);
                return table[l][logsize] | table[r-(1<<logsize)+1][logsize];
            }
        }
       
