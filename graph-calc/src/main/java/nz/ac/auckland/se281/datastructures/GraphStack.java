package nz.ac.auckland.se281.datastructures;

/**
 * A queue that is composed of a set of node items.
 *
 * @param <T> The type of each node item.
 */
public class GraphStack<T> {
  private NodeItem<T> head;
  private NodeItem<T> tail;

  public GraphStack() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Adds a node item to the queue.
   *
   * @param nodeItem The node item to add to the Stack.
   * @return void.
   */
  public void addToStack(T nodeItem) {
    if (head == null) { // if stack is empty add node item to top of stack (LIFO).
      NodeItem<T> newItem = new NodeItem<T>(nodeItem);
      this.head = newItem;
      this.tail = newItem;
    } else { // if stack is not empty add node item to top of stack (LIFO).
      NodeItem<T> addItem = new NodeItem<T>(nodeItem);
      NodeItem<T> currHead = head;
      while (currHead.getNext() != null) {
        currHead = currHead.getNext();
      }
      currHead.setNext(addItem); // set next node item to be added to stack.
      tail = addItem;
    }
  }

  /**
   * Removes a node item from the queue.
   *
   * @return void.
   */
  public T popNodeItem() { // remove node item from top of Stack (LIFO)
    if ((tail == null) && (head == null)) {
      return null;
    } else {
      NodeItem<T> currHead = head;
      NodeItem<T> prevNode = null;
      while (currHead.getNext() != null) { // remove node item from top of Stack (LIFO)
        prevNode = currHead;
        currHead = currHead.getNext();
      }
      if (prevNode == null) { // if stack is empty return null.
        head = null;
        tail = null;
        return currHead.getValue();
      } else if (currHead.getNext()
          == null) { // if stack is not empty remove node item from top of Stack (LIFO)
        prevNode.setNext(null);
        tail = prevNode;
        return currHead.getValue();
      }
      return null;
    }
  }

  public T getHead() {
    return head.getValue();
  }

  public T getTail() {
    return tail.getValue();
  }

  /**
   * Returns the size of the queue.
   *
   * @return The size of the queue.
   */
  public int getSize() {
    int size = 0;
    NodeItem<T> current = head;
    while (current != null) {
      size++;
      current = current.getNext(); // get size of stack
    }
    return size;
  }
}
