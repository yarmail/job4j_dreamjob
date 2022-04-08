package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Поступили новые вводные
 * 5. Реализация интерфейса [#2519]
 * Задание.
 * Добавить проверку введенных данных на стороне клиента с помощью js.
 * Все поля ввода должны быть заполнены.(о чем это? вроде как уже делали это)
 *
 * Добавить в модель Candidate поле cityId. (+)
 *
 * В форме создания и редактирования, динамически через ajax загружать список городов. (-)
 * Список городов должен храниться в отдельной таблице.(+)
 *
 * В одном из заданий на индексную страницу мы добавили вывод таблицы
 * со списком кандидатов и вакансий за последний день,
 * однако данный функционал не был реализован.
 * Ваша задача будет реализовать этот функционал - для этого:
 *
 * в PsqlStore добавить запросы, которые будут выбирать данные
 * добавленные за последние сутки, в запросе будете использовать
 * оператор between, для выполнения математических вычислений
 * для даты можно использовать операторы согласно документации (-)
 * postgrespro.ru/docs/postgresql/12/functions-datetime
 *
 * в запрос добавить полученный список и вывести его на странице через jstl.(-)
 *
 * Заполните README.md репозитория. В нем должны быть картинки интерфейса.(+)
 *
 */
public class Candidate {
    private int id;
    private String name;
    private int cityId;
    private LocalDateTime created;

    public Candidate(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getCreated() {
        return created;
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
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && cityId == candidate.cityId
                && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityId);
    }
}