package company.walmart2;

import java.util.*;

public class Rough {

    public static void main(String[] args) {
        String str = "jatin";

        StringBuffer sb = new StringBuffer(str);
        sb.setCharAt(2, 'i');

        System.out.println(sb.toString());
    }
}
