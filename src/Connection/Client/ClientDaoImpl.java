package Connection.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.AbstractDAO;

public class ClientDaoImpl extends AbstractDAO implements IClientDao {

   @Override
   public List<Client> getAll(String nom) {
      List<Client> list = new ArrayList<Client>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from client where nom like ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, nom +"%");
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new Client(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse")));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }

   @Override
   public void add(Client obj) {
      PreparedStatement pst = null;
      String sql = "insert into client(nom,prenom,tel,email,adresse) values (?,?,?,?,?)";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, obj.getNom());
         pst.setString(2, obj.getPrenom());
         pst.setString(3, obj.getTel());
         pst.setString(4, obj.getEmail());
         pst.setString(5, obj.getAdresse());
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}

   }

   @Override
   public void delete(long id) {
      PreparedStatement pst = null;
      String sql = "delete from client where id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setLong(1, id);
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}
   }

   @Override
   public List<Client> getAll() {
      List<Client> list = new ArrayList<Client>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from client";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         while(rs.next()){
            list.add(new Client(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse")));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }

   @Override
   public Client getOne(long id) {
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from client where id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setLong(1, id);
         rs = pst.executeQuery();
         if(rs.next())
            return new Client(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse"));
      }catch(SQLException e){ e.printStackTrace();}
      return null;
   }

   @Override
   public void updateOne(long id,Client client) {
      PreparedStatement pst = null;
      String sql = "UPDATE client SET nom = ? ,prenom = ? ,tel = ? ,email = ?,adresse = ? WHERE id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, client.getNom());
         pst.setString(2, client.getPrenom());
         pst.setString(3, client.getTel());
         pst.setString(4, client.getEmail());
         pst.setString(5, client.getAdresse());
         pst.setLong(6, id);
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}

   }
   
}
