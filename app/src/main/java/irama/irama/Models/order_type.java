package irama.irama.Models;

/**
 * Created by enagi on 4/7/2017.
 */

public class order_type {
    public String _id;
    public String code;
    public String name;
    public String description;
    public int isActive;

    public order_type(String _id, String code, String name, String description, int isActive) {
        this._id = _id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public String get_id() {

        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
