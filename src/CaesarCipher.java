import edu.duke.FileResource;

public class CaesarCipher {
    protected String encrypt(String input, int key) {
        StringBuilder s = new StringBuilder(input);
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isAlphabetic(c) && Character.isUpperCase(c)) {
                s.setCharAt(i, (char) ((c - 'A' + key + 26) % 26 + 'A'));
            }
            else if(Character.isAlphabetic(c) && Character.isLowerCase(c)) {
                s.setCharAt(i, (char) ((c - 'a' + key + 26) % 26 + 'a'));
            }
        }
        return s.toString();
    }

    private String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder s = new StringBuilder(input);
        for(int i = 0; i < s.length(); i++) {
            if(i%2 == 0) {
                s.replace(i,i+1, encrypt(s.substring(i, i+1), key1));
            }
            else {
                s.replace(i,i+1, encrypt(s.substring(i, i+1), key2));
            }
        }
        return s.toString();
    }

    private void testCaesar() {
        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
//        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
//        System.out.println(encryptTwoKeys("eeeeeee Anmol", 1, 23));
//        System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26-2, 26-20));
        System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24));
//        int key = 23;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String encrypted = encrypt(message, key);
//        System.out.println("Key is: " + key + "\n" + encrypted);
    }

    public static void main(String[] args) {
        CaesarCipher ob = new CaesarCipher();
        ob.testCaesar();
    }
}
