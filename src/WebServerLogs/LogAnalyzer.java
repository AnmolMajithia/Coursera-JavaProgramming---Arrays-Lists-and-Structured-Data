package WebServerLogs;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        String source = "/home/anmol/JavaProjects/Coursera/JavaProgramming-2/data/Web Server Logs/";
        FileResource fr = new FileResource(source + filename);
        for (String s : fr.lines()) {
            records.add(WebLogParser.parseEntry(s));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        if (records.size() == 0) { System.out.println("Read a file first."); return -1; }
        ArrayList<String> IPs = new ArrayList<>();
        for (LogEntry e : records) {
            String s = e.getIpAddress();
            if (!IPs.contains(s)) {
                IPs.add(s);
            }
        }
        return IPs.size();
    }

    public void printAllHigherThanNum(int num) {
        if (records.size() == 0) { System.out.println("Read a file first."); return; }
        for (LogEntry e : records) {
            if (e.getStatusCode() > num) {
                System.out.println(e);
            }

        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        if (records.size() == 0) { System.out.println("Read a file first."); return null; }
        ArrayList<String> IPs = new ArrayList<>();
        for (LogEntry e : records) {
            if (e.getAccessTime().toString().contains(someday)) {
                if (!IPs.contains(e.getIpAddress())) {
                    IPs.add(e.getIpAddress());
                }
            }
        }
        return IPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        if (records.size() == 0) { System.out.println("Read a file first."); return -1; }
        ArrayList<String> IPs = new ArrayList<>();
        for (LogEntry e : records) {
            if (low <= e.getStatusCode() && e.getStatusCode() <= high) {
                if (!IPs.contains(e.getIpAddress())) {
                    IPs.add(e.getIpAddress());
                }
            }
        }
        return IPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        if (records.size() == 0) { System.out.println("Read a file first."); return null; }
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry e : records) {
            String ip = e.getIpAddress();
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip) + 1);
            }
            else {
                counts.put(ip, 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = Integer.MIN_VALUE;
        for (int value : map.values()) {
            if (max < value) max = value;
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> IPs = new ArrayList<>();
        int maxCount = mostNumberVisitsByIP(map);
        for (String key : map.keySet()) {
            if (map.get(key) == maxCount) {
                IPs.add(key);
            }
        }
        return IPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        if (records.size() == 0) { System.out.println("Read a file first."); return null; }
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (LogEntry e : records) {
            String date = e.getAccessTime().toString().substring(4, 10);
            if (map.containsKey(date)) {
                ArrayList<String> temp = map.get(date);
//                if (!temp.contains(e.getIpAddress())) {
                    temp.add(e.getIpAddress());
                    map.put(date, temp);
//                }
            }
            else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(e.getIpAddress());
                map.put(date, temp);
            }
        }
        return map;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int max = Integer.MIN_VALUE;
        String ret = "";
        for (String key : map.keySet()) {
            if (max < map.get(key).size()) {
                max = map.get(key).size();
                ret = key;
            }
        }
        return ret;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        HashMap<String, Integer> count = new HashMap<>();
        ArrayList<String> IP = map.get(day);
        for (String s : IP) {
            if (count.containsKey(s)) {
                count.put(s, count.get(s) + 1);
            }
            else {
                count.put(s, 1);
            }
        }
        return iPsMostVisits(count);
    }
}
