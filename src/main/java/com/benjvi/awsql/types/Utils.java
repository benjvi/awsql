package com.benjvi.awsql.types;

import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created by benjamin on 27/10/2017.
 */
public class Utils {

    public static Object copyProperties(Object i, Class cloneSubType) {
        Gson gson = new Gson();
        String json = gson.toJson(i);
        return gson.fromJson(json, cloneSubType);
    }

    public static Object copyFlatProperties(Object i, Class cloneSubType) {
        try {
            Object obj = cloneSubType.newInstance();
            PropertyUtils.copyProperties(obj, i);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
