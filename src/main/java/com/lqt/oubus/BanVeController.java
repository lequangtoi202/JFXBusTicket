/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.oubus;

import com.lqt.pojo.BenXe;
import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.Ghe;
import com.lqt.pojo.KhachHang;
import com.lqt.pojo.Status;
import com.lqt.pojo.TrangThaiGhe;
import com.lqt.pojo.TuyenXe;
import com.lqt.pojo.User;
import com.lqt.pojo.VeXe;
import com.lqt.pojo.Xe;
import com.lqt.service.BenXeService;
import com.lqt.service.ChuyenXeService;
import com.lqt.service.GheService;
import com.lqt.service.KhachHangService;
import com.lqt.service.NhanVienService;
import com.lqt.service.TuyenXeService;
import com.lqt.service.VeXeService;
import com.lqt.service.XeService;
import com.lqt.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TOI
 */
public class BanVeController implements Initializable {

    private static final VeXeService veXeService = new VeXeService();
    private static final XeService xeService = new XeService();
    private static final GheService gheService = new GheService();
    private static final KhachHangService khachHangService = new KhachHangService();
    private static final BenXeService benXeService = new BenXeService();
    private static final ChuyenXeService chuyenXeService = new ChuyenXeService();
    private static final TuyenXeService tuyenXeService = new TuyenXeService();
    private static final NhanVienService nhanVienService = new NhanVienService();
    private User user;

    @FXML
    private Label lbMaUser;
    @FXML
    private Label lbUsername;
    @FXML
    private ComboBox<Xe> cbXe;
    @FXML
    private TableView<ChuyenXe> tbChuyenXe;
    @FXML
    private ComboBox<BenXe> cbBenDi;
    @FXML
    private ComboBox<BenXe> cbBenDen;
    @FXML
    private ComboBox<Ghe> cbGhe;
    @FXML
    private DatePicker dNgaySinh;
    @FXML
    private DatePicker dNgayDi;
    @FXML
    private TextField txtGioDi;
    @FXML
    private Label lbTenChuyen;
    @FXML
    private TextField txtMaChuyen;
    @FXML
    private TextField txtBenDi;
    @FXML
    private TextField txtBenDen;
    @FXML
    private TextField txtTenKH;
    @FXML
    private RadioButton rdNam;
    @FXML
    private RadioButton rdNu;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtDienThoai;
    @FXML
    private TextField txtCCCD;
    @FXML
    private Label lbThanhTien;

    public void setUserInfo(User user) throws SQLException {
        this.user = user;
        lbMaUser.setText(String.valueOf(nhanVienService.getNhanVienByUserId(user.getMaUser()).getMaNV()));
        lbUsername.setText(user.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Xe> dsXe = xeService.getAllXe();
            this.cbXe.setItems(FXCollections.observableList(dsXe));
            this.cbXe.setOnAction(e -> {
                Xe xeDaChon = this.cbXe.getSelectionModel().getSelectedItem();
                List<Ghe> dsGhe = null;
                if (xeDaChon != null) {
                    try {
                        dsGhe = gheService.getAllGheEmptyByMaXe(xeDaChon.getMaXe());
                    } catch (SQLException ex) {
                        Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.cbGhe.setItems(FXCollections.observableList(dsGhe));
                }

            });

            List<BenXe> benXe = benXeService.getAllBenXe();
            this.cbBenDi.setItems(FXCollections.observableList(benXe));
            this.cbBenDen.setItems(FXCollections.observableList(benXe));
            this.loadTableColumns();

            ToggleGroup toggleGroup = new ToggleGroup();
            this.rdNam.setToggleGroup(toggleGroup);
            this.rdNu.setToggleGroup(toggleGroup);

            this.rdNam.setOnAction(event -> {
                if (this.rdNam.isSelected()) {
                    this.rdNu.setSelected(false);
                }
            });
            this.rdNu.setOnAction(event -> {
                if (this.rdNu.isSelected()) {
                    this.rdNam.setSelected(false);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void timChuyenXe(ActionEvent e) throws SQLException {
        if (this.cbBenDi.getValue() == null || this.cbBenDen.getValue() == null) {
            MessageBox.getBox("Chuyến xe",
                    "Vui lòng chọn bến đi và bến đến",
                    Alert.AlertType.WARNING);
        } else {
            String tenBenXeDi = this.cbBenDi.getSelectionModel().getSelectedItem().getTenBen();
            String tenBenXeDen = this.cbBenDen.getSelectionModel().getSelectedItem().getTenBen();
            this.loadChuyenXe(tenBenXeDi, tenBenXeDen);
        }

    }

    public void muaVeHandler(ActionEvent e) throws ParseException, SQLException {
        if (kiemTraThongTin()) {
            String tenKH = this.txtTenKH.getText();
            boolean gioiTinh;
            gioiTinh = this.rdNam.isSelected();
            String diaChi = this.txtDiaChi.getText();
            String CCCD = this.txtCCCD.getText();
            String dienThoai = this.txtDienThoai.getText();

            Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(this.dNgaySinh.getValue().toString());

            //tạo khách hàng
            KhachHang khachHangSave = new KhachHang(tenKH, gioiTinh, ngaySinh, diaChi, dienThoai, CCCD);

            // format thời gian
            LocalDate ngayDi = dNgayDi.getValue();
            String gioDi = txtGioDi.getText();
            LocalTime localTime = LocalTime.parse(gioDi);

            LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, localTime);
            Duration duration = Duration.between(LocalDateTime.now(), thoiGianDi);
            long minutes = duration.toMinutes();

            if (minutes < 5) {
                MessageBox.getBox("Vé Xe", "Thời gian mua vé không hợp lệ!",
                        Alert.AlertType.WARNING).show();
            } else {
                int id = khachHangService.addKhachHang(khachHangSave);
                KhachHang khachHang = khachHangService.getKhachHangById(id);
                VeXe veXe = new VeXe(LocalDateTime.now(), Status.Done, Integer.parseInt(lbMaUser.getText()), khachHang.getMaKH(), Integer.parseInt(txtMaChuyen.getText()), this.cbGhe.getSelectionModel().getSelectedItem().getMaGhe());
                if (!veXeService.addVeXe(veXe)) {
                    throw new SQLException("Mua vé thất bại");
                } else {
                    if (!gheService.updateTrangThaiGheByMaGhe(this.cbGhe.getSelectionModel().getSelectedItem().getMaGhe(), TrangThaiGhe.Selected)) {
                        MessageBox.getBox("Ghế", "Chọn ghế không thành công", Alert.AlertType.WARNING).show();
                    }
                    MessageBox.getBox("Vé Xe", "MUA VÉ THÀNH CÔNG",
                            Alert.AlertType.INFORMATION).show();
                    clear();
                }
            }
        }
    }

    public boolean kiemTraThongTin() {
        Pattern dienThoaiRegex = Pattern.compile("^0\\d{9}$");
        Pattern CCCDRegex = Pattern.compile("^[0-9]{9,12}$");
        if (this.txtMaChuyen.getText().isEmpty()) {
            MessageBox.getBox("Chuyến xe", "Vui lòng chọn chuyến xe muốn đi",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtTenKH.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập tên khách hàng",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.dNgaySinh.getValue() == null) {
            MessageBox.getBox("Ngày Sinh", "Vui lòng nhập ngày sinh",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        Matcher dienThoaimatcher = dienThoaiRegex.matcher(this.txtDienThoai.getText().trim());
        Matcher CCCDmatcher = CCCDRegex.matcher(this.txtCCCD.getText().trim());
        Pattern ngaySinhRegex = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher ngaySinhMatchar = ngaySinhRegex.matcher(this.dNgaySinh.getValue().toString());
        if (this.dNgaySinh.getValue().isAfter(LocalDate.now()) && !ngaySinhMatchar.matches()) {
            MessageBox.getBox("Ngày sinh", "Ngày sinh không hợp lệ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtDienThoai.getText().trim().isEmpty()) {
            MessageBox.getBox("Điện thoại", "Vui lòng nhập số điện thoại.",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (!dienThoaimatcher.matches()) {
            MessageBox.getBox("Điện thoại", "Số điện thoại không hợp lệ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.txtCCCD.getText().trim().isEmpty()) {
            MessageBox.getBox("CCCD", "Vui lòng nhập số căn cước công dân.",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (!CCCDmatcher.matches()) {
            MessageBox.getBox("CCCD", "Căn cước công dân không hợp lệ.",
                    Alert.AlertType.WARNING).show();
            return false;
        }

        if (this.txtDiaChi.getText().trim().isEmpty()) {
            MessageBox.getBox("Khách hàng", "Vui lòng nhập địa chỉ",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        if (this.cbXe.getValue() == null) {
            MessageBox.getBox("Xe", "Vui lòng chọn xe đi",
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

    public void huyHandler(ActionEvent e) throws SQLException {
        clear();
    }

    public void clear() throws SQLException {
        this.txtBenDen.clear();
        this.txtBenDi.clear();
        this.txtDienThoai.clear();
        this.txtCCCD.clear();
        this.txtDiaChi.clear();
        this.lbTenChuyen.setText("Nơi đi --> Nơi đến");
        this.txtGioDi.clear();
        this.txtMaChuyen.clear();
        this.txtTenKH.clear();
        this.cbXe.getItems().clear();
        this.cbGhe.getItems().clear();
        List<Xe> dsXe = xeService.getAllXe();
        this.cbXe.setItems(FXCollections.observableList(dsXe));

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
                this.lbTenChuyen.setText(chuyenXe.getTenChuyen());
                this.txtGioDi.setText(gioDi.toString());
                this.dNgayDi.setValue(chuyenXe.getThoiGianDi().toLocalDate());
                try {
                    TuyenXe tuyenXe = tuyenXeService.getTuyenXeById(chuyenXe.getMaTuyenXe());
                    BenXe benXeDi = benXeService.getBenXeById(tuyenXe.getMaBenDi());
                    BenXe benXeDen = benXeService.getBenXeById(tuyenXe.getMaBenDen());
                    this.txtBenDi.setText(benXeDi.getTenBen());
                    this.txtBenDen.setText(benXeDen.getTenBen());
                    this.lbThanhTien.setText(String.valueOf(tuyenXe.getBangGia() + " VND"));
                } catch (SQLException ex) {
                    Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

        this.tbChuyenXe.getColumns().addAll(colMaChuyen, colTenChuyen, colThoiGianDi, colMaTaiXe, colMaTuyen, colChon);
    }

    private void loadChuyenXe(String benDi, String benDen) throws SQLException {
        List<ChuyenXe> dsChuyenXe = chuyenXeService.getChuyenXeByBenDiAndBenDen(benDi, benDen);
        if (!dsChuyenXe.isEmpty()) {
            this.tbChuyenXe.getItems().clear();
            this.tbChuyenXe.setItems(FXCollections.observableList(dsChuyenXe));
        } else {
            MessageBox.getBox("Chuyến xe", "Không có chuyến xe.", Alert.AlertType.WARNING).show();
        }
    }

    // CHUYỂN TRANG
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
