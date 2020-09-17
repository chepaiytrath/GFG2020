package company.amazon.oa.amcat;

import java.util.*;

public class Pagination {
    //sortParameter : 0(NAME), 1(RELEVANCE), 2(PRICE)
    //sortOrder : 0(ASCENDING), 1(DESCENDING)
    //PAGE NUMBER IS 0 INDEXED
    public static void main(String[] args) {
        int numOfItems = 3;
        HashMap<String, PairInt> items = new HashMap<>();
        items.put("item1", new PairInt(10, 15));
        items.put("item2", new PairInt(3, 4));
        items.put("item3", new PairInt(17, 8));
        items.put("item4", new PairInt(5, 19));
        items.put("item5", new PairInt(1, 16));
        items.put("item6", new PairInt(10, 7));
        items.put("item7", new PairInt(8, 7));
        items.put("item8", new PairInt(18, 9));
        items.put("item9", new PairInt(9, 10));
        items.put("item10", new PairInt(5, 15));
        items.put("item11", new PairInt(20, 3));

        int sortParameter = 2;
        int sortOrder = 0;
        int itemsPerPage = 3;
        int pageNumber = 3;
        System.out.println("Page 0 : " + fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, 0));
        System.out.println("Page 1 : " + fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, 1));
        System.out.println("Page 2 : " + fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, 2));
        System.out.println("Page 3 : " + fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, 3));
        System.out.println("Page 4 : " + fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, 4));
    }

    private static List<String> fetchItemsToDisplay(int numOfItems, HashMap<String, PairInt> items,
                                                    int sortParameter, int sortOrder,
                                                    int itemsPerPage, int pageNumber) {

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (sortParameter == 0) {
                if (sortOrder == 0) {
                    return a.compareTo(b);
                } else {
                    return b.compareTo(a);
                }
            } else if (sortParameter == 1) {
                if (sortOrder == 0) {
                    return items.get(a).relevance - items.get(b).relevance;
                } else {
                    return items.get(b).relevance - items.get(a).relevance;
                }
            } else {
                if (sortOrder == 0) {
                    return items.get(a).price - items.get(b).price;
                } else {
                    return items.get(b).price - items.get(a).price;
                }
            }
        });

        pq.addAll(items.keySet());

        Map<Integer, List<String>> res = new HashMap<>();

        int i = 0;
        while (!pq.isEmpty()) {
            String item = pq.poll();
            int pageNum = i / itemsPerPage;
            List<String> list = res.getOrDefault(pageNum, new ArrayList<>());
            list.add(item);
            res.put(pageNum, list);
            i++;
        }

        return res.get(pageNumber);
    }

    static class PairInt {
        int relevance;
        int price;

        PairInt(int relevance, int price) {
            this.relevance = relevance;
            this.price = price;
        }
    }
}
