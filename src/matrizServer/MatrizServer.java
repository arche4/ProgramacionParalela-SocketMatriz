package matrizServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrizServer {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        ServerSocket servidor;
        Socket conexion;
        DataInputStream entradaDatos;
        DataOutputStream salidaDatos;

        int[][] matriz = new int[10][4];
        int a, b, cont = 0;
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = 0;
            }
        }
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println(">>Iniciando servidor...");
            while (true) {
                conexion = servidor.accept();

                entradaDatos = new DataInputStream(conexion.getInputStream());
                a = Integer.parseInt(entradaDatos.readUTF());//
                b = Integer.parseInt(entradaDatos.readUTF());
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                if (cont < 40) {
                    if (matriz[a][b] == 0) {

                        matriz[a][b] = 1;

                        salidaDatos.writeUTF("Lugar " + a + "," + b + " comprado con exito");
                        cont++;
                    } else {
                        String ms = " ";
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 4; j++) {

                                if (matriz[i][j] == 0) {
                                    ms += "[" + String.valueOf(i) + "," + String.valueOf(j) + "]" + " ; ";
                                }
                            }
                        }
                        salidaDatos.writeUTF("El vagon [" + a + "," + b + "] esta ocupado, libres son:" + ms + " ");
                    }
                } else {
                    salidaDatos.writeUTF("El vagon se encuentra lleno, no es posible hacer la compra.");
                    conexion.close();
                }
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MatrizServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
