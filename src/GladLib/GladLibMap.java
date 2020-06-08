package GladLib;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.URLResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> used;
	private ArrayList<String> usedCategories;
	private int replaced;

	private final Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static final String dataSourceDirectory = "/home/anmol/JavaProjects/Coursera/JavaProgramming-2/data/GladLib.GladLib/data";

	public GladLibMap(){
		myMap = new HashMap<>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		replaced = 0;
	}

	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			myMap.put(f.getName().substring(0,f.getName().length()-4), readIt(source+"/"+f.getName()));
		}
		used = new ArrayList<>();
		usedCategories = new ArrayList<>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (myMap.containsKey(label)) {
			if (!usedCategories.contains(label)) {
				usedCategories.add(label);
			}
			return randomFrom(myMap.get(label));
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first + 1, last));
		while(used.contains(sub)) {
			sub = getSubstitute(w.substring(first + 1, last));
		}
		used.add(sub);
		replaced++;
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nNumber of words replaced : " + replaced);
		System.out.println("Number of options : " + totalWordsInMap());
		System.out.println("Number of options considered : " + totalWordsConsidered());
	}

	private int totalWordsInMap() {
		int total = 0;
		for (ArrayList s : myMap.values()) {
			total += s.size();
		}
		return total;
	}

	private int totalWordsConsidered() {
		int total = 0;
		for (String s : usedCategories) {
			total += myMap.get(s).size();
		}
		return total;
	}

	public static void main(String[] args) {
		GladLibMap ob =  new GladLibMap();
		ob.makeStory();
	}
}
