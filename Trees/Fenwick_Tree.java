package Trees;

public class Fenwick_Tree {

    int tree[];
    int n;

    public Fenwick_Tree(int []arr) {
        n = arr.length+1;
        tree = new int[n];
        buildTree(arr);
    }

    private void buildTree(int arr[]) {
        for(int i=0; i<arr.length; i++) {
            update(i, arr[i]);
        }
    }

    public void update(int index, int val) {
        index++;
        while(index < n) {
            tree[index] += val;
            index += (index & -index);
        }
    }

    public int query(int index) {
        index++;
        int sum = 0;
        while(index > 0) {
            sum += tree[index];
            index -= (index & -index);
        }
        return sum;
    }

    public int query(int l, int r) {
        return query(r) - query(l-1);
    }

    public void printTree() {
        for(int i: tree) System.out.print(i+" ");
        System.out.println();
    }


    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        Fenwick_Tree ft = new Fenwick_Tree(arr);
        ft.printTree();
        System.out.println(ft.query(0, 0));
        System.out.println(ft.query(0, 2));
        System.out.println(ft.query(0, 3));

        ft.update(2, 4);
        // {1. 2, 7, 4}

        System.out.println(ft.query(0, 3));        
    }
    
}
