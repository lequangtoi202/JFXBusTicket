/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.lqt.pojo.NhanVien;
import com.lqt.service.JdbcUtils;
import com.lqt.service.NhanVienService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 *
 * @author TOI
 */
public class NhanVienTester {
    private static final NhanVienService nhanVienService = new NhanVienService();
    
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
    public void testGetNhanVienByValidUserId() throws SQLException{
        int userId = 1;
        NhanVien nhanVien = nhanVienService.getNhanVienByUserId(userId);
        
        Assertions.assertNotNull(nhanVien);
        Assertions.assertEquals("Tới", nhanVien.getTenNV());
        //Assertions.assertTrue(nhanVien.isGioiTinh());
        //Assertions.assertEquals("123456789", nhanVien.getCCCD());
        //Assertions.assertEquals("0123456789", nhanVien.getDienThoai());
        //Assertions.assertEquals("nva@example.com", nhanVien.getEmail());
        Assertions.assertEquals(1, nhanVien.getMaUser());
    }
    
    @Test
    public void testGetNhanVienByInValidUserId() throws SQLException{
        int userId = 1000;
        NhanVien nhanVien = nhanVienService.getNhanVienByUserId(userId);
        
        Assertions.assertNull(nhanVien);
    }
}
