package service;

import types.TaskPriority;
import types.TaskType;

import java.util.Random;

/**
 * Class incapsulated statics service methods, which used for task random generating.
 */
public class TaskService {

    /**
     * Method for TaskType generating.
     * Generates task types as random value.
     *
     * @return TaskType - as an Instance of Enum
     */
    public static TaskType generateTaskType() {
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

    /**
     * Method for TaskPriority generating.
     * Generates task priority as random value.
     *
     * @return TaskPriority - as an Instance of Enum
     */
    public static TaskPriority generateTaskPriority() {
        int taskPriorityIndex = new Random().nextInt(5);
        switch (taskPriorityIndex) {
            case 1:
                return TaskPriority.NormalPriority;
            case 2:
                return TaskPriority.HighPriority;
        }
        return TaskPriority.LowPriority;
    }

}
