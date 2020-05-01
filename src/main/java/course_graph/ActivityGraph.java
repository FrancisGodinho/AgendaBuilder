package main.java.course_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class ActivityGraph implements IGraph{

    private Map<ActivityVertex, Integer> vertexMap; //vertices/degree map
    private Set<ActivityEdge> edgeSet;
    private Map<ActivityVertex, List<ActivityVertex>> adjacencyList;

    public ActivityGraph(ActivityVertex v){
        vertexMap = new HashMap<>();
        edgeSet = new HashSet<>();
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(ActivityVertex v){
        if(!vertexMap.containsKey(v)) {
            vertexMap.put(v, 0);
            adjacencyList.put(v, new ArrayList<>());
        }
        return true;
    }

    public boolean vertexExists(ActivityVertex v){
        return vertexMap.containsKey(v);
    }

    public boolean addEdge(ActivityEdge e){
        if(!edgeSet.contains(e)){
            edgeSet.add(e);
            return true;
        }
        return false;
    }

    public boolean edgeExists(ActivityEdge e){
        if(edgeSet.contains(e))
            return false;
        return true;
    }

    public boolean edgeExists(ActivityVertex v1, ActivityVertex v2){


    }

    public boolean remove(ActivityEdge e){
        if(edgeSet.contains(e)) {
            edgeSet.remove(e);
            return true;
        }
        return false;
    }

    public boolean remove(ActivityVertex v){
        if(vertexMap.containsKey(v)){
            vertexMap.remove(v);
            return true;
        }
        return false;
    }




}
