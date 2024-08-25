package controller;

import animatefx.animation.FadeIn;
import bo.BOFactory;
import bo.custom.CashierBO;
import bo.custom.Impl.LoginBOImpl;
import bo.custom.LoginBO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import dto.CashierDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginFormController {
    public TextField username;
    public PasswordField pswrd;
    public AnchorPane root;
    CashierBO cashierBO;


    public void LoginOnAction() throws IOException {

        String userName = username.getText().trim();
        String password = pswrd.getText().trim();
        if (Pattern.compile("^(admin)$").matcher(userName).matches()) {


        } else {
            username.setStyle("-fx-background-color: transparent;-fx-border-color:blue;");
            username.requestFocus();


        }
        if (Pattern.compile("^(admin)$").matcher(userName).matches()) {
        } else {


        }
        if (userName.length() > 0 && password.length() > 0) {
            if (userName.equalsIgnoreCase("admin")
                    && password.equals("admin")) {

                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
                window.centerOnScreen();
                String title = "Sign In";
                String message = "RETAIL STORE MANAGEMENT SYSTEM";

            } else {
                LoginBO loginBO = new LoginBOImpl();
                try {
                    CashierDTO cashierDTO = loginBO.getValidated(username.getText());
                    System.out.println(cashierDTO.getCastID()+" id from login form");
                    System.out.println(cashierDTO.getCastlogin() + " userName");
                    System.out.println(cashierDTO.getCastPassword() + " password");

                    if (cashierDTO.getCastlogin().equals(username.getText()) &&
                            cashierDTO.getCastPassword().equals(pswrd.getText())) {

                        Stage window = (Stage) this.root.getScene().getWindow();
//                        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/CashierForm.fxml"))));
                        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/CashierForm.fxml"));
                        Parent parent =  fxmlLoader.load();
                        CashierFormController controller = fxmlLoader.getController();
                        System.out.println("sending data");
                        controller.setCashierID(cashierDTO.getCastID());
                        window.setScene(new Scene(parent));
                        window.centerOnScreen();
                        window.show();

                    } else {
                        System.out.println("Invalid Password/Password");
                    }
                } catch (NullPointerException e) {
                    //System.out.println("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            username.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            username.requestFocus();
        }
    }


    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
