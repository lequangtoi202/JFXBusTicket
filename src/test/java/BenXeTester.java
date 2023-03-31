/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.lqt.pojo.BenXe;
import com.lqt.service.BenXeService;
import com.lqt.service.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author TOI
 */
public class BenXeTester {
    private static final BenXeService benXeService = new BenXeService();
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
    public void testGetBenXeByIdNotExisted() throws SQLException{
        int maBenXe = 20;
        BenXe benXe = benXeService.getBenXeById(maBenXe);
        Assertions.assertEquals(null, benXe);
    }
    
    @Test
    public void testGetBenXeById() throws SQLException{
        int maBenXe = 1;
        BenXe benXe = benXeService.getBenXeById(maBenXe);
        Assertions.assertEquals("Hà Nội", benXe.getTenBen());
    }
    
    @Test
    public void testGetAllBenXe() throws SQLException{
        List<BenXe> dsBenXe = benXeService.getAllBenXe();
        Assertions.assertTrue(!dsBenXe.isEmpty());
    }
}
