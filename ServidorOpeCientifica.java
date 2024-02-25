import java.rmi.Naming;
public class ServidorOpeCientifica{
	
	public void registrarObjetoRemoto(){
		try{
			//Creacion e instancia de una referencia remota
			IOpeCientifica opera2=new OpeCientifica();
			//Variable que almacena la url donde estara expuesto el objeto
			String url2="rmi://192.168.100.2:1099/OperaCientifica";
			Naming.rebind(url2,opera2);
			System.out.println("Servicio registrado correctamente..");

		}catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
	public ServidorOpeCientifica(){
		registrarObjetoRemoto();
	}
		public static void main(String[] args){
			System.out.println("Servidor iniciado..");
			new ServidorOpeCientifica();
		}
	}