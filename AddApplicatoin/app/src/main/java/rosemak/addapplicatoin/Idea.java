package rosemak.addapplicatoin;

import java.io.Serializable;

/**
 * Steven Roseman
 */
public class Idea implements Serializable {

    private static final long serialVersionUID = 12450459L;
    public Idea() {

    }
    public String mIdeaName = "";
    public String mIdeaDescription = "";
    public String mIdeaPriority = "";

    public Idea(String _name, String _description, String _priority) {

        mIdeaName = _name;
        mIdeaDescription = _description;
        mIdeaPriority = _priority;
    }

    public String getmIdeaDescription() {
        return mIdeaDescription;
    }

    public String getmIdeaName() {
        return mIdeaName;
    }

    public String getmIdeaPriority() {
        return mIdeaPriority;
    }

    public void setmIdeaDescription(String mIdeaDescription) {
        this.mIdeaDescription = mIdeaDescription;
    }

    public void setmIdeaName(String mIdeaName) {
        this.mIdeaName = mIdeaName;
    }

    public void setmIdeaPriority(String mIdeaPriority) {
        this.mIdeaPriority = mIdeaPriority;
    }

    @Override
    public String toString() {
        return mIdeaName;
    }
}
