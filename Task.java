import types.TaskPriority;
import types.TaskType;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Task {
    private static int taskCounter = 0;
    private int taskNumber;
    private TaskType taskType;
    private int taskDuration;
    private TaskPriority taskPriority;
    private Calendar date;
    private int weekN;
    private boolean isOvertimeTask;


    public Task(int taskNumber, TaskType taskType, int taskDuration, TaskPriority taskPriority, Calendar date, int weekN) {
        this.taskNumber = taskNumber;
        this.taskType = taskType;
        this.taskDuration = taskDuration;
        this.taskPriority = taskPriority;
        this.date = date;
        this.weekN = weekN;
        isOvertimeTask = false;
    }

    public Task(Calendar date) {
        this.date = date;
        taskNumber = ++taskCounter;
        taskPriority = generateTaskPriority();
        taskDuration = new Random().nextInt(1) + 1;
        taskType = generateTaskType();
        this.weekN = date.get(Calendar.WEEK_OF_YEAR);
        isOvertimeTask = false;
    }

    public Task(Calendar date, TaskType taskType) {
        this.date = date;
        taskNumber = ++taskCounter;
        taskPriority = generateTaskPriority();
        taskDuration = new Random().nextInt(1) + 1;
        this.taskType = taskType;
        this.weekN = date.get(Calendar.WEEK_OF_YEAR);
        isOvertimeTask = false;
    }

    public void setOvertimeTask(boolean isOvertimeTask) {
        this.isOvertimeTask = isOvertimeTask;
    }

    boolean isOvertimeTask() {
        return isOvertimeTask;
    }

    TaskType getTaskType() {
        return taskType;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    private TaskPriority generateTaskPriority() {
        int taskPriorityIndex = new Random().nextInt(5);
        switch (taskPriorityIndex) {
            case 1:
                return TaskPriority.NormalPriority;
            case 2:
                return TaskPriority.HighPriority;
        }
        return TaskPriority.LowPriority;
    }

    private TaskType generateTaskType() {
        int taskTypeIndex = new Random().nextInt(7);
        switch (taskTypeIndex) {
            case 1:
                return TaskType.Coding;
            case 2:
                return TaskType.Cleaning;
            case 3:
                return TaskType.MakeReporting;
            case 4:
                return TaskType.SaleServises;
            case 5:
                return TaskType.MakingDesign;
        }
        return TaskType.Testing;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getWeekN() {
        return weekN;
    }

    public Calendar getDate() {
        return date;


    }
}
