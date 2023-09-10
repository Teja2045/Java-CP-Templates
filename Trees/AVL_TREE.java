package Trees;

class TreeNode {
    int val;
    int depth;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        depth = 1;
        left = null;
        right = null;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        depth = 1;
        this.left = left;
        this.right = right;
    }

    int getBalance() {
        int leftDepth = this.left == null ? 0 : this.left.depth;
        int rightDepth = this.right == null ? 0 : this.right.depth;
        return rightDepth - leftDepth;
    }

    void calculateDepth() {
        int leftDepth = this.left == null ? 0 : this.left.depth;
        int rightDepth = this.right == null ? 0 : this.right.depth;
        this.depth = Math.max(leftDepth, rightDepth) + 1;
    }

}

public class AVL_TREE {

    TreeNode root;

    public AVL_TREE() {
        this.root = null;
    }

    TreeNode rotateLeft(TreeNode node) {
        TreeNode right = node.right;
        TreeNode temp = right.left;
        right.left = node;
        node.right = temp;
        return right;
    }

    TreeNode rotateRight(TreeNode node) {
        TreeNode left = node.left;
        TreeNode temp = left.right;
        left.right = node;
        node.left = temp;
        return left;
    }

    void insertIntoAVL(int val) {
        this.root = insert(val, root);
    }

    void preOrderAVL() {
        preOrder(root);
    }

    void preOrder(TreeNode node) {
        if (node == null) {
            System.out.print("null" + " ");
            return;
        }
        System.out.print(node.val + "|" + node.depth + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    TreeNode insert(int val, TreeNode node) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insert(val, node.left);

        } else {
            node.right = insert(val, node.right);
        }
        int balance = node.getBalance();
        if (balance > 1 && val > node.right.val) {
            node = rotateLeft(node);
        }
        if (balance > 1 && val < node.right.val) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        if (balance < -1 && val < node.left.val) {
            node = rotateRight(node);
        }
        if (balance < -1 && val > node.right.val) {
            node.left = rotateLeft(node);
            node = rotateRight(node);
        }
        if (node.left != null)
            node.left.calculateDepth();
        if (node.right != null)
            node.right.calculateDepth();

        node.calculateDepth();
        return node;
    }

    public static void main(String[] args) {
        AVL_TREE tree = new AVL_TREE();
        for (int i = 1; i <= 10; i++) {
            tree.insertIntoAVL(i);
        }
        tree.preOrderAVL();
    }
}
