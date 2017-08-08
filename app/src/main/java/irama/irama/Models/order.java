package irama.irama.Models;

/**
 * Created by enagi on 28/6/2017.
 */

public class order {

    private String id;
    private String description;
    private int isUrgent;
    private String requestOn;
    private String code;
    private String clientId;
    private String productId;
    private String employeeId;
    private String serieId;
    private String orderTypetId;
    public int isSync;

    public order() {
    }

    public order(String s, String toString, String s1, int anInt) {
    }

    public order(String id, String description, int isUrgent, String requestOn, String orderTypetId, int isSync) {
        this.id = id;
        this.description = description;
        this.isUrgent = isUrgent;
        this.requestOn = requestOn;
        this.orderTypetId = orderTypetId;
        this.isSync = isSync;
    }

    public order(String description, String requestOn, int isSync) {
        this.description = description;
        this.requestOn = requestOn;
        this.isSync = isSync;
    }

    public order(String description, int isUrgent, String requestOn, String code, String clientId, String productId, String employeeId, String serieId, String orderTypetId) {
        this.description = description;
        this.isUrgent = isUrgent;
        this.requestOn = requestOn;
        this.code = code;
        this.clientId = clientId;
        this.productId = productId;
        this.employeeId = employeeId;
        this.serieId = serieId;
        this.orderTypetId = orderTypetId;
    }

    public order(String id, String description, int isUrgent, String requestOn, String code, String clientId, String productId, String employeeId, String serieId, String orderTypetId) {
        this.id = id;
        this.description = description;
        this.isUrgent = isUrgent;
        this.requestOn = requestOn;
        this.code = code;
        this.clientId = clientId;
        this.productId = productId;
        this.employeeId = employeeId;
        this.serieId = serieId;
        this.orderTypetId = orderTypetId;
    }

    public int getIsSync() {
        return isSync;
    }

    public void setIsSync(int isSync) {
        this.isSync = isSync;
    }

    public String getId() {
        return id;
    }

    public int getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(int isUrgent) {
        this.isUrgent = isUrgent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(String requestOn) {
        this.requestOn = requestOn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSerieId() {
        return serieId;
    }

    public void setSerieId(String serieId) {
        this.serieId = serieId;
    }

    public String getOrderTypetId() {
        return orderTypetId;
    }

    public void setOrderTypetId(String orderTypetId) {
        this.orderTypetId = orderTypetId;
    }
}
