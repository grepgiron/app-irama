package irama.irama.Models;

/**
 * Created by enagi on 4/7/2017.
 */

public class parameters extends order {

    public String _id;
    public String name;
    public order order;

    public parameters(String _id, String name, order order) {
        this._id = _id;
        this.name = name;
        this.order = order;
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

    public order getOrder() {
        return order;
    }

    public void setOrder(order order) {
        this.order = order;
    }
}
