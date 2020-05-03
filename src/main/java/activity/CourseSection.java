package main.java.activity;


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
