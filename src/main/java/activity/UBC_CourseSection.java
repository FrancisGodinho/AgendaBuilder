package main.java.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UBC_CourseSection extends CourseSection{


    private String lab, tutorial, discussion;

    public UBC_CourseSection(int sectionNum, String lab, String tutorial, String discussion){
        super(sectionNum);

        if(lab == null || lab.equals(" "))
            this.lab = new String();
        else
            this.lab = lab;

        if(tutorial == null || tutorial.equals(" "))
            this.tutorial = new String();
        else
            this.tutorial = tutorial;

        if(discussion == null || discussion.equals(" "))
            this.discussion = new String();
        else
            this.discussion = lab;

    }

    /**
     * get the lab section
     * @return the lab section
     */
    public String getLab(){
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
        if(o instanceof UBC_CourseSection){
            UBC_CourseSection that = (UBC_CourseSection) o;
            return this.getLecture() == that.getLecture() && discussion.equals(that.discussion)
                    && lab.equals(that.lab) && tutorial.equals(that.tutorial);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = this.getLecture() * this.getLecture() + lab.hashCode() * lab.hashCode() + tutorial.hashCode() * tutorial.hashCode() + discussion.hashCode() * discussion.hashCode();
        return result;
    }
}
