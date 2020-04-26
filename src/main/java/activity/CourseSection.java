package main.java.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseSection {

    private int lecture;
    private String lab, tutorial, discussion;

    public CourseSection(int lecture){
        this.lecture = lecture;
    }

    public CourseSection(int sectionNum, String lab, String tutorial, String discussion){
        this.lecture = sectionNum;
        this.lab = lab;
        this.tutorial = tutorial;
        this.discussion = discussion;
    }

    /**
     * get the section number of the course
     * @return the section number
     */
    public int getLecture(){
        return lecture;
    }

    /**
     * get the lab section
     * @return the lab section
     */
    public String getLab() {
        return lab;
    }

    /**
     * get the tutorial section
     * @return the tutorial section
     */
    public String getTutorial(){
        return tutorial;
    }

    /**
     * get the discussion section
     * @return the discussion section
     */
    public String getDiscussion(){
        return discussion;
    }

    /**
     * get the sections for the lab, tutorial and discussion
     * @return a list of sections for the lab, tutorial and discussion (in that order)
     */
    public List<String> getSections(){
        return new ArrayList<>(Arrays.asList(lab, tutorial, discussion));
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CourseSection){
            CourseSection that = (CourseSection) o;
            return lecture == that.lecture && discussion.equals(that.discussion)
                    && lab.equals(that.lab) && tutorial.equals(that.tutorial);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = lecture ^ (lecture >>> 32);
        result = 31 * result + lab.hashCode() ^ (lab.hashCode() >>> 32);
        result = 31 * result + tutorial.hashCode() ^ (tutorial.hashCode() >>> 32);
        result = 31 * result + discussion.hashCode() ^ (discussion.hashCode() >>> 32);
        return result;
    }
}
