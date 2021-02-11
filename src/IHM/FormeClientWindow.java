package IHM;

import Connection.Client.ClientAddHandler;
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


public class FormeClientWindow {
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Stage window = new Stage();
   Label titleLabel = new Label("Nouveau Client");

   Label clientNomLabel = new Label("Nom :");
   public TextField clientNomTextField = new TextField(); 

   Label clientPrenomLabel = new Label("Prenom :");
   public TextField clientPrenomTextField = new TextField(); 
   
   Label clientTelLabel = new Label("Tel :");
   public TextField clientTelTextField = new TextField(); 
   
   Label clientEmailLabel = new Label("Email :");
   public TextField clientEmailTextField = new TextField(); 

   Label clientAdresseLabel = new Label("Adresse :");
   public TextField clientAdresseTextField = new TextField(); 

   HBox buttonsBox = new HBox();
   Button clientAddButton = new Button("Ajouter");
   Button clientCancelButton = new Button("Annuler");

   ClientAddHandler handler = new ClientAddHandler(this);

   public void  initWindow(){
      window.setScene(scene);
      window.setWidth(600);
      window.setHeight(480);
      window.setTitle("Nouveau clientt");
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
      buttonsBox.getChildren().addAll(clientAddButton,clientCancelButton);  
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
      clientAddButton.getStyleClass().add("addButton");
      clientCancelButton.getStyleClass().add("cancelButton");
      root.setSpacing(10);
      buttonsBox.setSpacing(20);
      buttonsBox.setAlignment(Pos.BASELINE_CENTER);
   }
   private void addEvents() {
      clientCancelButton.setOnAction(event -> {
         window.close();
      });
      clientAddButton.setOnAction(event -> {
         handler.addClick();
         window.close();
      });
      window.setOnCloseRequest(event -> {
         event.consume();
      });
   }
   public FormeClientWindow() {
      initWindow();
      addStylesToNodes();
      addNodesToWindow();
      addEvents();
      window.show();
   }
}
