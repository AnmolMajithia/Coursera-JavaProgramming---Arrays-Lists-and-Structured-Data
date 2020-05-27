public class WordPlay {
    private boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    private String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for(int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(isVowel(c)) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    private String emphasize(String phrase, char ch) {
        StringBuilder s = new StringBuilder(phrase);
        for(int i = 0; i < s.length(); i++) {
            char c = Character.toUpperCase(s.charAt(i));
            if(c == Character.toUpperCase(ch)) {
                if(i%2 == 0) {
                    s.setCharAt(i, '*');
                }
                else {
                    s.setCharAt(i, '+');
                }
            }
        }
        return s.toString();
    }

    private void test() {
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main(String[] args) {
        WordPlay ob = new WordPlay();
        ob.test();
    }
}
