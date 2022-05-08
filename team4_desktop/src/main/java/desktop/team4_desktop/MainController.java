package desktop.team4_desktop;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



import static com.sun.tools.javac.util.Constants.format;



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
    private AnchorPane apBackground;

    @FXML
    private AnchorPane apAgents;
    @FXML
    private AnchorPane apCustomers;
    @FXML
    private AnchorPane apPackages;

    @FXML
    private DatePicker dpkEndDate;

    @FXML
    private DatePicker dpkStartDate;

/* AGENTS TAB */

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
    private TextField fldAgtMiddleInitial;
    @FXML
    private TextField fldAgtLastName;
    @FXML
    private TextField fldAgtBusPhone;
    @FXML
    private TextField fldAgtEmail;
    @FXML
    private TextField fldAgtPosition;
    @FXML
    private TextField fldAgencyId;


    @FXML
    private Button btnAddAgent;
    @FXML
    private Button btnUpdateAgent;
    @FXML
    private Button btnEditAgent;



/* CUSTOMERS TAB */
    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Customer, Integer> colCustId;
    @FXML
    private TableColumn<Customer, String> colCustName;
    @FXML
    private TableColumn<Customer, String> colCustAddress;
    @FXML
    private TableColumn<Customer, String> colCustPostal;
    @FXML
    private TableColumn<Customer, String> colCustHomePhone;
    @FXML
    private TableColumn<Customer, String> colCustBusPhone;
    @FXML
    private TableColumn<Customer, String> colCustEmail;
    @FXML
    private TableColumn<Agent, Integer> colCustAgentId;

    @FXML
    private TextField fldCustFirstName;
    @FXML
    private TextField fldCustLastName;
    @FXML
    private TextField fldCustAddress;
    @FXML
    private TextField fldCustCity;
    @FXML
    private TextField fldCustPostal;
    @FXML
    private TextField fldCustProv;
    @FXML
    private TextField fldCustCountry;
    @FXML
    private TextField fldCustHomePhone;
    @FXML
    private TextField fldCustBusPhone;
    @FXML
    private TextField fldCustEmail;


    @FXML
    private Button btnUpdateCustomer;
    @FXML
    private Button btnAddCustomer;
    @FXML
    private Button btnEditCustomer;


/* PACKAGES TAB */
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
    private TextField fldPkgDesc;
    @FXML
    private TextField fldPkgBasePrice;
    @FXML
    private TextField fldPkgAgencyCommission;


    @FXML
    private Button btnUpdatePackage;
    @FXML
    private Button btnAddPackage;
    @FXML
    private Button btnEditPackage;



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
    private boolean update;

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
        assert colCustPostal != null : "fx:id=\"colCustPostal\" was not injected: check your FXML file 'main-view.fxml'.";
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

        assert btnAddAgent != null : "fx:id=\"btnAddAgent\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnAddPackage != null : "fx:id=\"btnAddPackage\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnUpdateAgent != null : "fx:id=\"btnUpdateAgent\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnUpdateCustomer != null : "fx:id=\"btnUpdateCustomer\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnUpdatePackage != null : "fx:id=\"btnUpdatePackage\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnEditCustomer != null : "fx:id=\"btnEditCustomer\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnEditAgent != null : "fx:id=\"btnEditAgent\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnEditPackage != null : "fx:id=\"btnEditPackage\" was not injected: check your FXML file 'main-view.fxml'.";

        assert fldAgencyId != null : "fx:id=\"fldAgencyId\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtBusPhone != null : "fx:id=\"fldAgtBusPhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtEmail != null : "fx:id=\"fldAgtEmail\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtFirstName != null : "fx:id=\"fldAgtFirstName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtLastName != null : "fx:id=\"fldAgtLastName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtMiddleInitial != null : "fx:id=\"fldAgtMiddleInitial\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldAgtPosition != null : "fx:id=\"fldAgtPosition\" was not injected: check your FXML file 'main-view.fxml'.";

        assert fldCustAddress != null : "fx:id=\"fldCustAddress\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustBusPhone != null : "fx:id=\"fldCustBusPhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustCity != null : "fx:id=\"fldCustCity\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustCountry != null : "fx:id=\"fldCustCountry\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustEmail != null : "fx:id=\"fldCustEmail\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustFirstName != null : "fx:id=\"fldCustFirstName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustHomePhone != null : "fx:id=\"fldCustHomePhone\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustLastName != null : "fx:id=\"fldCustLastName\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustPostal != null : "fx:id=\"fldCustPostal\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldCustProv != null : "fx:id=\"fldCustProv\" was not injected: check your FXML file 'main-view.fxml'.";

        assert fldPkgAgencyCommission != null : "fx:id=\"fldPkgAgencyCommission\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldPkgBasePrice != null : "fx:id=\"fldPkgBasePrice\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldPkgDesc != null : "fx:id=\"fldPkgDesc\" was not injected: check your FXML file 'main-view.fxml'.";
        assert fldPkgName != null : "fx:id=\"fldPkgName\" was not injected: check your FXML file 'main-view.fxml'.";

        assert dpkEndDate != null : "fx:id=\"dpkEndDate\" was not injected: check your FXML file 'main-view.fxml'.";
        assert dpkStartDate != null : "fx:id=\"dpkStartDate\" was not injected: check your FXML file 'main-view.fxml'.";

        // Setting Agents table column headings and cell values
        colAgtId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));
        colAgtFirstName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtFirstName"));
        colAgtMiddleInitial.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtMiddleInitial"));
        colAgtLastName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtLastName"));
        colAgtBusPhone.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtBusPhone"));
        colAgtEmail.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtEmail"));
        colAgtPosition.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtPosition"));
        colAgencyId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agencyId"));

        // Setting Customers table column headings and cell values
        colCustId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().custFirstNameProperty().get() + " " +
                cellData.getValue().custLastNameProperty().get()));
        colCustAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().custAddressProperty().get() + " " +
                cellData.getValue().custCityProperty().get() + ", " +
                cellData.getValue().custProvProperty().get()));
        colCustPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
        colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colCustBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        colCustAgentId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));

        // Setting Packages table column headings and cell values. Note that the formatting of dates and currencies had to be modified to display correctly.
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        colPackageId.setCellValueFactory(new PropertyValueFactory<Package, Integer>("packageId"));
        colPkgName.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgName"));
        colPkgStartDate.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd")
                .format(cellData.getValue().getPkgStartDate()))); // Custom formatting to get dates to display in the view
        colPkgEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd")
                .format(cellData.getValue().getPkgEndDate()))); // Custom formatting to get dates to display in the view
        colPkgDesc.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgDesc"));
        colPkgBasePrice.setCellValueFactory(new PropertyValueFactory<Package, Double>("pkgBasePrice"));
        colPkgBasePrice.setCellFactory(tc -> new TableCell<Package, Double>() {
            @Override
            protected void updateItem(Double pkgBasePrice, boolean empty) {
                super.updateItem(pkgBasePrice, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(pkgBasePrice)); // Applying currency formatting to get prices to display correctly in the view
                }
            }
        });
        colPkgAgencyCommission.setCellValueFactory(new PropertyValueFactory<Package, Double>("pkgAgencyCommission"));
        colPkgAgencyCommission.setCellFactory(tc -> new TableCell<Package, Double>() {
            @Override
            protected void updateItem(Double pkgAgencyCommission, boolean empty) {
                super.updateItem(pkgAgencyCommission, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(pkgAgencyCommission)); // Applying currency formatting to get prices to display correctly in the view
                }
            }
        });

        dpkStartDate.setValue(LocalDate.now()); // Set DatePicker start date to current day
        dpkEndDate.setValue(LocalDate.now()); // Set DatePicker start date to current day

        // Binding DB data to GUI table views
        tvCustomers.setItems(custDB);
        tvAgents.setItems(agentDB);
        tvPackages.setItems(packageDB);

        try {
            FileInputStream fis = new FileInputStream("team4_desktop/src/main/resources/connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user"); // Harv, please note the username we used is 'TravelExpertsAdmin'
            password = (String) p.get("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calls methods necessary to load information neatly on startup. getTableData() method code starts on line 525 below
        getTableData();

        // MouseClick events for the table views are specified below, note that double-clicks are required to get the edit fields to populate
        tvCustomers.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                fillCustomerDetails(event); // this method's code starts on line 583 below

                btnAddCustomer.setDisable(false);
                btnUpdateCustomer.setDisable(true);
                btnEditCustomer.setDisable(false);
                disableCustFields();
            }
        });

        tvPackages.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                fillPackageDetails(event);

                Package pkg = tvPackages.getSelectionModel().getSelectedItem();

                dpkStartDate.setValue(LocalDate.from(pkg.getPkgStartDate().toLocalDateTime()));
                dpkEndDate.setValue(LocalDate.from(pkg.getPkgEndDate().toLocalDateTime()));

                btnAddPackage.setDisable(false);
                btnUpdatePackage.setDisable(true);
                btnEditPackage.setDisable(false);
                disablePackageFields();
            }
        });

        tvAgents.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                fillAgentDetails(event);
                btnAddAgent.setDisable(false);
                btnUpdateAgent.setDisable(true);
                btnEditAgent.setDisable(false);
                disableAgentFields();
            }
        });

        // Edit customer button functionality
        btnEditCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                enableCustFields();
                btnUpdateCustomer.setDisable(false);
                btnAddCustomer.setDisable(true);
                update = true;
            }
        });
        // Edit package button functionality
        btnEditPackage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                enablePackageFields();
                btnUpdatePackage.setDisable(false);
                btnAddPackage.setDisable(true);
                update = true;
            }
        });
        // Edit agent button functionality
        btnEditAgent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                enableAgentFields();
                btnUpdateAgent.setDisable(false);
                btnAddAgent.setDisable(true);
                update = true;
            }
        });


        // Add customer button functionality
        btnAddCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                enableCustFields();
                btnUpdateCustomer.setDisable(false);
                btnAddCustomer.setDisable(true);
                update = false;
                fldCustFirstName.setText("");
                fldCustLastName.setText("");
                fldCustAddress.setText("");
                fldCustCity.setText("");
                fldCustProv.setText("");
                fldCustPostal.setText("");
                fldCustCountry.setText("");
                fldCustHomePhone.setText("");
                fldCustBusPhone.setText("");
                fldCustEmail.setText("");

            }
        });
        // Add package button functionality
        btnAddPackage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                enablePackageFields();
                btnUpdatePackage.setDisable(false);
                btnAddPackage.setDisable(true);
                update = false;

                fldPkgName.setText("");
                fldPkgDesc.setText("");
                fldPkgBasePrice.setText("");
                fldPkgAgencyCommission.setText("");

            }
        });
        // Add agent button functionality
        btnAddAgent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                enableAgentFields();
                btnUpdateAgent.setDisable(false);
                btnAddAgent.setDisable(true);
                update = false;

                fldAgtFirstName.setText("");
                fldAgtMiddleInitial.setText("");
                fldAgtLastName.setText("");
                fldAgtBusPhone.setText("");
                fldAgtEmail.setText("");
                fldAgtPosition.setText("");
                fldAgencyId.setText("");

            }
        });

    }
    // getTableData method, called above to populate the table views with agents, customers, and packages respectively
    private void getTableData() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM agents");
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
            // Brett - I just commented this out so that the JDBC driver is used rather than the SQLServer driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next())
            {
                custDB.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11).trim(), rs.getInt(12)));  // Trim in entry #11 to clear the whitespace on some email addresses
            }
            conn.close();
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM packages");
            while (rs.next())
            {
                packageDB.add(new Package(rs.getInt(1), rs.getString(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getFloat(6),
                        rs.getFloat(7)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // fillCustomerDetails method that is called on mouse click
    public void fillCustomerDetails(javafx.scene.input.MouseEvent mouseEvent) {
        Customer customer = tvCustomers.getSelectionModel().getSelectedItem();

        btnEditCustomer.setDisable(false);

        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select a row.");
            alert.showAndWait();

            return;
        }

        this.customerId = customer.getCustomerId();

        fldCustFirstName.setText(customer.getCustFirstName());
        fldCustLastName.setText(customer.getCustLastName());
        fldCustAddress.setText(customer.getCustAddress());
        fldCustCity.setText(customer.getCustCity());
        fldCustProv.setText(customer.getCustProv());
        fldCustPostal.setText(customer.getCustPostal());
        fldCustCountry.setText(customer.getCustCountry());
        fldCustHomePhone.setText(customer.getCustHomePhone());
        fldCustBusPhone.setText(customer.getCustBusPhone());
        fldCustEmail.setText(customer.getCustEmail());
    }
    // addCustomer method that is called on mouse click
    public void addCustomer() {
        this.customerId = -1;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "INSERT INTO CUSTOMERS " +
                            "(CUSTFIRSTNAME, CUSTLASTNAME, CUSTADDRESS, CUSTCITY, CUSTPROV, CUSTPOSTAL, CUSTCOUNTRY, CUSTHOMEPHONE, CUSTBUSPHONE, CUSTEMAIL, AGENTID) " +
                            "VALUES" +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, fldCustFirstName.getText());
            preparedStatement.setString(2, fldCustLastName.getText());
            preparedStatement.setString(3, fldCustAddress.getText());
            preparedStatement.setString(4, fldCustCity.getText());
            preparedStatement.setString(5, fldCustProv.getText());
            preparedStatement.setString(6, fldCustPostal.getText());
            preparedStatement.setString(7, fldCustCountry.getText());
            preparedStatement.setString(8, fldCustHomePhone.getText());
            preparedStatement.setString(9, fldCustBusPhone.getText());
            preparedStatement.setString(10, fldCustEmail.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Customer added successfully.");
            alert.showAndWait();

            this.customerId = -1;
            fldCustFirstName.setText("");
            fldCustLastName.setText("");
            fldCustAddress.setText("");
            fldCustCity.setText("");
            fldCustProv.setText("");
            fldCustPostal.setText("");
            fldCustCountry.setText("");
            fldCustHomePhone.setText("");
            fldCustBusPhone.setText("");
            fldCustEmail.setText("");


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnAddCustomer.setDisable(false);
        btnUpdateCustomer.setDisable(true);
        btnEditCustomer.setDisable(true);
        disableCustFields();
    }
    // updateCustomer method that is called on mouse click
    public void updateCustomer(MouseEvent mouseEvent) {

        if(update == false){

            addCustomer();
            return;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "UPDATE CUSTOMERS SET CUSTFIRSTNAME=?, CUSTLASTNAME=?, CUSTADDRESS=?, CUSTCITY=?, CUSTPROV=?, " +
                            "CUSTPOSTAL=?, CUSTCOUNTRY=?, CUSTHOMEPHONE=?, CUSTBUSPHONE=?, CUSTEMAIL=? WHERE CUSTOMERID='" +
                            this.customerId + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldCustFirstName.getText());
            preparedStatement.setString(2, fldCustLastName.getText());
            preparedStatement.setString(3, fldCustAddress.getText());
            preparedStatement.setString(4, fldCustCity.getText());
            preparedStatement.setString(5, fldCustProv.getText());
            preparedStatement.setString(6, fldCustPostal.getText());
            preparedStatement.setString(7, fldCustCountry.getText());
            preparedStatement.setString(8, fldCustHomePhone.getText());
            preparedStatement.setString(9, fldCustBusPhone.getText());
            preparedStatement.setString(10, fldCustEmail.getText());




            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Customer Info updated successfully.");
            alert.showAndWait();

            this.customerId = -1;
            fldCustFirstName.setText("");
            fldCustLastName.setText("");
            fldCustAddress.setText("");
            fldCustCity.setText("");
            fldCustProv.setText("");
            fldCustPostal.setText("");
            fldCustCountry.setText("");
            fldCustHomePhone.setText("");
            fldCustBusPhone.setText("");
            fldCustEmail.setText("");


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnEditCustomer.setDisable(true);
        btnUpdateCustomer.setDisable(true);
        btnAddCustomer.setDisable(false);
        disableCustFields();

    }

    public void fillPackageDetails(MouseEvent mouseEvent) {
        Package pkg = tvPackages.getSelectionModel().getSelectedItem();
        btnEditPackage.setDisable(false);

        if(pkg == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a row.");
            alert.showAndWait();

            return;
        }

        this.packageId = pkg.getPackageId();

        fldPkgName.setText(pkg.getPkgName());
//        fldPkgStartDate.setText(pkg.getPkgStartDate().toString());//format(DateFormat.YEAR_FIELD + DateFormat.MONTH_FIELD + DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD)
//        fldPkgEndDate.setText(pkg.getPkgEndDate().toString());//format(DateFormat.YEAR_FIELD + DateFormat.MONTH_FIELD + DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD)
        fldPkgDesc.setText(pkg.getPkgDesc());
        fldPkgBasePrice.setText(Double.toString(pkg.getPkgBasePrice()));
        fldPkgAgencyCommission.setText(Double.toString(pkg.getPkgAgencyCommission()));
    }

    public void addPackage() {

        this.packageId = -1;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "INSERT INTO PACKAGES " +
                            "(PKGNAME, PKGSTARTDATE, PKGENDDATE, PKGDESC, PKGBASEPRICE, PKGAGENCYCOMMISSION) " +
                            "VALUES" +
                            "(?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, fldPkgName.getText());
            preparedStatement.setString(2, dpkStartDate.getValue().toString());
            preparedStatement.setString(3, dpkEndDate.getValue().toString());
            preparedStatement.setString(4, fldPkgDesc.getText());
            try
            {
                preparedStatement.setString(5, fldPkgBasePrice.getText());
            }
            catch (NumberFormatException e) {
                preparedStatement.setString(5, "0");
            }
            try {
                preparedStatement.setString(6, fldPkgAgencyCommission.getText());
            }
            catch (NumberFormatException e) {
                preparedStatement.setString(6, "0");
            }

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Package added auccesfully.");
            alert.showAndWait();

            this.packageId = -1;
            fldPkgName.setText("");
            dpkStartDate.setValue(LocalDate.now());
            dpkEndDate.setValue(LocalDate.now());
            fldPkgDesc.setText("");
            fldPkgBasePrice.setText("");
            fldPkgAgencyCommission.setText("");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnAddPackage.setDisable(false);
        btnUpdatePackage.setDisable(true);
        btnEditPackage.setDisable(true);
        disablePackageFields();

    }

    public void updatePackage(MouseEvent mouseEvent) {

        if(update == false){
                addPackage();
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
            preparedStatement.setString(2, dpkStartDate.getValue().toString());
            preparedStatement.setString(3, dpkEndDate.getValue().toString());
            preparedStatement.setString(4, fldPkgDesc.getText());
            try
            {
                preparedStatement.setString(5, fldPkgBasePrice.getText());
            }
            catch (NumberFormatException e) {
                preparedStatement.setString(5, "0");
            }
            try {
                preparedStatement.setString(6, fldPkgAgencyCommission.getText());
            }
            catch (NumberFormatException e) {
                preparedStatement.setString(6, "0");
            }
            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Package updated succesfully.");
            alert.showAndWait();

            this.packageId = -1;
            fldPkgName.setText("");
            dpkStartDate.setValue(LocalDate.now());
            dpkEndDate.setValue(LocalDate.now());
            fldPkgDesc.setText("");
            fldPkgBasePrice.setText("");
            fldPkgAgencyCommission.setText("");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText(null);
            fail.setContentText("Package could not be added. Please check entered information and try again.");
        }

        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnEditPackage.setDisable(true);
        btnUpdatePackage.setDisable(true);
        btnAddPackage.setDisable(false);
        disablePackageFields();
    }

    public void fillAgentDetails(MouseEvent mouseEvent) {
        Agent agent = tvAgents.getSelectionModel().getSelectedItem();
        btnEditAgent.setDisable(false);

        if(agent == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a row.");
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

    public void addAgent() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query =
                    "INSERT INTO AGENTS " +
                            "(AGTFIRSTNAME, AGTMIDDLEINITIAL, AGTLASTNAME, AGTBUSPHONE, AGTEMAIL, AGTPOSITION, AGENCYID) " +
                            "VALUES" +
                            "(?, NULL, ?, ?, ?, ?, 1)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldAgtFirstName.getText());
            preparedStatement.setString(2, fldAgtLastName.getText());
            preparedStatement.setString(3, fldAgtBusPhone.getText());
            preparedStatement.setString(4, fldAgtEmail.getText());
            preparedStatement.setString(5, fldAgtPosition.getText());

            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Agent added succesfully.");
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


        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnEditAgent.setDisable(true);
        btnUpdateAgent.setDisable(true);
        btnAddAgent.setDisable(false);
        disableAgentFields();

    }

    public void updateAgent(MouseEvent mouseEvent) {


        if(update == false){
            addAgent();
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
            alert.setContentText("Agent updated succesfully.");
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

        custDB.clear();
        packageDB.clear();
        agentDB.clear();

        getTableData();

        btnEditAgent.setDisable(true);
        btnUpdateAgent.setDisable(true);
        btnAddAgent.setDisable(false);
        disableAgentFields();
    }

    public void enableCustFields() {
        fldCustFirstName.setDisable(false);
        fldCustLastName.setDisable(false);
        fldCustAddress.setDisable(false);
        fldCustCity.setDisable(false);
        fldCustPostal.setDisable(false);
        fldCustProv.setDisable(false);
        fldCustCountry.setDisable(false);
        fldCustHomePhone.setDisable(false);
        fldCustBusPhone.setDisable(false);
        fldCustEmail.setDisable(false);
    }

    public void disableCustFields() {
        fldCustFirstName.setDisable(true);
        fldCustLastName.setDisable(true);
        fldCustAddress.setDisable(true);
        fldCustCity.setDisable(true);
        fldCustPostal.setDisable(true);
        fldCustProv.setDisable(true);
        fldCustCountry.setDisable(true);
        fldCustHomePhone.setDisable(true);
        fldCustBusPhone.setDisable(true);
        fldCustEmail.setDisable(true);
    }


    public void enablePackageFields() {
        fldPkgName.setDisable(false);
        dpkStartDate.setDisable(false);
        dpkEndDate.setDisable(false);
        fldPkgDesc.setDisable(false);
        fldPkgBasePrice.setDisable(false);
        fldPkgAgencyCommission.setDisable(false);
    }

    public void disablePackageFields() {
        fldPkgName.setDisable(true);
        fldPkgDesc.setDisable(true);
        dpkStartDate.setDisable(true);
        dpkEndDate.setDisable(true);
        fldPkgBasePrice.setDisable(true);
        fldPkgAgencyCommission.setDisable(true);
    }

    public void enableAgentFields() {
        fldAgtFirstName.setDisable(false);
        fldAgtMiddleInitial.setDisable(false);
        fldAgtLastName.setDisable(false);
        fldAgtBusPhone.setDisable(false);
        fldAgtEmail.setDisable(false);
        fldAgtPosition.setDisable(false);
        fldAgencyId.setDisable(false);
    }

    public void disableAgentFields() {
        fldAgtFirstName.setDisable(true);
        fldAgtMiddleInitial.setDisable(true);
        fldAgtLastName.setDisable(true);
        fldAgtBusPhone.setDisable(true);
        fldAgtEmail.setDisable(true);
        fldAgtPosition.setDisable(true);
        fldAgencyId.setDisable(true);
    }

}
