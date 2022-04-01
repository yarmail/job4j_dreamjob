package store;

import model.City;
import model.Post;
import model.Candidate;
import model.User;

import java.util.Collection;

public interface Store {

    void savePost(Post post);

    void deletePost(int id);

    Post findByIdPost(int id);

    Post findByNamePost(String name);

    Collection<Post> findAllPosts();

    Collection<Post> findLastPosts();
/* --------------------------------- */

    void saveCandidate(Candidate candidate);

    void deleteCandidate(int id);

    Candidate findByIdCandidate(int id);

    Candidate findByNameCandidate(String name);

    Collection<Candidate> findAllCandidates();

    Collection<Candidate> findLastCandidates();
/* --------------------------------- */

    void saveUser(User user);

    void deleteUser(int id);

    Collection<User> findAllUser();

    User findByIdUser(int id);

    User findByEmailUser(String email);
/*------------------------------------- */

    public City findCityById(int id);

    public Collection<City> findAllCities();
}