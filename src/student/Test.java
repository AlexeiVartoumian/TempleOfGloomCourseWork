package student;


import game.NodeStatus;

import java.util.*;

public class Test {
//    System.out.println(state.getCurrentNode().getNeighbours());
//        state.getCurrentLocation();
//    Deque<Long> queue = new ArrayDeque<>();
//    Set<Long> visited = new HashSet<>();
//    //ArrayList<Long> visited = new ArrayList<>();
//    //queue.add(state.getDistanceToTarget());
//    int distance = state.getDistanceToTarget();
//    //queue.add(state.getCurrentLocation());
//        while (state.getDistanceToTarget() != 0) {
//        visited.add(state.getCurrentLocation());
//        System.out.println(state.getCurrentLocation() + " current location");
//        Collection<NodeStatus> thing = state.getNeighbours();
//        System.out.println("Arrays of nodes : " + Arrays.toString(thing.toArray()));
//        System.out.println("----------------------------------------");
//
//        ArrayList<NodeStatus> goback =  new ArrayList<>();
//
//        boolean flag = true;
//        for (NodeStatus nextPos : state.getNeighbours()) {
//            if (!visited.contains(nextPos.nodeID())) {
//                long nextId = nextPos.nodeID();
//                System.out.println("Currently at this position" + state.getCurrentLocation());
//                System.out.println("Attempting to move to this position" + nextId);
//                System.out.println("Visited" + visited);
//                System.out.println("-------------------------------------------");
//                if (!visited.contains(nextId)) {
//                    state.moveTo(nextId);
//                    flag = false;
//                    break;
//                }
//            }else{
//                goback.add(nextPos);
//            }
//        }
//        if (flag){
//            boolean rollback = true;
//            Set<Long> failedNodes = new HashSet<>();
//            Deque<Long> queue1 = new ArrayDeque<>();
//            queue1.add(state.getCurrentLocation());
//            while (rollback){
//
//                for (NodeStatus nextPos : state.getNeighbours()) {
//                    if (!visited.contains(nextPos.nodeID())){
//                        rollback = false;
//                        state.moveTo(nextPos.nodeID());
//                    }
//                    if (!failedNodes.contains(nextPos.nodeID())) {
//                        queue.add(nextPos.nodeID());
//                    }
//                }
//                failedNodes.add(state.getCurrentLocation());
//                state.moveTo(queue.removeFirst());

//                    for (NodeStatus nextPos : state.getNeighbours()) {
//                       if (!visited.contains(nextPos.nodeID())){
//                           rollback = false;
//                           state.moveTo(nextPos.nodeID());
//                           break;
//                       }
//                       if(!failedNodes.contains(nextPos.nodeID())) {
//                           failedNodes.add(nextPos.nodeID());
//                           state.moveTo(nextPos.nodeID());
//                           break;
//                       }
//                    }
//            }
//            System.out.println("*****************************************");
//            System.out.println("these neighbours have been visited before");
//            System.out.println(goback);
//            System.out.println(visited);
//            System.out.println("Arrays of current nodes : " + Arrays.toString(thing.toArray()));

//            System.out.println("*****************************************");

//                for (NodeStatus nextPos : state.getNeighbours()) {
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                    System.out.println("Currently at this position" + state.getCurrentLocation());
//                    System.out.println("Attempting to move to this position" + nextPos.nodeID());
//                    state.moveTo(nextPos.nodeID());
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                    break;
//                }
//        }
//    }
//        state.getCurrentLocation();
//        Deque<Integer>  queue = new ArrayDeque<>();
//        Set<Long> visited = new HashSet<>();
//        //queue.add(state.getDistanceToTarget());
//        int distance = state.getDistanceToTarget();
//        while(state.getDistanceToTarget() != 0){
//            visited.add(state.getCurrentLocation());
//            System.out.println(state.getCurrentLocation() + " current location");
//            System.out.println("----------------------------------------");
//            Collection<NodeStatus> thing  = state.getNeighbours();
//            System.out.println("Arrays of nodes : " + Arrays.toString(thing.toArray()));
//            for (NodeStatus nextPos : state.getNeighbours()) {
//                    if (!visited.contains(nextPos.nodeID()))  {
//                        long nextId = nextPos.nodeID();
//                        System.out.println("Currently at this position" + state.getCurrentLocation());
//                        System.out.println("Attempting to move to this position" + nextId);
//                        System.out.println("Visited" + visited);
//                        System.out.println("-------------------------------------------");
//                        if(!visited.contains(nextId)){
//
//                            state.moveTo(nextId);
//                            break;
//                        }
//                }
//                }
//        }
//}
}
