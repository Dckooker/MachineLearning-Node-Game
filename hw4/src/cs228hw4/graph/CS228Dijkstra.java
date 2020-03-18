/**
 * @author Devon Kooker
 */
package cs228hw4.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class CS228Dijkstra<V> implements Dijkstra<V> {

	private DiGraph<V> graph;
	private Vertex startVertex;
	private Map<V, Vertex> verts;
	private Map<V, Double> distances;
	private ArrayList<V> availableVerts;

	public CS228Dijkstra(DiGraph<V> graph) {
		this.graph = graph;
	}
	
	public String getVerts() {
		return "" + verts.keySet();
	}

	@Override
	public void run(V start) {

		startVertex = new Vertex(start);
		availableVerts = new ArrayList<V>();

		verts = new HashMap<V, Vertex>();
		distances = new HashMap<V, Double>();

		startVertex.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(startVertex);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (V neighbor : u.neighbors) {
				Vertex v;
				if (verts.containsKey(neighbor)) {
					v = verts.get(neighbor);
				} else {
					v = new Vertex(neighbor);
				}
				double weight = (double)graph.getEdgeCost(u.getVertex(), v.getVertex());
				double distanceThroughU = (double)u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
					verts.put(neighbor, v);
					distances.put(neighbor, weight);
				}
			}
		}

	}

	@Override
	public List<V> getShortestPath(V vertex) {
		V end = vertex;
		Vertex target = verts.get(vertex);
		ArrayList<V> path = new ArrayList<V>();
		for (Vertex v = target; v != null; v = v.previous) {
			path.add(v.getVertex());
		}

		Collections.reverse(path);
		return path;
	}

	@Override
	public int getShortestDistance(V vertex) {
		Double shortestDistance = 0.0;
		for (V pathItem : getShortestPath(vertex)) {
			if (distances.get(pathItem) != null) {
				shortestDistance += distances.get(pathItem);
			}
		}
		return shortestDistance.intValue();
	}

	// vertex sub class
	class Vertex implements Comparable<Vertex> {
		public V vert;

		public double minDistance = Double.POSITIVE_INFINITY;
		public Vertex previous;
		Set<V> neighbors;

		public Vertex(V vert) {
			this.vert = vert;
			neighbors = new HashSet<V>();
			Iterator<? extends V> iter = graph.getNeighbors(vert).iterator();
			Set<? extends V> neighborsFinal = graph.getNeighbors(vert);
			for (int i = 0; i < graph.getNeighbors(vert).size(); i++) {
				
				neighbors.add(iter.next());
				
				
			}
		}

		public V getVertex() {
			return vert;
		}

		public int compareTo(Vertex other) {
			return Double.compare(minDistance, other.minDistance);
		}
	}

}
