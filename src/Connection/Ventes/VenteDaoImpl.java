package Connection.Ventes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.AbstractDAO;
import Connection.Client.Client;
import Connection.Produit.Produit;

public class VenteDaoImpl extends AbstractDAO implements IVenteDao {

   @Override
   public void add(Vente obj) {
      PreparedStatement pst = null;
      String sql = "insert into vente(date,id_client) values (?,?)";
      try {
         pst = connection.prepareStatement(sql);
         pst.setDate(1,Date.valueOf(obj.getDate()) );
         pst.setLong(2, obj.getClient().getId());
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}
   }


   @Override
   public void delete(long id) {
   }

   @Override
   public List<Vente> getAll() {
      List<Vente> list = new ArrayList<Vente>();
      PreparedStatement pst = null;
      ResultSet rs;
      // String sql = "SELECT * FROM client RIGHT JOIN vente ON client.id = vente.id_client ORDER BY date";
      String sql = "SELECT * FROM vente RIGHT JOIN client ON vente.id_client = client.id where vente.id is not null";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new Vente(rs.getLong("id"),rs.getDate("date").toLocalDate(),new Client(rs.getLong("id_client"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse"))));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }

   @Override
   public Vente getOne(long id) {
      return null;
   }

   @Override
   public void updateOne(long id, Produit produit) {
      PreparedStatement pst = null;
      String sql = "UPDATE produit SET designation = ? ,qte = ? ,prix = ? ,date = ? WHERE id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, produit.getDesignation());
         pst.setLong(2, produit.getQte());
         pst.setDouble(3, produit.getPrix());
         pst.setDate(4,Date.valueOf(produit.getDate()));
         pst.setLong(5, id);
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}
   }

   @Override
   public void addLigneDeComande(Vente vente){
      PreparedStatement pst = null;
      String sql = "insert into ligne_de_commande(qteVendu,id_vente,id_produit) values (?,?,?)";
      try {
         for(LigneDeCommande ldc : vente.getLigneDeCommandeList()){
         pst = connection.prepareStatement(sql);
         pst.setLong(1,ldc.getQteVendu());
         pst.setLong(2,vente.getId());
         pst.setLong(3,ldc.getProduit().getId());
         pst.executeUpdate();
         }
      } catch (SQLException e) { e.printStackTrace();}
   }

   public List<LigneDeCommande> getLignesDeCommandes(Vente vente) {
      List<LigneDeCommande> list = new ArrayList<LigneDeCommande>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from produit right join ligne_de_commande on produit.id = ligne_de_commande.id_produit where ligne_de_commande.id_vente = ?";
      // select * FROM produit where id in (select id_produit from ligne_de_commande where id_vente = 54)
         try {
         pst = connection.prepareStatement(sql);
         pst.setLong(1, vente.getId());
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new LigneDeCommande(new Produit(rs.getString("designation")),rs.getLong("qteVendu")));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   } 
   public long getIDVente(){
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "SELECT * FROM vente WHERE id=(SELECT max(id) FROM vente)";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         rs.next();
         return rs.getLong("id");
      }catch (SQLException e) { e.printStackTrace();}
      return 0;
   }
}