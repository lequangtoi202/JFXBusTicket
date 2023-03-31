/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.lqt.pojo.Ghe;
import com.lqt.pojo.TrangThaiGhe;
import com.lqt.service.GheService;
import com.lqt.service.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class GheTester {

    private static final GheService gheService = new GheService();
    
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
    public void testThuHoiGhe() throws SQLException {
        boolean rs = gheService.thuHoiGhe();
        Assertions.assertTrue(rs);
    }
    
    @Test
    public void testUpdateTrangThaiGheByMaGhe() throws SQLException {
        TrangThaiGhe trangThai = TrangThaiGhe.Selected;
        Ghe ghe = new Ghe("A2", TrangThaiGhe.Empty, 2);
        int id = gheService.addGhe(ghe);
        boolean rs = gheService.updateTrangThaiGheByMaGhe(id, trangThai);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ghe WHERE Ma_ghe=?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        Assertions.assertTrue(result.next());
        Assertions.assertEquals(trangThai, TrangThaiGhe.valueOf(result.getString("Trang_thai")));
        Assertions.assertTrue(rs);
    }
    
    @Test
    public void testUpdateTrangThaiGheByMaXe() throws SQLException {
        List<Ghe> dsGhe = new ArrayList<>();
        TrangThaiGhe trangThai = TrangThaiGhe.Empty;
        Ghe newGhe = new Ghe("A2", TrangThaiGhe.Selected, 2);
        int id = gheService.addGhe(newGhe);
        Ghe ghe = gheService.getGheById(id);
        
        boolean rs = gheService.updateTrangThaiGheByMaXe(trangThai, ghe.getMaXe());
        
        dsGhe = gheService.getAllGheEmptyByMaXe(ghe.getMaXe());
        boolean laGheTrong = dsGhe.stream().allMatch(x -> x.getTrangThai().equals(TrangThaiGhe.Empty));
        Assertions.assertTrue(rs);
        Assertions.assertTrue(laGheTrong);
    }
    
    @Test
    public void testGetAllGheEmptyByMaXeNotExisted() throws SQLException {
        List<Ghe> dsGhe = new ArrayList<>();
        dsGhe = gheService.getAllGheEmptyByMaXe(4);
        
        Assertions.assertTrue(dsGhe.isEmpty());
    }
    
    @Test
    public void testGetAllGheEmptyByMaXe() throws SQLException {
        List<Ghe> dsGhe = new ArrayList<>();
        dsGhe = gheService.getAllGheEmptyByMaXe(1);
        
        Assertions.assertTrue(!dsGhe.isEmpty());
    }
    
}
