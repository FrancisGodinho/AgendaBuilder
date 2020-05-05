package test.java.courses_graph_test;


import main.java.activity.CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.util.Day;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ActivityGraphTest {

    private ActivityGraph testGraph = new ActivityGraph();

    @Test
    public void addVertexAndEdgeTest(){

        //these are some times
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 10, 0);
        TimeInstance time3 = new TimeInstance(Day.TUES, 10, 0);
        TimeInstance time4 = new TimeInstance(Day.TUES, 12, 0);
        TimeInstance time5 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time6 = new TimeInstance(Day.WED, 10, 0);
        TimeInstance time7 = new TimeInstance(Day.FRI, 8, 0);
        TimeInstance time8 = new TimeInstance(Day.FRI, 10, 0);

        //some possible course sections
        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "");

        //these are courses
        CourseActivity course1 = new CourseActivity("Elec", 201, sec1, time1, time2);
        CourseActivity course2 = new CourseActivity("Elec", 201, sec2, time3, time4);
        CourseActivity course3 = new CourseActivity("Math", 253, sec3, time5, time6);
        CourseActivity course4 = new CourseActivity("Math", 253, sec4, time7, time8);


        //create vertices
        ActivityVertex vertex1 = new ActivityVertex(course1);
        ActivityVertex vertex2 = new ActivityVertex(course2);
        ActivityVertex vertex3 = new ActivityVertex(course3);
        ActivityVertex vertex4 = new ActivityVertex(course4);

        //add vertex to graph
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);
        testGraph.addVertex(vertex4);

        //add some edges to graph
        testGraph.addEdge(new ActivityEdge(vertex1, vertex3));
        testGraph.addEdge(new ActivityEdge(vertex1, vertex4));
        testGraph.addEdge(new ActivityEdge(vertex3, vertex4));

        //testing vertexExists
        Assert.assertTrue(testGraph.vertexExists(vertex1));
        Assert.assertTrue(testGraph.vertexExists(vertex2));
        Assert.assertTrue(testGraph.vertexExists(vertex3));
        Assert.assertTrue(testGraph.vertexExists(vertex4));

        //testing edgeExits
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex1, vertex3)));
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex1, vertex4)));
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex4, vertex3)));

        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex1, vertex2)));
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex2, vertex3)));
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex2, vertex4)));

        //testing edgeExits with vertex input
        Assert.assertTrue(testGraph.edgeExists(vertex1, vertex3));
        Assert.assertTrue(testGraph.edgeExists(vertex1, vertex4));
        Assert.assertTrue(testGraph.edgeExists(vertex4, vertex3));

        Assert.assertFalse(testGraph.edgeExists(vertex1, vertex2));
        Assert.assertFalse(testGraph.edgeExists(vertex2, vertex3));
        Assert.assertFalse(testGraph.edgeExists(vertex2, vertex4));

    }

    @Test
    public void removeVertexAndEdgeTest(){
        //these are some times
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 10, 0);
        TimeInstance time3 = new TimeInstance(Day.TUES, 10, 0);
        TimeInstance time4 = new TimeInstance(Day.TUES, 12, 0);
        TimeInstance time5 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time6 = new TimeInstance(Day.WED, 10, 0);
        TimeInstance time7 = new TimeInstance(Day.FRI, 8, 0);
        TimeInstance time8 = new TimeInstance(Day.FRI, 10, 0);

        //some possible course sections
        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "");

        //these are courses
        CourseActivity course1 = new CourseActivity("Elec", 201, sec1, time1, time2);
        CourseActivity course2 = new CourseActivity("Elec", 201, sec2, time3, time4);
        CourseActivity course3 = new CourseActivity("Math", 253, sec3, time5, time6);
        CourseActivity course4 = new CourseActivity("Math", 253, sec4, time7, time8);


        //create vertices
        ActivityVertex vertex1 = new ActivityVertex(course1);
        ActivityVertex vertex2 = new ActivityVertex(course2);
        ActivityVertex vertex3 = new ActivityVertex(course3);
        ActivityVertex vertex4 = new ActivityVertex(course4);

        //add vertex to graph
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);
        testGraph.addVertex(vertex4);

        //testing remove vertex
        Assert.assertTrue(testGraph.remove(vertex1));
        Assert.assertTrue(testGraph.remove(vertex2));
        Assert.assertTrue(testGraph.remove(vertex3));

        //testing vertexExists
        Assert.assertFalse(testGraph.vertexExists(vertex1));
        Assert.assertFalse(testGraph.vertexExists(vertex2));
        Assert.assertFalse(testGraph.vertexExists(vertex3));
        Assert.assertTrue(testGraph.vertexExists(vertex4));

        //add vertices back
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);

        //add some edges to graph
        testGraph.addEdge(new ActivityEdge(vertex1, vertex3));
        testGraph.addEdge(new ActivityEdge(vertex1, vertex4));
        testGraph.addEdge(new ActivityEdge(vertex4, vertex3));

        //testing remove edge
        Assert.assertTrue(testGraph.remove(new ActivityEdge(vertex1, vertex3)));
        Assert.assertTrue(testGraph.remove(new ActivityEdge(vertex1, vertex4)));

        //testing edgeExits
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex1, vertex3)));
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex1, vertex4)));
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex4, vertex3)));
    }

    @Test
    public void returnVertexAndEdgeSetTest(){
        //these are some times
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 10, 0);
        TimeInstance time3 = new TimeInstance(Day.TUES, 10, 0);
        TimeInstance time4 = new TimeInstance(Day.TUES, 12, 0);
        TimeInstance time5 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time6 = new TimeInstance(Day.WED, 10, 0);
        TimeInstance time7 = new TimeInstance(Day.FRI, 8, 0);
        TimeInstance time8 = new TimeInstance(Day.FRI, 10, 0);

        //some possible course sections
        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "");

        //these are courses
        CourseActivity course1 = new CourseActivity("Elec", 201, sec1, time1, time2);
        CourseActivity course2 = new CourseActivity("Elec", 201, sec2, time3, time4);
        CourseActivity course3 = new CourseActivity("Math", 253, sec3, time5, time6);
        CourseActivity course4 = new CourseActivity("Math", 253, sec4, time7, time8);

        //create vertices
        ActivityVertex vertex1 = new ActivityVertex(course1);
        ActivityVertex vertex2 = new ActivityVertex(course2);
        ActivityVertex vertex3 = new ActivityVertex(course3);
        ActivityVertex vertex4 = new ActivityVertex(course4);

        testGraph = new ActivityGraph();

        //add vertex to graph
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);
        testGraph.addVertex(vertex4);

        //add some edges to graph
        testGraph.addEdge(new ActivityEdge(vertex1, vertex3));
        testGraph.addEdge(new ActivityEdge(vertex1, vertex4));
        testGraph.addEdge(new ActivityEdge(vertex4, vertex3));

        //creating the expected vertex output
        HashSet<ActivityVertex> outputVertexSet = new HashSet<>();
        outputVertexSet.add(vertex1);
        outputVertexSet.add(vertex2);
        outputVertexSet.add(vertex3);
        outputVertexSet.add(vertex4);

        //test allVertices
        assertEquals(outputVertexSet, testGraph.allVertices());



        //creating expected edge output
        HashSet<ActivityEdge> outputEdgeSet = new HashSet<>();
        outputEdgeSet.add(new ActivityEdge(vertex1, vertex3));
        outputEdgeSet.add(new ActivityEdge(vertex1, vertex4));
        outputEdgeSet.add(new ActivityEdge(vertex4, vertex3));

        //test allEdges
        assertEquals(outputEdgeSet, testGraph.allEdges());
        
        //remove an edge
        testGraph.remove(new ActivityEdge(vertex4, vertex3));
        Assert.assertFalse(outputEdgeSet.equals(testGraph.allEdges()));

        //remove vertex4
        testGraph.remove(vertex4);
        assertNotSame(outputVertexSet, testGraph.allVertices());
    }

    @Test
    public void incidentEdgesAndVerticesTest(){
        //these are some times
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 10, 0);
        TimeInstance time3 = new TimeInstance(Day.TUES, 10, 0);
        TimeInstance time4 = new TimeInstance(Day.TUES, 12, 0);
        TimeInstance time5 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time6 = new TimeInstance(Day.WED, 10, 0);
        TimeInstance time7 = new TimeInstance(Day.FRI, 8, 0);
        TimeInstance time8 = new TimeInstance(Day.FRI, 10, 0);

        //some possible course sections
        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "");

        //these are courses
        CourseActivity course1 = new CourseActivity("Elec", 201, sec1, time1, time2);
        CourseActivity course2 = new CourseActivity("Elec", 201, sec2, time3, time4);
        CourseActivity course3 = new CourseActivity("Math", 253, sec3, time5, time6);
        CourseActivity course4 = new CourseActivity("Math", 253, sec4, time7, time8);

        //create vertices
        ActivityVertex vertex1 = new ActivityVertex(course1);
        ActivityVertex vertex2 = new ActivityVertex(course2);
        ActivityVertex vertex3 = new ActivityVertex(course3);
        ActivityVertex vertex4 = new ActivityVertex(course4);

        //add vertex to graph
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);
        testGraph.addVertex(vertex4);

        //add some edges to graph
        testGraph.addEdge(new ActivityEdge(vertex1, vertex3));
        testGraph.addEdge(new ActivityEdge(vertex1, vertex4));
        testGraph.addEdge(new ActivityEdge(vertex2, vertex1));
        testGraph.addEdge(new ActivityEdge(vertex4, vertex3));

        //creating expected edge output
        HashSet<ActivityEdge> outputEdgeSet = new HashSet<>();
        outputEdgeSet.add(new ActivityEdge(vertex1, vertex3));
        outputEdgeSet.add(new ActivityEdge(vertex1, vertex4));
        outputEdgeSet.add(new ActivityEdge(vertex1, vertex2));

        //testing incidentEdges
        assertEquals(outputEdgeSet, testGraph.incidentEdges(vertex1));

        //add an edge to expectedEdgeSet
        outputEdgeSet.add(new ActivityEdge(vertex4, vertex3));

        //test incidentEdges
        Assert.assertFalse(outputEdgeSet.equals(testGraph.incidentEdges(vertex1)));

        //creating outputVertexSet
        HashSet<ActivityVertex> outputVertexSet = new HashSet<>();
        outputVertexSet.add(vertex3);
        outputVertexSet.add(vertex4);
        outputVertexSet.add(vertex2);

        //test adjacentVertices
        assertEquals(outputVertexSet, testGraph.adjacentVertices(vertex1));

        //add a vertex to outputVertexSet
        outputVertexSet.add(vertex1);

        //test adjacentVertices
        Assert.assertFalse(outputVertexSet.equals(testGraph.adjacentVertices(vertex1)));
    }

    @Test
    public void getNeighboursTest(){
        //these are some times
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 10, 0);
        TimeInstance time3 = new TimeInstance(Day.TUES, 10, 0);
        TimeInstance time4 = new TimeInstance(Day.TUES, 12, 0);
        TimeInstance time5 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time6 = new TimeInstance(Day.WED, 10, 0);
        TimeInstance time7 = new TimeInstance(Day.FRI, 8, 0);
        TimeInstance time8 = new TimeInstance(Day.FRI, 10, 0);

        //some possible course sections
        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "");

        //these are courses
        CourseActivity course1 = new CourseActivity("Elec", 201, sec1, time1, time2);
        CourseActivity course2 = new CourseActivity("Elec", 201, sec2, time3, time4);
        CourseActivity course3 = new CourseActivity("Math", 253, sec3, time5, time6);
        CourseActivity course4 = new CourseActivity("Math", 253, sec4, time7, time8);

        //create vertices
        ActivityVertex vertex1 = new ActivityVertex(course1);
        ActivityVertex vertex2 = new ActivityVertex(course2);
        ActivityVertex vertex3 = new ActivityVertex(course3);
        ActivityVertex vertex4 = new ActivityVertex(course4);

        //add vertex to graph
        testGraph.addVertex(vertex1);
        testGraph.addVertex(vertex2);
        testGraph.addVertex(vertex3);
        testGraph.addVertex(vertex4);

        //add some edges to graph
        testGraph.addEdge(new ActivityEdge(vertex1, vertex3));
        testGraph.addEdge(new ActivityEdge(vertex1, vertex4));
        testGraph.addEdge(new ActivityEdge(vertex2, vertex1));
        testGraph.addEdge(new ActivityEdge(vertex4, vertex3));

        //creating outputNeighbourMap
        Map<ActivityVertex, ActivityEdge> outputNeighbourMap = new HashMap<>();
        outputNeighbourMap.put(vertex3, new ActivityEdge(vertex1, vertex3));
        outputNeighbourMap.put(vertex2, new ActivityEdge(vertex1, vertex2));
        outputNeighbourMap.put(vertex4, new ActivityEdge(vertex4, vertex1));

        //testing getNeighbours
        assertEquals(outputNeighbourMap, testGraph.getNeighbours(vertex1));

        //remove a key and value from the outputNeighbourMap
        outputNeighbourMap.remove(vertex4, new ActivityEdge(vertex4, vertex1));

        //testing getNeighbours
        Assert.assertFalse(outputNeighbourMap.equals(testGraph.getNeighbours(vertex1)));
    }
}
