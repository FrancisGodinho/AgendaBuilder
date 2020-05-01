package main.java.course_graph;

import main.java.activity.UBC_CourseActivity;

public class ActivityVertex {

    private final UBC_CourseActivity activity;
    private int color;

    /**
     * Create a new ActivityVertex
     * @param vertexActivity the activity that the vertex represents
     */
    public ActivityVertex(UBC_CourseActivity vertexActivity){
        this.activity = vertexActivity;
        color = 0;
    }

    /**
     * Obtain the activity of the vertex
     * @return the course activity of the vertex
     */
    public UBC_CourseActivity getActivity(){
        return activity;
    }

    /**
     * Obtain the color of the vertex
     * @return the color of the vertex
     */
    public int getColor(){
        return color;
    }

    /**
     * Update the color of the vertex
     * @param newColor the new color of the vertex
     */
    public void updateColor(int newColor){
        color = newColor;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof ActivityVertex){
            ActivityVertex that = (ActivityVertex)o;
            return that.color == this.color && that.activity.equals(this.activity);
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = color ^ (color >>> 32);
        result = 31 * result + activity.hashCode() ^ (activity.hashCode() >>> 32);
        return result;
    }




}
