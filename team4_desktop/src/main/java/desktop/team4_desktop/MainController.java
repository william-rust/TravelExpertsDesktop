package desktop.team4_desktop;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabAgents;

    @FXML
    private Tab tabCustomers;

    @FXML
    private Tab tabPackages;

    @FXML
    private AnchorPane apAgents;

    @FXML
    private AnchorPane apBackground;

    @FXML
    private AnchorPane apCustomers;

    @FXML
    private AnchorPane apPackages;



    @FXML
    private TableView<Agent> tvAgents;

    @FXML
    private TableColumn<Agent, Integer> colAgtId;
    @FXML
    private TableColumn<Agent, String> colAgtFirstName;
    @FXML
    private TableColumn<Agent, String> colAgtMiddleInitial;
    @FXML
    private TableColumn<Agent, String> colAgtLastName;
    @FXML
    private TableColumn<Agent, String > colAgtBusPhone;
    @FXML
    private TableColumn<Agent, String> colAgtEmail;
    @FXML
    private TableColumn<Agent, String> colAgtPosition;
    @FXML
    private TableColumn<Agent, Integer> colAgencyId;

    @FXML
    private TextField fldAgtFirstName;
    @FXML
    private TextField fldAgtLastName;
    @FXML
    private TextField fldAgtBusPhone;
    @FXML
    private TextField fldAgtEmail;
    @FXML
    private TextField fldAgtPosition;

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Customer, Integer> colCustId;
    @FXML
    private TableColumn<Customer, String> colCustName;
    @FXML
    private TableColumn<Customer, String> colCustAddress;
    @FXML
    private TableColumn<Customer, String> colCustHomePhone;
    @FXML
    private TableColumn<Customer, String> colCustBusPhone;
    @FXML
    private TableColumn<Customer, String> colCustEmail;
    @FXML
    private TableColumn<Agent, Integer> colCustAgentId;

    @FXML
    private TextField fldCustName;
    @FXML
    private TextField fldCustAddress;
    @FXML
    private TextField fldCustHomePhone;
    @FXML
    private TextField fldCustBusPhone;
    @FXML
    private TextField fldCustEmail;

    @FXML
    private TableView<Package> tvPackages;

    @FXML
    private TableColumn<Package, Integer> colPackageId;
    @FXML
    private TableColumn<Package, String> colPkgName;
    @FXML
    private TableColumn<Package, String> colPkgStartDate;
    @FXML
    private TableColumn<Package, String> colPkgEndDate;
    @FXML
    private TableColumn<Package, String> colPkgDesc;
    @FXML
    private TableColumn<Package, Double> colPkgBasePrice;
    @FXML
    private TableColumn<Package, Double> colPkgAgencyCommission;

    @FXML
    private TextField fldPkgName;
    @FXML
    private TextField fldPkgStartDate;
    @FXML
    private TextField fldPkgEndDate;
    @FXML
    private TextField fldPkgDesc;
    @FXML
    private TextField fldPkgBasePrice;
    @FXML
    private TextField fldPkgAgencyCommission;


    /* Private Class variables */
    private int selectedIndex;
    private ObservableList<Agent> agentDB = FXCollections.observableArrayList();
    private ObservableList<Customer> custDB = FXCollections.observableArrayList();
    private ObservableList<Package> packageDB = FXCollections.observableArrayList();

    private int customerId = -1, packageId = -1, agentId = -1;
    private String user = "";
    private String password = "";
    private String url      = "";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    @FXML
    void initialize() {
        assert apAgents != null : "fx:id=\"apAgents\" was not injected: check your FXML file 'main-view.fxml'.";
        assert apBackground != null : "fx:id=\"apBackground\" was not injected: check your FXML file 'main-view.fxml'.";
        assert apCustomers != null : "fx:id=\"apCustomers\" was not injected: check your FXML file 'main-view.fxml'.";
        assert apPackages != null : "fx:id=\"apPackages\" was not injected: check your FXML file 'main-view.fxml'.";

        assert colAgtId != null : "fx:id=\"colAgtId\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtFirstName != null : "fx:id=\"colAgtFirstName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtMiddleInitial != null : "fx:id=\"colAgtMiddleInitial\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtLastName != null : "fx:id=\"colAgtLastName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtBusPhone != null : "fx:id=\"colAgtBusPhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtEmail != null : "fx:id=\"colAgtEmail\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgtPosition != null : "fx:id=\"colAgtPosition\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colAgencyId != null : "fx:id=\"colAgencyId\" was not injected: check your FXML file 'main-view.fxml'.";

        assert colCustId != null : "fx:id=\"colCustId\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustName != null : "fx:id=\"colCustName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustAddress != null : "fx:id=\"colCustAddress\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustHomePhone != null : "fx:id=\"colCustHomePhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustBusPhone != null : "fx:id=\"colCustBusPhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustEmail != null : "fx:id=\"colCustEmail\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colCustAgentId != null : "fx:id=\"colCustAgentId\" was not injected: check your FXML file 'main-view.fxml'.";

        assert colPackageId != null : "fx:id=\"colPackageId\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgAgencyCommission != null : "fx:id=\"colPkgAgencyCommission\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgBasePrice != null : "fx:id=\"colPkgBasePrice\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgDesc != null : "fx:id=\"colPkgDesc\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgEndDate != null : "fx:id=\"colPkgEndDate\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgName != null : "fx:id=\"colPkgName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert colPkgStartDate != null : "fx:id=\"colPkgStartDate\" was not injected: check your FXML file 'main-view.fxml'.";

        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tabAgents != null : "fx:id=\"tabAgents\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tabCustomers != null : "fx:id=\"tabCustomers\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tabPackages != null : "fx:id=\"tabPackages\" was not injected: check your FXML file 'main-view.fxml'.";


        assert tvAgents != null : "fx:id=\"tvAgents\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tvCustomers != null : "fx:id=\"tvCustomers\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tvPackages != null : "fx:id=\"tvPackages\" was not injected: check your FXML file 'main-view.fxml'.";


        colAgtId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));
        colAgtFirstName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtFirstName"));
        colAgtMiddleInitial.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtMiddleInitial"));
        colAgtLastName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtLastName"));
        colAgtBusPhone.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtBusPhone"));
        colAgtEmail.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtEmail"));
        colAgtPosition.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtPosition"));
        colAgencyId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agencyId"));

        colCustId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().custFirstNameProperty().get() + " " +
                cellData.getValue().custLastNameProperty().get()));
        colCustAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().custAddressProperty().get() + " " +
                cellData.getValue().custCityProperty().get() + ", " +
                cellData.getValue().custProvProperty().get() + ", " +
                cellData.getValue().custPostalProperty().get()));
        colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colCustBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        colCustAgentId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));


        colPackageId.setCellValueFactory(new PropertyValueFactory<Package, Integer>("packageId"));
        colPkgName.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgName"));
        colPkgStartDate.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgStartDate"));
        colPkgEndDate.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgEndDate"));
        colPkgDesc.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgDesc"));
        colPkgBasePrice.setCellValueFactory(new PropertyValueFactory<Package, Double>("pkgBasePrice"));
        colPkgAgencyCommission.setCellValueFactory(new PropertyValueFactory<Package, Double>("pkgAgencyCommission"));



        tvCustomers.setItems(custDB);
        tvAgents.setItems(agentDB);
        tvPackages.setItems(packageDB);

        try {
            FileInputStream fis = new FileInputStream("team4_desktop/src/main/resources/connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // calls methods necessary to load information neatly on startup
        getTableData();
    }

    private void getTableData() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from agents");
            while (rs.next())
            {
                agentDB.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next())
            {
                custDB.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            conn.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM packages");
            while (rs.next())
            {
                packageDB.add(new Package(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getFloat(6),
                        rs.getFloat(7)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillCustomerDetails(javafx.scene.input.MouseEvent mouseEvent) {
        Customer customer = tvCustomers.getSelectionModel().getSelectedItem();

        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select any row first!!");
            alert.showAndWait();

            return;
        }

        this.customerId = customer.getCustomerId();

        fldCustName.setText(customer.getCustFirstName());
        fldCustAddress.setText(customer.getCustAddress());
        fldCustHomePhone.setText(customer.getCustHomePhone());
        fldCustBusPhone.setText(customer.getCustBusPhone());
        fldCustEmail.setText(customer.getCustEmail());
    }

    public void updateCustomer(MouseEvent mouseEvent) {
        if(this.customerId == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Input Fields are empty!!");
            alert.showAndWait();

            return;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "UPDATE CUSTOMERS SET CUSTFIRSTNAME=?, CUSTEMAIL=?, CUSTHOMEPHONE=?, CUSTADDRESS=?, CUSTBUSPHONE=? WHERE CUSTOMERID='" +
                            this.customerId + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldCustName.getText());
            preparedStatement.setString(2, fldCustEmail.getText());
            preparedStatement.setString(3, fldCustHomePhone.getText());
            preparedStatement.setString(4, fldCustAddress.getText());
            preparedStatement.setString(5, fldCustBusPhone.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Customer Info updated Succesfully!!");
            alert.showAndWait();

            this.customerId = -1;
            fldCustName.setText("");
            fldCustEmail.setText("");
            fldCustHomePhone.setText("");
            fldCustAddress.setText("");
            fldCustBusPhone.setText("");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custDB.clear();
        getTableData();
    }

    public void fillPackageDetails(MouseEvent mouseEvent) {
        Package pkg = tvPackages.getSelectionModel().getSelectedItem();

        if(pkg == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select any row first!!");
            alert.showAndWait();

            return;
        }

        this.packageId = pkg.getPackageId();

        fldPkgName.setText(pkg.getPkgName());
        fldPkgStartDate.setText(pkg.getPkgStartDate().toString());
        fldPkgEndDate.setText(pkg.getPkgEndDate().toString());
        fldPkgDesc.setText(pkg.getPkgDesc());
        fldPkgBasePrice.setText(Float.toString(pkg.getPkgBasePrice()));
        fldPkgAgencyCommission.setText(Float.toString(pkg.getPkgAgencyCommission()));
    }

    public void updatePackage(MouseEvent mouseEvent) {
        if(this.packageId == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Input Fields are empty!!");
            alert.showAndWait();

            return;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "UPDATE PACKAGES SET PKGNAME=?, PKGSTARTDATE=?, PKGENDDATE=?, PKGDESC=?, PKGBASEPRICE=?, PKGAGENCYCOMMISSION=? WHERE PACKAGEID='" +
                            this.packageId + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldPkgName.getText());
            preparedStatement.setString(2, fldPkgStartDate.getText());
            preparedStatement.setString(3, fldPkgEndDate.getText());
            preparedStatement.setString(4, fldPkgDesc.getText());
            preparedStatement.setString(5, fldPkgBasePrice.getText());
            preparedStatement.setString(6, fldPkgAgencyCommission.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Package Info updated Succesfully!!");
            alert.showAndWait();

            this.packageId = -1;
            fldPkgName.setText("");
            fldPkgStartDate.setText("");
            fldPkgEndDate.setText("");
            fldPkgDesc.setText("");
            fldPkgBasePrice.setText("");
            fldPkgAgencyCommission.setText("");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        packageDB.clear();
        getTableData();
    }

    public void fillAgentDetails(MouseEvent mouseEvent) {
        Agent agent = tvAgents.getSelectionModel().getSelectedItem();

        if(agent == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select any row first!!");
            alert.showAndWait();

            return;
        }

        this.agentId = agent.getAgentId();

        fldAgtFirstName.setText(agent.getAgtFirstName());
        fldAgtLastName.setText(agent.getAgtLastName());
        fldAgtBusPhone.setText(agent.getAgtBusPhone());
        fldAgtEmail.setText(agent.getAgtEmail());
        fldAgtPosition.setText(agent.getAgtPosition());
    }

    public void updateAgent(MouseEvent mouseEvent) {
        if(this.agentId == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Input Fields are empty!!");
            alert.showAndWait();

            return;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "UPDATE AGENTS SET AGTFIRSTNAME=?, AGTLASTNAME=?, AGTBUSPHONE=?, AGTEMAIL=?, AGTPOSITION=? WHERE AGENTID='" +
                            this.agentId + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldAgtFirstName.getText());
            preparedStatement.setString(2, fldAgtLastName.getText());
            preparedStatement.setString(3, fldAgtBusPhone.getText());
            preparedStatement.setString(4, fldAgtEmail.getText());
            preparedStatement.setString(5, fldAgtPosition.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Agent Info updated Succesfully!!");
            alert.showAndWait();

            this.agentId = -1;
            fldAgtFirstName.setText("");
            fldAgtLastName.setText("");
            fldAgtBusPhone.setText("");
            fldAgtEmail.setText("");
            fldAgtPosition.setText("");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        agentDB.clear();
        getTableData();
    }
}
-