package zad_APS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;


class BNode<E> {

	public E info;
	public BNode<E> left;
	public BNode<E> right;

	static int LEFT = 1;
	static int RIGHT = 2;

	public BNode(E info) {
		this.info = info;
		left = null;
		right = null;
	}

	public BNode() {
		this.info = null;
		left = null;
		right = null;
	}

	public BNode(E info, BNode<E> left, BNode<E> right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "" + info;
	}
}

class BTree<E> {

	public BNode<E> root;

	public BTree() {
		root = null;
	}

	public BTree(E info) {
		root = new BNode<E>(info);
	}

	public void makeRoot(E elem) {
		root = new BNode(elem);
	}

	public void makeRootNode(BNode<E> node) {
		root = node;
	}

	public BNode<E> addChild(BNode<E> node, int where, E elem) {

		BNode<E> tmp = new BNode<E>(elem);

		if (where == BNode.LEFT) {
			if (node.left != null) // veke postoi element
				return null;
			node.left = tmp;
		} else {
			if (node.right != null) // veke postoi element
				return null;
			node.right = tmp;
		}

		return tmp;
	}

	public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

		if (where == BNode.LEFT) {
			if (node.left != null) // veke postoi element
				return null;
			node.left = tmp;
		} else {
			if (node.right != null) // veke postoi element
				return null;
			node.right = tmp;
		}

		return tmp;
	}

}

public class NodeDistance {

	public static BNode<String> najdi(BNode<String> root, String e) {
		BNode<String> result = null;
		if (root.left != null)
			result = najdi(root.left, e);

		if (root.info.equals(e))
			return root;
		if (result == null && root.right != null)
			result = najdi(root.right, e);

		return result;

	}

	public static BNode<String> LCA(BNode<String> root, BNode<String> p, BNode<String> q) {
		if (root == null) {
			return null;
		}

		// if either p or q is the root then root is LCA.
		if (root == p || root == q) {
			return root;
		} else {
			// get LCA of p and q in left subtree.
			BNode<String> l = LCA(root.left, p, q);

			// get LCA of p and q in right subtree.
			BNode<String> r = LCA(root.right, p, q);

			// if one of p or q is in leftsubtree and other is in right
			// then root it the LCA.
			if (l != null && r != null) {
				return root;
			}
			// else if l is not null, l is LCA.
			else if (l != null) {
				return l;
			} else {
				return r;
			}
		}
	}

	public static int findLevel(BNode<String> root, String k, int level) {
		if (root == null)
			return -1;
		if (root.info.equals(k))
			return level;
		int ll = findLevel(root.left, k, level + 2);
		if (ll != -1)
			return ll;
		return findLevel(root.right, k, level + 2);
	}

	public static int findDistance(BNode<String> root, String n1, String n2) {
		int d1 = Integer.MAX_VALUE, d2 = Integer.MAX_VALUE;
		d1 = findLevel(root, n1, 0);
		if (d1 == -1) {
			System.out.println("ERROR");
			return -1;
		}
		d2 = findLevel(root, n2, 0);
		if (d2 == -1) {
			System.out.println("ERROR");
			return -1;
		}
		BNode<String> k1 = najdi(root, n1);
		BNode<String> k2 = najdi(root, n2);
		BNode<String> lca = LCA(root, k1, k2);
		int dist = findLevel(root, lca.info, 0);
		return (d1 + d2 - 2 * dist);
	}

	public static void main(String[] args) throws Exception {
		int i, j, k;
		int index;
		String action;

		String line;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		BNode<String> nodes[] = new BNode[N];
		BTree<String> tree = new BTree<String>();

		for (i = 0; i < N; i++)
			nodes[i] = new BNode<String>();

		for (i = 0; i < N; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			index = Integer.parseInt(st.nextToken());
			nodes[index].info = st.nextToken();
			action = st.nextToken();
			if (action.equals("LEFT")) {
				tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
			} else if (action.equals("RIGHT")) {
				tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
			} else {
				// this node is the root
				tree.makeRootNode(nodes[index]);
			}
		}

		int cases = Integer.parseInt(br.readLine());
		for (int l = 0; l < cases; l++) {
			String split[] = br.readLine().split(" ");
			String from = split[0];
			String to = split[1];
			int d = findDistance(tree.root, from, to);
			if(d != -1)
			System.out.println(d);

		}
		br.close();

	}

}
