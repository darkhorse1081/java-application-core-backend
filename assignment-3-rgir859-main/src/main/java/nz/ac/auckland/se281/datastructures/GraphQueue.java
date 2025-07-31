package nz.ac.auckland.se281.datastructures;

/**
 * A queue that is composed of a set of node items.
 *
 * @param <T> The type of each node item.
 */
public class GraphQueue<T> {
  private NodeItem<T> head;

  public GraphQueue() {
    this.head = null;
  }

  /**
   * Adds a node item to the queue.
   *
   * @param nodeItem The node item to add to the queue.
   * @return void.
   */
  public void appendToQueue(T nodeItem) {
    if (head == null) {
      NodeItem<T> newItem = new NodeItem<T>(nodeItem);
      this.head = newItem;
    } else {
      NodeItem<T> addItem = new NodeItem<T>(nodeItem); // add node item to queue (FIFO)
      NodeItem<T> currHead = head;
      while (currHead.getNext() != null) {
        currHead = currHead.getNext();
      }
      currHead.setNext(addItem);
    }
  }

  /**
   * Removes a node item from the queue.
   *
   * @return void.
   */
  public void removeNodeItem() { // remove node item from queue (FIFO)
    if (head == null) {
      return;
    } else {
      NodeItem<T> currHead = head;
      head = currHead.getNext(); // remove node item from queue (FIFO)
    }
  }

  public T getHead() {
    return head.getValue();
  }

  /**
   * Returns the size of the queue.
   *
   * <p>The size of the queue is the number of node items in the queue.
   *
   * @return The size of the queue.
   */
  public int getSize() {
    int size = 0;
    NodeItem<T> current = head; // get size of queue
    while (current != null) {
      size++;
      current = current.getNext(); // increment size of queue by 1
    }
    return size;
  }
}
