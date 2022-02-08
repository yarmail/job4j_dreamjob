package store;

import model.Candidate;
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
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "desc", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "desc", new Date()));
        posts.put(3, new Post(3, "Senior Java Job", "desc", new Date()));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(1, "Middle Java"));
        candidates.put(3, new Candidate(1, "Senior Java"));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
}