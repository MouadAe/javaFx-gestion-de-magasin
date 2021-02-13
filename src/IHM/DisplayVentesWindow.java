package IHM;

import java.time.LocalDate;
import java.util.Date;

import Connection.Produit.Produit;
import Connection.Ventes.LigneDeCommande;
import Connection.Ventes.Vente;
import Connection.Ventes.VenteHandler;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.input.MouseEvent;
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

   TableColumn<LigneDeCommande, String> ldcDesignationColumn = new TableColumn<>("Designation");
   TableColumn<LigneDeCommande, Double> ldcQteVenduColumn = new TableColumn<>("Quantite vendu");
   public TableView<LigneDeCommande> ldcTableView = new TableView<>();
   // public ObservableList<LigneDeCommande> ligneDeCommandeObservableList = FXCollections.observableArrayList();

   VenteHandler handler = new VenteHandler(this);
   Vente currentVente;
   

   private void addColumnsToTableView(){
      ventesDisplayTableView.getColumns().addAll(idColumn,dateColumn,nomColumn,prenomColumn);
      ventesDisplayTableView.setItems(ventesObservableList);

      ldcTableView.getColumns().addAll(ldcDesignationColumn,ldcQteVenduColumn);

   }
   private void addNodesToPane(){
      root.getChildren().addAll(titleLabel,ventesDisplayTableView);
      root.getChildren().add(ldcTableView);
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

      ldcDesignationColumn.setCellValueFactory(
         (Callback<CellDataFeatures<LigneDeCommande, String>, ObservableValue<String>>) 
         new Callback<CellDataFeatures<LigneDeCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<LigneDeCommande, String> data) {
               return new SimpleStringProperty(data.getValue().getProduit().getDesignation());
            }   
         });
      ldcDesignationColumn.setPrefWidth(200);
      ldcQteVenduColumn.setCellValueFactory(new PropertyValueFactory("QteVendu"));
      ldcQteVenduColumn.setPrefWidth(200);

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
      ventesDisplayTableView.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() >= 1){
            currentVente = ventesDisplayTableView.getSelectionModel().getSelectedItem();
            // System.out.println(currentVente);
            // System.out.println(currentVente.getId());
            handler.displayLdcOfVente(currentVente);
         }
      });
   }

   public DisplayVentesWindow(){
      initWindow();
      addStylesToNodes();
      updateColumns();
      addEvents();
      addColumnsToTableView();
      handler.displayListVentes();
      addNodesToPane();
      window.show();
   }

}
