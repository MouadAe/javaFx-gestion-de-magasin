package IHM;

import Connection.Client.Client;
import Connection.Client.ClientUpdateHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ClientUpdateWindow {
   
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Stage window = new Stage();
   Label titleLabel = new Label("Mise a jour du client");

   Label clientNomLabel = new Label("Nouveau nom :");
   public TextField clientNomTextField = new TextField(); 

   Label clientPrenomLabel = new Label("Nouveau prenom :");
   public TextField clientPrenomTextField = new TextField();
   
   Label clientTelLabel = new Label("Nouveau Numero de telephone :");
   public TextField clientTelTextField = new TextField(); 
   
   Label clientEmailLabel = new Label("Nouveau email :");
   public TextField clientEmailTextField = new TextField(); 

   Label clientAdresseLabel = new Label("Nouvelle adresse :");
   public TextField clientAdresseTextField = new TextField(); 

   HBox buttonsBox = new HBox();
   Button clientApplyButton = new Button("Appliquer les changements");
   Button clientCancelButton = new Button("Annuler");
   Button clientDeleteButton = new Button("Supprimer ce client");

   ClientUpdateHandler handler = new ClientUpdateHandler(this);
   // le client selectionne a modifier :
   private Client currentClient = null; 
   
   public Client getCurrentClient() {
      return currentClient;
   }
   public void setCurrentClient(Client currentClient) {
      this.currentClient = currentClient;
   }

   public void  initWindow(){
      window.setScene(scene);
      window.setWidth(700);
      window.setHeight(480);
      window.setTitle("Gestion d'un client");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.initModality(Modality.APPLICATION_MODAL);
   }
   private void addNodesToWindow(){
      root.getChildren().add(titleLabel);
      root.getChildren().addAll(clientNomLabel,clientNomTextField);
      root.getChildren().addAll(clientPrenomLabel,clientPrenomTextField);
      root.getChildren().addAll(clientTelLabel,clientTelTextField);
      root.getChildren().addAll(clientEmailLabel,clientEmailTextField);
      root.getChildren().addAll(clientAdresseLabel,clientAdresseTextField);
      root.getChildren().add(buttonsBox);
      buttonsBox.getChildren().addAll(clientApplyButton,clientDeleteButton,clientCancelButton);  
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("labelTitle");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER);
      clientNomLabel.getStyleClass().add("labelForm");
      clientPrenomLabel.getStyleClass().add("labelForm");
      clientTelLabel.getStyleClass().add("labelForm");
      clientEmailLabel.getStyleClass().add("labelForm");
      clientAdresseLabel.getStyleClass().add("labelForm");
      root.setSpacing(10);
      buttonsBox.setSpacing(20);
      clientApplyButton.getStyleClass().add("applyButton");
      clientCancelButton.getStyleClass().add("cancelButton");
      clientDeleteButton.getStyleClass().add("deleteButton");
      buttonsBox.setAlignment(Pos.BASELINE_CENTER);
   }
   private void addEvents() {
      clientCancelButton.setOnAction(event -> {
         window.close();
      });
      clientApplyButton.setOnAction(event -> {
         currentClient.setNom(clientNomTextField.getText());
         currentClient.setPrenom(clientPrenomTextField.getText());
         currentClient.setTel(clientTelTextField.getText());
         currentClient.setEmail(clientEmailTextField.getText());
         currentClient.setAdresse(clientAdresseTextField.getText());
         handler.updateOneClient();
         window.close();
      });
      clientDeleteButton.setOnAction(event -> {
         handler.deleteClient();
         window.close();
      });
      window.setOnCloseRequest(event -> {
         event.consume();
      });
   }
   public void initInputs(){
      clientNomTextField.setText(currentClient.getNom());
      clientPrenomTextField.setText(currentClient.getPrenom());
      clientTelTextField.setText(currentClient.getTel());
      clientEmailTextField.setText(currentClient.getEmail());
      clientAdresseTextField.setText(currentClient.getAdresse());
   }
   public ClientUpdateWindow(Client cl) {
      currentClient = cl;
      initWindow();
      initInputs();
      addStylesToNodes();
      addNodesToWindow();
      addEvents();
      window.show();
   }
}
