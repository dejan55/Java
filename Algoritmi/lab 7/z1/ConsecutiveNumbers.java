import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class BNode<E> {

	public E info;
	public BNode<E> left;
	public BNode<E> right;
	char ltag;
	char rtag;

	static int LEFT = 1;
	static int RIGHT = 2;

	public BNode(E info) {
		this.info = info;
		left = null;
		right = null;
		ltag = '-';
		rtag = '-';
	}

}

class BTree<E> {

	public BNode<E> head;

	public BTree() {
		head = new BNode<E>(null);
		// po definicija ako nema koren, t.e. ako drvoto e prazno
		head.left = head;
		head.ltag = '-';
		// kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
		head.right = head;
		head.rtag = '+';
	}

	public BNode<E> makeRoot(E elem) {
		BNode<E> tmp = new BNode<E>(elem);
		head.left = tmp;
		head.ltag = '+';

		tmp.left = head;
		tmp.ltag = '-';
		tmp.right = head;
		tmp.rtag = '-';

		return tmp;
	}

	public BNode<E> addChild(E elem, int where, BNode<E> node) {
		BNode<E> tmp = new BNode<E>(elem);

		if (where == BNode.LEFT) {

			if (node.ltag == '+') // veke postoi element
				return null;

			tmp.left = node.left;
			tmp.ltag = '-';
			tmp.right = node;
			tmp.rtag = '-';
			node.left = tmp;
			node.ltag = '+';
		} else {

			if (node.rtag == '+') // veke postoi element
				return null;

			tmp.right = node.right;
			tmp.rtag = '-';
			tmp.left = node;
			tmp.ltag = '-';
			node.right = tmp;
			node.rtag = '+';
		}

		return tmp;
	}

	public BNode<E> successorInorder(BNode<E> node) {
		if (node.rtag == '-') {
			return node.right;
		}
		BNode<E> p = node.right;
		while (p.ltag == '+') {
			p = p.left;
		}
		return p;
	}

	public void inorder() {
		System.out.print("INORDER: ");

		if (head.ltag == '+')
			inorderR(head.left);

		System.out.println();
	}

	void inorderR(BNode<E> n) {

		if (n.ltag == '+')
			inorderR(n.left);

		System.out.print(n.info.toString() + " ");

		if (n.rtag == '+')
			inorderR(n.right);

	}

}

public class ConsecutiveNumbers {

	public static void main(String[] args) throws IOException {
		int i, j, k;
		int index;
		String action;

		String line;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		BNode<Integer> nodes[] = new BNode[N];
		BTree<Integer> tree = new BTree<Integer>();

		for (i = 0; i < N; i++)
			nodes[i] = new BNode<Integer>(0);

		for (i = 0; i < N; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			index = Integer.parseInt(st.nextToken());
			nodes[index].info = Integer.parseInt(st.nextToken());
			action = st.nextToken();
			if (action.equals("LEFT")) {
				nodes[index] = tree.addChild(nodes[index].info, BNode.LEFT, nodes[Integer.parseInt(st.nextToken())]);
			} else if (action.equals("RIGHT")) {
				nodes[index] = tree.addChild(nodes[index].info, BNode.RIGHT, nodes[Integer.parseInt(st.nextToken())]);
			} else {
				// this node is the root
				nodes[index] = tree.makeRoot(nodes[index].info);
			}
		}
		BNode<Integer> tmp = tree.head;
		while (tmp.ltag == '+')
			tmp = tmp.left;
		boolean flag = true;
		while (tmp != tree.head) {
			if (tree.successorInorder(tmp)!=tree.head) {
				if (tree.successorInorder(tmp).info != tmp.info + 1) {
					flag = false;
					break;
				}
			}
			tmp = tree.successorInorder(tmp);
		}
		System.out.println(flag);

	}
}