import com.lqt.pojo.Xe;
import com.lqt.service.JdbcUtils;
import com.lqt.service.XeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
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
public class XeTester {
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
    public void testGetAllXe() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        XeService xeService = new XeService();
        // Tạo một danh sách các xe mong đợi từ cơ sở dữ liệu
        List<Xe> expectedXeList = Arrays.asList(
            new Xe(1, "Huyndai Solati", "59D - 57245", 16, 3),
            new Xe(2, "Ford Transit", "59 B - 92746", 16, 3),
            new Xe(3, "Phương Trang", "65A - 54637", 16, 1),
            new Xe(4, "Phương Trang(ghế ngồi)", "55E - 74585", 16, 2),
            new Xe(5, "Ford Transit 2", "59H - 92776", 16, 3),
            new Xe(6, "Huyndai County", "62H - 92776", 16, 2),
            new Xe(7, "Huyndai Universe 47", "47A - 78695", 16, 1),
            new Xe(8, "Huyndai Universe 29", "47K - 78695", 16, 2),
            new Xe(9, "Samco", "55F - 78576", 16, 2),
            new Xe(10, "Samco 35", "57F - 45476", 16, 1)
        );
        // Thực thi phương thức getAllXe() để lấy danh sách xe thực tế
        List<Xe> actualXeList = null;
        try {
            actualXeList = xeService.getAllXe();
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh danh sách xe thực tế với danh sách xe mong đợi
        for (int i = 0; i < expectedXeList.size(); i++) {
            Assertions.assertEquals(expectedXeList.get(i).getMaXe(), actualXeList.get(i).getMaXe());
            Assertions.assertEquals(expectedXeList.get(i).getTenXe(), actualXeList.get(i).getTenXe());
            Assertions.assertEquals(expectedXeList.get(i).getBienSoXe(), actualXeList.get(i).getBienSoXe());
            Assertions.assertEquals(expectedXeList.get(i).getSoGhe(), actualXeList.get(i).getSoGhe());
            Assertions.assertEquals(expectedXeList.get(i).getMaLoaiXe(), actualXeList.get(i).getMaLoaiXe());
        }
    }

    private void fail(String lỗi_khi_truy_vấn_cơ_sở_dữ_liệu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Test
    public void testGetXeById() {
        // Tạo một đối tượng XeDAO để truy cập cơ sở dữ liệu
        XeService xeService = new XeService();
        // Tạo một xe mong đợi từ cơ sở dữ liệu với id = 1
        Xe expectedXe = new Xe(1, "Huyndai Solati", "59D - 57245", 16, 3);
        // Thực thi phương thức getXeById(1) để lấy xe thực tế với id = 1
        Xe actualXe = null;
        try {
            actualXe = xeService.getXeById(1);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế với xe mong đợi
        Assertions.assertNotNull(actualXe);
        Assertions.assertEquals(expectedXe.getMaXe(), actualXe.getMaXe());
        Assertions.assertEquals(expectedXe.getTenXe(), actualXe.getTenXe());
        Assertions.assertEquals(expectedXe.getBienSoXe(), actualXe.getBienSoXe());
        Assertions.assertEquals(expectedXe.getSoGhe(), actualXe.getSoGhe());
        Assertions.assertEquals(expectedXe.getMaLoaiXe(), actualXe.getMaLoaiXe());
    }
    
    @Test
    public void testGetXeByIdNull() {
        XeService xeService = new XeService();
        
        Xe actualXe = null;
        try {
            actualXe = xeService.getXeById(45);
        } catch (SQLException ex) {
            fail("Lỗi khi truy vấn cơ sở dữ liệu");
        }
        // So sánh xe thực tế với xe mong đợi
        Assertions.assertNull(actualXe);
       
    }
}
