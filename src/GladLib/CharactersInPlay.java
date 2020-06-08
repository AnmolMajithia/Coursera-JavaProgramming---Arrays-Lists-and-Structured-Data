package GladLib;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> count;

    CharactersInPlay() {
        names = new ArrayList<>();
        count = new ArrayList<>();
    }

    private void update(String person) {
        if (names.contains(person)) {
            int in = names.indexOf(person);
            count.set(in, count.get(in) + 1);
        } else {
            names.add(person);
            count.add(1);
        }
    }

    private void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int idx = line.indexOf(".");
            if (idx != -1) {
                String possibleName = line.substring(0, idx);
                update(possibleName);
            }
        }
    }

    private void charactersWithNumParts(int a, int b) {
        for (int i = 0; i < count.size(); i++) {
            if (a <= count.get(i) && count.get(i) <= b) {
                System.out.println("Character : " + names.get(i) + "\nNumber of lines : " + count.get(i) + "\n");
            }
        }
    }

    private int findIndexOfMax() {
        int max = 0, idx = -1;
        for (int i = 0; i < count.size(); i++) {
            if(max < count.get(i)) {
                max = count.get(i);
                idx = i;
            }
        }
        return idx;
    }

    private void tester() {
        findAllCharacters();
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i) > 5) {
                System.out.println("Character : " + names.get(i) + "\nNumber of lines : " + count.get(i) + "\n");
            }
        }
        System.out.println("\n\nNumber of lines in range :");
        charactersWithNumParts(10, 15);
        int max = findIndexOfMax();
        System.out.println("The character with most lines : " + names.get(max) + "\nNumber of lines : " + count.get(max));
    }

    public static void main(String[] args) {
        CharactersInPlay cp = new CharactersInPlay();
        cp.tester();
    }
}
