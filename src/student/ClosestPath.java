
package student;

import game.ExplorationState;

import java.util.*;
public class ClosestPath {

    /**
     * This method determines the shortest path from source node to any given visited node in the map.
     * Always begins from source.
     *
     * @param source : starting location
     * @param adjacencyList: Current state of Map
     * @param UpdateSourceTable: Hashset. Prevent updating distances twice i.e from a different nighbour.
     * @param DistanceFromSource: the distance table to be updated.
     * @param Globalvisited: only visit nodes in the queue which we have not already determined the distance
     * @return DistanceFromSource.
     */
    public static Hashtable<Long , Integer> UpdateSourceTable(Long source, HashMap<Long, ArrayList<Long>> adjacencyList, HashSet<Long> UpdateSourceTable,Hashtable<Long , Integer> DistanceFromSource,HashSet<Long> Globalvisited ){
        ArrayDeque<Long> queue = new ArrayDeque<>();
        queue.add(source);
        HashSet<Long> innervisited = new HashSet<>();
        while (innervisited.size() < Globalvisited.size()){
            Long curNode = queue.removeFirst();
            for(Long neighbour : adjacencyList.get(curNode) ){
                if (!UpdateSourceTable.contains(neighbour)){
                    DistanceFromSource.put(neighbour, DistanceFromSource.get(curNode) + 1);
                    UpdateSourceTable.add(neighbour);
                }
                if (Globalvisited.contains( neighbour) && !innervisited.contains(neighbour) ){
                    queue.add(neighbour);
                }
            }
            innervisited.add(curNode);
        }
        return DistanceFromSource;
    }
    /**
     * This method compares two given Unvisited Nodes and evaluates them according to parameters DistancefromSource and NodeDistances.
     * Current Implementation chooses the node closest to destination and furthest from the source.
     * @param possibleNode
     * @param currentNode
     * @param DistanceFromSource
     * @param NodeDistances
     * @return
     */
    public static  Long ScoreBoard(Long possibleNode , Long currentNode, Hashtable<Long , Integer> DistanceFromSource ,HashMap<Long, Integer> NodeDistances ){
        int possibleNodeDistanceFromSource = DistanceFromSource.get(possibleNode);
        int currentNodeDistancefromSource = DistanceFromSource.get(currentNode);
        int possibleNodeDistancetoDestination = NodeDistances.get(possibleNode);
        int currentNodeDistanceToDestination = NodeDistances.get(currentNode);
        int scoreforPos = 0;
        int scoreforCur = 0;

        int DistanceFromDest = Math.abs(possibleNodeDistancetoDestination - currentNodeDistanceToDestination);
        if (possibleNodeDistancetoDestination < currentNodeDistanceToDestination){
            scoreforPos += DistanceFromDest;
        }else{
            scoreforCur += DistanceFromDest;
        }
        //evaluate unequal scores choosing the higher score as winner.
        if (scoreforPos != scoreforCur){
            if (scoreforPos > scoreforCur)
                return possibleNode;
            else{
                return currentNode;
            }
        }
        //case of a tie choose node closer to destination.
        if (possibleNodeDistancetoDestination != currentNodeDistanceToDestination){
            if (possibleNodeDistancetoDestination < currentNodeDistanceToDestination)
                return possibleNode;
            else{
                return currentNode;
            }
        }
        //case of a tie choose node furthest from the source
        if (possibleNodeDistanceFromSource != currentNodeDistancefromSource){
            if (possibleNodeDistanceFromSource > currentNodeDistancefromSource)
                return possibleNode;
            else{
                return currentNode;
            }
        }
        ArrayList<Long> choose = new ArrayList<>(List.of(possibleNode,currentNode));
        // this chooses at random from two nodes in case they both have equal distance from both source and destination.
        // This handles many scenarios but a possible scenario that has not been considered
        // is where the source node is really close to the destination node in which case
        // without knowing the details of the map it could be better to choose a node closer to source.
        int val = (int) Math.round( Math.random() );
        return choose.get(val) ;
    }
    /**
     * Move to the smallest Distance to destination node that is not visited. in the case of many nodse of equal distance then use scoreboard to choose best node.
     * @param state
     * @param smallerthencurUnvisit
     * @param NextGreaterThan
     * @param AllgreaterThanOrEqualtoCurrentNode
     * @param DistanceFromSource
     * @param NodeDistances
     */
    public  static void moveToNextGreaterThan(ExplorationState state, boolean smallerthencurUnvisit, Long NextGreaterThan, ArrayList<Long> AllgreaterThanOrEqualtoCurrentNode, Hashtable<Long , Integer> DistanceFromSource, HashMap<Long, Integer> NodeDistances) {
        if (smallerthencurUnvisit) {
            state.moveTo(NextGreaterThan);
        } else {
            for (int i = 0; i < AllgreaterThanOrEqualtoCurrentNode.size(); i++) {
                NextGreaterThan = ClosestPath.ScoreBoard(AllgreaterThanOrEqualtoCurrentNode.get(i), NextGreaterThan, DistanceFromSource, NodeDistances);
            }
            state.moveTo(NextGreaterThan);
        }
    }
    /**
     * Modified BFS. Find shortest Path from source to destination storing a queue of explored paths as arrayLists.
     * First result will be the shortest path from source to destination.
     * @param visited
     * @param paths
     * @param adjacencyList
     * @param source
     * @param destination
     * @return
     */
    public static ArrayList<Long> findShortestPath(Set<Long> visited, Deque<ArrayList<Long>> paths, Map<Long, ArrayList<Long>> adjacencyList, long source , long destination) {
        paths.add(new ArrayList<>(List.of(source)));
        boolean notFound = true;
        ArrayList<Long> thePath = new ArrayList<>();
        while (notFound) {
            ArrayList<Long> currentPath = paths.removeFirst();
            long lastNodeInPath = currentPath.getLast();
            ArrayList<Long> nextValues = adjacencyList.get(lastNodeInPath);
            for (int i = 0; i < nextValues.size(); i++) {
                ArrayList<Long> values = new ArrayList<>(currentPath);
                if (nextValues.get(i) == destination) {
                    values.add(nextValues.get(i));
                    notFound = false;
                    thePath = values;
                    break;
                }
                if (visited.contains(nextValues.get(i)) && !values.contains(nextValues.get(i))) {
                    values.add(nextValues.get(i));
                    paths.add(values);
                }
            }
        }
        return thePath;
    }
}

