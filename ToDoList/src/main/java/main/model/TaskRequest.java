/**
 * Класс для частичного изменения полей задач со свойствами <b>done</b>, <b>title</b> и <b>description</b>.
 * @autor Егор Прилепский
 * @version 1.0
 */
package main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskRequest {

    /** Поле состояния задачи*/
    @JsonProperty(value = "isDone")
    private Boolean done;

    /** Поле названия задачи*/
    private String title;

    /** Поле описания задачи*/
    private String description;

    /**
     * метод получения значения поля
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
     * метод получения значения поля
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
     * метод получения значения поля
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
