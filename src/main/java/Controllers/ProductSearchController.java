package Controllers;

import Connection.DatabaseConnection;
import Models.ProductSearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;


public class ProductSearchController implements Initializable {
    @FXML
    private TableColumn<ProductSearchModel, String> brandColumn;

    @FXML
    private TableColumn<ProductSearchModel,String> desciptionColumn;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableColumn<ProductSearchModel,String> modelColumn;

    @FXML
    private TableColumn<ProductSearchModel,Integer> modelyearColumn;

    @FXML
    private TableColumn<ProductSearchModel,Integer> productIDColumn;

    @FXML
    private TableColumn<ProductSearchModel,String> productNameColumn;

    @FXML
    private TableView productTableView;


    ObservableList productSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection=databaseConnection.getConnection();
            String query ="SELECT *  FROM products";
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(query);

            while (resultSet.next()){

                Integer productID= resultSet.getInt("productID");
                String productName= resultSet.getString("productName");
                String brand= resultSet.getString("brand");
                String modelNumber= resultSet.getString("modelNumber");
                Integer modelYear= resultSet.getInt("modelYear");
                String description= resultSet.getString("description");


                //Populate the obserbable list
                productSearchModelObservableList.add(new ProductSearchModel(productID,productName,brand,modelNumber,modelYear,description));
            }


            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            brandColumn.setCellValueFactory(new PropertyValueFactory<>("branD"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelNumber"));
            modelyearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
            desciptionColumn.setCellValueFactory(new PropertyValueFactory<>("desCription"));

            productTableView.setItems(productSearchModelObservableList);

            FilteredList<ProductSearchModel>filteredList=new FilteredList<>(productSearchModelObservableList, b-> true);


            keywordTextField.textProperty().addListener((observable, oldValue,newValue)->{
                filteredList.setPredicate(productSearchModel ->{

                    //If no search value then display all records or whateaver records it current have no change.
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return  true;
                    }

                    String searchkeyword=newValue.toLowerCase();
                    if(productSearchModel.getProductName().toLowerCase().indexOf(searchkeyword) !=-1){
                        return true; //Means we found a match in ProductName
                    } else if (productSearchModel.getDesCription().toLowerCase().indexOf(searchkeyword)>-1) {
                        return true; //Means we found a match in Description
                    } else if (productSearchModel.getBranD().toLowerCase().indexOf(searchkeyword)>-1) {
                        return true;
                    } else if (productSearchModel.getModelNumber().toLowerCase().indexOf(searchkeyword)>-1) {
                        return true;
                    } else if (productSearchModel.getModelYear().toString().indexOf(searchkeyword)>-1) {
                        return  true;
                    }else
                        return false; //no matchs found
                });
            });
            SortedList<ProductSearchModel> sortedList=new SortedList<>(filteredList);
            //Bind sorted result with Table View
            sortedList.comparatorProperty().bind(productTableView.comparatorProperty());
            //Apply filtered and sorted data do the Table View
            productTableView.setItems(sortedList);

        } catch (SQLException e) {
            e.printStackTrace();
        }





        }
    }


