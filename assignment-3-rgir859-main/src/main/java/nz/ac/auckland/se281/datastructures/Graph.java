package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  private Set<Edge<T>> arcs;
  private Set<Node<T>> nodes;
  private Map<Node<T>, List<Node<T>>> adjacencyList;

  /**
   * Creates a new graph.
   *
   * <p>The graph is empty, i.e. it has no verticies or edges.
   *
   * @param verticies The set of verticies in the graph.
   * @param edges The set of edges in the graph.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.nodes = new HashSet<>(); // set of nodes in graph (verticies) and adjacency list.
    this.arcs = new HashSet<>();
    this.adjacencyList = new HashMap<Node<T>, List<Node<T>>>();

    for (final Edge<T> arc : edges) {
      arcs.add(new Edge<T>(arc.getSource().getNodeObject(), arc.getDestination().getNodeObject()));
      updateNodeDetails(arc.getSource(), arc.getDestination());
    }
    // Any remaining nodes without any connections
    for (final T vertex : verticies) {
      Node<T> newNode = new Node<T>(vertex);
      if (!nodes.contains(newNode)) {
        nodes.add(newNode);
        adjacencyList.put(newNode, new ArrayList<>()); // add node to graph and adjacency list.
      }
    }
  }

  public void updateNodeDetails(
      Node<T> sourceNode, Node<T> destinationNode) { // update node details for each edge
    if (!nodes.contains(sourceNode)) { // if node is not in graph, add to graph
      nodes.add(sourceNode);
      adjacencyList.put(sourceNode, new ArrayList<>());
    }
    if (!nodes.contains(destinationNode)) { // if node is not in graph, add to graph.
      nodes.add(destinationNode);
      adjacencyList.put(destinationNode, new ArrayList<>());
    }
    for (final Node<T> instance : nodes) { // update inDegreeCount and outDegreeCount for each node.
      if (instance.equals(sourceNode)) {
        instance.addOutDegreeCount();
        if (adjacencyList.get(instance) != null) {
          adjacencyList.get(instance).add(destinationNode);
        }
      }
      if (instance.equals(
          destinationNode)) { // update inDegreeCount and outDegreeCount for each node.
        instance.addInDegreeCount();
      }
    }
    Collections.sort(adjacencyList.get(sourceNode));
    Collections.sort(adjacencyList.get(destinationNode));
  }

  /**
   * Helper method for recursiveDepthFirstSearch. It recursively traverses the graph and adds the
   * nodes to the list.
   *
   * @param vertex The vertex to find the equivalence class of.
   * @param visitedList The list of visited nodes.
   * @param <T> The type of each vertex, that have a total ordering.
   * @return void function.
   */
  public void recursiveDepthFirstFunction(Node<T> vertex, List<T> visitedList) {
    List<Node<T>> tempNodeList = adjacencyList.get(vertex);
    for (Node<T> node : tempNodeList) {
      if (!visitedList.contains(node.getNodeObject())) { // add to visited list if not already in
        visitedList.add(node.getNodeObject());
        recursiveDepthFirstFunction(node, visitedList);
      }
    }
  }

  /**
   * Helper method for recursiveBreadthFirstSearch. It recursively traverses the graph and adds the
   * nodes to the list.
   *
   * @param nodeQueue The queue of nodes to be traversed.
   * @param visitedList The list of visited nodes.
   * @return void function.
   */
  public void recursiveBreadthFirstFunction(GraphQueue<T> nodeQueue, List<T> visitedList) {
    if (nodeQueue.getSize() == 0) {
      return;
    }
    Node<T> currNode = new Node<T>(nodeQueue.getHead()); // get head of queue and remove from queue
    nodeQueue.removeNodeItem();

    if (!visitedList.contains(currNode.getNodeObject())) { // add to visited list if not already in
      visitedList.add(currNode.getNodeObject());
    }
    List<Node<T>> tempNodeList = adjacencyList.get(currNode); // get adjacency list of current node
    for (Node<T> node : tempNodeList) {
      nodeQueue.appendToQueue(node.getNodeObject());
    }
    tempNodeList.clear();
    recursiveBreadthFirstFunction(
        nodeQueue, visitedList); // recursively call function with updated queue and visited list
  }

  /**
   * Returns the set of verticies in the graph.
   *
   * <p>Each vertex should only appear once in the set.
   *
   * <p>Each vertex should be comparable to other verticies in the set.
   *
   * <p>Each vertex should be immutable.
   *
   * @param <T> The type of each vertex, that have a total ordering.
   * @return The set of verticies in the graph.
   */
  public Set<T> getRoots() {

    ArrayList<Node<T>> tempRootList =
        new ArrayList<Node<T>>(); // list of root nodes to be sorted and returned.
    for (final Node<T> node : nodes) {
      int vertexOutDegreeCount = 0;
      if ((node.getInDegreeCount() == 0) && (node.getOutDegreeCount() > 0)) {
        tempRootList.add(node);
      } else if (isEquivalence()) {
        for (Edge<T> arc : arcs) {
          if (arc.getSource().getNodeId().equals(node.getNodeId())) { // get out degree count of
            vertexOutDegreeCount++;
          }
        }
        ArrayList<Node<T>> rootNodes =
            new ArrayList<Node<T>>(); // list of root nodes to be sorted and returned.
        for (Node<T> thisNode : nodes) {
          if (thisNode.getOutDegreeCount() == vertexOutDegreeCount) {
            rootNodes.add(thisNode);
          }
        }
        if (rootNodes.size() >= 1) { // if there are multiple roots, sort and add the first one.
          Collections.sort(rootNodes);
          tempRootList.add(rootNodes.get(0));
        }
      }
    }
    Collections.sort(tempRootList); // sort the list of root nodes and add to final set.
    final Set<T> rootList = new LinkedHashSet<T>();
    for (Node<T> node : tempRootList) {
      rootList.add(node.getNodeObject());
    }
    return rootList;
  }

  /**
   * Returns true if the graph is reflexive.
   *
   * <p>A graph is reflexive if each vertex has an edge to itself.
   *
   * @param <T> The type of each vertex, that have a total ordering.
   * @return true if the graph is reflexive.
   * @return false if the graph is not reflexive.
   */
  public boolean isReflexive() { // check if each node has an edge to itself (reflexive)
    for (Node<T> node : nodes) {
      if (!arcs.contains(new Edge<T>(node.getNodeObject(), node.getNodeObject()))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns true if the graph is symmetric.
   *
   * <p>A graph is symmetric if each edge has a reverse edge.
   *
   * @param <T> The type of each vertex, that have a total ordering.
   * @return true if the graph is symmetric.
   * @return false if the graph is not symmetric.
   */
  public boolean isSymmetric() { // check if each edge has a reverse edge (symmetric)
    for (Edge<T> arc : arcs) {
      if (!arcs.contains(
          new Edge<T>(arc.getDestination().getNodeObject(), arc.getSource().getNodeObject()))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns true if the graph is transitive.
   *
   * <p>A graph is transitive if each edge has a transitive edge.
   *
   * @return true if the graph is transitive.
   * @return false if the graph is not transitive.
   */
  public boolean isTransitive() { // check if each edge has a transitive edge (transitive)
    for (Edge<T> arcOne : arcs) {
      for (Edge<T> arcTwo : arcs) {
        if (arcOne.getDestination().equals(arcTwo.getSource())) {
          Edge<T> transitiveEdge =
              new Edge<T>(
                  arcOne.getSource().getNodeObject(), arcTwo.getDestination().getNodeObject());
          if (!arcs.contains(transitiveEdge)) { // check if each edge has a transitive edge
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the graph is anti-symmetric.
   *
   * <p>A graph is anti-symmetric if each edge has a reverse edge, except for edges where the source
   * and destination are the same vertex.
   *
   * @param <T> The type of each vertex, that have a total ordering.
   * @return true if the graph is anti-symmetric.
   * @return false if the graph is not anti-symmetric.
   */
  public boolean isAntiSymmetric() { // check if each edge has a reverse edge (antisymmetric)
    for (Edge<T> arcInstance : arcs) {
      Edge<T> reverseEdge =
          new Edge<T>(
              arcInstance.getDestination().getNodeObject(), // get reverse edge of arcInstance
              arcInstance.getSource().getNodeObject());
      if (arcs.contains(reverseEdge)
          && !arcInstance.getSource().equals(arcInstance.getDestination())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns true if the graph is an equivalence relation.
   *
   * @return true if the graph is an equivalence relation.
   * @return false if the graph is not an equivalence relation.
   */
  public boolean
      isEquivalence() { // check if graph is reflexive, symmetric and transitive (equivalence)
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }
    return false;
  }

  /**
   * Returns the set of verticies in the equivalence class of the vertex.
   *
   * <p>The equivalence class of a vertex is the set of all verticies that are equivalent to the
   * vertex.
   *
   * <p>Two verticies are equivalent if there is a path from one vertex to the other vertex.
   *
   * <p>Each vertex should only appear once in the set.
   *
   * @param vertex The vertex to find the equivalence class of.
   * @return The set of verticies in the equivalence class of the vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> equivalence = new HashSet<>(); // set of equivalent nodes to be returned.
    Node<T> vertexNode = new Node<T>(vertex);
    int vertexOutDegreeCount = 0;

    if (!isEquivalence()) { // if graph is not an equivalence relation, return empty set.
      return equivalence;
    }

    for (Edge<T> arc : arcs) { // get out degree count of vertex node in graph (equivalence).
      if (arc.getSource().getNodeId().equals(vertexNode.getNodeId())) {
        vertexOutDegreeCount++;
      }
    }
    for (Node<T> node :
        nodes) { // add node to equivalence set if out degree count is equal to vertex out degree
      // count.
      if (node.getOutDegreeCount() == vertexOutDegreeCount) {
        equivalence.add(node.getNodeObject());
      }
    }
    return equivalence;
  }

  /**
   * Breadth First Search implmented takes into account multiple roots. It uses a queue to traverse
   * the graph.
   *
   * @return list of traversed nodes in breadth first search.
   */
  public List<T> iterativeBreadthFirstSearch() {
    Set<T> listOfRoots = getRoots();
    List<T> breadthFirstSearch = new ArrayList<T>();
    GraphQueue<T> nodeQueue = new GraphQueue<T>();

    for (T root : listOfRoots) { // for each root, add to queue and breadthFirstSearch
      Node<T> rootNode = new Node<T>(root);
      for (Iterator<Node<T>> iter = nodes.iterator(); iter.hasNext(); ) {
        Node<T> node = iter.next();
        if (node.equals(rootNode)) {
          nodeQueue.appendToQueue(node.getNodeObject());
        }
      }
      breadthFirstSearch.add(root);
      while (nodeQueue.getSize() > 0) { // while queue is not empty, remove node and add to
        Node<T> currNode = new Node<T>(nodeQueue.getHead());
        nodeQueue.removeNodeItem();

        if (!breadthFirstSearch.contains(currNode.getNodeObject())) {
          breadthFirstSearch.add(currNode.getNodeObject());
        }
        List<Node<T>> tempNodeList = adjacencyList.get(currNode);
        for (Node<T> node : tempNodeList) {
          nodeQueue.appendToQueue(node.getNodeObject());
        }
        tempNodeList.clear(); // clear adjacency list of current node
      }
    }
    return breadthFirstSearch;
  }

  /**
   * Depth First Search implmented takes into account multiple roots. It uses a stack to traverse
   * the graph.
   *
   * @return list of traversed nodes in depth first search.
   */
  public List<T> iterativeDepthFirstSearch() {
    Set<T> listOfRoots = getRoots();
    List<T> depthFirstSearch = new ArrayList<T>();
    GraphStack<T> nodeStack = new GraphStack<T>();

    for (T root : listOfRoots) { // for each root, add to stack and depthFirstSearch
      Node<T> rootNode = new Node<T>(root);
      for (Iterator<Node<T>> iter = nodes.iterator(); iter.hasNext(); ) {
        Node<T> node = iter.next();
        if (node.equals(rootNode)) {
          nodeStack.addToStack(node.getNodeObject());
        }
      }

      Node<T> currNode = rootNode;
      while (nodeStack.getSize()
          > 0) { // while stack is not empty pop node and add to depthFirstSearch
        T poppedNode = nodeStack.popNodeItem();
        Node<T> newNode = new Node<T>(poppedNode);
        for (Iterator<Node<T>> iter = nodes.iterator(); iter.hasNext(); ) {
          Node<T> node = iter.next();
          if (node.equals(newNode)) {
            currNode = node;
            break;
          }
        }

        if (!depthFirstSearch.contains( // add to depthFirstSearch if not already in list
            currNode.getNodeObject())) {
          depthFirstSearch.add(currNode.getNodeObject());
        }
        List<Node<T>> tempNodeList = adjacencyList.get(currNode);
        for (int i = tempNodeList.size() - 1; i >= 0; i--) {
          nodeStack.addToStack(tempNodeList.get(i).getNodeObject());
        }
        tempNodeList.clear();
      }
    }
    return depthFirstSearch;
  }

  /**
   * Method to recursively traverse the graph in breadth first search. It takes into account
   * multiple roots.
   *
   * @return list of traversed nodes in breadth first search.
   */
  public List<T> recursiveBreadthFirstSearch() {
    Set<T> listOfRoots = getRoots();
    List<T> breadthFirstSearch = new ArrayList<T>();
    GraphQueue<T> nodeQueue = new GraphQueue<T>();

    for (T root : listOfRoots) { // for each root, add to queue and breadthFirstSearch
      Node<T> rootNode = new Node<T>(root);
      for (Iterator<Node<T>> iter = nodes.iterator(); iter.hasNext(); ) {
        Node<T> node = iter.next();
        if (node.equals(rootNode)) { // add root to breadthFirstSearch if in graph call
          // recursiveBreadthFirstFunction
          nodeQueue.appendToQueue(node.getNodeObject());
        }
      }
      breadthFirstSearch.add(root);
      recursiveBreadthFirstFunction(nodeQueue, breadthFirstSearch);
    }
    return breadthFirstSearch;
  }

  /**
   * Method to recursively traverse the graph in depth first search. It takes into account multiple
   * roots.
   *
   * @return list of traversed nodes in depth first search.
   */
  public List<T> recursiveDepthFirstSearch() {
    Set<T> listOfRoots = getRoots();
    List<T> depthFirstSearch = new ArrayList<T>();

    for (T root : listOfRoots) { // for each root, add to stack and depthFirstSearch
      Node<T> rootNode = new Node<T>(root);
      for (Iterator<Node<T>> iter = nodes.iterator(); iter.hasNext(); ) {
        Node<T> node = iter.next();
        if (node.equals(rootNode)) { // call recursiveDepthFirstFunction
          depthFirstSearch.add(node.getNodeObject());
          recursiveDepthFirstFunction(node, depthFirstSearch);
        }
      }
    }
    return depthFirstSearch;
  }
}
