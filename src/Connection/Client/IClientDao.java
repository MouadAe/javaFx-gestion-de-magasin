package Connection.Client;

import java.util.List;

import Connection.IDAO;

public interface IClientDao extends IDAO<Client> {
   List<Client> getAll(String nom);
   void updateOne(long id,Client client);
}
