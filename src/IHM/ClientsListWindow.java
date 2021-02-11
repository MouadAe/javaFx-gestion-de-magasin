package IHM;

import Connection.Client.Client;
import Connection.Client.ClientsListHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientsListWindow {
   Stage window = new Stage();
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Label titleLabel = new Label("Liste de clients");
   
   TableColumn<Client,String > idColumn = new TableColumn<>("Id"); 
   TableColumn<Client,String > nomColumn = new TableColumn<>("Nom"); 
   TableColumn<Client,String > prenomColumn = new TableColumn<>("Prenom"); 
   TableColumn<Client,String > telColumn = new TableColumn<>("Tel"); 
   TableColumn<Client,String > emailColumn = new TableColumn<>("Email"); 
   TableColumn<Client,String > adresseColumn = new TableColumn<>("Adresse");
   
   VBox bottomBox = new VBox();
   HBox findClientBox = new HBox();
   Label bottomLabel = new Label("Cliquez deux fois sur un client pour le modifier ou le supprimer");
   Label findClientLabel = new Label("Entrez le nom du client a chercher : ");
   TextField findClientTextField = new TextField();
   Button findClientFiltrer = new Button("Filtrer");

   ClientsListHandler handler = new ClientsListHandler(this);
   TableView<Client> clientsTableView = new TableView<>();
   public ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();

   private void addColumnsToTableView(){
      clientsTableView.getColumns().addAll(idColumn,nomColumn,prenomColumn,telColumn,emailColumn,adresseColumn);
      clientsTableView.setItems(clientsObservableList);
   }
   private void addNodesToPane(){
      findClientBox.getChildren().addAll(findClientLabel,findClientTextField,findClientFiltrer);
      bottomBox.getChildren().addAll(bottomLabel,findClientBox);
      root.getChildren().addAll(titleLabel,clientsTableView,bottomBox);
   }
   private void updateColumns(){
      idColumn.setCellValueFactory(new PropertyValueFactory("id"));
      idColumn.setPrefWidth(70);
      nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
      nomColumn.setPrefWidth(150);
      prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
      prenomColumn.setPrefWidth(150);
      telColumn.setCellValueFactory(new PropertyValueFactory("tel"));
      telColumn.setPrefWidth(200);
      emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
      emailColumn.setPrefWidth(250);
      adresseColumn.setCellValueFactory(new PropertyValueFactory("adresse"));
      adresseColumn.setPrefWidth(400);
   }
   private void initWindow(){
      window.setWidth(1250);
      window.setHeight(580);
      window.setTitle("Liste des clients");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.setScene(scene);
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("titlelistLabel");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER);
      bottomLabel.getStyleClass().add("bottomLabelClentList");
      findClientLabel.getStyleClass().add("findClientLabel");
      findClientFiltrer.getStyleClass().add("findClientFiltrerButton");
      findClientBox.setMinWidth(window.getWidth());
      findClientBox.setAlignment(Pos.BASELINE_CENTER); 
      bottomBox.setMinWidth(window.getWidth());
      bottomBox.setSpacing(15);
      findClientBox.setSpacing(15);
      bottomBox.setAlignment(Pos.BASELINE_CENTER); 
   }
   private void addEvents() {
      clientsTableView.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() > 1){
            new ClientUpdateWindow(clientsTableView.getSelectionModel().getSelectedItem());
            window.close();
         }
     });
     findClientFiltrer.setOnAction(event -> {
        handler.findClientsListWindow(findClientTextField.getText());
     });
  }

   public ClientsListWindow(){
      initWindow();
      addEvents();
      addStylesToNodes();
      updateColumns();
      addColumnsToTableView();
      handler.updateClientsListWindow();
      addNodesToPane();
      window.show();
   }

}
