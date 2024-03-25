package student;

import game.Node;

import java.util.*;

public class DijkstraState {
    //TODO make it a Singleton
    private final Collection<Node> graphNodes;
    private final Map<Node, Set<Node>> neighbours;
    private final Map<Node, Map<Node, Integer>> costToNeighbour;
    private final Map<Node, Integer> distances;
    private final Map<Node, Node> parents;
    private Node source;
    private final Node exit;

    public DijkstraState(Collection<Node> graphNodes, Node source, Node exit) {
        this.graphNodes = graphNodes;
        int n = graphNodes.size();
        neighbours = new HashMap<>(n);
        costToNeighbour = new HashMap<>(n);
        distances = new HashMap<>(n);
        parents = new HashMap<>(n);
        this.source = source;
        this.exit = exit;
        dijkstra(this.source);
    }

    public int getDistanceToExit() {
        return getDistanceToNode(exit);
    }

    public int getDistanceToNode(Node targetNode) {
        return distances.get(targetNode);
    }

    public Deque<Node> createPathToNode(Node target) {
        return createPath(target);
    }

    public void changeSource(Node source) {
        setSource(source);
        dijkstra(source);
    }

    public Collection<Node> getGraphNodes() { return graphNodes; }

    private void dijkstra(Node source) {
        initialiseTables();
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        distances.put(source, 0);
        queue.addAll(graphNodes);

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            for (Node neighbour : neighbours.get(currentNode)) {
                if (queue.contains(neighbour)) {
                    if (distances.get(neighbour) > distances.get(currentNode) +
                            costToNeighbour.get(currentNode).get(neighbour)) {
                        distances.put(neighbour, distances.get(currentNode) +
                                costToNeighbour.get(currentNode).get(neighbour));

                        parents.put(neighbour, currentNode);
                        // Refreshes the queue:
                        queue.remove(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }
    }

    private void initialiseTables() {
        for (Node node : graphNodes) {
            neighbours.put(node, node.getNeighbours());
            distances.put(node, Integer.MAX_VALUE);
            parents.put(node, null);
        }

        for (Node node : neighbours.keySet()) {
            Map<Node, Integer> costs = new HashMap<>();
            for (Node neighbour : neighbours.get(node)) {
                int length = node.getEdge(neighbour).length();
                costs.put(neighbour, length);
            }
            costToNeighbour.put(node, costs);
        }
    }

    private Deque<Node> createPath(Node node) {
        Deque<Node> path = new ArrayDeque<>();
        while (parents.get(node) != null) {
            path.addFirst(node);
            node = parents.get(node);
        }
        return path;
    }

    private void setSource(Node source) {
        this.source = source;
    }
}
