package Connection.Ventes;

import Connection.Produit.Produit;

public class LigneDeCommande {
   private long id;
   private Produit produit;
   private long qteVendu;
   private double sousTotal;
   private Vente vente;

   public LigneDeCommande( Produit produit, long qteVendu, Vente vente) {
      this.produit = produit;
      this.qteVendu = qteVendu;
      this.vente = vente;
      sousTotal = produit.getPrix() * qteVendu;
   }
   public Vente getVente() {
      return vente;
   }
   public void setVente(Vente vente) {
      this.vente = vente;
   }
   public Produit getProduit() {
      return produit;
   }

   public void setProduit(Produit produit) {
      this.produit = produit;
   }

   public long getQteVendu() {
      return qteVendu;
   }

   public void setQteVendu(long qteVendu) {
      this.qteVendu = qteVendu;
   }

   public double getSousTotal() {
      return sousTotal;
   }

   public void setSousTotal() {
      sousTotal = produit.getPrix() * qteVendu;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

}
