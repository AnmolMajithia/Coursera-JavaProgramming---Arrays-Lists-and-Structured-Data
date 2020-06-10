package VigenereCipher;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    private class Anmol {
        String decrypted;
        int max;
        int[] keys;

        Anmol(String decrypted, int max, int[] keys) {
            this.decrypted = decrypted;
            this.max = max;
            this.keys = keys;
        }
    }

    public String sliceString(String message, int start, int step) {
        StringBuilder ret = new StringBuilder();
        for (int i = start; i < message.length(); i += step) {
            ret.append(message.charAt(i));
        }
        return ret.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            key[i] = cc.getKey(sliceString(encrypted, i, klength));
        }
        return key;
    }

    public String keyBuilder(int[] keys) {
        StringBuilder s = new StringBuilder();
        for (int key : keys) {
            char c = (char) ((char) key + 'a');
            s.append(c);
        }
        return s.toString();
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<>();
        for (String s : fr.lines()) {
            s = s.toLowerCase();
            dict.add(s);
        }
        return dict;
    }

    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        String[] words = message.split("\\W+");
        for (String s : words) {
            if (dict.contains(s.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public Anmol breakForLanguage(String encrypted, HashSet<String> dict) {
        String ret = "";
        int max = 0;
        int[] keys = null;
        for (int i = 1; i < 101; i++) {
            int[] temp = tryKeyLength(encrypted, i, mostCommonCharIn(dict));
            VigenereCipher vc = new VigenereCipher(temp);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dict);
            if (count > max) {
                max = count;
                ret = decrypted;
                keys = temp;
            }
        }
        return new Anmol(ret, max, keys);
    }

    public char mostCommonCharIn(HashSet<String> dict) {
        int a = 'a';
        int max = 0;
        char ret = '\0';
        for (int i = a; i <= a+25; i++) {
            int count = 0;
            char ch = (char)i;
            for (String s : dict) {
                if (s.indexOf(ch) != -1) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                ret = ch;
            }
        }
        return ret;
    }

    public String breakForAllLang(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String lang = "";
        String decrypted = "";
        int[] keys = null;
        System.out.println("Working on language :");
        for (String s : languages.keySet()) {
            System.out.print(s + "... ");
            Anmol ob = breakForLanguage(encrypted, languages.get(s));
            if (max < ob.max) {
                max = ob.max;
                lang = s;
                decrypted = ob.decrypted;
                keys = ob.keys;

            }
            System.out.println("Done!");
        }
        System.out.println("\n\nKeys found : " + Arrays.toString(keys));
        System.out.println("Key Length : " + keys.length);
        System.out.println("Key as text : " + keyBuilder(keys));
        System.out.println("Word Match : " + max);
        System.out.println("Detected Language : " + lang);
        return decrypted;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        String source = "/home/anmol/JavaProjects/Coursera/JavaProgramming-2/data/Vignere/dictionaries/";
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};

        System.out.print("Generating dictionaries... ");
        HashMap<String, HashSet<String>> dict = new HashMap<>();
        for (String lang : languages) {
            FileResource dictfr = new FileResource(source+lang);
            HashSet<String> dictset = readDictionary(dictfr);
            dict.put(lang, dictset);
        }
        System.out.println("Done!");

        String result = breakForAllLang(encrypted, dict);
        System.out.println("\nDecrypted message : \n" + result);
    }

}
