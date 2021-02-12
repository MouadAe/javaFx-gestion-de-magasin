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
      String sql = "SELECT * FROM client RIGHT JOIN vente ON client.id = vente.id_client ORDER BY date";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new Vente(rs.getDate("date").toLocalDate(),new Client(rs.getLong("id_client"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse"))));
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

   @Override
   public List<Vente> getLignesDeCommandes(Vente vente) {
      List<Vente> list = new ArrayList<Vente>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "SELECT * FROM client RIGHT JOIN vente ON client.id = vente.id_client ORDER BY date";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new Vente(rs.getDate("date").toLocalDate(),new Client(rs.getLong("id_client"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse"))));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }
}