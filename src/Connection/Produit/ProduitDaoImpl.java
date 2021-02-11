package Connection.Produit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.AbstractDAO;

public class ProduitDaoImpl extends AbstractDAO implements IProduitDao {

   @Override
   public List<Produit> getAll(String des) {
      List<Produit> list = new ArrayList<Produit>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from produit where designation like ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, des + "%");
         rs = pst.executeQuery();
         while(rs.next()){
            Date date = rs.getDate("date");
            list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getLong("qte"),rs.getDouble("prix"),date.toLocalDate()));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }

   @Override
   public void add(Produit obj) {
      PreparedStatement pst = null;
      String sql = "insert into produit(designation,qte,prix,date) values (?,?,?,?)";
      try {
         pst = connection.prepareStatement(sql);
         pst.setString(1, obj.getDesignation());
         pst.setLong(2, obj.getQte());
         pst.setDouble(3, obj.getPrix());
         pst.setDate(4,Date.valueOf(obj.getDate()));
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}
   }

   @Override
   public void delete(long id) {
      PreparedStatement pst = null;
      String sql = "delete from produit where id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setLong(1, id);
         pst.executeUpdate();
      } catch (SQLException e) { e.printStackTrace();}
   }

   @Override
   public List<Produit> getAll() {
      List<Produit> list = new ArrayList<Produit>();
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from produit";
      try {
         pst = connection.prepareStatement(sql);
         rs = pst.executeQuery();
         while(rs.next()){
            Date date = rs.getDate("date");
            list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getLong("qte"),rs.getDouble("prix"),date.toLocalDate()));
         }
      }catch(SQLException e){ e.printStackTrace();}
      return list;
   }

   @Override
   public Produit getOne(long id) {
      PreparedStatement pst = null;
      ResultSet rs;
      String sql = "select * from produit where id = ?";
      try {
         pst = connection.prepareStatement(sql);
         pst.setLong(1, id);
         rs = pst.executeQuery();
         if(rs.next()){
            Date date = rs.getDate("date");
            return new Produit(rs.getLong("id"),rs.getString("designation"),rs.getLong("qte"),rs.getDouble("prix"),date.toLocalDate());
         }
      }catch(SQLException e){ e.printStackTrace();}
      return null;
   }
   
}
