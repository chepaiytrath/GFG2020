package company.google.oa;

public class BullsAndCows {
    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        System.out.println(findBullsAndCows(secret, guess));
    }

    private static String findBullsAndCows(String secret, String guess) {
        //Directly count bull for each matching digit
        //Count cow only for ones which are not matching
        int bull = 0;
        int cow = 0;


        int[] freqArrSecret = new int[10];
        int[] freqArrGuess = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char sec = secret.charAt(i);
            char gus = guess.charAt(i);
            if (sec == gus) {
                bull++;
            } else {
                freqArrSecret[sec - '0']++;
                freqArrGuess[gus - '0']++;
            }
        }


        // COWS CALCULATION: Just count the ones which didnt match
        // If digit occurred more times in secret than in guess, then that means all guessed digits will be considered as cows : all guesses are cows
        // If digit occurred more times in guess than in secret, only the number of digits in secret will be considered as cows : out of all guesses, the ones in secret are cows
        for (int i = 0; i < 10; i++) {
            cow += Math.min(freqArrSecret[i], freqArrGuess[i]);
        }
        return bull + "A" + cow + "B";
    }
}
