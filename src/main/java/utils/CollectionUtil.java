package utils;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isEmpty(Collection collection) {
        return ObjectUtil.isEmpty(collection) || collection.isEmpty();
    }

    public static boolean addIfNotNull(Collection collection, Object item) {
        if (!ObjectUtil.isEmpty(collection) || !ObjectUtil.isEmpty(item)) {
            System.out.println(item + "" + collection);
            collection.add(item);
            return true;
        }
        return false;
    }
}
