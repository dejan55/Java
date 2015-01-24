package apsZad3L9;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

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
}

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
		if (adjList[x].containsNeighbor(adjList[y])) {
			adjList[x].updateNeighborWeight(adjList[y], tezina);
		} else
			adjList[x].addNeighbor(adjList[y], tezina);
	}

	void deleteEdge(int x, int y) {
		adjList[x].removeNeighbor(adjList[y]);
	}

	/*******************************************************************************************************/
	/***************** DIJKSTRA ******************************************************************************/

	float[] dijkstra(int from, int to) {

        int prv = from;
		/* Minimalna cena do sekoe od teminjata */
		float distance[] = new float[this.num_nodes];
		/* dali za temeto e najdena konecnata (minimalna) cena */
        int previous[] = new int[this.num_nodes];
		boolean finalno[] = new boolean[this.num_nodes];
		for (int i = 0; i < this.num_nodes; i++) {
			distance[i] = -1;
			finalno[i] = false;
            previous[i] = Integer.MIN_VALUE;
		}

		finalno[from] = true;
		distance[from] = 0;

		/*
		 * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
		 */
		for (int i = 1; i < this.num_nodes; i++) {
			/* za site sledbenici na from presmetaj ja cenata */
			Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors().iterator();
			while (it.hasNext()) {
				GraphNodeNeighbor<E> pom = it.next();
				/* ako grankata kon sosedot nema konecna cena */
				if (!finalno[pom.node.getIndex()]) {
					/* ako ne e presmetana cena za temeto */
					if (distance[pom.node.getIndex()] <= 0) {
						distance[pom.node.getIndex()] = distance[from]
								+ pom.weight;
                        previous[pom.node.getIndex()] = from;
					}
					/* inaku, ako e pronajdena poniska */
					else if (distance[from] + pom.weight < distance[pom.node
							.getIndex()]) {
						distance[pom.node.getIndex()] = distance[from]
								+ pom.weight;
                        previous[pom.node.getIndex()] = from;

					}
				}
			}

			/* najdi teme so min. cena koja ne e konecna */
			boolean minit = false; /* min. ne e inicijaliziran */
			int k = -1;
			float minc = -1;
			/* proveri gi site teminja */
			for (int j = 0; j < this.num_nodes; j++) {
				if (!finalno[j]&&distance[j] != -1) { /*ako cenata ne e  konecna*/
					if (!minit) { /* ako ne e inicijaliziran minimumot */
						minc = distance[k = j]; /* proglasi go ova e minimum */
						minit = true; /* oznaci deka min e inicijaliziran */
					}
					/* proveri dali e pronajdeno teme so pomala cena */
					else if (minc > distance[j]&&distance[j] > 0)
						minc = distance[k = j];
				}
			}
			
			finalno[from = k] = true;
		}
        Stack<E> S = new Stack<>();
        int u = to;
		while (previous[u] != Integer.MIN_VALUE) {
			S.push(adjList[u].getInfo());
			u = previous[u];
			
		}

        
        System.out.print(adjList[prv].getInfo() + " ");
		while (!S.isEmpty()) {
			System.out.print(S.pop() + " ");
		}


		return distance;

	}

	float min_path(int from, int to) {

		float[] odDo = dijkstra(from, to);
		System.out.println();
		float[] doOd = dijkstra(to, from);
		System.out.println();

		return odDo[to] + doOd[from];
	}

	/*******************************************************************************************************/

	@Override
	public String toString() {
		String ret = new String();
		for (int i = 0; i < this.num_nodes; i++)
			ret += adjList[i] + "\n";
		return ret;
	}

	public int searchIndex(String s) {
		for (int i = 0; i < adjList.length; i++) {
			Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
			while (it.hasNext()) {
				GraphNodeNeighbor<E> pom = it.next();
				if (pom.node.getInfo().equals(s)) {
					return pom.node.getIndex();
				}
			}
		}
		return -1;
	}

}

public class Cities {

	public static void main(String[] args) throws Exception{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n_nodes = Integer.parseInt(br.readLine());
		
		String grad[] = new String[n_nodes];
		Graph<String> g = new Graph<String>(n_nodes,grad);
		
		int n_edges = Integer.parseInt(br.readLine());
		
	    int x,y; float tezina;
	    String xInfo, yInfo, pom[];
	    for(int i=0;i<n_edges;i++)
	    {
	        pom = br.readLine().split(" ");
	        x = Integer.parseInt(pom[0]);
	        xInfo = pom[1];
	        y = Integer.parseInt(pom[2]);
	        yInfo = pom[3];
	        g.adjList[x].setInfo(xInfo);
	        g.adjList[y].setInfo(yInfo);
	        tezina = Float.parseFloat(pom[4]);
	        g.addEdge(x,y,tezina);                  
	    } 
	    
	    String start = br.readLine();
	    String end = br.readLine();
	    br.close();
        
        int from = g.searchIndex(start);
        int to = g.searchIndex(end);    
    	
		float min_p = g.min_path(from,to);
		System.out.println(min_p);
	}

}
