package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  private Node<T> edgeSource;
  private Node<T> edgeDestination;

  public Edge(T source, T destination) { // constructor for edge
    this.edgeSource = new Node<T>(source);
    this.edgeDestination = new Node<T>(destination);
  }

  /** Returns the source of the edge. */
  public Node<T> getSource() { // getter for source
    return this.edgeSource;
  }

  /** Returns the destination of the edge. */
  public Node<T> getDestination() { // getter for destination
    return this.edgeDestination;
  }

  /**
   * Returns true if the edge is reflexive.
   *
   * <p>An edge is reflexive if the source and destination are the same.
   *
   * @return True if the edge is reflexive.
   * @return False if the edge is not reflexive.
   * @return The source of the edge.
   * @return The destination of the edge.
   */
  @Override
  public boolean equals(Object obj) {

    if (obj == null || getClass() != obj.getClass()) { // check if object is null or not
      return false;
    }
    if (this == obj) {
      return true;
    }

    Edge<T> other = (Edge<T>) obj; // check if source and destination of each edge are equal
    return (edgeSource.getNodeId() == other.getSource().getNodeId()
        && edgeDestination.getNodeId() == other.getDestination().getNodeId());
  }

  /**
   * Returns the hash code of the edge.
   *
   * <p>You must use the same hash function as the one in the equals method above.
   *
   * @return The hash code of the edge.
   */
  @Override
  public int hashCode() {
    return (edgeSource.getNodeId().hashCode() + edgeDestination.getNodeId().hashCode());
  }
}
