import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codon;

    CodonCount() {
        codon = new HashMap<>();
    }

    private void buildCodonMap(int start, String dna) {
        codon.clear();
        for (int i = start; i < dna.length()-2; i += 3) {
            String s = dna.substring(i,i+3);
            if (codon.containsKey(s)) {
                codon.put(s,codon.get(s) + 1);
            } else {
                codon.put(s, 1);
            }
        }
    }

    private String getMostCommonCodon() {
        int max = 0;
        String ret = "";
        for (String s : codon.keySet()) {
            if (max < codon.get(s)) {
                max = codon.get(s);
                ret = s;
            }
        }
        return ret;
    }

    private void printCodonCount(int start, int end) {
        for (String s : codon.keySet()) {
            if (start <= codon.get(s) && codon.get(s) <= end) {
                System.out.println("Codon : " + s + "\t\t Times : " + codon.get(s));
            }
        }
    }

    private void tester() {
        FileResource fr = new FileResource();
        for (String s : fr.lines()) {
            s = s.toUpperCase();
            for (int i = 0; i < 3; i++) {
                buildCodonMap(i, s);
                System.out.println("\n\n" + i + ":" + "Number of unique codons : " + codon.size());
                String comm = getMostCommonCodon();
                System.out.println("Most common codon : " + comm + "\t\t Count : " + codon.get(comm));
                printCodonCount(7,7);
            }
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
