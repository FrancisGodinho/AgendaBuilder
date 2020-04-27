package main.java.course_graph;

import main.java.activity.CourseActivity;

import javax.swing.text.EditorKit;
import java.util.NoSuchElementException;

public class ActivityEdge {

    ActivityVertex v1, v2;

    /**
     * Create a new edge
     * @param v1 the first edge (cannot be null)
     * @param v2 the second edge (cannot be the same as the first or null)
     */
    public ActivityEdge(ActivityVertex v1, ActivityVertex v2){

        if(v1 == null || v2 == null)
            throw new IllegalArgumentException("Vertex cannot be NULL");
        if(v1.equals(v2))
            throw new IllegalArgumentException("Vertices cannot be the same");
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Obtain the first vertex
     * @return the first vertex
     */
    public ActivityVertex v1(){
        return v1;
    }

    /**
     * Obtain the second vertex
     * @return the second vertex
     */
    public ActivityVertex v2(){
        return v2;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof ActivityEdge){
            ActivityEdge that = (ActivityEdge) o;
            return that.v1.equals(this.v1) && that.v2.equals(this.v2);
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = v1.hashCode() ^ (v1.hashCode() >>> 32);
        result = 31 * result + v2.hashCode() ^ (v2.hashCode() >>> 32);
        return result;
    }

    /**
     * Checks if a vertex is incident to the edge
     * @param v the vertex to check
     * @return true if the vertex is incident to this edge, false otherwise
     */
    public boolean incident(ActivityVertex v){
        if(v == null)
            return false;
        if(v.equals(v1) || v.equals(v2))
            return true;
        return false;
    }

    /**
     * Determines if the edge intersects this edge
     * @param e the edge to check
     * @return true if the edge intersects this edge, false otherwise
     */
    public boolean intersects(ActivityEdge e){
        if(e == null)
            return false;
        return this.incident(e.v1) || this.incident(e.v2);
    }

    /**
     * Determines if the edge intersects this edge
     * @param e the edge to check
     * @return the vertex if the edge intersects
     * @throws NoSuchElementException if the edge does not intersect
     */
    public ActivityVertex intersection(ActivityEdge e) throws NoSuchElementException{
        if(e == null)
            throw new NoSuchElementException("No common vertex");
        if(this.v1.equals(e.v1) || this.v1.equals(e.v2))
            return this.v1;
        if(this.v2.equals(e.v1) || this.v2.equals(e.v2))
            return v2;
        throw new NoSuchElementException("No common vertex");
    }

    /**
     * Returns a distinct vertex of the edge
     * @param v the vertex to check against
     * @return a vertex that is not v
     */
    public ActivityVertex distinctVertex(ActivityVertex v){
        if(this.v1.equals(v))
            return this.v2;
        return this.v1;
    }

    /**
     * Returns a distinct vertex of the edge
     * @param e the edge to check
     * @return a vertex that is not part of the edge e
     */
    public ActivityVertex distinctVertex(ActivityEdge e){
        if (this.equals(e))
            throw new NoSuchElementException("No distinct vertex");

        ActivityVertex sv;
        try {
            sv = this.intersection(e);
        }
        catch (NoSuchElementException nse) {
            // when there is no common vertex,
            // return any vertex (deterministic choice of v1 is okay).
            return v1;
        }
        if (v1.equals(sv)) {
            return v2;
        } else {
            return v1;
        }
    }


}
