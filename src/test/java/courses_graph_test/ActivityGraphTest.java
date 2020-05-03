package test.java.courses_graph_test;


import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class ActivityGraphTest {

    private ActivityGraph test = new ActivityGraph();

    @Test
    public void addVertexTest(){
        ActivityVertex v = ;
        assert test.addVertex(v) == true;
    }
}
