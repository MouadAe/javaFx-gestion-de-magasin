package Connection.Ventes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.Client.Client;

public class Vente {
   private long id;
   private LocalDate date;
   private List<LigneDeCommande> LigneDeCommandeList = new ArrayList<>();
   private double total;
   private Client client;
   
   public Vente(LocalDate date, List<LigneDeCommande> ligneDeCommandeList, Client client) {
      // this.id = id;
      this.date = date;
      LigneDeCommandeList = ligneDeCommandeList;
      this.client = client;
   }
   public Vente(LocalDate date, Client client) {
      this.date = date;
      this.client = client;
   }  

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public List<LigneDeCommande> getLigneDeCommandeList() {
      return LigneDeCommandeList;
   }
   public Client getClient() {
      return client;
   }
   public void setClient(Client client) {
      this.client = client;
   }
   public double getTotal() {
      total = 0;
      for(LigneDeCommande l : LigneDeCommandeList)
         total += l.getSousTotal();
      return total;
   }
   public void setLigneDeCommandeList(List<LigneDeCommande> ligneDeCommandeList) {
      LigneDeCommandeList = ligneDeCommandeList;
   }   
}
