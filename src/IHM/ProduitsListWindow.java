package IHM;

import Connection.Produit.Produit;
import Connection.Produit.ProduitsListHandler;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProduitsListWindow {
   Stage window = new Stage();
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Label titleLabel = new Label("Liste de produits");
   HBox totalHbox = new HBox();
   Label totalLabel = new Label("Total : ");
   public Label totalLabelValue = new Label("0.0");
   TableColumn<Produit,Long> idColumn = new TableColumn<>("Id"); 
   TableColumn<Produit,String> designationColumn = new TableColumn<>("Designation"); 
   TableColumn<Produit,Double> prixColumn = new TableColumn<>("Prix"); 
   TableColumn<Produit,Integer> qteColumn = new TableColumn<>("Qte"); 
   TableColumn<Produit,Double> totalColumn = new TableColumn<>("Total"); 
   TableColumn<Produit,LocalDate> dateColumn = new TableColumn<>("Date");

   ProduitsListHandler handler = new ProduitsListHandler(this);

   TableView<Produit> produitsTableView = new TableView<>();
   public ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
   
   private void addColumnsToTableView(){
      produitsTableView.getColumns().addAll(idColumn,designationColumn,prixColumn,qteColumn,totalColumn,dateColumn);
      produitsTableView.setItems(produitsObservableList);
   }
   private void addNodesToPane(){
      totalHbox.getChildren().addAll(totalLabel,totalLabelValue);
      root.getChildren().addAll(titleLabel,produitsTableView,totalHbox);
   }

   private void updateColumns(){
      idColumn.setCellValueFactory(new PropertyValueFactory("id"));
      idColumn.setPrefWidth(100);
      designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
      designationColumn.setPrefWidth(250);
      prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
      prixColumn.setPrefWidth(150);
      qteColumn.setCellValueFactory(new PropertyValueFactory("qte"));
      qteColumn.setPrefWidth(150);
      totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
      totalColumn.setPrefWidth(150);
      dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
      dateColumn.setPrefWidth(200);
   }
   private void initWindow(){
      window.setWidth(1020);
      window.setHeight(550);
      window.setTitle("Liste des produits");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.setScene(scene);
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("titlelistLabel");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER); 
      totalHbox.getStyleClass().add("listLabel");
      totalHbox.setAlignment(Pos.BASELINE_CENTER); 
   }

   public ProduitsListWindow(){
      initWindow();
      addStylesToNodes();
      updateColumns();
      addColumnsToTableView();
      handler.updateProduitsListWindow();
      addNodesToPane();
      window.show();
   }

}
