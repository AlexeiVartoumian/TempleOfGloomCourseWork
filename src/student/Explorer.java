package student;

import game.*;

import java.util.*;

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


        // ---------------------------HashTable + Map Data Structures to Store Information-------------------------------------
        HashMap<Long, ArrayList<Long>> adjacencyList = new HashMap<>(); // Create record of graph as exploring Map
        HashMap<Long, Integer> NodeDistances  = new HashMap<>(); // Distances of every Node to Destination
        Hashtable<Long , Integer> DistanceFromSource = new Hashtable<>();//Keeps track of shortestPath from Source to Curnode

        //----------------------------HashSets to Keep Track of Information---------------------------
        HashSet<Long> UpdateSourceTable = new HashSet<>();// needed to prevent updating Distance table twice. Only Updated when needed
        HashSet<Long> visited = new HashSet<>(); // stores nodes that have been traversed only.

        Set<Long> seen = new HashSet<>(); // Keeps record of All nodes. needed when all neighbours are visited=
        // deadEnd and choose unvisited node. Done with set Difference seen =  seen - visited.
        //---------------------------------------------------------------------------------------------------

        //init with Data structs with start node
        Long source = state.getCurrentLocation();
        NodeDistances.put(source, state.getDistanceToTarget());
        DistanceFromSource.put(source , 0);
        seen.add(source);
        UpdateSourceTable.add(source);

        while(state.getDistanceToTarget() != 0) {
            //Step1 : create adjacency List and store dist to destination in Node distance.
            ArrayList<Long> nodes = new ArrayList<>();
            for (NodeStatus neighbour : state.getNeighbours()) {
                nodes.add(neighbour.nodeID());
                seen.add(neighbour.nodeID());
                NodeDistances.put(neighbour.nodeID(), neighbour.distanceToTarget());
            }

            visited.add(state.getCurrentLocation());

            adjacencyList.put(state.getCurrentLocation(), nodes);


            Long nextSmall = null;
            Long NextGreaterThan = null;

            Integer curDistance = state.getDistanceToTarget();
            boolean AllNeighboursSeen = true;
            boolean smallerthencurUnvisit = false;
            DistanceFromSource = ClosestPath.UpdateSourceTable(source,adjacencyList,UpdateSourceTable,DistanceFromSource, visited );

            ArrayList<Long> AllgreaterThanOrEqualtoCurrentNode = new ArrayList<>();

            /**
             * Three things are happening in the loop.
             * 1. find Unvisited Node with the shortest distance to destination relative to currentNode.
             * 2. Find the next smallest Unvisited Node.
             * 3. Find All the other Unvisited nodes of Equal  or greater distance to destination relative to currentNode and add them to a list.
             *
             */

            for(Long find : adjacencyList.get(state.getCurrentLocation())){
                if (!visited.contains(find) && NodeDistances.get(find) <= curDistance){
                    AllNeighboursSeen = false;
                    smallerthencurUnvisit = true;

                    nextSmall = (nextSmall == null || NodeDistances.get(find) <= NodeDistances.get(nextSmall)) ? find : nextSmall;
                    AllgreaterThanOrEqualtoCurrentNode.add(find);
                }
                else if (!visited.contains(find) && NodeDistances.get(find) >= curDistance ){
                    AllNeighboursSeen = false;
                    NextGreaterThan = (NextGreaterThan == null || NodeDistances.get(find) <= NodeDistances.get(NextGreaterThan)) ? find : NextGreaterThan;
                    AllgreaterThanOrEqualtoCurrentNode.add(find);
                }

            }
            // If we are not at a dead End choose smallest unvisited node to destination.
            if(!AllNeighboursSeen) {

                if (nextSmall != null) {
                    state.moveTo(nextSmall);
                } else if (NextGreaterThan != null) {
                    ClosestPath.moveToNextGreaterThan(state, smallerthencurUnvisit, NextGreaterThan, AllgreaterThanOrEqualtoCurrentNode, DistanceFromSource, NodeDistances);
                }

            }else{
                //we are at a dead end and have to choose from current location a seen node that is not visited with smallest distance to destination.

                seen.removeAll(visited);

                Set<Long> copySet = new HashSet<>(seen);

                long destination = copySet.stream().reduce((first, second) -> second).orElseThrow();
                Hashtable<Long, Integer> finalDistanceFromSource = DistanceFromSource;

                destination = copySet.stream()
                        .limit(copySet.size() - 1)
                        .reduce(destination, (dest, element) -> ClosestPath.ScoreBoard(element, dest, finalDistanceFromSource, NodeDistances),
                                (a, b) -> b);

                Deque<ArrayList<Long>> paths = new ArrayDeque<>();
                ArrayList<Long> thePath = ClosestPath.findShortestPath(visited,paths, adjacencyList,state.getCurrentLocation(),destination);

                for(int i = 1; i < thePath.size() ; i++) {
                    state.moveTo(thePath.get(i));
                }
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
        DijkstraState dijkstra = new DijkstraState(state.getVertices(), state.getCurrentNode(), state.getExit());
        GoldListFactory goldListFactory = new GoldListFactory(dijkstra, state.getCurrentNode());
        Node currentNode = state.getCurrentNode();
        List<Pair<Node, Integer>> goldList = goldListFactory.getGoldList();

        for (var pair : goldList) {
            int distanceToTarget = dijkstra.getDistanceToNode(pair.first());
            dijkstra.changeSource(pair.first());
            if (state.getTimeRemaining() > distanceToTarget + dijkstra.getDistanceToExit()) {
                dijkstra.changeSource(currentNode);
                gotToNode(state, dijkstra, pair.first());
                currentNode = pair.first();
                dijkstra.changeSource(currentNode);
            } else {
                dijkstra.changeSource(currentNode);
            }
        }
        gotToExit(state, dijkstra);
    }

    private void gotToNode(EscapeState state, DijkstraState dijkstra, Node targetNode) {
        Deque<Node> path = dijkstra.createPathToNode(targetNode);
        for (Node node : path) {
            state.moveTo(node);
            pickGoldIfPresent(state);
            if (state.getCurrentNode() == state.getExit()) {
                return;
            }
        }
    }

    private void gotToExit(EscapeState state, DijkstraState dijkstra) {
        gotToNode(state, dijkstra, state.getExit());
    }

    private void pickGoldIfPresent(EscapeState state) {
        if (state.getCurrentNode().getTile().getGold() > 0) {
            state.pickUpGold();
        }
    }
    }

