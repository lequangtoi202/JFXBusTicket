import com.lqt.pojo.TaiXe;
import com.lqt.service.JdbcUtils;
import com.lqt.service.TaiXeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class TaiXeServiceTester {
    private static final TaiXeService taiXeService = new TaiXeService();
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
    public void TestGetTaiXeById() throws SQLException {
        int id = 1;
        TaiXe taiXe = taiXeService.getTaiXeById(id);
        
        Assertions.assertNotNull(taiXe);
        Assertions.assertEquals(1,taiXe.getMaTaiXe());
        Assertions.assertEquals("A", taiXe.getTenTaiXe());
        Assertions.assertTrue(taiXe.isGioiTinh());
        Assertions.assertEquals("TP HCM", taiXe.getDiaChi());
        Assertions.assertEquals("0988969820", taiXe.getDienThoai());
        Assertions.assertEquals("038202001543", taiXe.getCCCD());
        Assertions.assertEquals("abc@gmail.com", taiXe.getEmail());
    }
    
    @Test
    public void TestGetRoleById_Null() throws SQLException {
        int id = 10000;
        TaiXe taiXe = taiXeService.getTaiXeById(id);
        
        Assertions.assertNull(taiXe);
    }
    
    @Test
    public void testGetAllVeXe() throws SQLException {
        List<TaiXe> listTaiXe = taiXeService.getAllTaiXe();
        
        Assertions.assertNotNull(listTaiXe);
        Assertions.assertFalse(listTaiXe.isEmpty());
    }
    
    
        
}
