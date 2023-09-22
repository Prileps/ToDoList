/**
 * Класс, реализующий defaultController.
 * @autor Егор Прилепский
 * @version 1.0
 */
package main;

import main.model.Task;
import main.model.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Строка, выводимая после всех задач в списке
     */
    @Value("${createdString}")
    private String createdString;

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Метод, возвращающий все задачи
     * @param model - переменная класса, которые позволяет хранитьпеременные, которые можно передать в шаблон
     * @return - все задачи
     */
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        model.addAttribute("tasksCount", tasks.size());
        model.addAttribute("tasks", tasks);
        model.addAttribute("createdString", createdString);
        LOGGER.info("Пользователь вывел список всех задач");
        return "index";
    }
}
