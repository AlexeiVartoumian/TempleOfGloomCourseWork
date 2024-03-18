package student;

import game.NodeStatus;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class test2 {
//    Deque< Long[] > queue = new ArrayDeque<>();
//    Deque<Long[]> NextNodeCoords = new ArrayDeque<>();
//    Set<Long> visited = new HashSet<>();
//    Set<Long[]> rollBack = new HashSet<>();
//    //HashMap<Long , Set<Long[]>> rollback2 = new HashMap<>();
//        while (state.getDistanceToTarget() != 0) {
//        long source =state.getCurrentLocation();
//        boolean flag = true;
//        for(NodeStatus neighbours : state.getNeighbours()){
//            long destination = neighbours.nodeID();
//            Long[] cordinates = new Long[2];
//            cordinates[0] = source;
//            cordinates[1] = destination;
//            if(!visited.contains(destination) ) {
//                queue.add(cordinates);
//                NextNodeCoords.add(cordinates);
//                flag = false;
//            }
//        }
//        visited.add(source);
//        if(flag) {
//            boolean find = true;
//            while(find) {
//                Long[] lastAdded = queue.removeLast();
//                long destination =lastAdded[0];
//                long src =  lastAdded[1];
//                Long[] reverse = new Long[2];
//                reverse[0] = src;
//                reverse[1] = destination;
//                if (!rollBack.contains(reverse)){
//                    rollBack.add(reverse);
//                    System.out.println(state.getCurrentLocation());
//                    System.out.println("soruce " +  src + " destination " + destination);
//                    state.moveTo(destination);
//                    //find = false;
//                }
//            }
//        }else{
//            Long[] nextNode = NextNodeCoords.removeLast();
//            long destination =  nextNode[1];
//            state.moveTo(destination);
//        }
//
//    }

}
