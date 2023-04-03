/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.Role;
import com.lqt.pojo.User;
import com.lqt.service.RoleService;
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
    private static final RoleService roleService = new RoleService();
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    public void login(ActionEvent evt) throws SQLException, IOException {
        String username = this.txtUsername.getText().trim();
        String password = this.txtPassword.getText().trim();
        if (username.isEmpty()) {
            MessageBox.getBox("Login", "Vui lòng nhập tên đăng nhập",
                    Alert.AlertType.ERROR).show();
        } else if (password.isEmpty()) {
            MessageBox.getBox("Login", "Vui lòng nhập mật khẩu",
                    Alert.AlertType.ERROR).show();
        }else {
            User user = UserService.getUsernameAndPassword(username, password);
            if (user == null) {
                MessageBox.getBox("Login", "Tài khoản không tồn tại!",
                        Alert.AlertType.ERROR).show();
            } else {
                if (user.getPassword().equals(password)) {
                    MessageBox.getBox("Login", "Đăng nhập thành công",
                            Alert.AlertType.INFORMATION).show();
                    Role role = roleService.getRoleById(user.getRoleId());
                    if (role.getName().equals("ADMIN")) {
                        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("trangChuAdmin.fxml"));
                        Parent manageView = loader.load();
                        Scene scene = new Scene(manageView);
                        ChuyenXeController controller = loader.getController();
                        controller.setUserInfo(user);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        // chuyen trang
                        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("datVe.fxml"));
                        Parent manageView = loader.load();
                        Scene scene = new Scene(manageView);
                        DatVeController controller = loader.getController();
                        controller.setUserInfo(user);
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    MessageBox.getBox("Login", "Mật khẩu không đúng",
                            Alert.AlertType.ERROR).show();
                }
            }
        }

    }
}
