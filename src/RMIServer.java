import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            EmployeeService service = new EmployeeServiceImpl();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("EmployeeService", service);

            System.out.println("RMI Server Started...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}