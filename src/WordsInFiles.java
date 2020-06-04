import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> words;

    WordsInFiles() {
        words = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String s : fr.words()) {
            //s = s.toLowerCase(); //.replaceAll("[^a-z]", "");
            if (words.containsKey(s)) {
                if (!words.get(s).contains(f.getName())) {
                    words.get(s).add(f.getName());
                }
            } else {
                ArrayList<String> str = new ArrayList<>();
                str.add(f.getName());
                words.put(s, str);
            }
        }
    }

    private void buildWordFileMap() {
        words.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        int max = 0;
        for (String s : words.keySet()) {
            if (max < words.get(s).size()) {
                max = words.get(s).size();
            }
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int num) {
        ArrayList<String> res = new ArrayList<>();
        for (String s : words.keySet()) {
            if (words.get(s).size() == num) {
                res.add(s);
            }
        }
        return res;
    }

    private void printFilesIn(String word) {
        System.out.println("Name of files in which '" + word + "' comes in :");
        for (String s : words.get(word)) {
            System.out.println(s);
        }
    }

    private void tester() {
//        for (String s : words.keySet()) {
//            System.out.print(s + " occurs in files : ");
//            for (String str : words.get(s)) {
//                System.out.print(str + " ");
//            }
//            System.out.println();
//        }
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Max Number : " + max);
        ArrayList<String> ar = wordsInNumFiles(4);
        System.out.println("Number of files that appeared so and so times : " + ar.size());
//        for (String s : ar) {
//            printFilesIn(s);
//            System.out.println();
//        }
        printFilesIn("tree");
    }

    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }
}
