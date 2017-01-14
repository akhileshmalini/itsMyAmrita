package amrita.com.itsmyamrita;

/**
 * Created by Akhilesh on 7/26/2016.
 */

public class f_Faculty {
   public String names, designation, research, qualification, email,image;

    public f_Faculty() {
    }

    public f_Faculty(String designation, String email , String image, String names, String qualification, String research) {
        this.names = names;
        this.designation = designation;
        this.research = research;
        this.qualification = qualification;
        this.email = email;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getNames() {
        return names;
    }

    public String getQualification() {
        return qualification;
    }

    public String getResearch() {
        return research;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setResearch(String research) {
        this.research = research;
    }
}
