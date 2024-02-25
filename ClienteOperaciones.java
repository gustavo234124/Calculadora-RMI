import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ClienteOperaciones {
    public static void main(String[] args) {
        try {
            // Referencia hacia las operaciones del servicio 1
            String url1 = "rmi://192.168.100.1/Operaciones";
            IOperacion opera1 = (IOperacion) Naming.lookup(url1);

            // Referencia hacia las operaciones del servicio 2
            String url2 = "rmi://192.168.100.2/OperaCientifica";
            IOpeCientifica opera2 = (IOpeCientifica) Naming.lookup(url2);

            // Código de la aplicación cliente
            // Acceso a todas las operaciones
            Scanner lector = new Scanner(System.in);
            int opcion;
            float num1, num2, result;
            double num3, result2;

            do {
                System.out.println("Menu principal");
                System.out.println("1. Sumar");
                System.out.println("2. Restar");
                System.out.println("3. Multiplicar");
                System.out.println("4. Dividir");
                System.out.println("5. Seno");
                System.out.println("6. Coseno");
                System.out.println("7. Tangente");
                System.out.println("8. Raiz Cuadrada");
                System.out.println("9. Salir");

                System.out.println("Elige una opción del menú:");
                opcion = lector.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingresa el primer número:");
                        num1 = lector.nextFloat();
                        System.out.println("Ingresa el segundo número:");
                        num2 = lector.nextFloat();
                        result = opera1.suma(num1, num2);
                        System.out.println("El resultado de la suma es:" + result);
                        break;

                    case 2:
                        System.out.println("Ingresa el primer número:");
                        num1 = lector.nextFloat();
                        System.out.println("Ingresa el segundo número:");
                        num2 = lector.nextFloat();
                        result = opera1.resta(num1, num2);
                        System.out.println("El resultado de la resta es:" + result);
                        break;

                    case 3:
                        System.out.println("Ingresa el primer número:");
                        num1 = lector.nextFloat();
                        System.out.println("Ingresa el segundo número:");
                        num2 = lector.nextFloat();
                        result = opera1.multiplicacion(num1, num2);
                        System.out.println("El resultado de la multiplicación es:" + result);
                        break;

                    case 4:
                        System.out.println("Ingresa el primer número:");
                        num1 = lector.nextFloat();
                        System.out.println("Ingresa el segundo número:");
                        num2 = lector.nextFloat();
                        if (num2 == 0) {
                            System.out.println("Este número no es válido:" + num2);
                        } else {
                            result = opera1.division(num1, num2);
                            System.out.println("El resultado de la división es:" + result);
                        }
                        break;

                    case 5:
                        System.out.println("Ingresa el número:");
                        num3 = lector.nextDouble();
                        result2 = opera2.sen(num3);
                        System.out.println("El resultado del seno de " + num3 + " es: " + result2);
                        break;

                    case 6:
                        System.out.println("Ingresa el número:");
                        num3 = lector.nextDouble();
                        result2 = opera2.cos(num3);
                        System.out.println("El resultado del coseno de " + num3 + " es: " + result2);
                        break;

                    case 7:
                        System.out.println("Ingresa el número:");
                        num3 = lector.nextDouble();
                        result2 = opera2.tan(num3);
                        System.out.println("El resultado de la tangente de " + num3 + " es: " + result2);
                        break;

                    case 8:
                        System.out.println("Ingresa el número:");
                        num3 = lector.nextDouble();
                        result2 = opera2.raizCuadrada(num3);
                        System.out.println("El resultado de la raíz cuadrada de " + num3 + " es: " + result2);
                        break;

                    case 9:
                        System.out.println("Fin del programa!");
                        break;

                    default:
                        System.out.println("Opción no válida, intenta nuevamente");
                        break;
                }
                System.out.println();

            } while (opcion != 9);

        } catch (MalformedURLException mue) {
            System.out.println("La URL no está disponible:" + mue.getMessage());
        } catch (RemoteException re) {
            System.out.println("Red no disponible:" + re.getMessage());
        } catch (NotBoundException nbe) {
            System.out.println("Servicio no disponible:" + nbe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.out.println("El puerto de conexión es inválido:" + iae.getMessage());
        } catch (ArithmeticException ae) {
            System.out.println("Error en alguna operación:" + ae.getMessage());
        } catch (InputMismatchException ime) {
            System.out.println("Error en el ingreso de caracteres:" + ime.getMessage());
        }
    }
}

    
    
