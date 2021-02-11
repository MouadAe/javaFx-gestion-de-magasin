package Connection.Produit;

import java.time.LocalDate;

public class Produit {
   private long id;
   private String designation;
   private long qte;
   private double prix;
   private double total;
   private LocalDate date;
   
   public Produit(long id, String designation, long qte, double prix, LocalDate date) {
      this.id = id;
      this.designation = designation;
      this.qte = qte;
      this.prix = prix;
      this.date = date;
      this.total = qte * prix;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getDesignation() {
      return designation;
   }

   public void setDesignation(String designation) {
      this.designation = designation;
   }

   public long getQte() {
      return qte;
   }

   public void setQte(long qte) {
      this.qte = qte;
   }

   public double getPrix() {
      return prix;
   }

   public void setPrix(double prix) {
      this.prix = prix;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   };
   public double getTotal() {
      return total;
   }

   @Override
   public String toString() {
      return id + "\t" + designation + "\t" + qte + "\t" + prix + "\t" + date;
   }

}
