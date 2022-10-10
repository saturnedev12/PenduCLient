import java.rmi.RemoteException;

public class ImpClass implements Hello {
     ImpClass() {

     }

     @Override
     public ResponseServer showMsg(String msg) throws RemoteException {
          String word = "toto";
          // TODO Auto-generated method stub
          System.out.println(msg);
          if (msg == word) {
               return ResponseServer.SUCCESS;
          } else if (word.contains(msg)) {
               return ResponseServer.YES;
          }
          return ResponseServer.NO;

     }

}
