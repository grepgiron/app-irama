package irama.irama.Models;

/**
 * Created by enagi on 4/8/2017.
 */

public class product {

    private String _id;
    private String name;
    private String description;
    private String unit;

    public product() {
    }

    public product(String _id, String name, String description, String unit) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.unit = unit;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
