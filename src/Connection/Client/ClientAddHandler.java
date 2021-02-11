package Connection.Client;

import IHM.FormeClientWindow;

public class ClientAddHandler {
   IClientDao pdao = new ClientDaoImpl();
   FormeClientWindow formeClient = null;
   public ClientAddHandler(FormeClientWindow formeClient){
      this.formeClient = formeClient;
   }
   public void addClick(){
      String nom = formeClient.clientNomTextField.getText();
      String prenom = formeClient.clientPrenomTextField.getText();
      String tel = formeClient.clientTelTextField.getText();
      String email = formeClient.clientEmailTextField.getText();
      String adresse = formeClient.clientAdresseTextField.getText();
      pdao.add(new Client(0, nom, prenom, tel, email, adresse));
   }

}
