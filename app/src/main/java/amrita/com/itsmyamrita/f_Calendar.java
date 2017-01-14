package amrita.com.itsmyamrita;

/**
 * Created by Akhilesh on 7/20/2016.
 */

public class f_Calendar {
    String months,image;

    public f_Calendar(String months, String image) {
        this.months = months;
        this.image = image;

    }


    public f_Calendar() {
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
