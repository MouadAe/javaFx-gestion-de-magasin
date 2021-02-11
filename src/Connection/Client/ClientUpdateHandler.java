package Connection.Client;

import IHM.ClientUpdateWindow;

public class ClientUpdateHandler {
   ClientUpdateWindow clientUpdateWindow = null;

	public ClientUpdateHandler(ClientUpdateWindow clientUpdateWindow) {
      this.clientUpdateWindow = clientUpdateWindow;
   }
   public void updateOneClient(){
      IClientDao pdao = new ClientDaoImpl();
      pdao.updateOne(clientUpdateWindow.getCurrentClient().getId(),clientUpdateWindow.getCurrentClient());
   }
   public void deleteClient() {
      IClientDao pdao = new ClientDaoImpl();
      pdao.delete(clientUpdateWindow.getCurrentClient().getId());
   } 
}
