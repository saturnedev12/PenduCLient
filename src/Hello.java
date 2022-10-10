import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {

     ResponseServer showMsg(String msg) throws RemoteException;
}