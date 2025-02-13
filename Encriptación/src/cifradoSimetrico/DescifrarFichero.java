/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cifradoSimetrico;

/**
 *
 * @author Eduardo
 */
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.*;

public class DescifrarFichero {
    public static void main(String[] args) {
        

        String nombreArchivoCifrado = "textocifrado.txt.cifrado";

        try {
            // Leer la clave secreta desde el archivo
            FileInputStream fis = new FileInputStream("claveSecreta.key");
            ObjectInputStream ois = new ObjectInputStream(fis);
            SecretKey secretKey = (SecretKey) ois.readObject();
            ois.close();

            // Leer el archivo cifrado
            FileInputStream fisArchivoCifrado = new FileInputStream(nombreArchivoCifrado);
            byte[] archivoCifradoBytes = fisArchivoCifrado.readAllBytes();
            fisArchivoCifrado.close();

            // Crear el objeto Cipher para descifrar (AES)
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Descifrar el archivo
            byte[] archivoDescifrado = cipher.doFinal(archivoCifradoBytes);

            // Guardar el archivo descifrado
            try (FileOutputStream fos = new FileOutputStream(nombreArchivoCifrado + ".descifrado")) {
                fos.write(archivoDescifrado);
                System.out.println("Archivo descifrado guardado como '" + nombreArchivoCifrado + ".descifrado'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
