import java.rmi.Naming;

public class ServidorOperaciones {

    public void registrarObjetoRemoto() {
        try{
            IOperacion opera1= new Operacion();
            String url1="rmi://192.168.100.1:1099/Operaciones";
            Naming.rebind( url1,opera1);
            System.out.println("Servicio operaciones registrado correctamente..");
        }catch(Exception e){
            System.out.println("Error: " + e);
            }
            
        }
        public ServidorOperaciones(){
            registrarObjetoRemoto();
        }
        public static void main(String[] args){
            new ServidorOperaciones();
        }
    }