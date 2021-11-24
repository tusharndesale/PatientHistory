package com.perennial.pht.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtility {

    public boolean isValidMobileNo(long mobileNo)
    {
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(String.valueOf(mobileNo));
        return (match.find() && match.group().equals(mobileNo));
    }
}
