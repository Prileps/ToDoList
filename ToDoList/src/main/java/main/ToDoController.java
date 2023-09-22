/**
 * Класс, реализующий контроллер ToDoController.
 * @autor Егор Прилепский
 * @version 1.0
 */
package main;

import main.model.Task;
import main.model.TaskRepository;
import main.model.TaskRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Метод добавления новой задачи вв конец списка
     * @param task - новая задача, передаваемая пользователем
     * @return нувую добавленную задачу
     */
    @PostMapping(value = "/toDo/")
    public int addTask(@RequestBody Task task) {
        task.setCreationTime(LocalDateTime.now());
        Task newTask = taskRepository.save(task);
        LOGGER.info("Ползователь добавил новую задачу под номером " + newTask.getId());
        return newTask.getId();
    }

    /**
     * метод получения всех задач
     * @return все имеющиеся в списке задачи
     */
    @GetMapping("/toDo/")
    public List<Task> showAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        LOGGER.info("Пользователь запросил список всех задач");
        return tasks;
    }

    /**
     * Метод для получения конкретной задачи
     * @param id - идентификатор данной задачи
     * @return задачу с данным идентификатором
     */
    @GetMapping("/toDo/{id}")
    public ResponseEntity getTask(@PathVariable Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!taskRepository.existsById(id)) {
            LOGGER.error("Пользователь запросил несуществующее дело");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            LOGGER.info("Пользователь запросил задачу под номером " + id);
            return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
        }
    }

    /**
     * метод, реализующий удаление всех задач
     * @return код ответа
     */
    @DeleteMapping("/toDo/")
    public ResponseEntity deleteAllTasks() {
        taskRepository.deleteAll();
        LOGGER.info("Пользователь удалил все задачи");
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * метод удаления одной задачи по ее идентификатору
     * @param id - идентификатор задачи, которую необходимо удалить
     * @return - код ответа
     */
    @DeleteMapping("/toDo/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        if (!taskRepository.existsById(id)) {
            LOGGER.error("Пользователь попытался удалить несуществующее дело");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            taskRepository.deleteById(id);
            LOGGER.info("Пользователь удалил задачу под номером " + id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * метод частичного изменения полей в задаче
     * @param id - идентификатор задачи
     * @param taskRequest - параметры, которые необходимо изменить
     * @see TaskRequest
     * @return код ответа
     */
    @PatchMapping("/toDo/{id}")
    public ResponseEntity changeTask(@PathVariable Integer id, @RequestBody TaskRequest taskRequest) {
        if (!taskRepository.existsById(id)) {
            LOGGER.error("Пользователь попытлася изменить несуществующее дело");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Optional<Task> optional = taskRepository.findById(id);
            Task task = optional.get();
            if (taskRequest.isDone() != null){
                LOGGER.info("Пользователь изменил параметр isDone в задаче под номером " + id);
                task.setDone(taskRequest.isDone());
            }
            if (taskRequest.getDescription() != null){
                LOGGER.info("Пользователь изменил параметр description в задаче под номером " + id);
                task.setDescription(taskRequest.getDescription());
            }
            if (taskRequest.getTitle() != null){
                LOGGER.info("Пользователь изменил параметр title в задаче под номером " + id);
                task.setTitle(taskRequest.getTitle());
            }
            taskRepository.save(task);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}