package amrita.com.itsmyamrita;

/**
 * Created by Akhilesh on 7/20/2016.
 */

public class f_Events {
    String evContactPerson,evContactPhone,evDate,evDescription,evLocation,evName,evOnDuty,evPoster,evTime;

    public f_Events(String evContactPerson, String evContactPhone, String evDate, String evDescription, String evLocation, String evName, String evOnDuty, String evPoster, String evTime) {
        this.evContactPerson = evContactPerson;
        this.evContactPhone = evContactPhone;
        this.evDate = evDate;
        this.evDescription = evDescription;
        this.evLocation = evLocation;
        this.evName = evName;
        this.evOnDuty = evOnDuty;
        this.evPoster = evPoster;
        this.evTime = evTime;
    }

    public f_Events(String evName, String evPoster, String evTime, String evDate) {
        this.evName = evName;
        this.evPoster = evPoster;
        this.evTime = evTime;
        this.evDate = evDate;
    }

    public f_Events() {
    }


    public String getEvContactPerson() {
        return evContactPerson;
    }

    public void setEvContactPerson(String evContactPerson) {
        this.evContactPerson = evContactPerson;
    }

    public String getEvContactPhone() {
        return evContactPhone;
    }

    public void setEvContactPhone(String evContactPhone) {
        this.evContactPhone = evContactPhone;
    }

    public String getEvDate() {
        return evDate;
    }

    public void setEvDate(String evDate) {
        this.evDate = evDate;
    }

    public String getEvDescription() {
        return evDescription;
    }

    public void setEvDescription(String evDescription) {
        this.evDescription = evDescription;
    }

    public String getEvLocation() {
        return evLocation;
    }

    public void setEvLocation(String evLocation) {
        this.evLocation = evLocation;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvOnDuty() {
        return evOnDuty;
    }

    public void setEvOnDuty(String evOnDuty) {
        this.evOnDuty = evOnDuty;
    }

    public String getEvPoster() {
        return evPoster;
    }

    public void setEvPoster(String evPoster) {
        this.evPoster = evPoster;
    }

    public String getEvTime() {
        return evTime;
    }

    public void setEvTime(String evTime) {
        this.evTime = evTime;
    }
}
