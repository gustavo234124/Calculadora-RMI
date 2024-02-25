import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperacion extends Remote {
    public float suma(float num1,float num2) throws RemoteException;
    public float resta(float num1,float num2) throws RemoteException;
    public float multiplicacion(float num1,float num2) throws RemoteException;
    public float division(float num1,float num2) throws RemoteException;
}