import com.lqt.pojo.Status;
import com.lqt.pojo.VeXe;
import com.lqt.service.JdbcUtils;
import com.lqt.service.VeXeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class VeXeTester {
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testGetAllVeXe() throws SQLException {
        VeXeService dao = new VeXeService();
        List<VeXe> listVeXe = dao.getAllVeXe();
        
        Assertions.assertNotNull(listVeXe);
        Assertions.assertFalse(listVeXe.isEmpty());
    }
    
    @Test
    public void testGetVeXeById() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        VeXeService veXeService = new VeXeService();
        
        // Tạo một xe mong đợi từ cơ sở dữ liệu với id = 3
        VeXe expected = new VeXe(3, LocalDateTime.of(2023, 3, 6, 15, 30, 0), Status.Canceled, 2, 2, 1, 1);
        
        // Thực thi phương thức getXeById(3) để lấy xe thực tế với id = 3
        VeXe actualVeXe = null;
        try {
            actualVeXe = veXeService.getVeXeById(3);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế với xe mong đợi
        Assertions.assertNotNull(actualVeXe);
        Assertions.assertEquals(expected.getMaVeXe(), actualVeXe.getMaVeXe());
        Assertions.assertEquals(expected.getMaGhe(), actualVeXe.getMaGhe());
        Assertions.assertEquals(expected.getMaNV(), actualVeXe.getMaNV());
        Assertions.assertEquals(expected.getMaKH(), actualVeXe.getMaKH());
    }

    private void fail(String lỗi_khi_truy_vấn_cơ_sở_dữ_liệu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Test
    public void testVeXeBookedById() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        VeXeService veXeService = new VeXeService();
        
        // Tạo một xe mong đợi từ cơ sở dữ liệu với id = 3
        VeXe expected = new VeXe(3, LocalDateTime.of(2023, 3, 6, 15, 30, 0), Status.Canceled, 2, 2, 1, 1);
        
        
        VeXe actualVeXe = null;
        try {
            actualVeXe = veXeService.getVeXeById(3);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế với xe mong đợi
        Assertions.assertNotNull(actualVeXe);
        Assertions.assertEquals(expected.getMaVeXe(),actualVeXe.getMaVeXe());
    }
    
    @Test
    public void testVeXeBookedById_Null() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        VeXeService veXeService = new VeXeService();
        
        //Tạo một id không có trong csdl
        int id = 100;
        
        VeXe actualVeXe = null;
        try {
            actualVeXe = veXeService.getVeXeById(id);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // Mong muốn trả về null
        Assertions.assertNull(actualVeXe);
    }
    
    @Test
    public void testAddVeXe() throws SQLException {
        // Tạo một đối tượng VeXe mới để thêm vào cơ sở dữ liệu
        VeXe veXe = new VeXe();
        veXe.setThoiGianBan(LocalDateTime.now());
        veXe.setTrangThai(Status.Done);
        veXe.setMaNV(2);
        veXe.setMaKH(33);
        veXe.setMaChuyenXe(2);
        veXe.setMaGhe(2);

        // Thực hiện thêm mới đối tượng VeXe vào cơ sở dữ liệu
        VeXeService veXeService = new VeXeService();
        boolean result = veXeService.addVeXe(veXe);

        // Kiểm tra kết quả
        Assertions.assertTrue(result);

        // Kiểm tra dữ liệu đã được thêm vào cơ sở dữ liệu chưa
        String sql = "SELECT COUNT(*) FROM ve_xe WHERE Ma_kh = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, veXe.getMaKH());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
    }
    
    @Test
    public void testUpdateVeXe() throws SQLException {
        VeXe veXe = new VeXe();
        
        // Thực hiện cập nhật thông tin của đối tượng VeXe vào cơ sở dữ liệu
         VeXeService veXeService = new VeXeService();
        boolean result = veXeService.updateVeXe(veXe, 27, Status.Canceled);

        // Kiểm tra kết quả
        Assertions.assertTrue(result);

        // Kiểm tra dữ liệu đã được cập nhật trong cơ sở dữ liệu chưa
        String sql = "SELECT * FROM ve_xe WHERE Ma_Ve_Xe = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, 27);
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(Status.Canceled.toString(), rs.getString("Trang_thai"));
    }
    
    @Test
    public void testKiemTraTgianQuaHan() {
        VeXeService veXeService = new VeXeService();
        
        //Kiểm tra xem có trả ra ngoại lệ nào hay không
        Assertions.assertDoesNotThrow(() -> veXeService.kiemTraTgianQuaHan());
    }
    
    @Test
    public void testThuHoiVeXe() {
        VeXeService veXeService = new VeXeService();
        Assertions.assertDoesNotThrow(() -> veXeService.thuHoiVeXe());
    }

}
