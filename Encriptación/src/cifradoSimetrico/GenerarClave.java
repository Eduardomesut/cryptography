/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cifradoSimetrico;

/**
 *
 * @author Eduardo
 */
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

public class GenerarClave {
    public static void main(String[] args) {
        try {
            // Crear un generador de claves AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // Tamanho de la clave: 128 bits

            // Generar la clave secreta
            SecretKey secretKey = keyGenerator.generateKey();

            // Guardar la clave en un archivo
            try (FileOutputStream fos = new FileOutputStream("claveSecreta.key");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(secretKey);
                System.out.println("Clave secreta generada y guardada en 'claveSecreta.key'");
            }
        } catch (NoSuchAlgorithmException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

