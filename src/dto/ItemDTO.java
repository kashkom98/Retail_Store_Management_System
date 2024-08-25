package dto;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ItemDTO extends ArrayList<ItemDTO> {
    private String itemCode;
    private String SupplierID;
    private String Description;
    private String packSize;
    private String unitPrice;
    private String QtyOnHand;
    private String BatchID;

    public ItemDTO(String itemCode, String SupplierID, String description, String packSize, String unitPrice, String qtyOnHand, String batchID) {
        this.itemCode = itemCode;
        this.SupplierID = SupplierID;
        Description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        BatchID = batchID;
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public String getBatchID() {
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }

    @Override
    public Stream<ItemDTO> stream() {
        return null;
    }
}
