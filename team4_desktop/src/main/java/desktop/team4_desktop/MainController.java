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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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



    /* Private Class variables */
    private int selectedIndex;
    private ObservableList<Agent> agentDB = FXCollections.observableArrayList();
    private ObservableList<Customer> custDB = FXCollections.observableArrayList();
    private ObservableList<Package> packageDB = FXCollections.observableArrayList();



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



        // calls methods necessary to load information neatly on startup
        getTableData();





    }


    private void getTableData() {
        String user = "";
        String password = "";
        String url      = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

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
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM `customers`");
            while (rs.next())
            {
                custDB.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM `packages`");
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


}
