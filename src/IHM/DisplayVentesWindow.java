package IHM;

import java.time.LocalDate;

import Connection.Ventes.LigneDeCommande;
import Connection.Ventes.Vente;
import Connection.Ventes.VenteHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DisplayVentesWindow {
   Stage window = new Stage();
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Label titleLabel = new Label("Liste de ventes");
   HBox totalHbox = new HBox();
   TableColumn<Vente,String> idColumn = new TableColumn<>("id ");  
   TableColumn<Vente,String> nomColumn = new TableColumn<>("Nom ");  
   TableColumn<Vente,String> prenomColumn = new TableColumn<>("Prenom ");   
   TableColumn<Vente,LocalDate> dateColumn = new TableColumn<>("Date");
   public TableView<Vente> ventesDisplayTableView = new TableView<>();
   public ObservableList<Vente> ventesObservableList = FXCollections.observableArrayList();

   TableColumn<LigneDeCommande, String> lcDesignationColumn = new TableColumn<>("Designation");
   TableColumn<LigneDeCommande, Double> lcPrixColumn = new TableColumn<>("Prix");
   TableColumn<LigneDeCommande, Integer> lcQteColumn = new TableColumn<>("QteVendu");
   TableColumn<LigneDeCommande, Double> lcSousTotalColumn = new TableColumn<>("SousTotal");
   public TableView<LigneDeCommande> ligneDeCommandeTableView = new TableView<>();
   // public ObservableList<LigneDeCommande> ligneDeCommandeObservableList = FXCollections.observableArrayList();

   VenteHandler handler = new VenteHandler(this);
   Vente currentVente;
   

   private void addColumnsToTableView(){
      ventesDisplayTableView.getColumns().addAll(idColumn,dateColumn,nomColumn,prenomColumn);
      ventesDisplayTableView.setItems(ventesObservableList);

      ligneDeCommandeTableView.getColumns().addAll(lcDesignationColumn, lcPrixColumn, lcQteColumn, lcSousTotalColumn);

   }
   private void addNodesToPane(){
      root.getChildren().addAll(titleLabel,ventesDisplayTableView);
   }

   private void updateColumns(){
      idColumn.setCellValueFactory(new PropertyValueFactory("id"));
      idColumn.setPrefWidth(100);
      dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
      dateColumn.setPrefWidth(200);
      nomColumn.setCellValueFactory(
            (Callback<CellDataFeatures<Vente, String>, ObservableValue<String>>) 
            new Callback<CellDataFeatures<Vente, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<Vente, String> data) {
                  return new SimpleStringProperty(data.getValue().getClient().getNom());
               }
            });
      nomColumn.setPrefWidth(250);
      prenomColumn.setCellValueFactory(
            (Callback<CellDataFeatures<Vente, String>, ObservableValue<String>>) 
            new Callback<CellDataFeatures<Vente, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<Vente, String> data) {
                  return new SimpleStringProperty(data.getValue().getClient().getPrenom());
               }
            });
      prenomColumn.setPrefWidth(250);
   }
   private void initWindow(){
      window.setWidth(820);
      window.setHeight(890);
      window.setTitle("Liste des ventes");
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
   public void addEvents(){
      ventesDisplayTableView.setOnMouseClicked(event -> {
         currentVente = ventesDisplayTableView.getSelectionModel().getSelectedItem();
         handler.displayLdcOfVente(currentVente);
      });
   }

   public DisplayVentesWindow(){
      initWindow();
      addStylesToNodes();
      updateColumns();
      addColumnsToTableView();
      handler.displayListVentes();
      addNodesToPane();
      window.show();
   }

}
