package company.amazon.oa.amcat;

import java.util.*;

public class TopKFrequentlyMentionedKeywordsReview {
    /*@GhOsTfReAk93 Basically it was just:

    Regex to get the words and put into an array
    Add the count of each word into a hash map
    Return the word with the highest count. If there are multiple, return an array sorted lexicographically.

    Need to make sure to remove special characters and numbers from words and set the words to lower case (i.e. abc123! -> abc)*/







    //  The comparison of strings is case-insensitive.
    //  Multiple occurences of a keyword in a review should be considered as a single mention.
    //  If keywords are mentioned an equal number of times in reviews, sort alphabetically.
    //  If the value of topNCompetitors is more than the number of competitors discussed in the reviews then output the names of only the competitors mention.
    public static void main(String[] args) {
        // FOLLOWING 2 TEST CASES CONFLICT WHEN SPLITTING ON BASIS OF ("\\W"), ("\\s+") OR (" ")


        /*int numCompetitors = 1;
        int topNCompetitors = 1;
        String[] competitors = new String[]{"middle"};
        int numReviews = 3;
        String[] reviews = new String[]{
                "",
                "",
                "middle! "
        };*/


        int numCompetitors = 2;
        int topNCompetitors = 1;
        String[] competitors = new String[]{"trader","joe"};
        int numReviews = 6;
        String[] reviews = new String[]{
                "trader joe's",
                "joe'asdf",
                "joe'xyz",
                "joe`book",
                "joe-biden",
                "bitcoin trader"
        };


        List<String> arr = getTopToys2(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        for (String s : arr) {
            System.out.print(s + " ");
        }
    }

    // 5.12 PM : started
    // 5.27 : first draft

    public static List<String> getTopToys(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        Set<String> competitorsSet = new HashSet<>(Arrays.asList(competitors));
        for (int i = 0; i < numReviews; i++) {
            String review = reviews[i];
            String[] arr = review.split(" ");

            Set<String> alreadyProcessedInSameLine = new HashSet<>();
            for (int j = 0; j < arr.length; j++) {
                String word = arr[j].toLowerCase();
                if (competitorsSet.contains(word) && !alreadyProcessedInSameLine.contains(word)) {
                    countMap.put(word, countMap.getOrDefault(word, 0) + 1);
                    alreadyProcessedInSameLine.add(word);
                }
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int c1 = countMap.get(a);
            int c2 = countMap.get(b);
            if (c1 != c2) {
                return c2 - c1;
            } else {
                return a.compareTo(b);
            }
        });
        pq.addAll(countMap.keySet());
        while (!pq.isEmpty() && topNCompetitors-- > 0) {
            res.add(pq.poll());
        }
        return res;
    }

    public static List<String> getTopToys2(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {
        Map<String, Integer> frequencies = new HashMap<>();
        List<String> keywordList = Arrays.asList(competitors);

        for (String review : reviews) {
            review = review.toLowerCase();
            Set<String> encounteredWords = new HashSet<>();
            for (final String reviewWord : review.split("\\W")) {
                if (!encounteredWords.contains(reviewWord) && keywordList.contains(reviewWord)) {
                    Integer currentFrequency = frequencies.getOrDefault(reviewWord, 0);
                    frequencies.put(reviewWord, currentFrequency + 1);
                    encounteredWords.add(reviewWord);
                }
            }
        }

        List<String> res = new ArrayList<>();
        String[] frequencyArray = frequencies.keySet().toArray(new String[topNCompetitors]);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int c1 = frequencies.get(a);
            int c2 = frequencies.get(b);
            if (c1 != c2) {
                return c2 - c1;
            } else {
                return a.compareTo(b);
            }
        });
        pq.addAll(frequencies.keySet());
        while (!pq.isEmpty() && topNCompetitors-- > 0) {
            res.add(pq.poll());
        }
        return res;
    }
}
