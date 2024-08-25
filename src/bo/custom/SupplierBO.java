package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.SupplierDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SupplierBO extends SuperBO {
    boolean addSupplier(SupplierDTO Supplier) throws ClassNotFoundException, SQLException;

    boolean deleteSupplier(String id) throws ClassNotFoundException, SQLException;

    boolean updateSupplier(SupplierDTO Supplier) throws ClassNotFoundException, SQLException;

    SupplierDTO searchSupplier(String id) throws ClassNotFoundException, SQLException;

    ObservableList<SupplierDTO> getAllSupplier() throws ClassNotFoundException, SQLException;

    int getRowCount()throws ClassNotFoundException,SQLException;
}
