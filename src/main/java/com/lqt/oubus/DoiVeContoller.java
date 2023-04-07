/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.Ghe;
import com.lqt.pojo.KhachHang;
import com.lqt.pojo.Status;
import com.lqt.pojo.TrangThaiGhe;
import com.lqt.pojo.TuyenXe;
import com.lqt.pojo.User;
import com.lqt.pojo.VeXe;
import com.lqt.pojo.Xe;
import com.lqt.service.ChuyenXeService;
import com.lqt.service.GheService;
import com.lqt.service.KhachHangService;
import com.lqt.service.TuyenXeService;
import com.lqt.service.VeXeService;
import com.lqt.service.XeService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class DoiVeContoller {

    private static final VeXeService veXeService = new VeXeService();
    private static final XeService xeService = new XeService();
    private static final GheService gheService = new GheService();
    private static final KhachHangService khachHangService = new KhachHangService();
    private static final ChuyenXeService chuyenXeService = new ChuyenXeService();
    private static final TuyenXeService tuyenXeService = new TuyenXeService();
    private User user;
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbThanhTien;
    @FXML
    private ComboBox<Ghe> cbGhe;
    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtDienThoai;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtCCCD;
    @FXML
    private TextField txtBienSoXe;
    @FXML
    private ComboBox<ChuyenXe> cbChuyenXe;
    @FXML
    private TextField txtGioDi;
    @FXML
    private TextField txtMaVe;
    @FXML
    private DatePicker dNgaySinh;
    @FXML
    private DatePicker dThoiGianDi;

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
            if (veXe != null) {
                Ghe ghe = gheService.getGheById(veXe.getMaGhe());
                ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(veXe.getMaChuyenXe());
                TuyenXe tuyenXe = tuyenXeService.getTuyenXeById(chuyenXe.getMaTuyenXe());
                Xe xe = xeService.getXeById(ghe.getMaXe());
                List<ChuyenXe> dsChuyenXe = chuyenXeService.getAllChuyenXe();
                List<Ghe> dsGhe = gheService.getAllGheEmptyByMaXe(xe.getMaXe());

                KhachHang khachHang = khachHangService.getKhachHangById(veXe.getMaKH());
                this.cbChuyenXe.setItems(FXCollections.observableList(dsChuyenXe));
                this.cbGhe.setItems(FXCollections.observableList(dsGhe));
                this.txtBienSoXe.setText(xe.getBienSoXe());
                this.txtCCCD.setText(khachHang.getCCCD());
                this.txtDiaChi.setText(khachHang.getDiaChi());
                this.txtDienThoai.setText(khachHang.getDienThoai());
                this.cbChuyenXe.setValue(chuyenXe);
                this.cbGhe.setValue(ghe);
                this.txtGioDi.setText(chuyenXe.getThoiGianDi().toLocalTime().toString());
                this.txtTenKH.setText(khachHang.getTenKH());

                this.dThoiGianDi.setValue(chuyenXe.getThoiGianDi().toLocalDate());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.dNgaySinh.setValue(LocalDate.parse(khachHang.getNgaySinh().toString(), formatter));
                this.lbThanhTien.setText(tuyenXe.getBangGia() + " VNĐ");

            } else {
                MessageBox.getBox("Vé Xe", "KHÔNG CÓ VÉ XE CÓ MÃ LÀ " + maVe + " HOẶC VÉ ĐÃ ĐƯỢC MUA.",
                        Alert.AlertType.WARNING).show();
            }
        }
    }

    public boolean kiemTraThongTin() {
        if (this.txtTenKH.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập tên khách hàng",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtDienThoai.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập số điện thoại",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtDiaChi.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập địa chỉ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtCCCD.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập căn cước công dân",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        Pattern dienThoaiRegex = Pattern.compile("^0\\d{9}$");
        Pattern CCCDRegex = Pattern.compile("^[0-9]{9,12}$");
        Matcher dienThoaimatcher = dienThoaiRegex.matcher(this.txtDienThoai.getText().trim());
        Matcher CCCDmatcher = CCCDRegex.matcher(this.txtCCCD.getText().trim());
        Pattern ngaySinhRegex = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher ngaySinhMatchar = ngaySinhRegex.matcher(this.dNgaySinh.getValue().toString());
        if (!dienThoaimatcher.matches()) {
            MessageBox.getBox("Điện thoại", "Số điện thoại không hợp lệ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (!CCCDmatcher.matches()) {
            MessageBox.getBox("CCCD", "Căn cước công dân không hợp lệ.",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        
        if (this.dNgaySinh.getValue().isAfter(LocalDate.now())&&!ngaySinhMatchar.matches()) {
            MessageBox.getBox("Ngày sinh", "Ngày sinh không hợp lệ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.cbChuyenXe.getValue() == null) {
            MessageBox.getBox("Xe", "Vui lòng chọn chuyến xe",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.cbGhe.getValue() == null) {
            MessageBox.getBox("Ghế", "Vui lòng chọn ghế ngồi",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        return true;
    }

    public void suaVeHandler(ActionEvent evt) throws SQLException, ParseException {
        if (txtMaVe.getText().trim().isBlank()) {
            MessageBox.getBox("Vé Xe", "Vui lòng nhập mã vé",
                    Alert.AlertType.WARNING).show();
        } else {
            int maVe = Integer.parseInt(this.txtMaVe.getText().trim());
            VeXe veXe = veXeService.getVeXeBookedById(maVe);
            if (veXe != null) {
                if (kiemTraThongTin()) {
                    LocalDate ngayDi = this.dThoiGianDi.getValue();
                    String gioDi = txtGioDi.getText();
                    LocalTime localTime = LocalTime.parse(gioDi);

                    LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, localTime);
                    Duration duration = Duration.between(LocalDateTime.now(), thoiGianDi);
                    long minutes = duration.toMinutes();

                    if (minutes < 60) {
                        MessageBox.getBox("Vé Xe", "Thời gian đổi vé không hợp lệ!",
                                Alert.AlertType.WARNING).show();
                    } else {
                        //sửa thông tin khách hàng
                        KhachHang khachHang = khachHangService.getKhachHangById(veXe.getMaKH());
                        khachHang.setCCCD(this.txtCCCD.getText());
                        khachHang.setDiaChi(this.txtDiaChi.getText());
                        khachHang.setDienThoai(this.txtDienThoai.getText());
                        khachHang.setTenKH(this.txtTenKH.getText());
                        Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(this.dNgaySinh.getValue().toString());
                        khachHang.setNgaySinh(ngaySinh);
                        if (!khachHangService.updateKhachHang(khachHang)) {
                            MessageBox.getBox("Khách hàng", "Cập nhật thông tin khách hàng thất bại.",
                                    Alert.AlertType.WARNING).show();
                        }
                        int maGheDaDat = veXe.getMaGhe();
                        if (!gheService.updateTrangThaiGheByMaGhe(maGheDaDat, TrangThaiGhe.Empty)) {
                            MessageBox.getBox("Ghế", "Cập nhật ghế thất bại.",
                                    Alert.AlertType.WARNING).show();
                        } else {
                            //lấy ghế mới
                            Ghe gheMoi = this.cbGhe.getValue();
                            veXe.setMaGhe(gheMoi.getMaGhe());
                            veXe.setMaChuyenXe(this.cbChuyenXe.getValue().getMaChuyenXe());
                            if (!veXeService.updateVeXe(veXe, veXe.getMaVeXe(), Status.Booked)) {
                                MessageBox.getBox("Vé xe", "Cập nhật thông tin vé xe thất bại.",
                                        Alert.AlertType.WARNING).show();
                            } else {
                                if (!gheService.updateTrangThaiGheByMaGhe(gheMoi.getMaGhe(), TrangThaiGhe.Selected)) {
                                    MessageBox.getBox("Ghế", "Cập nhật ghế thất bại.",
                                            Alert.AlertType.WARNING).show();
                                } else {
                                    MessageBox.getBox("Vé xe", "Cập nhật thông tin vé xe thành công.",
                                            Alert.AlertType.INFORMATION).show();
                                }
                            }
                        }
                    }
                }
            } else {
                MessageBox.getBox("Vé Xe", "KHÔNG CÓ VÉ XE CÓ MÃ LÀ " + maVe + " HOẶC VÉ ĐÃ ĐƯỢC MUA.",
                        Alert.AlertType.WARNING).show();
            }
        }
    }

    public void clear() {
        this.txtBienSoXe.clear();
        this.txtCCCD.clear();
        this.txtDiaChi.clear();
        this.txtDienThoai.clear();
        this.txtGioDi.clear();
        this.txtTenKH.clear();
    }

    //-----------Chuyển trang ------------- 
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

    public void chuyenTrangHuyHoacLayVe(MouseEvent m) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) m.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("huyHoacLayVe.fxml"));
        Parent BanVeView = loader.load();
        Scene scene = new Scene(BanVeView);
        stage.setScene(scene);
        HuyHoacLayVeController controller = loader.getController();
        controller.setUserInfo(user);
        stage.show();
    }

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

    public void DangXuat(ActionEvent e) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginView = loader.load();
        Scene scene = new Scene(loginView);
        stage.setScene(scene);
        stage.show();
    }
}
