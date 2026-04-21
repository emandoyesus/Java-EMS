import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeService extends Remote {

    String addEmployee(String name, String position, double salary) throws RemoteException;

    String updateEmployee(int id, String name, String position, double salary) throws RemoteException;

    String deleteEmployee(int id) throws RemoteException;

    List<Employee> getEmployees() throws RemoteException;
}