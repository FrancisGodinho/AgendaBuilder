package main.java.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UBC_CourseSection extends CourseSection{


    private String lab, tutorial, discussion;

    public UBC_CourseSection(int sectionNum, String lab, String tutorial, String discussion){
        super(sectionNum);
        this.lab = lab;
        this.tutorial = tutorial;
        this.discussion = discussion;
    }

    /**
     * get the lab section
     * @return the lab section
     * @throws NoSuchElementException if there is no lab associated
     */
    public String getLab() throws NoSuchElementException{
        if(lab == null)
            throw new NoSuchElementException("No lab found");
        return lab;
    }

    /**
     * get the tutorial section
     * @return the tutorial section
     * @throws NoSuchElementException if there is no tutorial associated
     */
    public String getTutorial() throws NoSuchElementException{
        if(lab == null)
            throw new NoSuchElementException("No tutorial found");
        return tutorial;
    }

    /**
     * get the discussion section
     * @return the discussion section
     * @throws NoSuchElementException if there is no discussion associated
     */
    public String getDiscussion() throws NoSuchElementException{
        if(lab == null)
            throw new NoSuchElementException("No discussion found");
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
