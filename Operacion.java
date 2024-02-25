import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operacion extends UnicastRemoteObject implements IOperacion{
    public Operacion() throws RemoteException{
        super();
    }



    public float suma(float num1,float num2)throws RemoteException{
        return num1 + num2;

    }
    public float resta(float num1,float num2)throws RemoteException{
        return num1 - num2;

    }
    public float multiplicacion(float num1,float num2)throws RemoteException{
        return num1 * num2;

    }
    public float division(float num1,float num2)throws RemoteException{
        return num1 / num2;

    }

}