package apsZad2L9;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class GraphNodeNeighbor<E> {
	GraphNode<E> node;
	float weight;

	public GraphNodeNeighbor(GraphNode<E> node, float weight) {
		this.node = node;
		this.weight = weight;
	}

	public GraphNodeNeighbor(GraphNode<E> node) {
		this.node = node;
		this.weight = 0;
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>) obj;
		return pom.node.equals(this.node);
	}

}

class Edge {
	private int fromVertex, toVertex;
	private float weight;

	public Edge(int from, int to, float weight) {
		this.fromVertex = from;
		this.toVertex = to;
		this.weight = weight;
	}

	public int getFrom() {
		return this.fromVertex;
	}

	public int getTo() {
		return this.toVertex;
	}

	public float getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return "(" + fromVertex + "," + toVertex + ")=" + weight + " ";
	}

}

class GraphNode<E> {
	private int index; // index (reden broj) na temeto vo grafot
	private E info;
	private LinkedList<GraphNodeNeighbor<E>> neighbors;

	public GraphNode(int index, E info) {
		this.index = index;
		this.info = info;
		neighbors = new LinkedList<GraphNodeNeighbor<E>>();
	}

	public boolean containsNeighbor(GraphNode<E> o) {
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, 0);
		return neighbors.contains(pom);
	}

	public void addNeighbor(GraphNode<E> o, float weight) {
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, weight);
		neighbors.add(pom);
	}

	public void removeNeighbor(GraphNode<E> o) {
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, 0);
		if (neighbors.contains(pom))
			neighbors.remove(pom);
	}

	@Override
	public String toString() {
		String ret = "INFO:" + info + " SOSEDI:";
		for (int i = 0; i < neighbors.size(); i++)
			ret += "(" + neighbors.get(i).node.info + "," + neighbors.get(i).weight + ") ";
		return ret;

	}

	public void updateNeighborWeight(GraphNode<E> o, float weight) {
		Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
		while (i.hasNext()) {
			GraphNodeNeighbor<E> pom = i.next();
			if (pom.node.equals(o))
				pom.weight = weight;
		}

	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNode<E> pom = (GraphNode<E>) obj;
		return (pom.info.equals(this.info));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
		this.neighbors = neighbors;
	}

}

class Graph<E> {

	int num_nodes;
	GraphNode<E> adjList[];

	@SuppressWarnings("unchecked")
	public Graph(int num_nodes, E[] list) {
		this.num_nodes = num_nodes;
		adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			adjList[i] = new GraphNode<E>(i, list[i]);
	}

	@SuppressWarnings("unchecked")
	public Graph(int num_nodes) {
		this.num_nodes = num_nodes;
		adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			adjList[i] = new GraphNode<E>(i, null);
	}

	int adjacent(int x, int y) {
		// proveruva dali ima vrska od jazelot so
		// indeks x do jazelot so indeks y
		return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
	}

	void addEdge(int x, int y, float tezina) {
		// dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
		// i obratno
		if (adjList[x].containsNeighbor(adjList[y])) {
			adjList[x].updateNeighborWeight(adjList[y], tezina);
			adjList[y].updateNeighborWeight(adjList[x], tezina);
		} else {
			adjList[x].addNeighbor(adjList[y], tezina);
			adjList[y].addNeighbor(adjList[x], tezina);
		}
	}

	void deleteEdge(int x, int y) {
		adjList[x].removeNeighbor(adjList[y]);
		adjList[y].removeNeighbor(adjList[x]);
	}

	/*******************************************************************************************************/
	/************************ PRIM **************************************************************************/

	// Metoda koja go naogja najmaloto rebro do
	// teme na neposeten sosed
	private Edge getMinimalEdge(boolean[] included) {
		int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
		float minweight = Float.MAX_VALUE;

		for (int i = 0; i < this.num_nodes; i++) {
			if (included[i]) {
				// ako e vkluceno temeto i
				// izmini gi negovite nevkluceni sosedi
				Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
				while (it.hasNext()) {
					GraphNodeNeighbor<E> pom = it.next();
					// ako sosedot ne e poseten i ima do sega najmala tezina
					if (!included[pom.node.getIndex()] && pom.weight < minweight) {
						index1 = i;
						index2 = pom.node.getIndex();
						minweight = pom.weight;
					}
				}
			}
		}

		if (minweight < Float.MAX_VALUE) {
			Edge ret = new Edge(index1, index2, minweight);
			return ret;
		}
		return null;
	}

	int prim(int start_index) {
		// lista koja kje gi sodrzi MST rebra;
		int rez = 0;

		boolean included[] = new boolean[this.num_nodes];
		for (int i = 0; i < this.num_nodes; i++)
			included[i] = false;

		included[start_index] = true;

		for (int i = 0; i < this.num_nodes - 1; i++) {
			Edge e = this.getMinimalEdge(included);
			if (e == null) {
				System.out.println("Ne mozat da se povrzat site jazli");
				break;
			}
			included[e.getFrom()] = included[e.getTo()] = true;
			rez+=e.getWeight();
		}

		return rez;
	}

	public int searchIndex(String sef) {
		for (int i = 0; i < adjList.length; i++) {
			Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
			while(it.hasNext()){
				GraphNodeNeighbor<E> pom = it.next();
				if(pom.node.getInfo().equals(sef)){
					return pom.node.getIndex();
				}
			}
		}
		return -1;
	}
}

public class Hierarchy {

	public static void main(String[] args) {
		Scanner br = new Scanner(System.in);
		int n_nodes = Integer.parseInt(br.nextLine());

		String clen[] = new String[n_nodes];
		Graph<String> g = new Graph<String>(n_nodes, clen);

		int n_edges = Integer.parseInt(br.nextLine());

		int x, y, tezina;
		String xInfo, yInfo, pom[];
		for (int i = 0; i < n_edges; i++) {
			pom = br.nextLine().split(" ");
			x = Integer.parseInt(pom[0]);
			xInfo = pom[1];
			y = Integer.parseInt(pom[2]);
			yInfo = pom[3];
			g.adjList[x].setInfo(xInfo);
			g.adjList[y].setInfo(yInfo);
			tezina = Integer.parseInt(pom[4]);
			g.addEdge(x, y, tezina);
			g.addEdge(y, x, tezina);
		}

		String sef = br.nextLine();
		br.close();

		System.out.println(g.prim(g.searchIndex(sef)));

	}
}