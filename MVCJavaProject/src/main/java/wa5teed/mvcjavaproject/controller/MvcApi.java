package wa5teed.mvcjavaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wa5teed.mvcjavaproject.model.Task;
import wa5teed.mvcjavaproject.model.TaskList;
import wa5teed.mvcjavaproject.repository.TaskListRepository;
import wa5teed.mvcjavaproject.repository.TaskRepository;

@Controller
public class MvcApi {
    private final TaskListRepository taskListRepository;
    private final TaskRepository taskRepository;

    public MvcApi(
            TaskListRepository taskListRepository,
            TaskRepository taskRepository
    ) {
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String getTaskLists(ModelMap modelMap) {
        modelMap.addAttribute("taskLists", taskListRepository.findAll());
        modelMap.addAttribute("taskList", new TaskList());
        return "index";
    }

    @GetMapping("/get-task-list")
    public String getTaskList(@RequestParam Long taskListId, ModelMap modelMap) {
        supportModel(modelMap, taskListId);
        return "tasks";
    }

    @PostMapping("/add-task-list")
    public String addTaskList(@ModelAttribute("taskList") TaskList taskList) {
        taskListRepository.save(taskList);
        return "redirect:/";
    }

    @PostMapping("/delete-task-list")
    public String deleteTask(@RequestParam Long taskListId) {
        taskRepository.deleteByListId(taskListId);
        taskListRepository.deleteById(taskListId);
        return "redirect:/";
    }

    @PostMapping("/add-task")
    public String addTask(
            @ModelAttribute("taskListId") Long taskListId,
            @ModelAttribute("task") Task task,
            ModelMap modelMap
    ) {
        task.setListId(taskListId);
        taskRepository.save(task);
        supportModel(modelMap, taskListId);
        return "tasks";
    }

    @PostMapping("/mark-task")
    public String markTask(
            @RequestParam Long taskId,
            @ModelAttribute("taskListId") Long taskListId,
            ModelMap modelMap
    ) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
        supportModel(modelMap, taskListId);
        return "tasks";
    }

    private void supportModel(ModelMap modelMap, Long taskListId) {
        modelMap.addAttribute("task", new Task());
        modelMap.addAttribute("tasks", taskRepository.findByListId(taskListId));
        modelMap.addAttribute("taskListId", taskListId);
    }
}
