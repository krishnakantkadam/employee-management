package core;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionLRUCache {
    private final int maxSize;
    private final Map<String, String> sessionCache;

    public SessionLRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.sessionCache = new LinkedHashMap<String, String>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > maxSize;
            }
        };
    }

    public String getSession(String sessionId) {
        String value = sessionCache.get(sessionId);
        System.out.println("\n====== GET Operation: sessionId=" + sessionId + " ======");
        printCache();
        return value;
    }

    public void putSession(String sessionId, String sessionData) {
        sessionCache.put(sessionId, sessionData);
        System.out.println("\n====== PUT Operation: sessionId=" + sessionId + ", data=" + sessionData + " ======");
        printCache();
    }

    public void removeSession(String sessionId) {
        sessionCache.remove(sessionId);
        System.out.println("\n====== REMOVE Operation: sessionId=" + sessionId + " ======");
        printCache();
    }

    private void printCache() {
        System.out.println("+-----------------+-----------------+");
        System.out.println("| Cache Status: " + sessionCache.size() + "/" + maxSize + " entries");
        System.out.println("+-----------------+-----------------+");
        System.out.println("| SessionId       | Data           |");
        System.out.println("+-----------------+-----------------+");
        sessionCache.forEach((key, value) ->
            System.out.printf("| %-14s | %-14s |%n", key, value)
        );
        System.out.println("+-----------------+-----------------+");
    }

    public static void main(String[] args) {
        SessionLRUCache lruCache = new SessionLRUCache(3);
        System.out.println("=== Starting LRU Cache Demo (Max Size: 3) ===\n");

        lruCache.putSession("session1", "User1");
        lruCache.putSession("session2", "User2");
        lruCache.putSession("session3", "User3");
        lruCache.getSession("session1");
        lruCache.putSession("session4", "User4");
        lruCache.getSession("session1");
        lruCache.getSession("session2");
        lruCache.getSession("session3");
        lruCache.getSession("session4");
    }
}