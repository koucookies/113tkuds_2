
import java.util.*;

class CacheLevel {

    int capacity;
    int accessCost;
    Map<Integer, String> data = new HashMap<>();
    Map<Integer, Integer> accessFrequency = new HashMap<>();
    Map<Integer, Long> lastAccessTime = new HashMap<>();

    public CacheLevel(int capacity, int accessCost) {
        this.capacity = capacity;
        this.accessCost = accessCost;
    }

    boolean contains(int key) {
        return data.containsKey(key);
    }

    void put(int key, String value) {
        if (data.size() >= capacity && !data.containsKey(key)) {
            evict();
        }
        data.put(key, value);
        accessFrequency.putIfAbsent(key, 0);
        updateAccess(key);
    }

    String get(int key) {
        if (!data.containsKey(key)) {
            return null;
        }
        updateAccess(key);
        return data.get(key);
    }

    void updateAccess(int key) {
        accessFrequency.put(key, accessFrequency.getOrDefault(key, 0) + 1);
        lastAccessTime.put(key, System.nanoTime());
    }

    void evict() {
        int evictKey = data.keySet().stream().min((k1, k2) -> {
            int cmp = Integer.compare(accessFrequency.get(k1), accessFrequency.get(k2));
            if (cmp == 0) {
                return Long.compare(lastAccessTime.get(k1), lastAccessTime.get(k2));
            }
            return cmp;
        }).orElseThrow();
        data.remove(evictKey);
        accessFrequency.remove(evictKey);
        lastAccessTime.remove(evictKey);
    }

    Set<Integer> keySet() {
        return data.keySet();
    }

    String remove(int key) {
        accessFrequency.remove(key);
        lastAccessTime.remove(key);
        return data.remove(key);
    }
}

public class MultiLevelCacheSystem {

    private final CacheLevel[] levels;

    public MultiLevelCacheSystem() {
        levels = new CacheLevel[]{
            new CacheLevel(2, 1),
            new CacheLevel(5, 3),
            new CacheLevel(10, 10)
        };
    }

    public String get(int key) {
        for (int i = 0; i < levels.length; i++) {
            String value = levels[i].get(key);
            if (value != null) {
                promoteToUpperLevel(i, key, value);
                return value;
            }
        }
        return null;
    }

    public void put(int key, String value) {
        int levelFound = findLevelContaining(key);
        if (levelFound != -1) {
            levels[levelFound].put(key, value);
            promoteToUpperLevel(levelFound, key, value);
            return;
        }

        for (int i = 0; i < levels.length; i++) {
            if (levels[i].data.size() < levels[i].capacity) {
                levels[i].put(key, value);
                return;
            }
        }

        levels[levels.length - 1].put(key, value);
    }

    private int findLevelContaining(int key) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].contains(key)) {
                return i;
            }
        }
        return -1;
    }

    private void promoteToUpperLevel(int fromLevel, int key, String value) {
        for (int i = fromLevel - 1; i >= 0; i--) {
            if (!levels[i].contains(key)) {
                levels[fromLevel].remove(key);
                levels[i].put(key, value);
                break;
            }
        }
    }

    public void printCache() {
        for (int i = 0; i < levels.length; i++) {
            System.out.println("L" + (i + 1) + ": " + levels[i].data);
        }
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache();

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printCache();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCache();
    }
}
