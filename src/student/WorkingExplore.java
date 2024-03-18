package student;

import game.NodeStatus;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;

public class WorkingExplore {
    /**
     * Because we only now the immediate neighbours of a given node
     * the approach I did here was to create an adjacency list as we pass
     * along.keep randomly exploring until at a given node. all nodes are
     * have been visited.
     * This is where it gets ugly.
     * then the idea is to use bfs to find a unexplored node and generate
     * a path to get to that node. eventually the explorer will go there
     * that the flag code.
     */
//        if (state.getDistanceToTarget() == 0) {
//        return;
//    }
//    // create a stack to keep track of the moves made using ArrayDeque as it is faster than Stack and allow adding and removing to the front
//    // of the list and the end of the list
//    ArrayDeque<Long> madeMoves = new ArrayDeque<>();
//    ArrayDeque<Long> visited = new ArrayDeque<>();
//

//        while (state.getDistanceToTarget() > 0) {
//        visited.push(state.getCurrentLocation()); // add the current location to the visited list
//
//        // get the neighbors of the current location, filter out the visited ones and sort the rest by distance to the Orb
//        List<Long> neighborTiles = state.getNeighbours().stream().filter(neighbor -> !visited.contains(neighbor.nodeID()))
//                .sorted(Comparator.comparing(NodeStatus::distanceToTarget))
//                .map(NodeStatus::nodeID).toList();
//
//        // if there are neighbors that we can move to we go to the first in the list as that is the one that bring us closer to the Orb
//        // and add it to the made moves list
//        if (!neighborTiles.isEmpty()) {
//            state.moveTo(neighborTiles.getFirst());
//
//            madeMoves.addFirst(state.getCurrentLocation());// add the current location to the made moves list
//
//        } else {
//            // if there are no neighbors, move back to the previous location
//            madeMoves.removeFirst();
//            state.moveTo(madeMoves.peekFirst());
//        }
//    }
    //       HashMap<Long, ArrayList<Long>> adjacencyList = new HashMap<>();
//       Set<Long> visited = new HashSet<>();
//       Set<Long> seen = new HashSet<>();
//       while(state.getDistanceToTarget() != 0){
//           ArrayList<Long> nodes = new ArrayList<>();
//           for(NodeStatus neighbour : state.getNeighbours()){
//               nodes.add(neighbour.nodeID());
//               seen.add(neighbour.nodeID());
//           }
//           visited.add(state.getCurrentLocation());
//           adjacencyList.put(state.getCurrentLocation(),nodes);
//           boolean flag = true;
//           for (int i = 0; i < nodes.size(); i++) {
//               if( !visited.contains(nodes.get(i))) {
//                 state.moveTo(nodes.get(i));
//                 flag = false;
//                 break;
//               }
//           }
//
//           if (flag){
//               seen.removeAll(visited);
//               Set<Long> copySet = new HashSet<>(seen);  // Use the same set type for consistency
//               Object[] array =  copySet.toArray();
//               System.out.println(adjacencyList);
//
//               long destination = (long) array[array.length-1];
//               Deque<ArrayList<Long>> paths = new ArrayDeque<>();
//               paths.add(new ArrayList<>(List.of(state.getCurrentLocation())));
//               boolean notfound = true;
//               ArrayList<Long> thePath = new ArrayList<>();
//               System.out.println(adjacencyList);
//               while(notfound){
//                   ArrayList<Long> currentPath =  paths.removeFirst();
//                   long lastNodeInPath = currentPath.getLast();
//                   ArrayList<Long> nextValues = adjacencyList.get(lastNodeInPath);
//                   for (int i = 0; i < nextValues.size(); i++) {
//                       ArrayList<Long> values = new ArrayList<>(currentPath);
//                       if(nextValues.get(i) == destination){
//                           values.add(nextValues.get(i));
//                           notfound = false;
//                           thePath = values;
//                           break;
//                       }
//                        if (visited.contains(nextValues.get(i)) && !values.contains(nextValues.get(i)) ) {
//                            values.add(nextValues.get(i));
//                            paths.add(values);
//                        }
//                   }
//               }
//               for(int i = 1; i < thePath.size() ; i++) {
//                   System.out.println("your path is such " + thePath);
//                   System.out.println(state.getCurrentLocation());
//                   System.out.println(thePath.get(i));
//                   System.out.println("next location" + thePath.get(i));
//                   state.moveTo(thePath.get(i));
//               }
//           }
//       }
}
