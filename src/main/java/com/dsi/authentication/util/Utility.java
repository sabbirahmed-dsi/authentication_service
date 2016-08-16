package com.dsi.authentication.util;

import com.dsi.authentication.exception.CustomException;
import com.dsi.authentication.exception.ErrorContext;
import com.dsi.authentication.exception.ErrorMessage;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created by sabbir on 6/13/16.
 */
public class Utility {

    private static final Logger logger = Logger.getLogger(Utility.class);

    private static final String CHAR = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
    private static final int PASSWORD_LENGTH = 8;

    public static boolean isNullOrEmpty(String s){

        if(s==null ||s.isEmpty() ){
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(List list){

        if(list==null || list.size() == 0 ){
            return true;
        }
        return false;
    }

    public static String generateRandomPassword() {
        Random random = new SecureRandom();

        String pw = "";
        for (int i=0; i<PASSWORD_LENGTH; i++)
        {
            int index = (int)(random.nextDouble()*CHAR.length());
            pw += CHAR.substring(index, index+1);
        }
        return pw;
    }

    public static final String generateRandomString(){
        return UUID.randomUUID().toString();
    }

    public static final Date today() {
        return new Date();
    }

    public static final String validation(JSONObject requestObj, String str) throws CustomException {

        ErrorContext errorContext;
        try {
            if (requestObj.has(str)) {
                return requestObj.getString(str);

            } else {
                errorContext = new ErrorContext(str, null, "Params: '"+ str +"' are missing.");
            }
        } catch (Exception e){
            errorContext = new ErrorContext(str, null, e.getMessage());
        }
        ErrorMessage errorMessage = new ErrorMessage(Constants.AUTHENTICATE_SERVICE_0008,
                Constants.AUTHENTICATE_SERVICE_0008_DESCRIPTION, errorContext);
        throw new CustomException(errorMessage);
    }

    public static final String getFinalToken(String header) {
        String[] tokenPart = header.split("[\\$\\(\\)]");
        return tokenPart[2];
    }

    public static final String getTokenSecretKey(String key){
        byte[] valueDecoded = Base64.getDecoder().decode(key.getBytes());
        return new String(valueDecoded);
    }
}
