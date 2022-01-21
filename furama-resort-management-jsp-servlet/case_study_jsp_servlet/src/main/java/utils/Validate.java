package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean checkPhoneNumber(String phoneNumber) {
        String regex = "(^09[01]\\d{7}$)||(\\(^84\\)\\+9[01]\\d{7}$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static boolean checkPersonalID(String personalID) {
        String regex = "(^\\d{9}$)||(^\\d{12}$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(personalID);

        return matcher.matches();
    }

    public static boolean checkEmail(String email){
        String regex = "[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();

    }

    public static boolean checkIDCustomer(String idCustomer) {
        String regex = "^KH-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idCustomer);

        return matcher.matches();
    }

    public static boolean checkIDFacility(String idFacility) {
        String regex = "^DV-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idFacility);

        return matcher.matches();
    }
}
