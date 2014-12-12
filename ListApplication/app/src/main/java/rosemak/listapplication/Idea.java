package rosemak.listapplication;

import java.io.Serializable;

/**
 * Created by stevierose on 12/10/14.
 */
public class Idea implements Serializable{
    private static final long serialVersionUID = 12450459L;
    public Idea() {

    }
    public String idea_name = "";
    public String idea_description = "";
    public String idea_priority = "";

    public String getIdea_description() {
        return idea_description;
    }

    public String getIdea_name() {
        return idea_name;
    }

    public String getIdea_priority() {
        return idea_priority;
    }

    public void setIdea_description(String idea_description) {
        this.idea_description = idea_description;
    }

    public void setIdea_name(String idea_name) {
        this.idea_name = idea_name;
    }

    public void setIdea_priority(String idea_priority) {
        this.idea_priority = idea_priority;
    }

    @Override
    public String toString() {
        return idea_name;
    }
}
