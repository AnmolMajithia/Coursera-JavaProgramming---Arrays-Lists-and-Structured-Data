package VigenereCipher;

import edu.duke.FileResource;

import java.io.File;
import java.util.Arrays;

public class Tester {
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(4);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        System.out.println("Message : \n" + message);
        System.out.println("\nEncrypted : \n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("\nDecrypted : \n" + decrypted);
    }

    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int key = cc.getKey(encrypted);
        System.out.println("Key : " + key);
        System.out.println("\nDecrypted via Cracker : \n" + cc.decrypt(encrypted));
    }

    public void testVigenereCipher(String key) {
        key = key.toLowerCase();
        int[] keys = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            keys[i] = c-'a';
        }
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = vc.encrypt(message);
        System.out.println("Message : \n" + message);
        System.out.println("\nEncrypted : \n" + encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("\nDecrypted : \n" + decrypted);
    }

    public void testVigenereBreaker() {
        new VigenereBreaker().breakVigenere();
    }

    public static void main(String[] args) {
        Tester ob = new Tester();
//        ob.testCaesarCipher();
//        ob.testCaesarCracker();
//        ob.testVigenereCipher("rome");
        ob.testVigenereBreaker();
    }
}
