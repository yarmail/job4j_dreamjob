package store;
/*
Эта версия файла до того, как Store стал
интерфейсом - сохранено отдельно, для обучения
 */

import model.Candidate;
import model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище вакансий - Синглтон
 *
 * Ключ для генерации ID
 * private static AtomicInteger POST_ID = new AtomicInteger(4);
 */
public class StoreOld {
    private static final StoreOld INST = new StoreOld();
    private static AtomicInteger postId = new AtomicInteger(4);
    private static AtomicInteger candidateId = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private StoreOld() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
        candidates.put(1, new Candidate(1, "Junior Java", 1));
        candidates.put(2, new Candidate(2, "Middle Java", 2));
        candidates.put(3, new Candidate(3, "Senior Java", 3));
    }

    public static StoreOld instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    /**
     * incrementAndGet - возвращает значение после
     * выполнения операции приращения к предыдущему значению
     */
    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(postId.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(candidateId.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }
}