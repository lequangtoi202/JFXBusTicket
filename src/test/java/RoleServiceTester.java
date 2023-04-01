
import com.lqt.pojo.Role;
import com.lqt.service.JdbcUtils;
import com.lqt.service.RoleService;
import java.sql.Connection;
import java.sql.SQLException;
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
public class RoleServiceTester {
    private static final RoleService roleService = new RoleService();
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
    public void TestGetRoleById() throws SQLException {
        int id = 1;
        Role role = roleService.getRoleById(id);
        
        Assertions.assertEquals(1,role.getRoleId());
        Assertions.assertEquals("ADMIN", role.getName());
    }
    
    @Test
    public void TestGetRoleById_Null() throws SQLException {
        int id = 3;
        Role role = roleService.getRoleById(id);
        
        Assertions.assertNull(role);
    }
}
