import edu.duke.FileResource;

public class CaesarBreaker {
    private void countLetters(String str, int[] counts) {
        str = str.toUpperCase();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(Character.isAlphabetic(c)) {
                counts[c-'A'] += 1;
            }
        }
    }

    private int maxIndex(int[] counts) {
        int max = 0, idx = -1;
        for (int i = 0; i < counts.length; i++) {
            if(max < counts[i]) {
                max = counts[i];
                idx = i;
            }
        }
        return idx;
    }

    private String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26-key);
    }

    private String halfOfString(String message, int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < message.length(); i+=2) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    private int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        int i = maxIndex(counts);
        return (i-4+26)%26;
    }

    private String decryptTwoKeys(String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println("Key 1: " + key1 + "\nKey 2: " + key2);
        String firstHalfDecrypted = decrypt(firstHalf, key1);
        String secondHalfDecrypted = decrypt(secondHalf, key2);
        StringBuilder ret = new StringBuilder();
        int j = 0, k = 0;
        for(int i = 0; i < encrypted.length(); i++) {
            if(i%2 == 0) {
                ret.append(firstHalfDecrypted.charAt(j++));
            }
            else {
                ret.append(secondHalfDecrypted.charAt(k++));
            }
        }
        return ret.toString();
    }

    private void testDecryptTwoKeys() {
//        System.out.println(decryptTwoKeys("fbfbfbf Bknlm"));
//        System.out.println(decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
//        System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        System.out.println(decryptTwoKeys(new FileResource().asString()));
    }

    private void testDecrypt() {
        String encrypted = "bbbbbbb Xkjli";
        int[] counts = new int[26];
        countLetters(encrypted, counts);
        int i = maxIndex(counts);
        System.out.println(decrypt(encrypted, (i-4+26)%26));
    }

    public static void main(String[] args) {
        CaesarBreaker ob = new CaesarBreaker();
        ob.testDecrypt();
        ob.testDecryptTwoKeys();
    }
}
