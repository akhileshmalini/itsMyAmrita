package amrita.com.itsmyamrita;

/**
 * Created by Akhilesh on 7/1/2016.
 */

public class f_Members {
    String image, position, names,email,phone;

    public f_Members(String email,String image,String names, String phone, String position) {
            this.image = image;
        this.position = position;
        this.names = names;
        this.email=email;
        this.phone=phone;

    }

    public f_Members() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
