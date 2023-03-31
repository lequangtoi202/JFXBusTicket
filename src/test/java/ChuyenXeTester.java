
import com.lqt.pojo.ChuyenXe;
import com.lqt.service.ChuyenXeService;
import com.lqt.service.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
 * @author TOI
 */
public class ChuyenXeTester {
    private static final ChuyenXeService chuyenXeService = new ChuyenXeService();
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
    public void testGetAllChuyenXe() throws SQLException {
        List<ChuyenXe> dsChuyenXe = chuyenXeService.getAllChuyenXe();
        Assertions.assertTrue(!dsChuyenXe.isEmpty());
    }

    @Test
    public void testGetChuyenXeByBenDiAndBenDen() throws SQLException {
        String benDi = "Hà Nội";
        String benDen = "Nam Định";

        // Act
        List<ChuyenXe> chuyenXeList = chuyenXeService.getChuyenXeByBenDiAndBenDen(benDi, benDen);
        System.out.println(chuyenXeList.size());
        // Assert
        Assertions.assertEquals("Hà Nội - Hải Phòng 2", chuyenXeList.get(0).getTenChuyen());
        Assertions.assertEquals(1, chuyenXeList.get(0).getMaTaiXe());
        Assertions.assertEquals(2, chuyenXeList.get(0).getMaTuyenXe());
        Assertions.assertEquals(4, chuyenXeList.get(0).getMaChuyenXe());
    }

    @Test
    public void testGetChuyenXeById() throws SQLException {
        int maChuyenXe = 4;

        // Act
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(maChuyenXe);
        // Assert
        Assertions.assertNotNull(chuyenXe);
        Assertions.assertEquals("Hà Nội - Hải Phòng 2", chuyenXe.getTenChuyen());
        Assertions.assertEquals(1, chuyenXe.getMaTaiXe());
        Assertions.assertEquals(2, chuyenXe.getMaTuyenXe());
        Assertions.assertEquals(4, chuyenXe.getMaChuyenXe());
    }

    @Test
    public void testGetChuyenXeByIdNotFound() throws SQLException {
        // Arrange
        int id = 999;

        // Act
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);

        // Assert
        Assertions.assertNull(chuyenXe);
    }

    @Test
    public void testAddChuyenXe() throws SQLException {
        String gioDi = "15:50:00";
        LocalDate ngayDi = LocalDate.of(2023, 4, 5);
        LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, LocalTime.parse(gioDi));
        ChuyenXe chuyenXe = new ChuyenXe("Hà Nội - Hải Phòng 3", thoiGianDi, 1, 1, false);
        // Act
        boolean success = chuyenXeService.addChuyenXe(chuyenXe);
        ChuyenXe chuyenXeMoiNhat = chuyenXeService.getChuyenXeMoiNhat();
        Assertions.assertEquals(chuyenXe.getTenChuyen(), chuyenXeMoiNhat.getTenChuyen());
        Assertions.assertEquals(chuyenXe.getThoiGianDi(), chuyenXeMoiNhat.getThoiGianDi());
        Assertions.assertEquals(chuyenXe.getMaTuyenXe(), chuyenXeMoiNhat.getMaTuyenXe());
        Assertions.assertEquals(chuyenXe.getMaTaiXe(), chuyenXeMoiNhat.getMaTaiXe());
        // Assert
        Assertions.assertTrue(success);

    }

    @Test
    public void testUpdateChuyenXe() throws SQLException {
        String gioDi = "17:00:00";
        LocalDate ngayDi = LocalDate.of(2023, 4, 8);
        LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, LocalTime.parse(gioDi));
        ChuyenXe chuyenXe = new ChuyenXe("Hà Nội - Nam Định 3", thoiGianDi, 2, 2, false);
        boolean success = chuyenXeService.addChuyenXe(chuyenXe);
        Assertions.assertTrue(success);
        
        ChuyenXe chuyenXeMoiNhat = chuyenXeService.getChuyenXeMoiNhat();
        ChuyenXe chuyenXeUpdate = new ChuyenXe("Hà Nội - Nam Định 4", LocalDateTime.of(LocalDate.of(2023, 4, 12), LocalTime.parse("11:30:00")), 2, 2, true);
        chuyenXeService.updateChuyenXe(chuyenXeUpdate, chuyenXeMoiNhat.getMaChuyenXe());
        ChuyenXe chuyenXeSauUpdate = chuyenXeService.getChuyenXeById(chuyenXeMoiNhat.getMaChuyenXe());
        
        Assertions.assertEquals("Hà Nội - Nam Định 4", chuyenXeSauUpdate.getTenChuyen());
        Assertions.assertEquals(LocalDateTime.of(LocalDate.of(2023, 4, 12), LocalTime.parse("11:30:00")), chuyenXeSauUpdate.getThoiGianDi());
        Assertions.assertEquals(2, chuyenXeSauUpdate.getMaTuyenXe());
        Assertions.assertEquals(2, chuyenXeSauUpdate.getMaTaiXe());
        Assertions.assertTrue(chuyenXeSauUpdate.isIs_updated());
    }
    
    @Test
    public void testDeleteChuyenXe() throws SQLException {
        String gioDi = "16:00:00";
        LocalDate ngayDi = LocalDate.of(2023, 4, 9);
        LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, LocalTime.parse(gioDi));
        ChuyenXe chuyenXe = new ChuyenXe("Hà Nội - Nam Định 5", thoiGianDi, 2, 1, false);
        boolean success = chuyenXeService.addChuyenXe(chuyenXe);
        Assertions.assertTrue(success);
        
        ChuyenXe chuyenXeMoiNhat = chuyenXeService.getChuyenXeMoiNhat();
        chuyenXeService.deleteChuyenXe(chuyenXeMoiNhat.getMaChuyenXe());
        
        ChuyenXe chuyenXeSauKhiXoa = chuyenXeService.getChuyenXeById(chuyenXeMoiNhat.getMaChuyenXe());
        Assertions.assertNull(chuyenXeSauKhiXoa);
    }
    
}
