package Connection.Ventes;

import Connection.Client.ClientDaoImpl;
import Connection.Client.IClientDao;
import IHM.VenteMainWindow;

public class VentesClientsListHandler {
   VenteMainWindow clientsList = null;

   public VentesClientsListHandler(VenteMainWindow clientsList) {
      this.clientsList = clientsList;
   }
   public void updateVenteMainWindow(){
      IClientDao pdao = new ClientDaoImpl();
      clientsList.clientsObservableList.addAll(pdao.getAll());
   }
 
}
