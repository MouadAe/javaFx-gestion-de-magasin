package IHM;

import Connection.Produit.ProduitAddHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormeProduitWindow {
   VBox root = new VBox();
   Scene scene = new Scene(root);
   Stage window = new Stage();
   Label titleLabel = new Label("Nouveau Produit");
   Label produitDesignationLabel = new Label("Designation :");
   public TextField produitDesignationTextField = new TextField(); 

   Label produitPrixLabel = new Label("Prix :");
   public TextField produitPrixTextField = new TextField(); 
   
   Label produitQteLabel = new Label("Qte :");
   public TextField produitQteTextField = new TextField(); 
   
   Label produitDateLabel = new Label("Date :");
   public DatePicker produitDateSaisie = new DatePicker(); 

   HBox buttonsBox = new HBox();
   Button produitAddButton = new Button("Ajouter");
   Button produitCancelButton = new Button("Annuler");

   ProduitAddHandler handler = new ProduitAddHandler(this);

   public void  initWindow(){
      window.setScene(scene);
      window.setWidth(650);
      window.setHeight(430);
      window.setTitle("Nouveau Produit");
      window.getIcons().add(new Image("file:src/design/icon.PNG"));
      window.initModality(Modality.APPLICATION_MODAL);
   }
   private void addNodesToWindow(){
      root.getChildren().add(titleLabel);
      root.getChildren().addAll(produitDesignationLabel,produitDesignationTextField);
      root.getChildren().addAll(produitPrixLabel,produitPrixTextField);
      root.getChildren().addAll(produitQteLabel,produitQteTextField);
      root.getChildren().addAll(produitDateLabel,produitDateSaisie);
      root.getChildren().add(buttonsBox);
      buttonsBox.getChildren().addAll(produitAddButton,produitCancelButton);  
   }
   private void addStylesToNodes() {
      scene.getStylesheets().add("file:src/design/styles.css");
      titleLabel.getStyleClass().add("labelTitle");
      titleLabel.setMinWidth(window.getWidth());
      titleLabel.setAlignment(Pos.BASELINE_CENTER);

      produitDesignationLabel.getStyleClass().add("labelForm");
      produitDesignationLabel.setPadding(new Insets(0,0,0,5));

      produitPrixLabel.getStyleClass().add("labelForm");
      produitPrixLabel.setPadding(new Insets(0,0,0,5));

      produitQteLabel.getStyleClass().add("labelForm");
      produitQteLabel.setPadding(new Insets(0,0,0,5));

      produitDateLabel.getStyleClass().add("labelForm");
      produitDateLabel.setPadding(new Insets(0,0,0,5));
      
      produitAddButton.getStyleClass().add("addButton");
      produitCancelButton.getStyleClass().add("cancelButton");
      root.setSpacing(10);
      buttonsBox.setSpacing(25);
      buttonsBox.setAlignment(Pos.BASELINE_CENTER);
   }

  private void addEvents() {
     produitCancelButton.setOnAction(event -> {
        window.close();
     });
     produitAddButton.setOnAction(event -> {
         handler.addClick();
         window.close();
     });
     window.setOnCloseRequest(event -> {
        event.consume();
     });
  }
  
  public FormeProduitWindow(){
   initWindow();
   addStylesToNodes();
   addNodesToWindow();
   addEvents();
   window.show();
  }
}
