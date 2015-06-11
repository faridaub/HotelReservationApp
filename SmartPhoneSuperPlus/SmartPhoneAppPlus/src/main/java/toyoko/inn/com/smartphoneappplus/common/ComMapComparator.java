package toyoko.inn.com.smartphoneappplus.common;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Farid on 2015/02/23.
 */
public class ComMapComparator implements Comparator<Map<String, String>> {
    private final String key;

    public ComMapComparator(String key) {
        this.key = key;
    }

    public int compare(Map<String, String> first,Map<String, String> second) {
        String firstValue = first.get(key);
        String secondValue = second.get(key);
        return firstValue.compareTo(secondValue);
    }
}
