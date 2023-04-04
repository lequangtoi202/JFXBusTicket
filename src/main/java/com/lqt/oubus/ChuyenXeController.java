/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.TaiXe;
import com.lqt.pojo.TuyenXe;
import com.lqt.pojo.User;
import com.lqt.pojo.Xe;
import com.lqt.service.ChuyenXeService;
import com.lqt.service.TaiXeService;
import com.lqt.service.TuyenXeService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class ChuyenXeController implements Initializable {

    private static final ChuyenXeService chuyenXeService = new ChuyenXeService();
    private static final TuyenXeService tuyenXeService = new TuyenXeService();
    private static final TaiXeService taiXeService = new TaiXeService();
    private User user;
    @FXML
    private Label lbUsername;
    @FXML
    private TextField txtTenChuyen;
    @FXML
    private TextField txtGioDi;
    @FXML
    private DatePicker dNgayDi;
    @FXML
    private TextField txtMaChuyen;
    @FXML
    private ComboBox<TaiXe> cbTaiXe;
    @FXML
    private ComboBox<TuyenXe> cbTuyenXe;
    @FXML
    private TableView<ChuyenXe> tbChuyenXe;

    public void setUserInfo(User user) {
        this.user = user;
        this.lbUsername.setText(user.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadChuyenXe();
            List<TaiXe> dsTaiXe = taiXeService.getAllTaiXe();
            List<TuyenXe> dsTuyenXe = tuyenXeService.getAllTuyenXe();
            this.cbTaiXe.setItems(FXCollections.observableList(dsTaiXe));
            this.cbTuyenXe.setItems(FXCollections.observableList(dsTuyenXe));

        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadTableColumns();
    }

    public void loadChuyenXe() throws SQLException {
        List<ChuyenXe> dsChuyenXe = chuyenXeService.getAllChuyenXe();
        if (!dsChuyenXe.isEmpty()) {
            this.tbChuyenXe.getItems().clear();
            this.tbChuyenXe.setItems(FXCollections.observableList(dsChuyenXe));
        } else {
            MessageBox.getBox("Chuyến xe", "Không có chuyến xe.", Alert.AlertType.WARNING).show();
        }
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

        TableColumn colChon = new TableColumn();
        colChon.setCellFactory(p -> {
            Button btn = new Button("Chọn");

            btn.setOnAction(evt -> {
                Button b = (Button) evt.getSource();
                TableCell cell = (TableCell) b.getParent();
                ChuyenXe chuyenXe = (ChuyenXe) cell.getTableRow().getItem();
                LocalTime gioDi = chuyenXe.getThoiGianDi().toLocalTime();
                this.txtMaChuyen.setText(String.valueOf(chuyenXe.getMaChuyenXe()));
                this.txtTenChuyen.setText(chuyenXe.getTenChuyen());
                try {
                    this.cbTaiXe.setValue(taiXeService.getTaiXeById(chuyenXe.getMaTaiXe()));
                    this.cbTuyenXe.setValue(tuyenXeService.getTuyenXeById(chuyenXe.getMaTuyenXe()));
                } catch (SQLException ex) {
                    Logger.getLogger(ChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.txtGioDi.setText(gioDi.toString());
                this.dNgayDi.setValue(chuyenXe.getThoiGianDi().toLocalDate());
            });
            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

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
                            if (chuyenXeService.deleteChuyenXe(chuyenXe.getMaChuyenXe())) {
                                MessageBox.getBox("Chuyến xe", "Xóa chuyến xe thành công!", Alert.AlertType.INFORMATION).show();
                                this.loadChuyenXe();
                            } else {
                                MessageBox.getBox("Chuyến xe", "Xóa chuyến xe thất bại", Alert.AlertType.ERROR).show();
                            }
                        } catch (SQLException ex) {
                            MessageBox.getBox("Chuyến xe", "Xóa chuyến xe thất bại", Alert.AlertType.ERROR).show();
                            Logger.getLogger(ChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });
        this.tbChuyenXe.getColumns().addAll(colMaChuyen, colTenChuyen, colThoiGianDi, colMaTaiXe, colMaTuyen, colChon, colXoa);
    }

    public void clear() throws SQLException {
        this.txtGioDi.setText("");
        this.txtMaChuyen.setText("");
        this.txtTenChuyen.clear();
        this.dNgayDi.setValue(LocalDate.now());
        this.cbTaiXe.getItems().clear();
        this.cbTuyenXe.getItems().clear();
        List<TuyenXe> dsTuyenXe = tuyenXeService.getAllTuyenXe();
        this.cbTuyenXe.setItems(FXCollections.observableList(dsTuyenXe));
        List<TaiXe> dsTaiXe = taiXeService.getAllTaiXe();
        this.cbTaiXe.setItems(FXCollections.observableList(dsTaiXe));
    }

    public void themHandler(ActionEvent evt) throws SQLException {
        if (kiemTraThongTin()) {
            String tenChuyen = this.txtTenChuyen.getText();
            String gioDi = this.txtGioDi.getText();
            LocalDate ngayDi = this.dNgayDi.getValue();
            LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, LocalTime.parse(gioDi));
            TuyenXe tuyenXe = this.cbTuyenXe.getSelectionModel().getSelectedItem();
            TaiXe taiXe = this.cbTaiXe.getSelectionModel().getSelectedItem();
            ChuyenXe chuyenXe = new ChuyenXe(tenChuyen, thoiGianDi, tuyenXe.getMaTuyenXe(), taiXe.getMaTaiXe(), false);
            if (chuyenXeService.addChuyenXe(chuyenXe)) {
                MessageBox.getBox("Chuyến xe", "Tạo chuyến xe thành công!",
                        Alert.AlertType.INFORMATION).show();
                this.loadChuyenXe();
            } else {
                MessageBox.getBox("Chuyến xe", "Tạo chuyến xe thất bại!",
                        Alert.AlertType.ERROR).show();
            }
            clear();
        }
    }

    public boolean kiemTraThongTin() {
        Pattern gioDiRegex = Pattern.compile("^\\d{2}:\\d{2}$");
        Pattern ngayDiRegex = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        if (this.txtTenChuyen.getText().trim().isEmpty()) {
            MessageBox.getBox("Xe", "Vui lòng nhập tên chuyến xe",
                    Alert.AlertType.WARNING).show();
            return false;
        }

        if (this.dNgayDi.getValue() == null) {
            MessageBox.getBox("Xe", "Vui lòng chọn ngày đi",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        Matcher ngayDiMatcher = ngayDiRegex.matcher(this.dNgayDi.getValue().toString());
        Matcher gioDiMatcher = gioDiRegex.matcher(this.txtGioDi.getText().trim());
        if (this.dNgayDi.getValue().isBefore(LocalDate.now()) && !ngayDiMatcher.matches()) {
            MessageBox.getBox("Xe", "Ngày đi không hợp lệ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (!gioDiMatcher.matches()) {
            MessageBox.getBox("Xe", "Giờ đi không đúng định dạng",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.cbTuyenXe.getValue() == null) {
            MessageBox.getBox("Xe", "Vui lòng chọn tuyến xe",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.cbTaiXe.getValue() == null) {
            MessageBox.getBox("Xe", "Vui lòng chọn tài xế",
                    Alert.AlertType.WARNING).show();
            return false;
        }

        return true;
    }

    public void suaHandler(ActionEvent evt) throws SQLException {
        if (this.txtMaChuyen.getText().isEmpty()) {
            MessageBox.getBox("Xe", "Vui lòng chọn chuyến xe để sửa",
                    Alert.AlertType.WARNING).show();
        } else {
            if (kiemTraThongTin()) {
                int maChuyen = Integer.parseInt(this.txtMaChuyen.getText());
                String tenChuyen = this.txtTenChuyen.getText();
                String gioDi = this.txtGioDi.getText();
                LocalDate ngayDi = this.dNgayDi.getValue();
                LocalTime gioDiL = LocalTime.parse(gioDi);
                LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, gioDiL);
                System.out.println(thoiGianDi);
                TuyenXe tuyenXe = this.cbTuyenXe.getSelectionModel().getSelectedItem();
                TaiXe taiXe = this.cbTaiXe.getSelectionModel().getSelectedItem();
                ChuyenXe chuyenXe = new ChuyenXe(maChuyen, tenChuyen, thoiGianDi, tuyenXe.getMaTuyenXe(), taiXe.getMaTaiXe(), false);
                if (chuyenXeService.updateChuyenXe(chuyenXe, maChuyen)) {
                    MessageBox.getBox("Chuyến xe", "Sửa chuyến xe thành công!",
                            Alert.AlertType.INFORMATION).show();
                    this.loadChuyenXe();
                } else {
                    MessageBox.getBox("Chuyến xe", "Sửa chuyến xe thất bại!",
                            Alert.AlertType.ERROR).show();
                }
                clear();
            }
        }
    }
    

    //----------------------------Chuyển trang-----------------------------
    public void chuyenTrangTraCuu(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("traCuuChuyenXe.fxml"));
        Parent traCuuView = loader.load();
        Scene scene = new Scene(traCuuView);
        stage.setScene(scene);
        TraCuuController controller = loader.getController();
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
