/**
 * Класс задач со свойствами <b>id</b>, <b>creationTime</b>, <b>done</b>, <b>title</b> и <b>description</b>.
 * @autor Егор Прилепский
 * @version 1.0
 */
package main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    /** Поле идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /** Поле время создания*/
    private LocalDateTime creationTime;

    /** Поле состояния задачи*/
    @JsonProperty(value = "isDone")
    @Column(name = "is_done")
    private Boolean done;

    /** Поле названия задачи*/
    private String title;

    /** Поле описания задачи*/
    private String description;

    /**
     * метод получения значения поля {@link Task#id}
     * @return возвращает идентификатор задачи
     */
    public int getId() {
        return id;
    }

    /**
     * метод для установки значений полей
     * @param id - идентификатор задачи
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * метод получения значения поля {@link Task#creationTime}
     * @return возвращает время создания задачи
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * метод для установки значений полей
     * @param creationTime - время создания задачи
     */
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * метод получения значения поля {@link Task#done}
     * @return возвращает состояние задачи задачи
     */
    public Boolean isDone() {
        return done;
    }

    /**
     * метод для установки значений полей
     * @param done - состояние задачи
     */
    public void setDone(Boolean done) {
        this.done = done;
    }

    /**
     * метод получения значения поля {@link Task#title}
     * @return возвращает название задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * метод для установки значений полей
     * @param title - название задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * метод получения значения поля {@link Task#description}
     * @return возвращает описание задачи
     */
    public String getDescription() {
        return description;
    }

    /**
     * метод для установки значений полей
     * @param description - описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
