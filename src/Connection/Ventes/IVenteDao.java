package Connection.Ventes;

import java.util.List;

import Connection.IDAO;
import Connection.Produit.Produit;

public interface IVenteDao extends IDAO<Vente> {
   void updateOne(long id,Produit produit);
   void addLigneDeComande(Vente vente);
   // List<Vente> getLignesDeCommandes(Vente vente);
}
