import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {

    public EmployeeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String addEmployee(String name, String position, double salary) throws RemoteException {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO employees(name, position, salary) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);

            stmt.executeUpdate();

            return "Employee Added";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public String updateEmployee(int id, String name, String position, double salary) throws RemoteException {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);

            stmt.executeUpdate();

            return "Employee Updated";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public String deleteEmployee(int id) throws RemoteException {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return "Employee Deleted";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public List<Employee> getEmployees() throws RemoteException {

        List<Employee> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
