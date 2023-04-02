/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.BenXe;
import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.User;
import com.lqt.service.BenXeService;
import com.lqt.service.ChuyenXeService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class TraCuuController implements Initializable {

    private static final ChuyenXeService chuyenXeService = new ChuyenXeService();
    private static final BenXeService benXeService = new BenXeService();
    private User user;
    @FXML
    private Label lbUsername;
    @FXML
    private ComboBox<BenXe> cbBenDi;
    @FXML
    private ComboBox<BenXe> cbBenDen;
    @FXML
    private TableView<ChuyenXe> tbChuyenXe;

    public void setUserInfo(User user) {
        this.user = user;
        this.lbUsername.setText(user.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<BenXe> benXe;
        try {
            benXe = benXeService.getAllBenXe();
            this.cbBenDi.setItems(FXCollections.observableList(benXe));
            this.cbBenDen.setItems(FXCollections.observableList(benXe));
        } catch (SQLException ex) {
            Logger.getLogger(TraCuuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadTableColumns();
    }

    public void timChuyenXe(ActionEvent e) throws SQLException {
        if (this.cbBenDi.getValue() == null || this.cbBenDen.getValue() == null) {
            MessageBox.getBox("Chuyến xe", "Vui lòng chọn bến đi và bến đến",
                    Alert.AlertType.WARNING).show();
        } else {
            String tenBenXeDi = this.cbBenDi.getSelectionModel().getSelectedItem().getTenBen();
            String tenBenXeDen = this.cbBenDen.getSelectionModel().getSelectedItem().getTenBen();
            this.loadChuyenXe(tenBenXeDi, tenBenXeDen);
        }

    }

    private void loadChuyenXe(String benDi, String benDen) throws SQLException {
        List<ChuyenXe> dsChuyenXe = chuyenXeService.getChuyenXeByBenDiAndBenDen(benDi, benDen);
        this.tbChuyenXe.getItems().clear();
        this.tbChuyenXe.setItems(FXCollections.observableList(dsChuyenXe));
    }

    private void loadTableColumns() {

        TableColumn colMaChuyen = new TableColumn("Mã chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenXe"));

        TableColumn colTenChuyen = new TableColumn("Tên chuyến");
        colTenChuyen.setCellValueFactory(new PropertyValueFactory("tenChuyen"));

        TableColumn colThoiGianDi = new TableColumn("Thời gian");
        colThoiGianDi.setCellValueFactory(new PropertyValueFactory("thoiGianDi"));

        TableColumn colMaTaiXe = new TableColumn("Mã Tài xế");
        colMaTaiXe.setCellValueFactory(new PropertyValueFactory("maTaiXe"));

        TableColumn colMaTuyen = new TableColumn("Mã tuyến xe");
        colMaTuyen.setCellValueFactory(new PropertyValueFactory("maTuyenXe"));

        TableColumn colXoa = new TableColumn();
        colXoa.setCellFactory(p -> {
            Button btn = new Button("Xóa");

            btn.setOnAction(evt -> {
                Alert confirm = MessageBox.getBox("Chuyến xe",
                        "Bạn có chắc chắn muốn xóa chuyến xe này",
                        Alert.AlertType.CONFIRMATION);
                confirm.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        ChuyenXe chuyenXe = (ChuyenXe) cell.getTableRow().getItem();

                        try {
                            if (chuyenXeService.deleteChuyenXe(chuyenXe.getMaChuyenXe()) == true) {
                                MessageBox.getBox("Chuyến xe", "Xóa chuyến xe thành công!", Alert.AlertType.INFORMATION).show();
                                this.loadChuyenXe(this.cbBenDi.getValue().getTenBen(), this.cbBenDen.getValue().getTenBen());
                            } else {
                                MessageBox.getBox("Chuyến xe", "Xóa chuyến xe không thành công!", Alert.AlertType.WARNING).show();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            MessageBox.getBox("Chuyến xe", "Xóa chuyến xe không thành công!", Alert.AlertType.WARNING).show();
                            Logger.getLogger(ChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });
        this.tbChuyenXe.getColumns().addAll(colMaChuyen, colTenChuyen, colThoiGianDi, colMaTaiXe, colMaTuyen, colXoa);
    }

    //----------------------------Chuyển trang-----------------------------
    public void chuyenTrangAdmin(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trangChuAdmin.fxml"));
        Parent adminView = loader.load();
        Scene scene = new Scene(adminView);
        ChuyenXeController controller = loader.getController();
        controller.setUserInfo(user);
        stage.setScene(scene);
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
