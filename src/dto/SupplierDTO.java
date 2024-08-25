package dto;

public class SupplierDTO {
    private String SupplierID;
    private String SupplierName;
    private String SupplierAddress;
    private String SupplierPhone;
    private String SupplierEmail;

    public SupplierDTO(String SupplierID, String SupplierName, String SupplierAddress, String SupplierPhone, String SupplierEmail) {
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.SupplierAddress = SupplierAddress;
        this.SupplierPhone = SupplierPhone;
        this.SupplierEmail = SupplierEmail;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public void setSupplierAddress(String SupplierAddress) {
        this.SupplierAddress = SupplierAddress;
    }

    public String getSupplierPhone() {
        return SupplierPhone;
    }

    public void setSupplierPhone(String SupplierPhone) {
        this.SupplierPhone = SupplierPhone;
    }

    public String getSupplierEmail() {
        return SupplierEmail;
    }

    public void setSupplierEmail(String SupplierEmail) {
        this.SupplierEmail = SupplierEmail;
    }
}
