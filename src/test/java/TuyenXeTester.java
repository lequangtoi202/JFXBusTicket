import com.lqt.pojo.TuyenXe;
import com.lqt.service.JdbcUtils;
import com.lqt.service.TuyenXeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
public class TuyenXeTester {
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
    public void testGetAllTuyenXe() throws SQLException {
        // Tạo đối tượng TuyenXeDao
        TuyenXeService tuyenXeService = new TuyenXeService();
        
        // Gọi phương thức getAllTuyenXe
        List<TuyenXe> listTuyenXe = tuyenXeService.getAllTuyenXe();
        
        //Tạo dữ liệu mẫu
        //Tuyến xe có trong csdl
        TuyenXe expected = new TuyenXe(1, "Hà Nội - Hải Phòng", 100000, 1, 2);
        
        //Kiểm tra có láy được danh sách hay không
        Assertions.assertTrue(!listTuyenXe.isEmpty());
        
        // Kiểm tra nội dung của tuyến xe mong muốn có
        Assertions.assertEquals(expected.getMaTuyenXe(), listTuyenXe.get(0).getMaTuyenXe());
        Assertions.assertEquals(expected.getTenTuyen(), listTuyenXe.get(0).getTenTuyen());
        Assertions.assertEquals(expected.getBangGia(), listTuyenXe.get(0).getBangGia(), 0.01);
        Assertions.assertEquals(expected.getMaBenDi(), listTuyenXe.get(0).getMaBenDi());
        Assertions.assertEquals(expected.getMaBenDen(), listTuyenXe.get(0).getMaBenDen());
    }
    
    @Test
    public void testGetTuyenXeById() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        TuyenXeService tuyenXeService = new TuyenXeService();
        
        // Tạo một xe mong đợi từ cơ sở dữ liệu với id = 1
        TuyenXe expected = new TuyenXe(1, "Hà Nội - Hải Phòng", 100000, 1, 2);
        
        // Thực thi phương thức getXeById(1) để lấy xe thực tế với id = 1
        TuyenXe actualXe = null;
        try {
            actualXe = tuyenXeService.getTuyenXeById(1);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế với xe mong đợi
        Assertions.assertNotNull(actualXe);
        Assertions.assertEquals(expected.getMaTuyenXe(), actualXe.getMaTuyenXe());
        Assertions.assertEquals(expected.getTenTuyen(), actualXe.getTenTuyen());
        Assertions.assertEquals(expected.getBangGia(), actualXe.getBangGia(), 0.01);
        Assertions.assertEquals(expected.getMaBenDi(), actualXe.getMaBenDi());
        Assertions.assertEquals(expected.getMaBenDen(), actualXe.getMaBenDen());
    }

    private void fail(String lỗi_khi_truy_vấn_cơ_sở_dữ_liệu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Test
    public void testGetTuyenXeById_Null() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        TuyenXeService tuyenXeService = new TuyenXeService();
        
        // Thực thi phương thức getXeById(1) để lấy xe thực tế với id = 1
        TuyenXe actualXe = null;
        try {
            actualXe = tuyenXeService.getTuyenXeById(100);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế mong đợi
        Assertions.assertNull(actualXe);
    }
}
