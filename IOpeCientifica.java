import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOpeCientifica extends Remote{
	public double sen(double num1) throws RemoteException;
	public double cos(double num1) throws RemoteException;
	public double tan(double num1) throws RemoteException;
	public double raizCuadrada(double num1) throws RemoteException;
	public double logaritmoNatural(double num1) throws RemoteException;
	public double logaritmoBase10(double num1) throws RemoteException;
	public double potencia(double base, double exponente) throws RemoteException;
	public double valorAbsoluto(double num1) throws RemoteException;
	public double factorial(int num1) throws RemoteException;
	public double exponencial(double num1) throws RemoteException;
	public double arcotangente(double num1) throws RemoteException;
		

}