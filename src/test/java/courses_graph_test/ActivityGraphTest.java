package test.java.courses_graph_test;


import com.sun.source.tree.AssertTree;
import main.java.activity.UBC_CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.util.Day;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.HashSet;

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
        UBC_CourseActivity course1 = new UBC_CourseActivity("Elec", 201, sec1, time1, time2);
        UBC_CourseActivity course2 = new UBC_CourseActivity("Elec", 201, sec2, time3, time4);
        UBC_CourseActivity course3 = new UBC_CourseActivity("Math", 253, sec3, time5, time6);
        UBC_CourseActivity course4 = new UBC_CourseActivity("Math", 253, sec4, time7, time8);


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

        //check that the vertices were added works
        Assert.assertTrue(testGraph.vertexExists(vertex1));
        Assert.assertTrue(testGraph.vertexExists(vertex2));
        Assert.assertTrue(testGraph.vertexExists(vertex3));
        Assert.assertTrue(testGraph.vertexExists(vertex4));

        //check that the edges were added
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex1, vertex3)));
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex1, vertex4)));
        Assert.assertTrue(testGraph.edgeExists(new ActivityEdge(vertex4, vertex3)));

        //check that these edges are not in the graph
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex1, vertex2)));
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex2, vertex3)));
        Assert.assertFalse(testGraph.edgeExists(new ActivityEdge(vertex2, vertex4)));
    }
}
