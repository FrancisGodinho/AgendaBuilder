package main.java.course_graph;

import javax.swing.text.EditorKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public abstract class ActivityGraph implements IGraph{

    private Map<ActivityVertex, Integer> vertexMap;
    private Set<ActivityEdge> edgeSet;

    private List<List<Integer>> adjacencyMatrix;

    public ActivityGraph(ActivityVertex v){
        vertexMap = new HashMap<>();
        edgeSet = new HashSet<>();
        adjacencyMatrix = new ArrayList<>();
    }

    public boolean addVertex(ActivityVertex v){
        if(!vertexMap.containsKey(v)) {
            vertexMap.put(v, 0);
            return true;
        }
        return false;
    }

    public boolean vertexExists(ActivityVertex v){
        if(vertexMap.containsKey(v))
            return true;
        return false;
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
