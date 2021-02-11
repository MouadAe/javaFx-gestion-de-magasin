package Connection.Produit;

import java.util.List;

import Connection.IDAO;

public interface IProduitDao extends IDAO<Produit>{
   public List<Produit> getAll(String des);
}
