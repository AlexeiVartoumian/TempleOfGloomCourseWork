package student;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPathTest {

    Long source;
    HashMap<Long, ArrayList<Long>> adjacencyList;
    HashSet<Long> updateSourceOnce;
    Hashtable<Long , Integer> DistanceFromSource;

    HashMap<Long, Integer> NodeDistances;
    HashSet<Long> globalSeen;

    @BeforeEach
    void setUp(){
        source = 0L;
        adjacencyList = new HashMap<>();
        globalSeen = new HashSet<>();
        updateSourceOnce = new HashSet<>();
        DistanceFromSource = new Hashtable<>();
        NodeDistances = new HashMap<>();
        DistanceFromSource.put(0L , 0);

        updateSourceOnce.add(0L);
        globalSeen.add(0L);
        globalSeen.add(1L);
        globalSeen.add(2L);

        adjacencyList.put(0L , new ArrayList<>(List.of(1L)));
        adjacencyList.put(1L , new ArrayList<>(List.of(0L,2L)));
        adjacencyList.put(2L , new ArrayList<>(List.of(1L,3L)));

        NodeDistances.put(0L , 3);
        NodeDistances.put(1L , 2);
        NodeDistances.put(2L , 1);
        NodeDistances.put(3L , 0);
    }

    @Test
    void UpdateSourceTableTest(){

        Hashtable<Long , Integer> TestVal = new Hashtable<>();
        TestVal.put(0L, 0);
        TestVal.put(1L, 1);
        TestVal.put(2L, 2);
        TestVal.put(3L, 3);
        Assertions.assertEquals(TestVal , ClosestPath.UpdateSourceTable(source,adjacencyList,updateSourceOnce,DistanceFromSource,globalSeen));
    }

    @Test
    void ScoreBoardTest(){
        Long PossibleNode = 2L;
        Long CurNode = 1L;
        ClosestPath.UpdateSourceTable(source,adjacencyList,updateSourceOnce,DistanceFromSource,globalSeen);
        Assertions.assertEquals(2L , ClosestPath.ScoreBoard(PossibleNode,CurNode,DistanceFromSource,NodeDistances));
    }

    @Test
    void moveToNextGreaterThanTest() {
        boolean smallerDistThenCurNotVisited1 = true;
        boolean smallerDistThenCurNotVisited2 = false;
        ArrayList<Long> possibleNodes = new ArrayList<>(List.of(2L, 1L));
        Long NextGreaterThan = 1L;
        ClosestPath.UpdateSourceTable(source, adjacencyList, updateSourceOnce, DistanceFromSource, globalSeen);

        Assertions.assertEquals(1L , ClosestPath.moveToNextGreaterThan(smallerDistThenCurNotVisited1,NextGreaterThan,possibleNodes,DistanceFromSource,NodeDistances));
        Assertions.assertEquals(2L , ClosestPath.moveToNextGreaterThan(smallerDistThenCurNotVisited2,NextGreaterThan,possibleNodes,DistanceFromSource,NodeDistances));

    }
    @Test
    void findShortestPathTest() {

        globalSeen.add(3L);

        ArrayList<Long> path = new ArrayList<>(List.of(0L, 1L, 2L, 3L));
        Deque<ArrayList<Long>> queue = new ArrayDeque<>();

        Assertions.assertEquals(path, ClosestPath.findShortestPath(globalSeen, queue, adjacencyList, 0L, 3L));
    }
}