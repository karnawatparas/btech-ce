import java.lang.StringBuilder;

public class BinarySearchTree {

	private static TreeNode root;

	static class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public int getData() {
			return this.data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public TreeNode getLeft() {
			return this.left;
		}

		public TreeNode getRight() {
			return this.right;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public boolean hasLeftChild() {
			return this.left != null;
		}

		public boolean hasRightChild() {
			return this.right != null;
		}

		@Override
		public String toString() {
			return String.valueOf(this.data);
		}
	}

	public BinarySearchTree() {
		root = null;
	}

	public void insert(int value) {
		root = insert(root, value);
	}

	private TreeNode insert(TreeNode root, int value) {
		if (root == null) {
			root = new TreeNode(value);
			return root;
		} else {
			if (root.getData() < value) {
				root.setRight(insert(root.getRight(), value));
			} else if (root.getData() > value) {
				root.setLeft(insert(root.getLeft(), value));
			} else {
				System.err.println("No duplicate value allowed!");
			}
			return root;
		}
	}

	public boolean search(int value) {
		return search(root, value) != null;
	}

	private TreeNode search(TreeNode root, int value) {
		if (root == null) {
			return null;
		} else {
			if (root.getData() == value) {
				return root;
			} else if (root.getData() > value) {
				return search(root.getLeft(), value);
			} else {
				return search(root.getRight(), value);
			}
		}
	}

	public void delete(int value) {
		root = delete(root, value);
	}

	private TreeNode delete(TreeNode root, int value) {
		if (root == null)
			return root;

		if (value < root.getData())
			root.setLeft(delete(root.getLeft(), value));
		else if (value > root.getData())
			root.setRight(delete(root.getRight(), value));
		else {
			if (!root.hasLeftChild())
				return root.getRight();
			else if (!root.hasRightChild())
				return root.getLeft();

			TreeNode temp = root.getRight();
			int minValue = temp.getData();
			while (temp.hasLeftChild()) {
				temp = temp.getLeft();
				minValue = temp.getData();
			}

			root.setData(minValue);

			root.setRight(delete(temp, minValue));
		}

		return root;
	}

	public void inorder() {
		System.out.print("Inorder > ");
		this.inorder(root);
		System.out.println();
	}

	private void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.getLeft());
			System.out.print(root.getData() + " ");
			inorder(root.getRight());
		}
	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();

		System.out.println();

		bst.insert(50);
		bst.inorder();

		bst.insert(30);
		bst.inorder();

		bst.insert(60);
		bst.inorder();

		bst.insert(20);
		bst.insert(40);
		bst.inorder();

		bst.delete(20);
		bst.inorder();

		bst.delete(30);
		bst.inorder();

		bst.delete(50);
		bst.inorder();

		System.out.println("Found number 60 in tree? " + bst.search(60));
		System.out.println("Found number 20 in tree? " + bst.search(20));
		System.out.println();
	}

}
