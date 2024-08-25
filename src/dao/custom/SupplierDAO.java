package dao.custom;

import dao.CrudDAO;
import entity.Customer;
import entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    String getSupplierLastId()throws Exception;
    int getRowCount()throws ClassNotFoundException, SQLException;

}
