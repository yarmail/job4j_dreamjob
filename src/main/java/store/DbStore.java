package store;

import model.Candidate;
import model.Post;
import model.User;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Примечания
 * сразу после создания класса Idea ругается на таблицу post
 * вероятно надо сначала сделать так, чтобы она видела таблицу
 * После запуска update_001.sql с созданием таблица ошибка ушла
 *
 * Пул содержит активные соединение с базой.
 * Когда вызывается метод Connection.close()
 * соединение не закрывается а возвращается обратно в пул.
 * Пул активируется в кострукторе
 * pool.setDriverClassName ...
 * pool.setUrl...
 * pool.setUsername ...
 *
 * Создание вакансии.  private Post create
 * Здесь выполняется обычный sql запрос.
 *
 * (есть тесты)
 */
public class DbStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();
    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class.getName());

    private DbStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        DbStore.class.getClassLoader()
                                .getResourceAsStream("db.properties")
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new DbStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    /**
     * Прописываем все операции над Post
     */
    private Post createPost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO post(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return post;
    }

    private void updatePost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE post SET "
                     + "name=(?) WHERE id=(?)")) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void savePost(Post post) {
        if (post.getId() == 0) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

    public void deletePost(int id) {
        try (Connection cn = pool.getConnection(); PreparedStatement ps = cn.prepareStatement("DELETE FROM post WHERE id = (?)")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Post findByIdPost(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public Post findByNamePost(String name) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE name = ?")
        ) {
            ps.setString(1, name);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return posts;
    }

    /**
     * Прописываем все операции над Candidate
     */
    private Candidate createCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO candidate(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return candidate;
    }

    private void updateCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE candidate SET "
                     + "name=(?) WHERE id=(?)")) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            createCandidate(candidate);
        } else {
            updateCandidate(candidate);
        }
    }

    public void deleteCandidate(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM candidate WHERE id = (?)")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Candidate findByIdCandidate(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate WHERE id = (?)")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public Candidate findByNameCandidate(String name) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate WHERE name = (?)")
        ) {
            ps.setString(1, name);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return candidates;
    }

    /**
     * Прописываем все операции над User
     */
    private User createUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "INSERT INTO users(name, email, password) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(1, user.getEmail());
            ps.setString(1, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    private void updateUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "UPDATE users SET name = ?, email = ?, password = ?"
                                     + "WHERE id = ? ")
        ) {
            ps.setString(1, user.getName());
            ps.setString(1, user.getEmail());
            ps.setString(1, user.getPassword());
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void saveUser(User user) {
        if (user.getId() == 0) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    public void deleteUser(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM users WHERE id = ? ")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User findByIdUser(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public Collection<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users")
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    public User findByEmailUser(String email) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users WHERE email = ?")
        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}