package irama.irama.Models;

/**
 * Created by enagi on 26/6/2017.
 */

public class clients {

    private long id;
    private String _id;
    private String cliendId;
    private String code;
    private String name;
    private String phone;
    private String email;
    private String rtn;
    private String address;
    private int isActive;
    public int isSync;

    public clients() {
    }

    public clients(String name, String phone, String email, String rtn, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.rtn = rtn;
        this.address = address;
    }

    public clients(String _id, String name, String rtn, String phone,String email, String address, int isActive) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.rtn = rtn;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
    }

    public clients(String _id, String name, String rtn, int isActive) {
        this._id = _id;
        this.name = name;
        this.rtn = rtn;
        this.isActive = isActive;
    }



    public int getIsSync() {
        return isSync;
    }

    public void setIsSync(int isSync) {
        this.isSync = isSync;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCliendId() {
        return cliendId;
    }

    public void setCliendId(String cliendId) {
        this.cliendId = cliendId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
