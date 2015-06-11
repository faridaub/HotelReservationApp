package toyoko.inn.com.smartphoneappplus.adapter;

import android.util.Pair;

/**
 * Created by Farid on 2015/02/16.
 */
public class KeyValuePair extends Pair<String,String> {

    public KeyValuePair(String key, String value) {
        super(key, value);
    }

    public String getKey(){
        return super.first;
    }

    public String getValue(){
        return super.second;
    }
}

