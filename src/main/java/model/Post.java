package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Вакансия
 *
 * Примечания
 * Для обозначения даты можно использовать
 * java.time.LocalDateTime
 * this.created = LocalDateTime.now();
 * или например
 * import java.sql.Timestamp;
 * import java.time.Instant;
 * this.created = Timestamp.from(Instant.now());
 *
 * Пробуем округлить время до секунд, чтобы при
 * двойном повторном запросе в тесте
 * Объекты были равны.
 * Если не округлять - объекты получаются разными
 * может быть от округления или по другой причине
 * this.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
 *
 */
public class Post {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created;

    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public Post(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created + '}';
    }
}