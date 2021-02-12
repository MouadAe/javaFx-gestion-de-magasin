package Connection.Ventes;

import IHM.DisplayVentesWindow;
import IHM.GestionVenteWindow;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
import Connection.Produit.IProduitDao;
import Connection.Produit.Produit;
import Connection.Produit.ProduitDaoImpl;

public class VenteHandler {
   GestionVenteWindow venteWindow = null;
   DisplayVentesWindow dispalyVentesWindow = null;

   public VenteHandler(GestionVenteWindow venteWindow) {
      this.venteWindow = venteWindow;
   }
   public VenteHandler(DisplayVentesWindow dispalyVentesWindow) {
      this.dispalyVentesWindow = dispalyVentesWindow;
   }

   public List<Produit> getListProduits(){
      IProduitDao pdao = new ProduitDaoImpl();
      return pdao.getAll();
   }
   public void displayProduitsListWindow(){
      IProduitDao pdao = new ProduitDaoImpl();
      List<Produit> list = pdao.getAll();
      venteWindow.produitsObservableList.clear();
      venteWindow.produitsObservableList.addAll(list);
   }
   public void updateProduits(ArrayList<Produit> listDesProduits,Vente vente){
      IVenteDao vDao = new VenteDaoImpl();
      IProduitDao pDao = new ProduitDaoImpl();
      ArrayList<Produit> dbListProduits = (ArrayList<Produit>) pDao.getAll();
      for(int i=0; i < listDesProduits.size(); i++){
         if(listDesProduits.get(i) == dbListProduits.get(i))   continue;
         vDao.updateOne(listDesProduits.get(i).getId(), listDesProduits.get(i)); 
      }
   }
   public void addLignesDeCommande(Vente vente){
      VenteDaoImpl vDao = new VenteDaoImpl();
      vDao.add(vente);
      // vdao.getAll();
      // System.out.println(vDao.getIDVente());
      vente.setId(vDao.getIDVente());
      vDao.addLigneDeComande(vente);
   }
   // DisplayVentesWindow :
   public void displayListVentes(){
      IVenteDao vDao = new VenteDaoImpl();
      dispalyVentesWindow.ventesDisplayTableView.setItems(FXCollections.observableArrayList(vDao.getAll()));
   } 
   public void displayLdcOfVente(Vente vente){
      VenteDaoImpl vDao = new VenteDaoImpl();
      for(Produit p : vDao.getLignesDeCommandes(vente))
         System.out.println(p.getDesignation());
      dispalyVentesWindow.produitTableView.setItems(FXCollections.observableArrayList(
            vDao.getLignesDeCommandes(vente)));

   }
}
