package utils;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isEmpty(Collection collection){
        return ObjectUtil.isEmpty(collection) || collection.isEmpty();
    }
    public static void addIfNotNull(Collection collection, Object item){
        if (!ObjectUtil.isEmpty(item)) collection.add(item);
    }
}
