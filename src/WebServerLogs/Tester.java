package WebServerLogs;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("short-test_log");
        ob.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog2_log");
        System.out.println("Unique IPs : " + ob.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog1_log");
        ob.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog2_log");
        System.out.println(ob.uniqueIPVisitsOnDay("Sep 24").size());
//        System.out.println(ob.uniqueIPVisitsOnDay("Sep 30").size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog2_log");
        System.out.println(ob.countUniqueIPsInRange(200, 299));
//        System.out.println(ob.countUniqueIPsInRange(300, 399));
    }

    public void testCounts() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog2_log");
        HashMap<String, Integer> counts = ob.countVisitsPerIP();
        System.out.println(ob.mostNumberVisitsByIP(counts));
        System.out.println(ob.iPsMostVisits(counts));

    }

    public void testMap() {
        LogAnalyzer ob = new LogAnalyzer();
        ob.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = ob.iPsForDays();
        System.out.println(map);
        System.out.println(ob.dayWithMostIPVisits(map));
        System.out.println(ob.iPsWithMostVisitsOnDay(map, "Sep 29"));
    }

    public static void main(String[] args) {
        Tester ob = new Tester();
//        ob.testLogAnalyzer();
//        ob.testUniqueIP();
//        ob.testPrintAllHigherThanNum();
//        ob.testUniqueIPVisitsOnDay();
//        ob.testCountUniqueIPsInRange();
//        ob.testCounts();
        ob.testMap();
    }
}
