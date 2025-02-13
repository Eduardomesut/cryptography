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
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CifrarFichero {
    public static void main(String[] args) {
        

        String nombreArchivo = "textocifrado.txt";

        try {
            // Leer la clave secreta desde el archivo
            FileInputStream fis = new FileInputStream("claveSecreta.key");
            ObjectInputStream ois = new ObjectInputStream(fis);
            SecretKey secretKey = (SecretKey) ois.readObject();
            ois.close();

            // Leer el contenido del archivo a cifrar
            FileInputStream fisArchivo = new FileInputStream(nombreArchivo);
            byte[] archivoBytes = fisArchivo.readAllBytes();
            fisArchivo.close();

            // Crear el objeto Cipher para cifrar (AES)
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Cifrar los datos
            byte[] archivoCifrado = cipher.doFinal(archivoBytes);

            // Guardar el archivo cifrado
            try (FileOutputStream fos = new FileOutputStream(nombreArchivo + ".cifrado")) {
                fos.write(archivoCifrado);
                System.out.println("Archivo cifrado guardado como '" + nombreArchivo + ".cifrado'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

