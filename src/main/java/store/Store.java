package store;

import model.Post;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Хранилище вакансий
 * Синглтон
 */
public class Store {
    private static final Store INST = new Store();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "desc", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "desc", new Date()));
        posts.put(3, new Post(3, "Middle Java Job", "desc", new Date()));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}