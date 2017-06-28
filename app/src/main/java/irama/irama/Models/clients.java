package irama.irama.Models;

/**
 * Created by enagi on 26/6/2017.
 */

public class clients {

    private String _id;
    private String code;
    private String name;
    private String rtn;
    private boolean isActive;

    public clients(String _id, String code, String name, String rtn, boolean isActive) {
        this._id = _id;
        this.code = code;
        this.name = name;
        this.rtn = rtn;
        this.isActive = isActive;
    }

    public clients(String _id, String name, String rtn, boolean isActive) {
        this._id = _id;
        this.name = name;
        this.rtn = rtn;
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

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
