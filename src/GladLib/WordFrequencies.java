package GladLib;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;

    private WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase(); // replaceAll("[^a-z]", ""); -- tooBigBrained
            if(!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int in = myWords.indexOf(word);
                myFreqs.set(in, myFreqs.get(in) + 1);
            }
        }
    }

    private int findIndexOfMax() {
        int max = 0, idx = -1;
        for (int i = 0; i < myFreqs.size(); i++) {
            if(max < myFreqs.get(i)) {
                max = myFreqs.get(i);
                idx = i;
            }
        }
        return idx;
    }

    private void tester() {
        findUnique();
        System.out.println("Number of unique words : " + myFreqs.size());
        for (int i = 0; i < myFreqs.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int maxBoi = findIndexOfMax();
        System.out.println("The word that occurs most often is : " + myWords.get(maxBoi) + " and it occurs : " + myFreqs.get(maxBoi) + " times.");
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
