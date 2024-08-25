package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SupplierDAO;
import entity.Orderdetail;
import entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean add(Supplier ID) throws ClassNotFoundException, SQLException {
        String sql = "insert into Supplier values(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, ID.getSupplierID(),ID.getSupplierName(),ID.getSupplierAddress(),ID.getSupplierPhone(),ID.getSupplierEmail());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE SupplierID= ?";
        return CrudUtil.executeUpdate(sql, ID);
    }

    @Override
    public boolean update(Supplier ID) throws ClassNotFoundException, SQLException {
        String sql = "update Supplier set SupplierName =?,SupplierAddress=?,SupplierPhone=?,SupplierEmail=? where SupplierID=?";
        return CrudUtil.executeUpdate(sql, ID.getSupplierName(), ID.getSupplierAddress(), ID.getSupplierPhone(), ID.getSupplierEmail(),ID.getSupplierID());
    }

    @Override
    public Supplier search(String ID) throws ClassNotFoundException, SQLException {
        String sql = "select * from Supplier where SupplierID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, ID);
        if (rst.next()) {
            return new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from Supplier";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ObservableList<Supplier> allSupplier = FXCollections.observableArrayList();
        while (rst.next()) {
            allSupplier.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return allSupplier;
    }

    @Override
    public String getSupplierLastId() throws Exception {
        return null;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(SupplierID) FROM Supplier";
        ResultSet rst = CrudUtil.executeQuery(SQL, new Object[0]);
        return rst.next() ? rst.getInt(1) : -1;
    }
}
