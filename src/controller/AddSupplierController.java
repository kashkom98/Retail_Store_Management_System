package controller;

import bo.BOFactory;
import bo.custom.SupplierBO;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import dto.SupplierDTO;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {
    public TextField txtSupId;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtCompanyName;
    public TextField txtPhoneNo;
    public Button btnSave;
    public TableView<SupplierDTO> tblSup;
    public TableColumn<Object, Object> colSupId;
    public TableColumn<Object, Object> colComName;
    public TableColumn<Object, Object> colAddress;
    public TableColumn<Object, Object> colPhoneNo;
    public TableColumn<Object, Object> colEmail;
    SupplierBO SupplierBO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SupplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Supplier);
        colSupId.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colComName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("SupplierPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("SupplierEmail"));
        setTxtcustID();
        loadAllSupplier();
    }

    public void setTxtcustID() {
        try {
            int id = this.SupplierBO.getRowCount();
            if (id == 0) {
                this.txtSupId.setText("S001");
            }

            if (id > 0 && id <= 8) {
                this.txtSupId.setText("S00" + (id + 1));
            }

            if (id >= 9 && id <= 98) {
                this.txtSupId.setText("S0" + (id + 1));
            }

            if (id >= 99) {
                this.txtSupId.setText("S" + (id + 1));
            }
        } catch (ClassNotFoundException | SQLException var2) {
            var2.printStackTrace();
        }
        //customer Count Code
    }

    public void addOnAction() {
        try {
            boolean isAdded = SupplierBO.addSupplier(new SupplierDTO(
                    txtSupId.getText(),
                    txtCompanyName.getText(),
                    txtAddress.getText(),
                    txtPhoneNo.getText(),
                    txtEmail.getText()));
            String tilte;
            String message;
            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Supplier Is Added";
                loadAllSupplier();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Supplier Not Added", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Supplier Is Not Added";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Supplier Is Already On The Sever";
            String message = "Supplier Is Not Added";
        }
    }


    public void searchOnAction() {
        try {
            String SupplierID = txtSupId.getText();
            SupplierDTO searchSaplay = SupplierBO.searchSupplier(SupplierID);
            if (searchSaplay != null) {
                txtSupId.setText(searchSaplay.getSupplierID());
                txtCompanyName.setText(searchSaplay.getSupplierName());
                txtAddress.setText(searchSaplay.getSupplierAddress());
                txtPhoneNo.setText(searchSaplay.getSupplierPhone());
                txtEmail.setText(searchSaplay.getSupplierEmail());

                String tilte = "Supplier Searched ";
                String message = "Supplier Is " + "" + txtCompanyName.getText() + "";


            } else {
                String tilte = "Searched Customer Not Found";
                String message = "Try Again";
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        //Customer Search Is Over(With Notification)
    }

    public void updateOnAction() {
        try {
            String SupplierID = txtSupId.getText();
            String SupplierName = txtCompanyName.getText();
            String SupplierAddress = txtAddress.getText();
            String SupplierPhone = txtPhoneNo.getText();
            String SupplierEmail = txtEmail.getText();
            SupplierDTO SupplierDTO = new SupplierDTO(SupplierID, SupplierName, SupplierAddress, SupplierPhone, SupplierEmail);
            boolean updateCustomer = SupplierBO.updateSupplier(SupplierDTO);
            String tilte;
            String message;
            if (updateCustomer) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Supplier Is Updated";
                loadAllSupplier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Supalyer Not Update", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Un Successful";
                message = "Customer Is Not Updated";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteOnAction() {
        String ID = txtSupId.getText();
        try {
            boolean isDelete = SupplierBO.deleteSupplier(ID);
            String tilte;
            String message;
            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier Delete Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Delete Success";
                message = "Supplier Is Deleted";
                loadAllSupplier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Supplier Not Delete", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Suppier Not Found";
                message = "Sorry";
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private void loadAllSupplier() {
        try {
            ObservableList<SupplierDTO> allSupplier = SupplierBO.getAllSupplier();
            tblSup.setItems(allSupplier);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void tblSupplierClick(MouseEvent mouseEvent) {
        SupplierDTO c = tblSup.getSelectionModel().getSelectedItem();
        txtSupId.setText(c.getSupplierID());
        txtAddress.setText(c.getSupplierAddress());
        txtEmail.setText(c.getSupplierEmail());
        txtCompanyName.setText(c.getSupplierName());
        txtPhoneNo.setText(c.getSupplierPhone());

    }
}

