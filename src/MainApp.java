import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainApp extends Application {

    TableView<Employee> table = new TableView<>();

    TextField nameField = new TextField();
    TextField positionField = new TextField();
    TextField salaryField = new TextField();

    EmployeeService service;

    @Override
    public void start(Stage stage) {

        // ---------------- RMI CONNECTION ----------------
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            service = (EmployeeService) registry.lookup("EmployeeService");

            table.setItems(FXCollections.observableArrayList(service.getEmployees()));

        } catch (Exception e) {
            showAlert("Error connecting to server!");
            e.printStackTrace();
        }

        // ---------------- FORM ----------------
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        nameField.setPromptText("Enter name");
        positionField.setPromptText("Enter position");
        salaryField.setPromptText("Enter salary");

        Button addBtn = new Button("Add Employee");
        Button deleteBtn = new Button("Delete Selected");
        Button updateBtn = new Button("Update Employee");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Position:"), 0, 1);
        grid.add(positionField, 1, 1);

        grid.add(new Label("Salary:"), 0, 2);
        grid.add(salaryField, 1, 2);

        grid.add(addBtn, 1, 3);

        // ---------------- TABLE ----------------
        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> posCol = new TableColumn<>("Position");
        posCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Employee, Double> salCol = new TableColumn<>("Salary");
        salCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        table.getColumns().addAll(idCol, nameCol, posCol, salCol);

        // ---------------- SELECT ROW ----------------
        table.setOnMouseClicked(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nameField.setText(selected.getName());
                positionField.setText(selected.getPosition());
                salaryField.setText(String.valueOf(selected.getSalary()));
            }
        });

        // ---------------- ADD ----------------
        addBtn.setOnAction(e -> {
            if (!validateInputs()) return;

            try {
                String response = service.addEmployee(
                        nameField.getText(),
                        positionField.getText(),
                        Double.parseDouble(salaryField.getText())
                );

                System.out.println(response);
                showAlert(response);

                table.setItems(FXCollections.observableArrayList(service.getEmployees()));
                clearFields();

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error adding employee!");
            }
        });

        // ---------------- DELETE ----------------
        deleteBtn.setOnAction(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Please select an employee!");
                return;
            }

            try {
                String response = service.deleteEmployee(selected.getId());

                System.out.println(response);
                showAlert(response);

                table.setItems(FXCollections.observableArrayList(service.getEmployees()));
                clearFields();

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error deleting employee!");
            }
        });

        // ---------------- UPDATE ----------------
        updateBtn.setOnAction(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Please select an employee!");
                return;
            }

            if (!validateInputs()) return;

            try {
                String response = service.updateEmployee(
                        selected.getId(),
                        nameField.getText(),
                        positionField.getText(),
                        Double.parseDouble(salaryField.getText())
                );

                System.out.println(response);
                showAlert(response);

                table.setItems(FXCollections.observableArrayList(service.getEmployees()));
                // ❌ NO clearFields() here

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error updating employee!");
            }
        });

        // ---------------- LAYOUT ----------------
        VBox root = new VBox(10, grid, table, addBtn, updateBtn, deleteBtn);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Employee Management System");
        stage.setScene(scene);
        stage.show();
    }

    // ---------------- VALIDATION ----------------
    private boolean validateInputs() {

        if (nameField.getText().isEmpty()) {
            showAlert("Name is required!");
            return false;
        }

        if (positionField.getText().isEmpty()) {
            showAlert("Position is required!");
            return false;
        }

        try {
            Double.parseDouble(salaryField.getText());
        } catch (Exception e) {
            showAlert("Salary must be a number!");
            return false;
        }

        return true;
    }

    // ---------------- CLEAR ----------------
    private void clearFields() {
        nameField.clear();
        positionField.clear();
        salaryField.clear();
    }

    // ---------------- ALERT ----------------
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}