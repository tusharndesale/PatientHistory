package com.perennial.pht.utilities;

public class CommonUtility {

    public static boolean isValidMobileNo(long mobileNo)
    {
        String phoneNumber = String.valueOf(mobileNo);
        String regex = "(0/91)?[7-9][0-9]{9}";
        boolean result = phoneNumber.matches(regex);
        return result;
    }
}
