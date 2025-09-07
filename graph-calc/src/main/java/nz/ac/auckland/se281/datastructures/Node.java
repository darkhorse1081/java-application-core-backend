package nz.ac.auckland.se281.datastructures;

/**
 * A node in a graph that represents a vertex. The actual vertex is stored in the nodeObject field.
 * Attributes of the node are stored in the other fields.
 *
 * @param <T> The type of the vertex.
 */
public class Node<T>
    implements Comparable<Node<T>> { // implements comparable interface to compare nodeIds
  private final T nodeObject;
  private String nodeId;
  private int inDegreeCount;
  private int outDegreeCount;

  /**
   * Creates a new node. The node ID is set to the string representation of the node object.
   * Therefore, the node object must have a toString method that returns a unique string.
   *
   * @param nodeId The ID of the node. This is usually the string representation of the node.
   */
  public Node(T nodeId) { // constructor for node object
    this.nodeObject = nodeId;
    this.nodeId = nodeId.toString();
    this.inDegreeCount = 0;
    this.outDegreeCount = 0;
  }

  /**
   * Creates a new node.
   *
   * @param nodeId The ID of the node.
   */
  public String getNodeId() { // getter for nodeId of node
    return nodeId;
  }

  /**
   * Returns the node object of the node.
   *
   * @return The node object of the node.
   */
  public T getNodeObject() {
    return nodeObject;
  }

  /**
   * Returns the node object of the node.
   *
   * @return The node object of the node.
   */
  public T getNodeObject(String nodeId) { // getter for node object of node
    if (this.nodeId == nodeId) {
      return nodeObject;
    } else {
      return null;
    }
  }

  /**
   * Returns this node if the node object is the same as the parameter.
   *
   * @return The existing node if the node object is the same as the parameter.
   */
  public Node<T> getThisObject(T existingNode) {
    if (this.nodeObject == existingNode) {
      return this;
    } else {
      return null;
    }
  }

  /**
   * Returns the in-degree of the node.
   *
   * @return The in-degree of the node.
   */
  public int getInDegreeCount() {
    return inDegreeCount;
  }

  /**
   * Returns the out-degree of the node.
   *
   * @return The out-degree of the node.
   */
  public int getOutDegreeCount() {
    return outDegreeCount;
  }

  /**
   * Updates the in-degree or out-degree of the node.
   *
   * @return void. inDegreeCount is incremented by 1.
   */
  public void addInDegreeCount() {
    this.inDegreeCount++;
  }

  /**
   * Updates the out-degree of the node.
   *
   * @return void. outDegreeCount is incremented by 1.
   */
  public void addOutDegreeCount() {
    this.outDegreeCount++;
  }

  /**
   * Returns true if the node is reflexive.
   *
   * <p>A node is reflexive if the in-degree and out-degree are both greater than 0.
   *
   * @return True if the node is reflexive.
   */
  @Override
  public String toString() {
    return nodeId;
  }

  /**
   * Returns true if the node is reflexive.
   *
   * <p>A node is reflexive if the in-degree and out-degree are both greater than 0.
   *
   * @return True if the node is reflexive.
   */
  @Override
  public boolean equals(Object obj) { // override equals method to compare nodeIds

    if (this == obj) { // if nodeIds are equal return true
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) { // if nodeIds are not equal return false
      return false;
    }

    Node<T> other = (Node<T>) obj; // cast object to node object to compare nodeIds
    return nodeId == other.nodeId;
  }

  /**
   * Returns the hashcode of the node.
   *
   * @return The hashcode of the node.
   */
  @Override
  public int hashCode() {
    return nodeId.hashCode(); // return hashcode of nodeId
  }

  /**
   * Compares the node to another node.
   *
   * @param other The other node to compare to.
   * @return The result of the comparison.
   */
  @Override
  public int compareTo(Node<T> other) {
    int num1 = Integer.parseInt(nodeId);
    int num2 = Integer.parseInt(other.nodeId);
    return Integer.compare(num1, num2);
  }
}
