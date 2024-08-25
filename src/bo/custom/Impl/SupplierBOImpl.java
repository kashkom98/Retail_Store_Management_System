package bo.custom.Impl;

import bo.SuperBO;
import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.CustomerDAO;
import dao.custom.SupplierDAO;
import dto.CustomerDTO;
import dto.SupplierDTO;
import entity.Customer;
import entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO SupplierDAO= (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Supplier);

    @Override
    public boolean addSupplier(SupplierDTO Supplier) throws ClassNotFoundException, SQLException {
        return SupplierDAO.add(new Supplier(Supplier.getSupplierID(),Supplier.getSupplierName(),Supplier.getSupplierAddress(),Supplier.getSupplierPhone(),Supplier.getSupplierEmail()));

    }

    @Override
    public boolean deleteSupplier(String id) throws ClassNotFoundException, SQLException {
        return SupplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO Supplier) throws ClassNotFoundException, SQLException {
        return SupplierDAO.update(new Supplier(Supplier.getSupplierID(),Supplier.getSupplierName(),Supplier.getSupplierAddress(),Supplier.getSupplierPhone(),Supplier.getSupplierEmail()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws ClassNotFoundException, SQLException {
        Supplier search = SupplierDAO.search(id);
        return new SupplierDTO(search.getSupplierID(),search.getSupplierName(),search.getSupplierAddress(),search.getSupplierPhone(),search.getSupplierEmail());
    }

    @Override
    public ObservableList<SupplierDTO> getAllSupplier() throws ClassNotFoundException, SQLException {
        ObservableList<Supplier> all =SupplierDAO.getAll();
        ObservableList<SupplierDTO> allSupplier = FXCollections.observableArrayList();
        for (Supplier ID : all) {
            SupplierDTO dto = new SupplierDTO(ID.getSupplierID(),ID.getSupplierName(),ID.getSupplierAddress(),ID.getSupplierPhone(),ID.getSupplierEmail());
            allSupplier.add(dto);
        }
        return allSupplier;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        return SupplierDAO.getRowCount();
    }
}
