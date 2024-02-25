import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OpeCientifica extends UnicastRemoteObject implements IOpeCientifica {

    public OpeCientifica() throws RemoteException {
        super();
    }

    public double sen(double num1) throws RemoteException {
        return Math.sin(num1);
    }

    public double cos(double num1) throws RemoteException {
        return Math.cos(num1);
    }

    public double tan(double num1) throws RemoteException {
        return Math.tan(num1);
    }

    public double raizCuadrada(double num1) throws RemoteException {
        return Math.sqrt(num1);
    }

    public double logaritmoNatural(double num1) throws RemoteException {
        return Math.log(num1);
    }

    public double logaritmoBase10(double num1) throws RemoteException {
        return Math.log10(num1);
    }

    public double potencia(double base, double exponente) throws RemoteException {
        return Math.pow(base, exponente);
    }

    public double valorAbsoluto(double num1) throws RemoteException {
        return Math.abs(num1);
    }

    public double factorial(int num1) throws RemoteException {
        if (num1 < 0) {
            throw new RemoteException("El factorial no está definido para números negativos");
        }

        double resultado = 1;
        for (int i = 1; i <= num1; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public double exponencial(double num1) throws RemoteException {
        return Math.exp(num1);
    }

    public double arcotangente(double num1) throws RemoteException {
        return Math.atan(num1);
    }
}
