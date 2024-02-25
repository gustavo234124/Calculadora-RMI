import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class CalculadoraGUI extends JFrame {

    private IOperacion operacionesBasicas;
    private IOpeCientifica operacionesCientificas;

    private JTextField display;

    public CalculadoraGUI() {
        setTitle("Calculadora RMI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.weightx = 1.0;
        contentPanel.add(display, c);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "Sen", "Cos", "Tan", "sqrt",
            "Ln", "Log10", "x^y", "Abs",
            "!", "Exp", "Atan", "C","Reiniciar"
        };

        c.gridwidth = 1;
        c.weightx = 0.25;
        int x = 0;
        int y = 1;
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            c.gridx = x;
            c.gridy = y;
            contentPanel.add(button, c);

            x++;
            if (x > 3) {
                x = 0;
                y++;
            }
        }

        //boton para reiniciar
        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.setActionCommand("Reiniciar");
        reiniciarButton.addActionListener(new ButtonClickListener());
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=4;
        contentPanel.add(reiniciarButton,c);

        add(contentPanel);
        pack();
        setLocationRelativeTo(null);

        initializeRMIConnection();
    }

        private void initializeRMIConnection(){
        try {
            String urlBasicas = "rmi://192.168.100.1/Operaciones";
            operacionesBasicas = (IOperacion) Naming.lookup(urlBasicas);

            String urlCientificas = "rmi://192.168.100.2/OperaCientifica";
            operacionesCientificas = (IOpeCientifica) Naming.lookup(urlCientificas);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private class ButtonClickListener implements ActionListener {
        private String pendingOperation = "";
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                if(command == null || "Reiniciar".equals(command)){
                    dispose();
                    new CalculadoraGUI().setVisible(true);
                }else if ("0123456789.".contains(command)) {
                    display.setText(display.getText() + command);
                } else if ("+-*/".contains(command)) {
                    
                    display.setText(display.getText() + " " + command + " ");
    
                } else if ("=".equals(command)) {
                    String expression = display.getText();
                    if (expression.contains("^")) {
                        // ... (manejar potencia)
                    } else {
                        String[] parts = expression.split(" ");
                        if (parts.length == 3) {
                            try {
                                float num1 = Float.parseFloat(parts[0]);
                                float num2 = Float.parseFloat(parts[2]);
                                float result = 0;
                                switch (parts[1]) {
                                    // ... (resto de las operaciones básicas)
                                }
                                display.setText(Float.toString(result));
                            } catch (RemoteException re) {
                                handleServerConnectionError(operacionesBasicas, "Operaciones básicas");
                            }
                        } else {
                            showError("Error: entrada no válida para la operación.");
                        }
                    }
                }
                
                    else{
                        String[] parts = display.getText().split(" ");
                        if(parts.length == 3){
                            try{
                                float num1 = Float.parseFloat(parts[0]);
                                float num2 = Float.parseFloat(parts[2]);  
                                float result = 0;
                            switch (parts[1]) {
                                case "+":
                                    result = operacionesBasicas.suma(num1, num2);
                                    break;
                                case "-":
                                    result = operacionesBasicas.resta(num1, num2);
                                    break;
                                case "*":
                                    result = operacionesBasicas.multiplicacion(num1, num2);
                                    break;
                                case "/":
                                    if (num2 != 0) {
                                        result = operacionesBasicas.division(num1, num2);
                                    } else {
                                        showError("Error: División por cero.");
                                        return;
                                    }
                            }
                            display.setText(Float.toString(result));
                        } catch (RemoteException re) {
                            handleServerConnectionError(operacionesBasicas, "Operaciones básicas");
                        }
                    
                } else if ("x^y".equals(command)) {
                    // Cambios para el botón de la potencia
                    display.setText(display.getText() + " ^ ");
                    pendingOperation = "^";
                } else if ("Sen".equals(command) || "Cos".equals(command) || "Tan".equals(command) || "sqrt".equals(command) ||
                        "Ln".equals(command) || "Log10".equals(command) || "x^y".equals(command) || "Abs".equals(command) ||
                        "!".equals(command) || "Exp".equals(command) || "Atan".equals(command)) {
                    String expression = display.getText();
                    double num = Double.parseDouble(expression);
                    double result = 0;
                    try {
                        switch (command) {
                            case "Sen":
                                result = operacionesCientificas.sen(num);
                                break;
                            case "Cos":
                                result = operacionesCientificas.cos(num);
                                break;
                            case "Tan":
                                result = operacionesCientificas.tan(num);
                                break;
                            case "sqrt":
                                result = operacionesCientificas.raizCuadrada(num);
                                break;
                            case "Ln":
                                if (num > 0) {
                                    result = operacionesCientificas.logaritmoNatural(num);
                                } else {
                                    showError("Error: El logaritmo natural no está definido para números no positivos.");
                                    return;
                                }
                            
                                break;
                            case "Log10":
                                if (num > 0) {
                                    result = operacionesCientificas.logaritmoBase10(num);
                                } else {
                                    showError("Error: El logaritmo base 10 no está definido para números no positivos.");
                                    return;
                                }
                                break;
                                case "x^y":
                                display.setText(display.getText() + "^");
                                return;
                            
                            case "Abs":
                                result = operacionesCientificas.valorAbsoluto(num);
                                break;
                            case "!":
                                if (num >= 0 && num == (int) num) {
                                    result = operacionesCientificas.factorial((int) num);
                                } else {
                                    showError("Error: El factorial no está definido para números no enteros o negativos.");
                                    return;
                                }
                                break;
                            case "Exp":
                                result = operacionesCientificas.exponencial(num);
                                break;
                            case "Atan":
                                result = operacionesCientificas.arcotangente(num);
                                break;
                        }
                    
                        display.setText(Double.toString(result));
                    } catch (RemoteException re) {
                        handleServerConnectionError(operacionesCientificas, "Operaciones científicas");
                    }
                    
                }else if ("x^y".equals(command)) {
                    String expression = display.getText();
                    String[] parts = expression.split("\\^");
                    if (parts.length == 2) {
                        try {
                            double base = Double.parseDouble(parts[0]);
                            // Obtener el exponente (lo que está después del ^)
                            double exponente = Double.parseDouble(parts[1]);
                            double result = Math.pow(base, exponente);
                            display.setText(Double.toString(result));
                        } catch (NumberFormatException nfe) {
                            showError("Error: Entrada no válida para la potencia.");
                        }
                    
                    } else {
                        showError("Error: Entrada no válida para la potencia.");
                    }
                    return;
                } else if ("C".equals(command)) {
                    display.setText("");
                }
            
            } catch (NumberFormatException ex) {
                showError("Error: Entrada no válida.");
            }
        }
    
        private void showError(String message) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        private void handleServerConnectionError(Object server, String serverName) {
            int attemptCount = 0;
            while (attemptCount < 3) {
                try {
                    Thread.sleep(2000); // Espera 2 segundos antes de intentar nuevamente.
                    System.out.println("Intentando reconectar con el servidor de " + serverName + "...");
                    establishConnection(server, serverName);
                    return;
                } catch (Exception e) {
                    attemptCount++;
                }
            }
            showError("Error: No se puede conectar con el servidor de " + serverName + ". Por favor, verifique la conexión.");
        }
    
        private void establishConnection(Object server, String serverName) {
            try {
                if (server == operacionesBasicas) {
                    String urlBasicas = "rmi://192.168.100.1/Operaciones";
                    operacionesBasicas = (IOperacion) Naming.lookup(urlBasicas);
                } else if (server == operacionesCientificas) {
                    String urlCientificas = "rmi://192.168.100.1/OperaCientifica";
                    operacionesCientificas = (IOpeCientifica) Naming.lookup(urlCientificas);
                }
                System.out.println("Conexión con el servidor de " + serverName + " restablecida.");
            } catch (Exception ex) {
                // La conexión no se pudo restablecer, se manejará en el siguiente ciclo.
            }
            
        }
    
    
    }
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraGUI().setVisible(true);
        });
    }
}

