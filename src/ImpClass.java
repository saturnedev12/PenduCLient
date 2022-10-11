
import java.rmi.RemoteException;

public class ImpClass implements Hello {
     ImpClass() {

     }

     @Override
     public ResponseServer showMsg(String msg) throws RemoteException {
          String word = "toto";
          // TODO Auto-generated method stub
          System.out.println(msg);

          return new ResponseServer("", ResponseStatus.YES);

     }

}