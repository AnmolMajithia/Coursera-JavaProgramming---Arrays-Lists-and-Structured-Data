package Cryptography;

import edu.duke.FileResource;

public class WordLengths {
    private void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int letterCount = 0;
            for (int index=0; index < word.length(); index++) {
                if ((index == 0 || index == (word.length()-1)) && !Character.isLetter(word.charAt(index))) {
                    continue;
                }
                letterCount++;
            }
            if (letterCount >= counts.length) {
                counts[counts.length-1] += 1;
            }
            else {
                counts[letterCount] += 1;
            }
        }
    }

    private int indexOfMax(int[] values) {
        int max = 0;
        int idx = -1;
        for (int i = 0; i < values.length; i++) {
            if(values[i] > max) {
                max = values[i];
                idx = i;
            }
        }
        return idx;
    }

    private void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] arr = new int[31];
        countWordLengths(fr, arr);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=0) {
                System.out.println("Number of words of length " + i + ": " + arr[i]);
            }
        }
        System.out.println("Most common word length is: " + indexOfMax(arr));
    }

    public static void main(String[] args) {
        WordLengths ob = new WordLengths();
        ob.testCountWordLengths();
    }
}
