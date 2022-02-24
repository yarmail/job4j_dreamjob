package store;

import model.Post;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DbStoreTest {
    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.savePost(post);
        Post postInDb = store.findByIdPost(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }
}