package Connection.Client;

import IHM.ClientsListWindow;

public class ClientsListHandler {
   ClientsListWindow clientsList = null;

   public ClientsListHandler(ClientsListWindow clientsList) {
      this.clientsList = clientsList;
   }
   public void updateClientsListWindow(){
      IClientDao pdao = new ClientDaoImpl();
      clientsList.clientsObservableList.addAll(pdao.getAll());
   }
   public void findClientsListWindow(String nom){
      IClientDao pdao = new ClientDaoImpl();
      clientsList.clientsObservableList.clear();
      clientsList.clientsObservableList.addAll(pdao.getAll(nom));
   }
}
