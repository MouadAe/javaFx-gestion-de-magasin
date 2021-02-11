package Connection.Ventes;

import Connection.IDAO;
import Connection.Produit.Produit;

public interface IVenteDao extends IDAO<Vente> {
   void updateOne(long id,Produit produit);
   void addLigneDeComande(LigneDeCommande ldc);
}
