package cs228hw4.graph;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents a directed graph with edge costs.  Vertices cannot be null.
 * Do not alter the structure of the graph while iterating, or the behavior
 * is not well-defined.  Similarly, behavior is undefined if the objects
 * of type V that comprise the set of vertices of this graph are mutable
 * and are changed after being added to the graph.  All methods run in
 * constant time.
 * 
 * @author Justin Mason
 *
 * @param <V> the type of the objects being modeled as vertices in the graph
 */
public interface DiGraph<V> extends Iterable<V>
{
   /**
    * Retrieves the collection of vertices that terminate an edge for which
    * the given vertex is the initial vertex.
    * 
    * @param vertex the vertex for which the neighbors are desired
    * @return a Collection of the neighbors of the given vertex
    * @throws IllegalArgumentException if the given vertex does not exist in
    *         the graph or is null
    */
   Set<? extends V> getNeighbors(V vertex);
   
   /**
    * Retrieves the cost of the edge between the given vertices.
    * 
    * @param start the initial vertex of the edge
    * @param end the terminal vertex of the edge
    * @return the cost of this edge
    * @throws IllegalArgumentException if either of the given nodes does not
    *         exist or is null or they do exist but there is no edge between
    *         them
    */
   int getEdgeCost(V start, V end);
   
   /**
    * Get the number of vertices in this graph.
    * 
    * @return the number of vertices in this graph
    */
   int numVertices();
   
   /**
    * Returns an iterator over the vertices in this graph.  This iterator
    * will not allow modification of the graph.
    * 
    * @see java.lang.Iterable#iterator()
    */
   Iterator<V> iterator();
}
