package main.java.course_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;


public class ActivityGraph implements IGraph{


    private Map<ActivityVertex, Integer> vertexMap; //vertices,degree map
    private Set<ActivityEdge> edgeSet;
    private Map<ActivityVertex,List<ActivityVertex>> adjacencyList;


    public ActivityGraph(){
        vertexMap = new HashMap<>();
        edgeSet = new HashSet<>();
        adjacencyList = new HashMap<>();
    }

    /**
     * Add a vertex to the graph
     *
     * @param v vertex to add
     * @return true if the vertex was added successfully and false otherwise
     */
    public boolean addVertex(ActivityVertex v){
        if(!vertexMap.containsKey(v)) {
            vertexMap.put(v, 0);
            adjacencyList.put(v, new ArrayList<>());
        }
        return true;
    }

    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true of v is part of the graph and false otherwise
     */
    public boolean vertexExists(ActivityVertex v){
        return vertexMap.containsKey(v);
    }

    /**
     * Add an edge of the graph
     *
     * @param e the edge to add to the graph
     * @return true if the edge was successfully added and false otherwise
     */
    public boolean addEdge(ActivityEdge e){
        if(edgeSet.contains(e)){
            return true;
        }
        if(!vertexExists(e.v1()) || !vertexExists(e.v2()))
            return false;
        adjacencyList .get(e.v1()) .add(e.v2());
        adjacencyList .get(e.v2()) .add(e.v1());
        edgeSet.add(e);
        return true;
    }

    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graoh and false otherwise
     */
    public boolean edgeExists(ActivityEdge e){
        return edgeSet.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */
    public boolean edgeExists(ActivityVertex v1, ActivityVertex v2){
        return adjacencyList.get(v1).contains(v2);
    }


    /**
     * Remove an edge from the graph
     *
     * @param e the edge to remove
     * @return true if e was successfully removed and false otherwise
     */
    public boolean remove(ActivityEdge e){
        if(edgeSet.contains(e)) {
            adjacencyList.get(e.v1()).remove(e.v2());
            adjacencyList.get(e.v2()).remove(e.v1());
            edgeSet.remove(e);
            return true;
        }
        return true;
    }

    /**
     * Remove a vertex from the graph
     *
     * @param v the vertex to remove
     * @return true if v was successfully removed and false otherwise
     */
    public boolean remove(ActivityVertex v){
        if(vertexMap.containsKey(v)){
            for(int i = 0; i < adjacencyList.get(v).size(); i ++){
                adjacencyList.get(adjacencyList.get(v).get(i)).remove(v);
            }
            adjacencyList.remove(v);
            vertexMap.remove(v);
        }
        return true;
    }

    /**
     * Obtain a set of all vertices in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return a set of all vertices in the graph
     */
    public Set<ActivityVertex> allVertices(){
        return vertexMap.keySet();
    }

    /**
     * Obtain a set of all edges incident on v.
     * Access to this set **should not** permit graph mutations.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     */
    public Set<ActivityEdge> incidentEdges(ActivityVertex v){
        Set<ActivityEdge> returnSet = new HashSet<>();

        for(int i = 0; i < adjacencyList.get(v).size(); i ++){
            returnSet.add(new ActivityEdge( v, adjacencyList.get(v).get(i)));
        }
        return returnSet;
    }

    /**
     * Obtain a set of all vertices incident on v.
     * Access to this set **should not** permit graph mutations.
     *
     * @param v the vertex of interest
     * @return all vertices adjacent on v
     */
    public Set<ActivityVertex> adjacentVertices(ActivityVertex v){
        Set<ActivityVertex> incVertices = new HashSet<>();

        for(int i = 0; i < adjacencyList.get(v).size(); i ++){
            incVertices.add(adjacencyList.get(v).get(i));
        }
        return incVertices;
    }

    /**
     * Obtain a set of all edges in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return all edges in the graph
     */
    public Set<ActivityEdge> allEdges(){
        return new HashSet<>(edgeSet);
    }

    /**
     * Obtain all the neighbours of vertex v.
     * Access to this map **should not** permit graph mutations.
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     */
    public Map<ActivityVertex, ActivityEdge> getNeighbours(ActivityVertex v){
        Map<ActivityVertex, ActivityEdge> vertexEdge = new HashMap<>();

        for(int i = 0; i < adjacencyList.get(v).size(); i ++){
            vertexEdge.put(adjacencyList.get(v).get(i), new ActivityEdge(v, adjacencyList.get(v).get(i)));
        }
        return vertexEdge;
    }
}
