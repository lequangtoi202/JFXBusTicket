/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.User;
import com.lqt.service.UserService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class LoginController {
    private static final UserService UserService = new UserService();
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    
    public void login(ActionEvent evt) throws SQLException, IOException{
        String username = this.txtUsername.getText().trim();
        String password = this.txtPassword.getText().trim();
        
        User user = UserService.getUsernameAndPassword(username, password);
        if (user.getPassword().equals(password)){
            MessageBox.getBox("Login", "Login Success", 
                Alert.AlertType.INFORMATION).show();
            
            // chuyen trang
            Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("datVe.fxml"));
            Parent manageView = loader.load();
            Scene scene = new Scene(manageView);
            DatVeController controller = loader.getController();
            controller.setUserInfo(user);
            stage.setScene(scene);
            stage.show();
            
        }else{
            MessageBox.getBox("Login", "Login Fail!", 
                Alert.AlertType.ERROR).show();
        }
    }
}
