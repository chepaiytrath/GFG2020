package company.amazon.oa.amcat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NearestCity {
    public static void main(String[] args) {
        /*int numOfCities = 3;
        String[] cities = new String[]{"c1", "c2", "c3"};
        int[] xCoordinates = new int[]{3, 2, 1};
        int[] yCoordinates = new int[]{3, 2, 3};
        int numOfQueries = 3;
        String[] queries = new String[]{"c1", "c2", "c3"};*/

        int numOfCities = 6;
        String[] cities = {"green", "yellow", "red", "blue", "grey", "pink"};
        int[] xCoordinates = {10, 20, 15, 30, 10, 15};
        int[] yCoordinates = {30, 25, 30, 40, 25, 25};
        int numOfQueries = 4;
        String[] queries = {"grey", "blue", "red", "pink"};
        System.out.println(Arrays.asList(findNearestCity(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries)));
        System.out.println(Arrays.asList(findNearestCity2(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries)));
    }

    static class City {
        String name;
        int x;
        int y;

        City(String city, int i, int j) {
            this.name = city;
            this.x = i;
            this.y = j;
        }

        public int getDistanceFrom(int i, int j) {
            return Math.abs(this.x - i) + Math.abs(this.y - j);
        }
    }
    // PASSES ALL TEST CASES ON https://aonecode.com/amazon-online-assessment-nearest-cities
    private static String[] findNearestCity(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates, int numOfQueries, String[] queries) {
        String[] res = new String[numOfQueries];
        int idx = 0;
        Map<String, City> map = new HashMap<>();
        for (int i = 0; i < numOfCities; i++) {
            City c = new City(cities[i], xCoordinates[i], yCoordinates[i]);
            map.put(c.name, c);
        }
        for (String query : queries) {
            int queryX = map.get(query).x;
            int queryY = map.get(query).y;
            PriorityQueue<City> pq = new PriorityQueue<>((a, b) -> {
                int disA = a.getDistanceFrom(queryX, queryY);
                int disB = b.getDistanceFrom(queryX, queryY);
                if (disA != disB) {
                    return disA - disB;
                } else {
                    return a.name.compareTo(b.name);
                }
            });

            for (Map.Entry<String, City> entry : map.entrySet()) {
                City c = entry.getValue();
                if (!c.name.equals(query) && (c.x == queryX || c.y == queryY)) {
                    pq.add(c);
                }
            }

            if (pq.isEmpty()) {
                res[idx++] = null;
                continue;
            }
            res[idx] = pq.poll().name;
            idx++;
        }
        return res;
    }

    // PASSES ALL TEST CASES ON https://aonecode.com/amazon-online-assessment-nearest-cities
    public static String[] findNearestCity2(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates, int numOfQueries,
                                            String[] queries) {

        HashMap<String, Map.Entry<String, Integer>> map = new HashMap<>();

        // FOR EACH CITY, ITS OWN PRIORITYQUEUE
        PriorityQueue<Map.Entry<String, Integer>> pq[] = new PriorityQueue[numOfCities];
        HashMap<String, Integer> cityIndexMap = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            cityIndexMap.put(cities[i], i);
        }

        // INITIALIZE EACH PRIORITYQUEUE FOR EACH CITY
        for (int i = 0; i < pq.length; i++) {
            // EACH PRIORITYQUEUE STORES THE CITIES WITH EITHER X OR Y COORDINATE SAME AS THAT CITY'S COORDINATES IN A SORTED ORDER
            // SORTING ORDER: FIRST ON BASIS OF DISTANCE, IF DISTANCE IS SAME, THEN ON BASIS OF CITY NAME
            // ENTRY: CITYNAME, DISTANCE w.r.t WHICH CITY'S PRIORITY QUEUE IT IS
            pq[i] = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue() != 0 ? a.getValue() - b.getValue() : a.getKey().compareTo(b.getKey()));
        }

        // FIND ALL CITIES WHO HAVE SAME XCOORDINATE AS CITY[i]
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                if (i == j)
                    continue;
                if (xCoordinates[i] == xCoordinates[j]) {
                    int x = Math.abs(xCoordinates[i] - xCoordinates[j]);
                    int y = Math.abs(yCoordinates[i] - yCoordinates[j]);
                    int dis = x + y;
                    HashMap<String, Integer> m = new HashMap<>();
                    m.put(cities[j], dis);
                    for (Map.Entry<String, Integer> e : m.entrySet())
                        pq[i].offer(e);
                }
            }
        }

        // FIND ALL CITIES WHO HAVE SAME YCOORDINATE AS CITY[i]
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                if (i == j)
                    continue;
                if (yCoordinates[i] == yCoordinates[j]) {
                    int x = Math.abs(xCoordinates[i] - xCoordinates[j]);
                    int y = Math.abs(yCoordinates[i] - yCoordinates[j]);
                    int d = x + y;
                    HashMap<String, Integer> m = new HashMap<>();
                    m.put(cities[j], d);
                    for (Map.Entry<String, Integer> e : m.entrySet())
                        pq[i].offer(e);
                }
            }
        }

        // POPULATE RESULT ARRAY WITH TOP ELEMENT FROM EACH PRIORITYQUEUE
        String res[] = new String[numOfQueries];
        for (int i = 0; i < queries.length; i++) {
            int idx = cityIndexMap.get(queries[i]);
            if (!pq[idx].isEmpty()) {
                Map.Entry<String, Integer> e = pq[idx].peek();
                res[i] = e.getKey();
            } else res[i] = null;
        }
        return res;
    }
}
