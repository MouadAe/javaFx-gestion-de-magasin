package IHM;

import Connection.Client.Client;
import Connection.Ventes.VentesClientsListHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VenteMainWindow {
   Stage window = new Stage();
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Label titleLabel = new Label("Choisir le client");
   
   TableColumn<Client,String > nomColumn = new TableColumn<>("Nom"); 
   TableColumn<Client,String > prenomColumn = new TableColumn<>("Prenom"); 

   TableView<Client> clientsTableView = new TableView<>();
   VentesClientsListHandler handler = new VentesClientsListHandler(this);

   public ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();
   Client currentClient = null;
   HBox buttonsBox = new HBox();
   Button addVenteToClientButton = new Button("Ajouter une vente");
   Button ListVentesClientButton = new Button("Liste des ventes");
   Boolean clientIsClicked = false;

   private void addColumnsToTableView(){
      clientsTableView.getColumns().addAll(nomColumn,prenomColumn);
      clientsTableView.setItems(clientsObservableList);
   }
   private void addNodesToPane(){
      root.getChildren().addAll(titleLabel,clientsTableView,buttonsBox);
      buttonsBox.getChildren().add(ListVentesClientButton);
   }
   private void updateColumns(){
      nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
      nomColumn.setPrefWidth(390);
      prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
      prenomColumn.setPrefWidth(390);
   }
   private void initWindow(){
      window.setWidth(800);
      window.setHeight(600);
      window.setTitle("Liste des clients");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.setScene(scene);
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("titlelistLabel");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER);
      buttonsBox.setMinWidth(window.getWidth());
      buttonsBox.setAlignment(Pos.BASELINE_CENTER);
      // root.setSpacing(30);
      buttonsBox.setSpacing(20);
      buttonsBox.setPadding(new Insets(20));
      addVenteToClientButton.getStyleClass().add("applyButton");
      ListVentesClientButton.getStyleClass().add("orangeButton");
   }
   private void addEvents() {
      clientsTableView.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() >= 1)
            currentClient = clientsTableView.getSelectionModel().getSelectedItem();
         if(!clientIsClicked) buttonsBox.getChildren().add(addVenteToClientButton);
         clientIsClicked = true;
      });
      addVenteToClientButton.setOnAction(event -> {
         new GestionVenteWindow(currentClient);
      });
      ListVentesClientButton.setOnAction(event -> {
        new DisplayVentesWindow();
      });
   }

   public VenteMainWindow(){
      initWindow();
      addEvents();
      addStylesToNodes();
      updateColumns();
      addColumnsToTableView();
      addNodesToPane();
      handler.updateVenteMainWindow();
      window.show();
   }

}
