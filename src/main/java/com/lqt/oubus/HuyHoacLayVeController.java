/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.Status;
import com.lqt.pojo.TrangThaiGhe;
import com.lqt.pojo.User;
import com.lqt.pojo.VeXe;
import com.lqt.service.GheService;
import com.lqt.service.VeXeService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class HuyHoacLayVeController {

    private static final VeXeService veXeService = new VeXeService();
    private static final GheService gheService = new GheService();
    private User user;
    @FXML
    private Label lbUsername;
    @FXML
    private TextField txtMaVe;
    @FXML
    private TextField txtMaVeXe;
    @FXML
    private TextField txtThoiGianBan;
    @FXML
    private TextField txtMaKH;
    @FXML
    private TextField txtMaNV;
    @FXML
    private TextField txtMaChuyenXe;
    @FXML
    private TextField txtMaGhe;
    @FXML
    private TextField txtTrangThai;

    public void setUserInfo(User user) throws SQLException {
        this.user = user;
        lbUsername.setText(user.getUsername());
    }

    public void timVeHandler(ActionEvent evt) throws SQLException {
        if (txtMaVe.getText().trim().isBlank()) {
            MessageBox.getBox("Vé Xe", "Vui lòng nhập mã vé",
                    Alert.AlertType.WARNING).show();
        } else {
            int maVe = Integer.parseInt(this.txtMaVe.getText().trim());
            VeXe veXe = veXeService.getVeXeBookedById(maVe);
            clear();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            if (veXe != null) {
                this.txtMaGhe.setText(String.valueOf(veXe.getMaGhe()));
                this.txtMaNV.setText(String.valueOf(veXe.getMaNV()));
                this.txtMaVeXe.setText(String.valueOf(veXe.getMaVeXe()));
                this.txtMaChuyenXe.setText(String.valueOf(veXe.getMaChuyenXe()));
                this.txtMaKH.setText(String.valueOf(veXe.getMaKH()));
                this.txtThoiGianBan.setText(veXe.getThoiGianBan().format(formatter));
                this.txtTrangThai.setText(veXe.getTrangThai().toString());
            } else {
                MessageBox.getBox("Vé Xe", "KHÔNG CÓ VÉ XE CÓ MÃ LÀ " + maVe + " HOẶC VÉ ĐÃ ĐƯỢC MUA.",
                        Alert.AlertType.WARNING).show();
            }
        }
    }

    public void clear() {
        this.txtMaChuyenXe.clear();
        this.txtMaGhe.clear();
        this.txtMaKH.clear();
        this.txtMaVeXe.clear();
        this.txtThoiGianBan.clear();
        this.txtMaNV.clear();
        this.txtTrangThai.clear();
    }

    public void huyVeHandler(ActionEvent evt) throws SQLException {
        if (!txtMaVeXe.getText().isEmpty()) {
            int maVeXe = Integer.parseInt(this.txtMaVeXe.getText());
            VeXe veXe = veXeService.getVeXeBookedById(maVeXe);
            Alert confirm = MessageBox.getBox("Vé xe",
                    "Bạn có muốn hủy vé xe này không?",
                    Alert.AlertType.CONFIRMATION);
            confirm.showAndWait().ifPresent((ButtonType res) -> {
                if (res == ButtonType.OK) {
                    try {
                        if (veXeService.updateVeXe(veXe, maVeXe, Status.Canceled) == true) {
                            if (!gheService.updateTrangThaiGheByMaGhe(veXe.getMaGhe(), TrangThaiGhe.Empty)) {
                                MessageBox.getBox("Ghế", "Hủy ghế thất bại!", Alert.AlertType.WARNING).show();
                            } else {
                                MessageBox.getBox("Vé xe", "Hủy vé thành công!", Alert.AlertType.INFORMATION).show();
                                clear();
                            }
                        } else {
                            MessageBox.getBox("Vé xe", "Hủy vé thất bại", Alert.AlertType.WARNING).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(HuyHoacLayVeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            MessageBox.getBox("Vé xe",
                    "Vui lòng nhập mã vé muốn hủy",
                    Alert.AlertType.WARNING).show();
        }
    }
    
    

    public void layVeHandler(ActionEvent evt) throws SQLException {
        if (!txtMaVeXe.getText().isEmpty()) {
            int maVeXe = Integer.parseInt(this.txtMaVeXe.getText());
            VeXe veXe = veXeService.getVeXeBookedById(maVeXe);
            Alert confirm = MessageBox.getBox("Vé xe",
                    "Bạn có muốn lấy vé xe này không?",
                    Alert.AlertType.CONFIRMATION);
            confirm.showAndWait().ifPresent((ButtonType res) -> {
                if (res == ButtonType.OK) {
                    try {
                        if (veXeService.updateVeXe(veXe, maVeXe, Status.Done) == true) {
                            MessageBox.getBox("Vé xe", "Lấy vé thành công!", Alert.AlertType.INFORMATION).show();
                            clear();
                        } else {
                            MessageBox.getBox("Vé xe", "Lấy vé thất bại", Alert.AlertType.INFORMATION).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(HuyHoacLayVeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            MessageBox.getBox("Vé xe", "Vui lòng điền mã vé.", Alert.AlertType.WARNING).show();
        }
    }

    // ------------------------------------------Chuyển trang----------------
    public void chuyenTrangBanVe(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("banVe.fxml"));
        Parent BanVeView = loader.load();
        Scene scene = new Scene(BanVeView);
        stage.setScene(scene);
        BanVeController controller = loader.getController();
        controller.setUserInfo(user);
        stage.show();
    }

    public void chuyenTrangDatVe(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("datVe.fxml"));
        Parent DatVeView = loader.load();
        Scene scene = new Scene(DatVeView);
        DatVeController controller = loader.getController();
        controller.setUserInfo(user);
        stage.setScene(scene);
        stage.show();
    }

    public void chuyenTrangDoiVe(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doiVe.fxml"));
        Parent BanVeView = loader.load();
        Scene scene = new Scene(BanVeView);
        stage.setScene(scene);
        DoiVeContoller controller = loader.getController();
        controller.setUserInfo(user);
        stage.show();
    }

    public void DangXuat(ActionEvent e) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginView = loader.load();
        Scene scene = new Scene(loginView);
        stage.setScene(scene);
        stage.show();
    }
}
