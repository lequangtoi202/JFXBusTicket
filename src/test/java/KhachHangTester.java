/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.lqt.pojo.KhachHang;
import com.lqt.service.JdbcUtils;
import com.lqt.service.KhachHangService;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class KhachHangTester {
    private static final KhachHangService khachHangService = new KhachHangService();
    
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
    public void testGetKhachHangByValidId() throws SQLException{
        int maKH = 40;
        KhachHang khachHang = khachHangService.getKhachHangById(maKH);
        Assertions.assertNotNull(khachHang);
//        Assertions.assertTrue(khachHang.isGioiTinh());
//        Assertions.assertEquals("123456789", khachHang.getCCCD());
//        Assertions.assertEquals("0123456789", khachHang.getDienThoai());
//        Assertions.assertEquals("ABC", khachHang.getTenKH());
    }
    @Test
    public void testGetKhachHangByInValidId() throws SQLException{
        int maKH = 2000;
        KhachHang khachHang = khachHangService.getKhachHangById(maKH);
        Assertions.assertNull(khachHang);
    }
    
    @Test
    public void testAddKhachHang() throws SQLException, ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        KhachHang khachHang = new KhachHang("Nguyễn Văn A", true, dateFormat.parse("2023-04-12"), "ABC", "0988969820", "0192847562134");
        int id = khachHangService.addKhachHang(khachHang);
        
        KhachHang khachHangMoiThem = khachHangService.getKhachHangById(id);
        Assertions.assertEquals("Nguyễn Văn A", khachHangMoiThem.getTenKH());
    }
    
    @Test
    public void testUpdateKhachHang() throws SQLException, ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        KhachHang khachHang = new KhachHang("Nguyễn Văn A", true, dateFormat.parse("2003-04-12"), "ABC", "0988969820", "0192847562134");
        int id = khachHangService.addKhachHang(khachHang);
        
        KhachHang khachHangUpdate = new KhachHang(id, "Nguyễn Thị B", false, dateFormat.parse("1999-04-11"), "Nguyễn Thị Thập, Quận 7", "0988969820", "0192847562134");
        boolean rs = khachHangService.updateKhachHang(khachHangUpdate);
        Assertions.assertTrue(rs);
        
        KhachHang khachHangMoiCapNhat = khachHangService.getKhachHangById(id);
        Assertions.assertEquals("Nguyễn Thị B", khachHangMoiCapNhat.getTenKH());
        Assertions.assertEquals(dateFormat.parse("1999-04-11"), khachHangMoiCapNhat.getNgaySinh());
        Assertions.assertFalse(khachHangMoiCapNhat.isGioiTinh());
        Assertions.assertEquals(khachHangUpdate.getDienThoai(), khachHangMoiCapNhat.getDienThoai());
        Assertions.assertEquals(khachHangUpdate.getCCCD(), khachHangMoiCapNhat.getCCCD());
    }
}
