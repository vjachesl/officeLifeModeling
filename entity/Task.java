package entity;

import service.TaskService;
import types.TaskPriority;
import types.TaskStatus;
import types.TaskType;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Viacheslav on 17.03.16.
 * Class modelating the entity.Task entity.
 * it incapsulate all info about the task for employee.
 *
 */
public class Task {
    /**
     * The class value is used for storing last task number.
     */
    private static int taskCounter = 0;

    /**
     * The value is used for unique task number.
     */
    private int taskNumber;

    /** The value is used for storing task type as an instance Enum TaskType. */
    private TaskType taskType;

    /**
     * The value is used for storing task type as an instance Enum TaskStatus.
     */
    private TaskStatus taskStatus;

    /** The value is used for task duration in full hours storing*/
    private int taskDuration;

    /** The value is used for task priority as  an instanceEnum TaskPriority*/
    private TaskPriority taskPriority;

    /** The value is used for task date/time storing. It putted during new task generating only*/
    private Calendar date;

    /** The value is used for task week number in current month storing.
     * It putted during new task generating only*/
    private int weekN;

    /** The value is used for indicating the overtimed task with additional payment.*/
    private boolean isOvertimeTask;


    /**Constructor puts just Date into the new task. All additional parameters are generated insude.
     **/
    public Task(Calendar date) {
        this.date = date;
        taskNumber = ++taskCounter;
        taskPriority = TaskService.generateTaskPriority();
        taskDuration = new Random().nextInt(1) + 1;
        taskStatus = TaskStatus.New;
        taskType = TaskService.generateTaskType();
        this.weekN = date.get(Calendar.WEEK_OF_YEAR);
        isOvertimeTask = false;
    }

    /** Overloaded Constructor - uses when neet to generate
     * specific task in specific time(like cleaning at the morning).**/
    public Task(Calendar date, TaskType taskType) {
        this(date);
        this.taskType = taskType;
    }

    /**
     * Set of getters for parameters *
     */
    public TaskType getTaskType() {
        return taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * Setter for task status changing *
     */
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getWeekN() {
        return weekN;
    }

    public boolean isOvertimeTask() {
        return isOvertimeTask;
    }

    /**
     * Setter for task overtime flag putted *
     */
    public void setOvertimeTask(boolean isOvertimeTask) {
        this.isOvertimeTask = isOvertimeTask;
    }

    public Calendar getDate() {
        return date;
    }
}
