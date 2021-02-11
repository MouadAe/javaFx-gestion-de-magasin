package Connection.Produit;

import java.time.LocalDate;

import IHM.FormeProduitWindow;

public class ProduitAddHandler {
   IProduitDao pdao = new ProduitDaoImpl();
   FormeProduitWindow formeProduit = null;
	public ProduitAddHandler(FormeProduitWindow formeProduit) {
      this.formeProduit = formeProduit;
   }
   public void addClick(){
      String designation = formeProduit.produitDesignationTextField.getText();
      long qte = Long.valueOf(formeProduit.produitQteTextField.getText());
      double prix =  Double.valueOf(formeProduit.produitPrixTextField.getText());
      LocalDate date = formeProduit.produitDateSaisie.getValue(); 
      pdao.add(new Produit(0, designation,qte,prix,date));
   }
   
}
