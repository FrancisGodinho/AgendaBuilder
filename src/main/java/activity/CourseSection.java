package main.java.activity;


import java.util.ArrayList;
import java.util.List;

public class CourseSection {

    private int lecture;

    /**
     * Create a new course section
     * @param lecture the section of the lecture
     */
    public CourseSection(int lecture){
        this.lecture = lecture;
    }

    /**
     * get the section number of the course
     * @return the section number
     */
    public int getLecture(){
        return lecture;
    }

    /**
     * Get all the sections (excluding the lecture section)
     * @return a list of all sections
     */
    public List<String> getSections(){
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CourseSection){
            CourseSection that = (CourseSection) o;
            return lecture == that.lecture;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return lecture * lecture;
    }
}
