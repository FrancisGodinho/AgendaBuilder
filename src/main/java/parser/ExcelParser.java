package main.java.parser;

import main.java.course_graph.ActivityGraph;

import java.util.List;

public interface ExcelParser {


    void initVertices(ActivityGraph g, List<String> courseNames);

}
