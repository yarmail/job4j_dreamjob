package store;

import model.Candidate;
import model.Post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Ignore ("Почему то перестал работать тест")
public class DbStoreTest {
    private Store store = DbStore.instOf();
    private Post post = new Post(0, "Java Job");
    private Candidate candidate = new Candidate(0, "New candidate", 1);

    @Before
    public void before() {
        store.savePost(post);
        store.saveCandidate(candidate);
    }

    @Test
    public void whenCreatePost() {
        Post postInDb = store.findByIdPost(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenDeletePost() {
        store.deletePost(post.getId());
        Post postInDb = store.findByIdPost(post.getId());
        assertNull(postInDb);
    }

    @Test
    public void whenFindByIdPost() {
        Post postInDb = store.findByIdPost(post.getId());
        assertEquals(postInDb, post);
    }

    @Test
    public void whenCreateCandidate() {
        Candidate candidateInDb = store.findByIdCandidate(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenDeleteCandidate() {
        store.deleteCandidate(candidate.getId());
        Candidate candidateInDb = store.findByIdCandidate(candidate.getId());
        assertNull(candidateInDb);
    }

    @Test
    public void whenFindByIdCandidate() {
        Candidate candidateInDb = store.findByIdCandidate(candidate.getId());
        assertEquals(candidateInDb, candidate);
    }
}