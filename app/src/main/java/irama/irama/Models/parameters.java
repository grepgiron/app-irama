package irama.irama.Models;

/**
 * Created by enagi on 4/7/2017.
 */

public class parameters {

   public String name;
    public String description;
    public String date;
    public String code;
    public int isSync;


    public parameters() {

    }

    public parameters(String name, String description, String date, String code, int isSync) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.code = code;
        this.isSync = isSync;
    }


    public String getName() {
        return name;
    }

    public int getIsSync() {
        return isSync;
    }

    public void setIsSync(int isSync) {
        this.isSync = isSync;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
