package algorithm.mathematical;

public class ApowBmodM {
      
    // utility function to calculate a%m 
    static int aModM(String s, int mod) 
    { 
        int number = 0; 
        for (int i = 0; i < s.length(); i++) 
        { 
              
            // (s[i]-'0') gives the digit 
            // value and form the number 
            number = (number * 10 ); 
            int x = Character.getNumericValue(s.charAt(i)); 
            number = number + x; 
            number %= mod; 
        } 
          
        return number; 
    } 
  
    // Returns find (a^b) % m 
    static int ApowBmodM(String a, int b, int m) 
    { 
          
        // Find a%m 
        int ans = aModM(a, m); 
        int mul = ans;
      
        // now multiply ans by b-1 times  
        // and take mod with m 
        for (int i = 1; i < b; i++) 
            ans = (ans * mul) % m; 
      
        return ans; 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
        String a = "987";
        int b = 2, m = 4;
        System.out.println(ApowBmodM(a, b, m)); 
    }
} 