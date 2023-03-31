import com.lqt.pojo.User;
import com.lqt.service.JdbcUtils;
import com.lqt.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
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
public class UserServiceTester {
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
    public void testGetUsernameAndPassword() throws SQLException {
        // Tạo đối tượng UserDao
        UserService userService = new UserService();
        
        // Tạo dữ liệu mẫu
        User expectedUser = new User(2, "admin", "123", 1);
        String username = "admin";
        String password = "123";
        
        // Gọi phương thức getUsernameAndPassword
        User actualUser = userService.getUsernameAndPassword(username, password);
        
        // So sánh kết quả trả về với kết quả mong đợi
        Assertions.assertEquals(expectedUser.getMaUser(), actualUser.getMaUser());
        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getRoleId(), actualUser.getRoleId());
    }
    
    @Test
    public void testGetUsernameAndPasswordWithInvalidUsername() throws SQLException {
        // Tạo đối tượng UserDao
        UserService userService = new UserService();
        
        // Tạo dữ liệu mẫu
        String username = "invalid";
        String password = "123";
        
        // Gọi phương thức getUsernameAndPassword
        User actualUser = userService.getUsernameAndPassword(username, password);
        
        // Kết quả trả về là null khi không tìm thấy user
        Assertions.assertNull(actualUser);
    }
    
    @Test
    public void testGetUsernameAndPasswordWithInvalidPassword() throws SQLException {
        // Tạo đối tượng UserDao
        UserService userService = new UserService();
        
        // Tạo dữ liệu mẫu
        User expectedUser = new User(2, "admin", "123445456", 1);
        String username = "admin";
        String password = "123445456";
        
        // Gọi phương thức getUsernameAndPassword
        User actualUser = userService.getUsernameAndPassword(username, password);
        
        // Kết quả trả về hai giá trị khác nhau
        Assertions.assertNotEquals(expectedUser.getPassword(), actualUser.getPassword());
       
    }
}
