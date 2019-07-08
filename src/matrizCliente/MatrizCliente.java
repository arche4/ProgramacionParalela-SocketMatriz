package matrizCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrizCliente {

    private static final int PUERTO = 12345;
    private final static String IP = "127.0.0.1";

    public static void main(String[] args) {

        Socket conexion;
        DataOutputStream salidaDatos;
        DataInputStream entradaDatos;
        Scanner teclado = new Scanner(System.in);
        System.out.println(">>Iniciando cliente...");

        while (true) {
            try {
                conexion = new Socket(IP, PUERTO);
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                salidaDatos.writeUTF(teclado.nextLine());
                salidaDatos.writeUTF(teclado.nextLine());
                entradaDatos = new DataInputStream(conexion.getInputStream());

                System.out.println("Server>>" + entradaDatos.readUTF());
                conexion.close();

            } catch (IOException ex) {
                Logger.getLogger(MatrizCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
