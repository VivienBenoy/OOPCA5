package com.dkit.oopca5.client;

/* This class should contain static methods to verify input in the application
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker
{
    public boolean verifyCAONumber(int caoNumber)
    {
        String regEx = "^[0-9]{8}$";
        Pattern patternCAONumber= Pattern.compile(regEx);
        Matcher matcherCAONumber=patternCAONumber.matcher(String.valueOf(caoNumber));
        if(matcherCAONumber.matches())
        {
            return true;
        }
        return false;
    }
    public boolean verifyDateOfBirth(String dateOfBirth){
        String regExDateOfBirth="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
        Pattern patternDateOfBirth=Pattern.compile(regExDateOfBirth);
        Matcher matcherDateOfBirth= patternDateOfBirth.matcher(dateOfBirth);
        if(matcherDateOfBirth.matches())
        {
            return true;
        }
        return false;
    }
    public boolean verifyPasswrd(String password){
        String regExPassword=".{8,}";
        Pattern patternPassword=Pattern.compile(regExPassword);
        Matcher matcherPassword=patternPassword.matcher(password);
        if(matcherPassword.matches()){
            return true;
        }
        return false;
    }
}
