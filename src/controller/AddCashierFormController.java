package controller;

import bo.BOFactory;
import bo.custom.CashierBO;
import bo.custom.CustomerBO;
import bo.custom.Impl.CashierIBOmpl;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import com.sun.deploy.xml.GeneralEntity;
import dto.CashierDTO;
import dto.CustomerDTO;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class AddCashierFormController implements Initializable {
    public TextField CashierID;
    public TextField Password;
    public TextField CashierName;
    public TextField Login;
    public ImageView imageid;
    public Button setOnAction;
    public DatePicker BirthDay;
    public TextField CashierAddress;
    public TextField picTitle;
    public TableView tblCashier;
    public TableColumn colCashId;
    public TableColumn colCashName;
    public TableColumn colCashAddress;
    public TableColumn colcashBirthDay;

    CashierBO cashierBO = new CashierIBOmpl();
    String picName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER);
        colCashId.setCellValueFactory(new PropertyValueFactory<>("castID"));
        colCashName.setCellValueFactory(new PropertyValueFactory<>("castName"));
        colCashAddress.setCellValueFactory(new PropertyValueFactory<>("castAddress"));
        colcashBirthDay.setCellValueFactory(new PropertyValueFactory<>("castBirthDay"));
        loadAllCashier();
    }

    private void loadAllCashier() {
        try {
            ObservableList<CashierDTO> allCashier = cashierBO.getAllCashier();
            tblCashier.setItems(allCashier);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOnAction() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        picName = file.getAbsolutePath();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageid.setImage(image);
        } catch (IOException ignored) {

        }
    }


    public void addOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdded = cashierBO.addCashier(new CashierDTO(
                    CashierID.getText(),
                    CashierName.getText(),
                    BirthDay.getValue().toString(),
                    CashierAddress.getText(),
                    picName,
                    Login.getText(),
                    Password.getText()));
            String tilte;
            String message;
            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Cashier Is Added";
                loadAllCashier();


            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Cashier Is Not Added";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Cashier Is Already On The Sever";
            String message = "Cashier Is Not Added";
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            String castID = CashierID.getText();
            CashierDTO searchCashier = cashierBO.searchCashier(CashierID.getText());
            System.out.println(searchCashier.toString());
            CashierID.setText(searchCashier.getCastID());
            CashierName.setText(searchCashier.getCastName());
            BirthDay.setValue(LocalDate.parse(searchCashier.getCastBirthDay()));
            CashierAddress.setText(searchCashier.getCastAddress());
            setPic(searchCashier.getCastPhoto());
            Password.setText(searchCashier.getCastPassword());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPic(String castPhoto) {
        try {
            File file = new File(castPhoto);
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageid.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testingIcon(ActionEvent actionEvent) {

    }

    public void cashierUpdateOnAction(ActionEvent actionEvent) {
        try {
            String castID = CashierID.getText();
            String castName = CashierName.getText();
            String castBirthDay = BirthDay.getValue().toString();
            String castAddress = CashierAddress.getText();
            String caslogin = Login.getText();
            String caspassword = Password.getText();
            CashierDTO cashierDTO = new CashierDTO(castID, castName, castBirthDay, castAddress, "castPhoto", caslogin, caspassword);
            boolean updateCashier = cashierBO.updateCashier(cashierDTO);
            String tilte;
            String message;
            if (updateCashier) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Not Update ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Cashier Is Updated";
                loadAllCashier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Update", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Un Successful";
                message = "Cashier Is Not Updated";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //Customer Update Is Over(With Notification)

    }

    public void cashierDeleteOnAction(ActionEvent actionEvent) {
        String ID = CashierID.getText();
        try {
            boolean isDelete = cashierBO.deleteCashier(ID);
            String tilte;
            String message;
            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Delete Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Delete Success";
                message = "Cashier Is Deleted";
                loadAllCashier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Deleted", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Cashier Not Found";
                message = "Sorry";
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }


    public void tblCashierClick() {
        CashierDTO c = (CashierDTO) tblCashier.getSelectionModel().getSelectedItem();
        CashierID.setText(c.getCastID());
        CashierName.setText(c.getCastName());
        CashierAddress.setText(c.getCastAddress());
        BirthDay.setValue(LocalDate.parse(c.getCastBirthDay()));
        Login.setText(c.getCastlogin());
        Password.setText(c.getCastPassword());

    }
}
