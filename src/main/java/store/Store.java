package store;

import model.Post;
import model.Candidate;
import model.User;

import java.util.Collection;

public interface Store {

    void savePost(Post post);

    void deletePost(int id);

    Post findByIdPost(int id);

    Collection<Post> findAllPosts();
/* --------------------------------- */

    void saveCandidate(Candidate candidate);

    void deleteCandidate(int id);

    Candidate findByIdCandidate(int id);

    Collection<Candidate> findAllCandidates();
/* --------------------------------- */

    void saveUser(User user);

    void deleteUser(int id);

    Collection<User> findAllUser();

    User findByIdUser(int id);

    User findByEmailUser(String email);
}