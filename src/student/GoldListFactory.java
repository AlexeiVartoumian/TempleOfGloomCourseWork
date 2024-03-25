package student;

import game.Cavern;
import game.Node;
import game.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class GoldListFactory {
    //TODO make it a Singleton
    public static final double THRESHOLD_FACTOR = 0.65;
    private final DijkstraState dijkstra;
    private final Node startingNode;
    private final double threshold;


    public GoldListFactory(DijkstraState dijkstra, Node startingNode) {
        this.dijkstra = dijkstra;
        this.startingNode = startingNode;
        threshold = getThreshold();
    }

    public List<Pair<Node, Integer>> getGoldList() {

        List<Pair<Node, Integer>> highValuePath = new LinkedList<>();
        List<Node> valueNodes = sortByThreshold(dijkstra.getGraphNodes());
        List<Long> valueNodesID = valueNodes.stream()
                .map(Node::getId)
                .collect(Collectors.toCollection(LinkedList::new));

        Node nearestHighValue;

        for (int i = 0; i < valueNodes.size(); i++) {
            Optional<Node> min = valueNodes.stream()
                    .filter(node -> valueNodesID.contains(node.getId()))
                    .min(Comparator.comparingInt(dijkstra::getDistanceToNode));
            if (min.isPresent()) {
                nearestHighValue = min.get();
                highValuePath.add(new Pair<>(nearestHighValue, nearestHighValue.getTile().getGold()));
                valueNodesID.remove(nearestHighValue.getId());
                dijkstra.changeSource(nearestHighValue);
            }
        }
        dijkstra.changeSource(startingNode);
        return highValuePath;
    }

    private List<Node> sortByDistance(Collection<Node> goldList) {
        return goldList.stream()
                .sorted(Comparator.comparingInt(dijkstra::getDistanceToNode))
                .toList();
    }

    private List<Node> sortByThreshold(Collection<Node> goldList) {
        return goldList.stream()
                .filter(node -> node.getTile().getGold() >= threshold)
                .toList();
    }

    private double getThreshold() {
        return Cavern.MAX_GOLD_VALUE * THRESHOLD_FACTOR;
    }
}
