package company.amazon.oa.amcat;

import java.util.*;

public class TrendingWords {
    public static void main(String[] args) {
        int k = 2;
        String[] keywords = new String[]{"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String[] reviews = new String[]{
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular."
        };

        String[] arr = find(reviews, keywords, k);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    private static String[] find(String[] reviews, String[] keywords, int k) {
        List<String> res = new ArrayList<>();
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> countMap = new HashMap<>();
        for (String line : reviews) {
            String[] keys = line.split("\\W");
            Set<String> added = new HashSet<>();
            for (String key : keys) {
                String lowerKey = key.toLowerCase();
                if(keywordSet.contains(lowerKey) && !added.contains(lowerKey)){
                    countMap.put(lowerKey, countMap.getOrDefault(lowerKey, 0) + 1);
                    added.add(lowerKey);
                }
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>((a, b) -> a.word != b.word ? a.count.compareTo(b.count) : b.word.compareTo(a.word));
        for (String key : keywords) {
            if (countMap.containsKey(key)) {
                Word word = new Word(key, countMap.get(key));
                pq.add(word);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll().word);
        }

        Collections.reverse(res);
        return res.toArray(new String[res.size()]);
    }

    static class Word {
        String word;
        Integer count;

        Word(String word, Integer count) {
            this.word = word;
            this.count = count;
        }
    }
}
