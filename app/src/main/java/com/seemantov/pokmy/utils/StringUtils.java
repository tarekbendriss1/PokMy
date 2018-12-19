package com.seemantov.pokmy.utils;

import android.text.TextUtils;

import java.net.URLDecoder;

public class StringUtils {

    public static final String UTF8 = "UTF-8";

    public static boolean isAlphabetic(String aString){
        int charCount=0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        if(aString.length() == 0) return false;//zero length string ain't alpha
        for(int i=0;i<aString.length();i++){
            for(int j=0;j<alphabet.length();j++){
                if(aString.substring(i,i+1).equals(alphabet.substring(j,j+1))
                        || aString.substring(i,i+1).equals(alphabet.substring(j,j+1).toLowerCase()))
                    charCount++;
            }
            if(charCount != (i+1)){
                System.out.println("\n**Invalid input! Enter alpha values**\n");
                return false;
            }
        }
        return true;
    }

    public static Boolean isEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf("@")!=email.length() && email.indexOf("@")!=0
                && email.indexOf("@")!=email.length()-1  && email.indexOf("@")!=email.length()-2  && email.indexOf(".")!=0
                && email.indexOf(".")!=email.length() && email.indexOf(".")!=email.length()-1
                && email.indexOf("@")!=email.length()-3 && email.indexOf(".")!=email.length()-2;
    }

    public static String decodeURL(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            try {
                decoded = URLDecoder.decode(str, UTF8);
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    public static String decodeUTF8(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            byte[] src = str.getBytes();
            try {
                decoded = decodeURL(new String(src, UTF8));
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    public static String upperFirstChar(String str) {
        return isNotEmpty(str) ? str.substring(0, 1).toUpperCase() + str.substring(1) : str;
    }

    public static String upperCase(String str) {
        return isNotEmpty(str) ? str.toUpperCase() : str;
    }

    public static String lowerCase(String str) {
        return isNotEmpty(str) ? str.toLowerCase() : str;
    }

    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static String trim(String str) {
        return (isNotEmpty(str)) ? str.trim().replaceAll("\\s+", "") : str;
    }

    public static String trimAndReplace(String str) {
        return (isNotEmpty(str)) ? trim(str).replace("/", "") : str;
    }
}
