import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import IHM.ClientsListWindow;
import IHM.FormeClientWindow;
import IHM.FormeProduitWindow;
import IHM.ProduitsListWindow;
import IHM.VenteMainWindow;

public class App extends Application {
    private BorderPane root = new BorderPane();
    private Scene scene = new Scene(root);

    MenuItem nouveauProduitMenuItem = new MenuItem("Nouveau");
    MenuItem listeProduitsMenuItem = new MenuItem("Liste");
    MenuItem nouveauClientsMenuItem = new MenuItem("Nouveau");
    MenuItem listeClientsMenuItem = new MenuItem("Liste");
    MenuItem ventesMenuItem = new MenuItem("Gestion des ventes");
    MenuItem HelpMenuItem = new MenuItem("?");

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu produitsMenu = new Menu("Produits");
        Menu ClientsMenu = new Menu("Clients");
        Menu VentesMenu = new Menu("Ventes");
        Menu InventairesMenu = new Menu("Inventaires");
        Menu PaiementsMenu = new Menu("Paiements");
        Menu HelpMenu = new Menu("Help");

        produitsMenu.getItems().addAll(nouveauProduitMenuItem, listeProduitsMenuItem);
        ClientsMenu.getItems().addAll(nouveauClientsMenuItem, listeClientsMenuItem);
        VentesMenu.getItems().add(ventesMenuItem);
        HelpMenu.getItems().addAll(HelpMenuItem);

        menuBar.getMenus().addAll(produitsMenu, ClientsMenu, VentesMenu, InventairesMenu, PaiementsMenu, HelpMenu);
        root.setTop(menuBar);
    }

    private void addStylesToNodes() {
        scene.getStylesheets().add("file:src/design/styles.css");
        root.getStyleClass().add("mainWindow");
    }

    private void addEvents() {
        // Produit :
        nouveauProduitMenuItem.setOnAction(event -> {
            new FormeProduitWindow();
        });
        listeProduitsMenuItem.setOnAction(event -> {
            new ProduitsListWindow();
        });
        // Client :
        nouveauClientsMenuItem.setOnAction(event -> {
            new FormeClientWindow();
        });
        listeClientsMenuItem.setOnAction(event -> {
            new ClientsListWindow();
        });
        // Vente :
        ventesMenuItem.setOnAction(event -> {
            new VenteMainWindow();
        });
    }

    @Override
    public void start(Stage window) throws Exception {
        createMenu();
        addEvents();
        addStylesToNodes();
        window.setScene(scene);
        window.setWidth(1000);
        window.setHeight(750);
        window.setTitle("Gestion de magasin");
        window.getIcons().add(new Image("file:src/design/icon.png"));
        window.show();
    }
}