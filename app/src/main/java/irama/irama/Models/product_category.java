package irama.irama.Models;

/**
 * Created by enagi on 26/6/2017.
 */

public class product_category {

    private int id;
    private String name;
    private String _id;
    private String description;

    public product_category(int id, String name, String _id, String description) {
        this.id = id;
        this.name = name;
        this._id = _id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setId(int id) {
        this.id = id;
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
}
