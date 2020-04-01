package com.switchfully.api.security.validation;

import java.io.IOException;

public class Validation {
    public static void isValidEmailAddress(String email) throws IOException {
        ExternalEmailValidation.sendGET(email);
    }


//    public static boolean isValidInss(String inss) {
//        String ePattern = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}-[0-9]{3}\\.[0-9]{2}$";
//        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//        java.util.regex.Matcher m = p.matcher(inss);
//        if (!m.matches()) {
//            throw new InssNotValidException(inss);
//        }
//        return true;
//    }
}
