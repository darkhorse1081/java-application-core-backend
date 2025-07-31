package nz.ac.auckland.se281.datastructures;

/**
 * A node item that is composed of a value and a pointer to the next node item.
 *
 * @param <T> The type of each node item.
 * @return The value of the node item.
 * @return The next node item.
 */
public class NodeItem<T> {
  private T item;
  private NodeItem<T> next; // pointer to next node item

  public NodeItem() {}

  /**
   * Creates a new node item.
   *
   * @param newItem The value of the node item.
   */
  public NodeItem(T newItem) {
    this.item = newItem;
    this.next = null; // set next node item to be added to stack.
    ;
  }

  /**
   * Creates a new node item. The next node item is set to the value of the next parameter.
   *
   * @param newItem The value of the node item.
   * @param next The next node item.
   */
  public NodeItem(T newItem, T next) {
    this.item = newItem;
    this.next = new NodeItem<T>(next); // set next node item to be added to stack.
  }

  /**
   * Sets the value of the node item.
   *
   * @param nextItem The next node item.
   * @return void.
   */
  public void setNext(NodeItem<T> nextItem) {
    this.next = nextItem;
  }

  // getter for node item value
  public T getValue() {
    return this.item;
  }

  // getter for next node item
  public NodeItem<T> getNext() {
    return this.next;
  }
}
