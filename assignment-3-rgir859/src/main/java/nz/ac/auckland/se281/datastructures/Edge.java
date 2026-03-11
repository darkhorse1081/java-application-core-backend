package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {

  private final T source;
  private final T destination;

  public Edge(T source, T destination) { // constructor
    this.source = source;
    this.destination = destination;
  } 

  public T getSource() {
    // source into node class -> node stores into set/hashmap
    // 
      return source;
    }
    
  public T getDestination() {
      return destination;
    }


}
