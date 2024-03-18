package student;

import game.*;
import java.util.*;
import java.util.stream.Collectors;

public class Explorer {


    /**
     * Explore the cavern, trying to find the orb in as few steps as possible.
     * Once you find the orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb,
     * it will count as a failure.
     * <p>
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * <p>
     * At every step, you only know your current tile's ID and the ID of all
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles).
     * <p>
     * To get information about the current state, use functions
     * getCurrentLocation(),
     * getNeighbours(), and
     * getDistanceToTarget()
     * in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * <p>
     * Use function moveTo(long id) in ExplorationState to move to a neighboring
     * tile by its ID. Doing this will change state to reflect your new position.
     * <p>
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     *
     * @param state the information available at the current state
     */
    public void explore(ExplorationState state) {
        //TODO : Explore the cavern and find the orb

        // if the distance to the target is 0, then we are at the orb and we can return
        if (state.getDistanceToTarget() == 0) {
            return;
        }
        // create a stack to keep track of the moves made using ArrayDeque as it is faster than Stack and allow adding and removing to the front
        // of the list and the end of the list
        ArrayDeque<Long> madeMoves = new ArrayDeque<>();
        ArrayDeque<Long> visited = new ArrayDeque<>();


        while (state.getDistanceToTarget() > 0) {
            visited.push(state.getCurrentLocation()); // add the current location to the visited list

            // get the neighbors of the current location, filter out the visited ones and sort the rest by distance to the Orb
            List<Long> neighborTiles = state.getNeighbours().stream().filter(neighbor -> !visited.contains(neighbor.nodeID()))
                    .sorted(Comparator.comparing(NodeStatus::distanceToTarget))
                    .map(NodeStatus::nodeID).toList();

            // if there are neighbors that we can move to we go to the first in the list as that is the one that bring us closer to the Orb
            // and add it to the made moves list
            if (!neighborTiles.isEmpty()) {
                state.moveTo(neighborTiles.getFirst());

                madeMoves.addFirst(state.getCurrentLocation());// add the current location to the made moves list

            } else {
                // if there are no neighbors, move back to the previous location
                madeMoves.removeFirst();
                state.moveTo(madeMoves.peekFirst());
            }
        }

    }
    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        //TODO: Escape from the cavern before time runs out

        System.out.println(state.getVertices());

        System.out.println(state.getTimeRemaining());

        System.out.println("Starting position row" +  state.getCurrentNode().getTile().getRow());
        System.out.println("Starting position column" +state.getCurrentNode().getTile().getColumn() );

        state.getCurrentNode().getTile().getRow();
        state.getCurrentNode().getTile().getColumn();

        System.out.println("Ending position  row" +  state.getExit().getTile().getRow());
        System.out.println("Ending position column" + state.getExit().getTile().getColumn() );

        state.getExit().getTile().getRow();
        state.getExit().getTile().getColumn();
        state.getCurrentNode();
        //HashMap< Long , ArrayList<ArrayList<Number>>> adjacencyList = new HashMap<>();
        System.out.println("starting node" + state.getCurrentNode().getId());
        System.out.println("finish node" + state.getExit().getId());
        HashMap< Long , ArrayList<Long>> adjacencyList = new HashMap<>();
        HashMap< Node , ArrayList<Node>> adjacencyList2 = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(state.getCurrentNode());

        while (visited.size() != state.getVertices().size()){

            Node currentNode = queue.removeFirst();

            ArrayList<Long> neighbours = new ArrayList<>();
            ArrayList<Node> neighbours2 = new ArrayList<>();

            for(Node node : currentNode.getNeighbours()){
                neighbours.add(node.getId());
                neighbours2.add(node);
                if (!visited.contains(node)){
                    queue.add(node);
                }

            }
            visited.add(currentNode);
            adjacencyList.put(currentNode.getId(),neighbours);
            adjacencyList2.put(currentNode,neighbours2);
        }
        //System.out.println(adjacencyList);
        ArrayDeque<Node> queue2 = new ArrayDeque<>();
        Set<Node> seen = new HashSet<>();
        queue2.add(state.getCurrentNode());

        ArrayList<Long> nodepaths = new ArrayList<>();
        seen.add(state.getCurrentNode());
        boolean notfound = true;

        Deque<ArrayList<Node>> paths = new ArrayDeque<>();
        Deque<ArrayList<Long>> pathId = new ArrayDeque<>();

        paths.add(new ArrayList<Node>(List.of(state.getCurrentNode())));
        pathId.add(new ArrayList<Long>(List.of(state.getCurrentNode().getId())));

        seen.add(state.getCurrentNode());
        ArrayList<Node> finalpath = new ArrayList<>();
        ArrayList<Long> finalId = new ArrayList<>();

        while(notfound){

            Node curNode = queue2.removeFirst();
            ArrayList<Node> curpath = paths.removeFirst();
            ArrayList<Long> curpathId = pathId.removeFirst();

            for(Node node: adjacencyList2.get(curNode)){
                ArrayList copy = new ArrayList(curpath);
                ArrayList copyId = new ArrayList(curpathId);
                if(!seen.contains(node)){

                    queue2.add(node);

                    copy.add(curNode);
                    copyId.add(curNode.getId());


                    paths.add(copy);
                    pathId.add(copyId);

                    seen.add(node);
                    if (node.getId() == state.getExit().getId()){
                        notfound = false;
                        finalpath = copy;
                        finalId = copyId;
                        break;
                    }
                }
            }

        }
        System.out.println(finalpath);
        System.out.println(finalId);
        for (int i = 2; i < finalpath.size(); i++) {
            state.moveTo(finalpath.get(i));
        }

        }





}