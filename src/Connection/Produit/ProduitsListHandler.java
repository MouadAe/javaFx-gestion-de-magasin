package Connection.Produit;

import java.util.List;

import IHM.ProduitsListWindow;

public class ProduitsListHandler {
   ProduitsListWindow produitsList = null;
   
   public ProduitsListHandler(ProduitsListWindow produitsList) {
      this.produitsList = produitsList;
   }
   private void calculerTotal() {
      double total = 0;
      for(Produit p : produitsList.produitsObservableList)
         total += p.getTotal();
      produitsList.totalLabelValue.setText(total+"");
   }

   public void updateProduitsListWindow(){
      IProduitDao pdao = new ProduitDaoImpl();
      List<Produit> list = pdao.getAll();
      produitsList.produitsObservableList.clear();
      produitsList.produitsObservableList.addAll(list);
      calculerTotal();
   }

}
