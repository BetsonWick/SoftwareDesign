import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class LRUCacheTest {
    @Test
    void randomizedTest() {
        Random random = new Random(1);

        for (int capacity = 1; capacity < 20; capacity++) {
            LRUCacheImpl<Integer, Integer> lruCache =
                    new LRUCacheImpl<>(capacity);
            LRUCacheByLinkedHashMap<Integer, Integer>
                    lruCacheByLinkedHashMap =
                    new LRUCacheByLinkedHashMap<>(capacity);
            for (int j = 0; j < 10000; j++) {
                if (random.nextBoolean()) {
                    int key = random.nextInt(-10, 10);
                    int value = random.nextInt(-10, 10);
                    lruCache.put(key, value);
                    lruCacheByLinkedHashMap.put(key, value);
                } else {
                    int key = random.nextInt(-10, 10);
                    Assertions.assertEquals(
                            lruCache.get(key),
                            lruCacheByLinkedHashMap.get(key)
                    );
                }
            }
        }
    }

    private static class LRUCacheByLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;

        private LRUCacheByLinkedHashMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
