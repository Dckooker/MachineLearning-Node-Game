package cs228hw4.graph;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.iastate.cs228.game.GalaxyState;
import edu.iastate.cs228.game.SystemState;

public class GraphClass<V extends SystemState> implements DiGraph<V> {

	GalaxyState g;
	//SystemState[] sysState = g.getSystems();

	public GraphClass(GalaxyState g) {
		this.g = g;

		for (SystemState state : g.getSystems()) {
			addVertex(state);
			addEdges(state);
		}

	}
	
	public Map<SystemState, Vertex> Verts = new HashMap<SystemState, Vertex>();


	class Vertex {
		Map<SystemState, Integer> edges = new HashMap<SystemState, Integer>();

		Vertex(SystemState vertex) {
		}
	}

	public void addVertex(SystemState vertex) {
		
		Verts.put(vertex, new Vertex(vertex));
	}

	public void addEdges(SystemState vertex) {
		SystemState[] neighbors = vertex.getNeighbors();
		int[] costs = vertex.getTravelCost();

		for (int i = 0; i < neighbors.length; i++) {
			Verts.get(vertex).edges.put(neighbors[i], costs[i]);
		}

	}

	public SystemState neighborWithLeastCost(SystemState current) {
		SystemState[] neighbors = current.getNeighbors();
		int[] costs = current.getTravelCost();
		int min = costs[0];
		SystemState target = neighbors[0];
		for (int i = 0; i < costs.length; i++) {
			if (min > costs[i]) {
				min = costs[i];
				target = neighbors[i];
			}
		}
		return target;
	}
	
	public SystemState badNeighborWithLeastCost(SystemState current, ArrayList<SystemState> badNeighborsList) {
		ArrayList<SystemState> badNeighbors = badNeighborsList;
		int min = getEdgeCost2(current,badNeighbors.get(0));
		SystemState target = badNeighbors.get(0);
		for(SystemState system: badNeighbors) {
			int edgeCost = getEdgeCost2(current,system);
			if(getEdgeCost2(current,system) < min) {
				min = getEdgeCost2(current,system);
				target = system;
			}
		}
		return target;
	}

	private int getEdgeCost2(SystemState start, SystemState end) { 
		boolean hasStart = Verts.containsKey(start);
		int cost = Verts.get(start).edges.get(end);
		return cost;
	}
	
	public String getVerts() {
		String vertList = "";
			vertList += Verts.keySet();
		return vertList;
	}
	
	public int numVerts() {
		return Verts.keySet().size();
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Set<? extends V> getNeighbors(V vertex) {
		Set<V> neighbors = new HashSet<V>();
		if(Verts.containsKey(vertex)) {
			neighbors.addAll((Collection<? extends V>) Arrays.asList(vertex.getNeighbors()));
			
		}
		return neighbors;
	}

	@Override
	public int getEdgeCost(V start, V end) {
		int cost = Verts.get(start).edges.get(end);
		return cost;
	}

	@Override
	public int numVertices() {
		return Verts.size();
	}

	@Override
	public Iterator<V> iterator() {
		return null;
	}

}
