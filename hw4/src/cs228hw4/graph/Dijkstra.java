package cs228hw4.graph;

import java.util.List;

/**
 * This class represents Dijkstra's algorithm for finding the shortest path
 * through a graph.  It works with a directed graph and implementations should
 * have a one-parameter constructor that accepts a reference to a DiGraph<V>.
 * In reality, the algorithm works on undirected graphs as well, but those are
 * just a specialization of DiGraph where each edge added is added in both
 * directions with the same cost each way.  Each instance of Dijkstra<V> should
 * be able to handle calculating the shortest path from any vertex in the given
 * graph to any other vertex, as well as the corresponding shortest "distances".
 * A client to this interface will construct the concrete implementation with 
 * the graph in question, then call run(V) to set the start vertex, then query
 * for shortest paths/distances from that start vertex.  The client can then
 * call run(V) again to set a new start node and repeat.
 * 
 * @author Justin Mason
 *
 * @param <V> the type of the objects being modeled as vertices in the graph
 *            this algorithm will work on
 */
public interface Dijkstra<V>
{
   //Constructor: public Dijkstra(DiGraph<V> graph);

   /**
    * Uses Dijkstra's shortest path algorithm to calculate and store the 
    * shortest paths to every vertex in the graph as well as the total costs
    * of each of those paths.  This should run in O(E log V) time, where E is
    * the size of the edge set, and V is the size of the vertex set.
    * 
    * @param start the vertex from which shortest paths should be calculated
    */
   void run(V start);

   /**
    * Retrieve, in O(V) time, the pre-calculated shortest path to the given 
    * node.
    * 
    * @param vertex the vertex to which the shortest path, from the start 
    *        vertex, is being requested
    * @return a list of vertices comprising the shortest path from the start 
    *         vertex to the given destination vertex, both inclusive
    */
   List<V> getShortestPath(V vertex);

   /**
    * Retrieve, in constant time, the total cost to reach the given vertex from
    * the start vertex via the shortest path.  If there is no path, this value
    * is Integer.MAX_VALUE.
    * 
    * @param vertex the vertex to which the cost of the shortest path, from the
    *        start vertex, is desired
    * @return the cost of the shortest path to the given vertex from the start
    *         vertex or Integer.MAX_VALUE if there is path
    */
   int getShortestDistance(V vertex);
}
