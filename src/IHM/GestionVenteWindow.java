package IHM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.Client.Client;
import Connection.Produit.Produit;
import Connection.Ventes.LigneDeCommande;
import Connection.Ventes.VenteHandler;
import Connection.Ventes.Vente;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GestionVenteWindow {
   Stage window = new Stage();
   VBox root = new VBox();
   HBox titleTopBox = new HBox();
   Scene scene = new Scene(root);
   Label titleLabel;
   TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
   TableColumn<Produit, Double> prixColumn = new TableColumn<>("Prix");
   TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Qte");
   TableView<Produit> produitsTableView = new TableView<>();
   public ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
   VenteHandler handler = new VenteHandler(this);
   List<Produit> listeDesProduits = (ArrayList<Produit>) handler.getListProduits();

   Produit selectedProduit;
   LigneDeCommande selectedLigneDeCommande;
   Client currentClient;

   TableColumn<LigneDeCommande, String> lcDesignationColumn = new TableColumn<>("Designation");
   TableColumn<LigneDeCommande, Double> lcPrixColumn = new TableColumn<>("Prix");
   TableColumn<LigneDeCommande, Integer> lcQteColumn = new TableColumn<>("QteVendu");
   TableColumn<LigneDeCommande, Double> lcSousTotalColumn = new TableColumn<>("SousTotal");
   TableView<LigneDeCommande> ligneDeCommandeTableView = new TableView<>();
   public ObservableList<LigneDeCommande> ligneDeCommandeObservableList = FXCollections.observableArrayList();
   Vente currentVente;
   List<LigneDeCommande> lignesDeCommandes = new ArrayList<>();

   HBox mainBox = new HBox();

   VBox leftSide = new VBox();
   VBox venteBox = new VBox();
   Label venteBoxTitle = new Label("Detail de vente");
   DatePicker venteBoxDate = new DatePicker(LocalDate.now());

   // HBox produitBox = new HBox();
   VBox produitBox = new VBox();
   Label produitBoxDesignation = new Label();
   Label produitBoxPrix = new Label();

   HBox qteHbox = new HBox();
   Label produitBoxQteLabel = new Label("Quantite : ");
   TextField produitBoxQteTextField = new TextField();

   Button addProduitButton = new Button("Ajouter au panier");

   Boolean produitIsClicked = false;
   HBox deleteProduitHbox = new HBox();
   Button deleteProduitButton = new Button("supprimer du panier");

   VBox rightSide = new VBox();
   HBox totalHbox = new HBox();
   Label totalDesLignesDeCommandesLabel = new Label("Total du panier : ");
   Label totalDesLignesDeCommandesValue = new Label("0");

   HBox enregistrer = new HBox();
   Button saveButton = new Button("Enregistrer");
   Boolean saveButtonIsClicked = false;
   Boolean selectedLigneCmdExist = false;

   private void addColumnsToTableView() {
      produitsTableView.getColumns().addAll(designationColumn, prixColumn, qteColumn);
      produitsObservableList = FXCollections.observableArrayList(listeDesProduits);
      produitsTableView.setItems(produitsObservableList);

      ligneDeCommandeTableView.getColumns().addAll(lcDesignationColumn, lcPrixColumn, lcQteColumn, lcSousTotalColumn);
      // ligneDeCommandeTableView.setItems(ligneDeCommandeObservableList);
   }

   private void addNodesToPane() {
      root.getChildren().addAll(titleTopBox, mainBox);
      mainBox.getChildren().addAll(leftSide, rightSide);
      titleTopBox.getChildren().add(titleLabel);

      leftSide.getChildren().addAll(venteBox, produitsTableView);
      leftSide.setSpacing(30);
      // rightSide.setSpacing(30);

      venteBox.getChildren().addAll(venteBoxTitle, venteBoxDate);
      
      // leftSide.getChildren().addAll(produitBox);
      produitBox.getChildren().addAll(produitBoxDesignation, produitBoxPrix);
      qteHbox.getChildren().addAll(produitBoxQteLabel,produitBoxQteTextField);
      produitBox.getChildren().addAll(qteHbox,addProduitButton);
      
      rightSide.getChildren().addAll(ligneDeCommandeTableView,totalHbox);
      totalHbox.getChildren().addAll(totalDesLignesDeCommandesLabel,totalDesLignesDeCommandesValue);
      deleteProduitHbox.getChildren().add(deleteProduitButton);
      enregistrer.getChildren().add(saveButton);
   }

   private void updateColumns() {
      // Produit
      designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
      designationColumn.setPrefWidth(200);
      prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
      prixColumn.setPrefWidth(100);
      qteColumn.setCellValueFactory(new PropertyValueFactory("qte"));
      qteColumn.setPrefWidth(100);
      // Ligne de commande
      // lcDesignationColumn.setCellValueFactory(new PropertyValueFactory("produit.getDesignation()")); Cette ligne ne marche pas!!
      lcDesignationColumn.setCellValueFactory(
            (Callback<CellDataFeatures<LigneDeCommande, String>, ObservableValue<String>>) 
            new Callback<CellDataFeatures<LigneDeCommande, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<LigneDeCommande, String> data) {
                  return new SimpleStringProperty(data.getValue().getProduit().getDesignation());
               }
            });
      lcDesignationColumn.setPrefWidth(250);
      lcPrixColumn.setCellValueFactory(
         new Callback<CellDataFeatures<LigneDeCommande, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(CellDataFeatures<LigneDeCommande, Double> data) {
               return new SimpleDoubleProperty(data.getValue().getProduit().getPrix()).asObject();
            }
         }
      );
      lcPrixColumn.setPrefWidth(100);
      lcQteColumn.setCellValueFactory(new PropertyValueFactory("qteVendu"));
      lcQteColumn.setPrefWidth(100);
      lcSousTotalColumn.setCellValueFactory(new PropertyValueFactory("sousTotal"));
      lcSousTotalColumn.setPrefWidth(100);
   }
   private void initWindow(){
      window.setWidth(1300);
      window.setHeight(850);
      window.setTitle("Liste des produits");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.setScene(scene);
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("titlelistLabel");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER);
      leftSide.setMinWidth((window.getWidth()/2) - 20);
      rightSide.setMinWidth((window.getWidth()/2) - 20);
      enregistrer.setMinWidth(window.getWidth());
      enregistrer.setAlignment(Pos.BASELINE_CENTER);
      venteBox.setAlignment(Pos.BASELINE_CENTER);
      produitBox.setAlignment(Pos.BASELINE_CENTER);
      qteHbox.setAlignment(Pos.BASELINE_CENTER);
      produitBox.setAlignment(Pos.BASELINE_CENTER);
      qteHbox.setAlignment(Pos.BASELINE_CENTER);
      totalHbox.setAlignment(Pos.BASELINE_CENTER);
      // deleteProduitHbox.setMinWidth(window.getWidth());
      deleteProduitHbox.setAlignment(Pos.BASELINE_CENTER);

      // venteBox.setSpacing(40); 
   }
   private void addEvents() {
      produitsTableView.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() >= 1)
            selectedProduit = produitsTableView.getSelectionModel().getSelectedItem();
         produitBoxDesignation.setText("Designation : " + selectedProduit.getDesignation());
         produitBoxPrix.setText("Prix unitaire: " + selectedProduit.getPrix());
         if(!produitIsClicked){
            leftSide.getChildren().add(produitBox);
         } 
         produitIsClicked = true;
      });

      addProduitButton.setOnAction(event -> {
         for(Produit p : listeDesProduits)
         if(p == selectedProduit){
            p.setQte(p.getQte() - Long.valueOf(produitBoxQteTextField.getText()));
            break;
         }
         produitsObservableList.clear();
         produitsObservableList = FXCollections.observableArrayList(listeDesProduits);
         produitsTableView.setItems(produitsObservableList);

         if(!lignesDeCommandes.isEmpty()){
            for(LigneDeCommande l : lignesDeCommandes)
               if(l.getProduit() == selectedProduit){
                  l.setQteVendu(l.getQteVendu() + Long.valueOf(produitBoxQteTextField.getText()));
                  l.setSousTotal();
                  selectedLigneCmdExist = true;
                  break;
               }
            ligneDeCommandeObservableList.clear();
         }
         if(!selectedLigneCmdExist)
            lignesDeCommandes.add(new LigneDeCommande(0,selectedProduit,Long.valueOf(produitBoxQteTextField.getText()),currentVente));

         currentVente = new Vente(0, venteBoxDate.getValue(),lignesDeCommandes,currentClient);

         ligneDeCommandeObservableList = FXCollections.observableArrayList(currentVente.getLigneDeCommandeList());
         ligneDeCommandeTableView.setItems(ligneDeCommandeObservableList);

         totalDesLignesDeCommandesValue.setText(currentVente.getTotal()+"");

         if(!lignesDeCommandes.isEmpty()){
            if(!saveButtonIsClicked)
               root.getChildren().add(enregistrer);
            saveButtonIsClicked = true;
         }
         selectedLigneCmdExist = false;   
      });
      saveButton.setOnAction(event -> {
         handler.updateProduits((ArrayList<Produit>)listeDesProduits,currentVente);
         handler.addLignesDeCommande(currentVente);
         window.close();
      });
      ligneDeCommandeTableView.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() >= 1)
            selectedLigneDeCommande = ligneDeCommandeTableView.getSelectionModel().getSelectedItem();
         rightSide.getChildren().add(deleteProduitHbox);
      });
   }
   public GestionVenteWindow(Client cl){
      initWindow();
      currentClient = cl; 
      titleLabel = new Label("Bonjour " + currentClient.getNom() + " " + currentClient.getPrenom());
      addColumnsToTableView();
      updateColumns();
      addEvents();
      // handler.displayProduitsListWindow();
      addNodesToPane();
      addStylesToNodes();
      window.show();
   }
}
