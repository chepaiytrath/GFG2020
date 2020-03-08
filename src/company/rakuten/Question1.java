package company.rakuten;

import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer N = Integer.parseInt(sc.nextLine());

        Validator v = new Validator();
        for (int i = 0; i < N; i++) {
            try {
                v.isValid(sc.nextLine());
            } catch (AlphaNumericException ane) {
                System.out.println(ane.getMessage());
            } catch (StringException se) {
                System.out.println(se.getMessage());
            }
        }
    }

    static class AlphaNumericException extends Exception {
        AlphaNumericException(String message) {
            super(message);
        }
    }

    static class StringException extends Exception {
        StringException(String message) {
            super(message);
        }
    }

    static class Validator {
        Validator() {

        }

        public boolean isValid(String str) throws AlphaNumericException, StringException {
            boolean isNumber = isNumber(str);
            if (!isNumber) {
                boolean isString = isString(str);
                if (isString) {
                    throw new StringException("Its a String");
                } else {
                    throw new AlphaNumericException("Its an AlphaNumeric");
                }
            } else {
                System.out.println("Valid Format");
                return true;
            }
        }

        public boolean isString(String str) {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) == true) {
                    return false;
                }
            }
            return true;
        }

        public boolean isNumber(String str) {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) == false) {
                    return false;
                }
            }
            return true;
        }
    }
}

