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
        String[] competitors = new String[]{"trader", "joe"};
        int numReviews = 6;
        String[] reviews = new String[]{
                "trader joe's",
                "joe'asdf",
                "joe'xyz",
                "joe`book",
                "joe-biden",
                "bitcoin trader"
        };

        /*int numCompetitors = 10;
        int topNCompetitors = 2;
        String[] competitors = new String[]{"region", "clearly", "follow", "business", "describe", "authority", "everyone", "power", "home", "education"};
        int numReviews = 10;
        String[] reviews = new String[]{
                "school people clearly religious agreement? church scene bit every technology back! entire against everybody civil region never? industry business describe laugh suffer? everyone. television push service energy report clearly president everyone. home",
                "region describe left region power seek describe authority interest often. everyone general ",
                "business must clearly period, price? describe several goal decade power home leader sit talk charge standard movie reality business foreign especially. away I program become anything interview class business shake` power else space particularly home partner method parent? study come describe history same? film, against pretty. ",
                "garden last kind behind may animal mind point step oil any` authority education deep? country none. scientist space imagine moment ten sound before deal industry since ahead finally hit problem run` identify. college member remember ",
                "color sex baby house five hear result` really any situation talk himself back somebody special media first report style spring board after likely seat agency point purpose apply financial because seven because` Mr` Republican ago huge look policy church from history stand ",
                "follow recently product business clearly road him? next everyone cancer lay` member on sure, movement come everyone! method region air, church see quite` shoulder down parent beat quickly authority especially likely follow form seem discussion present ",
                "final cost cultural floor legal Mr! professor? home amount skill follow ",
                "laugh business lie region morning key carry rest inside indicate oil over pattern power region before color summer maintain power region possible method baby, describe century education option peace chair few item stock hear cell message picture anyone follow language authority agreement movement stage foot ",
                "follow heat soldier. enough sexual soldier perform future must degree matter affect fine legal no contain, leg able! off information born article record join role American clearly generation material among new structure power during stuff financial power plan avoid speak figure notice party international? cover crime growth take power program our never cost team ",
                "education power show child particular! else! television management ground standard cause receive suddenly smile scene along consumer number make perform follow serve power home common, enough hundred employee character region option policy pretty professional power tend region` image? step fall different law huge state history like agency home! exist pass everyone legal "
        };*/

        List<String> arr = getTopToys(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        for (String s : arr) {
            System.out.print(s + " ");
        }
    }

    public static List<String> getTopToys(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {
        Map<String, Integer> countMap = new HashMap<>();
        Set<String> competitorsSet = new HashSet<>(Arrays.asList(competitors));
        for (int i = 0; i < numReviews; i++) {
            /*String review = reviews[i];
            String[] arr = review.split(" ");*/

            String review = reviews[i];
//            String[] arr = review.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
            String[] arr = review.replaceAll("/[^a-zA-Z ]/g", "").toLowerCase().split("\\s+");

            Set<String> alreadyProcessedInSameLine = new HashSet<>();
            for (int j = 0; j < arr.length; j++) {
                String word = arr[j].toLowerCase().replaceAll("\\W", "");
                if (competitorsSet.contains(word) && !alreadyProcessedInSameLine.contains(word)) {
                    countMap.put(word, countMap.getOrDefault(word, 0) + 1);
                    alreadyProcessedInSameLine.add(word);
                }
            }
        }

        // DONT USE PRIORITY QUEUE FOR SORTING
        // PRIORITYQUEUE MAINTAINS ONLY THE TOP ELEMENT IN QUEUE : SORTING ORDER NOT NECESSARILY MAINTAINED IN THE REST OF THE ELEMENTS
        List<String> ls = new ArrayList<>(countMap.keySet());
        Collections.sort(ls, (a, b) -> {
            if (countMap.get(a).equals(countMap.get(b))) {
                return a.compareTo(b);
            } else {
                return countMap.get(b) - countMap.get(a);
            }
        });

        List<String> res = ls.subList(0, topNCompetitors);
        // String[] arr = res.toArray(new String[res.size()]);
        return res;
    }
}
