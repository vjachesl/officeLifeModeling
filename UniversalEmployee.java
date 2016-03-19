import types.EmployeeType;
import types.PositionsType;
import types.TaskPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 17.03.16.
 */
public class UniversalEmployee implements Employee {
    private static int empCounter = 0;
    private String empName;
    private String empSurName;
    private int empNumber;
    private EmployeeType empType;
    private List<PositionsType> positionsTypeList;
    private List<Task> assignedTasks;
    private int timeLeftToWorkAtDay;
    private int timeLeftToWorkAtMonth;
    private List<Task> completedTasks;


    public UniversalEmployee(EmployeeType empType) {
        empNumber = ++empCounter;
        empName = "empName" + empNumber;
        empSurName = "empSurName" + empNumber;
        timeLeftToWorkAtMonth = 40;
        positionsTypeList = new ArrayList<PositionsType>();
        assignedTasks = new ArrayList<Task>();
    }

    public boolean addPosition(PositionsType positionsType) {
        for (PositionsType p : positionsTypeList) {
            if (p == null || p.isSharedPosition() == true) {
                positionsTypeList.add(positionsType);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSuitableForTask(Task task) {
        if (timeLeftToWorkAtDay > task.getTaskDuration() &&
                timeLeftToWorkAtMonth > task.getTaskDuration() &&
                this.ifEmplHasNeededPosition(task.getTaskType().getPositionsType())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTaskToEmployee(Task task) {
        if (isSuitableForTask(task)) {
            timeLeftToWorkAtDay = timeLeftToWorkAtDay - task.getTaskDuration();
            timeLeftToWorkAtMonth = timeLeftToWorkAtMonth - task.getTaskDuration();
            assignedTasks.add(task);
            return true;
        }
        return false;
    }

    @Override
    public boolean doTheTask(boolean isOverTimeTask) {
        while (assignedTasks.size() > 0) {
            if (!completingTask(TaskPriority.HighPriority, isOverTimeTask)) {
                if (!completingTask(TaskPriority.NormalPriority, isOverTimeTask)) {
                    if (!completingTask(TaskPriority.LowPriority, isOverTimeTask)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public EmployeeType getEmpType() {
        return empType;
    }

    @Override
    public void setDayHours() {
        timeLeftToWorkAtDay = 8;
    }

    @Override
    public String getEmployeeNumberName() {
        return empNumber + "_" + empSurName;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public boolean ifEmplHasNeededPosition(PositionsType positionsType) {
        for (PositionsType p : positionsTypeList) {
            if (p.equals(positionsType)) return true;
        }
        return false;
    }

    private Task findingAllAssignedTaskWithPriority(TaskPriority taskPriority) {
        List<Task> resultList = new ArrayList<>();
        for (Task t : assignedTasks) {
            if (t.getTaskPriority().equals(taskPriority)) resultList.add(t);
        }
        if (resultList.size() == 1) return resultList.get(1);
        else {
            Task taskToReturn = resultList.get(0);
            for (Task t : assignedTasks) {
                if (t.getTaskType().getPositionsType().getPositionRate() >
                        taskToReturn.getTaskType().getPositionsType().getPositionRate()) {
                    taskToReturn = t;
                }
                return taskToReturn;
            }

        }
        return null;
    }

    private boolean completingTask(TaskPriority taskPriority, boolean isOverTimeTask) {
        Task currentTask = findingAllAssignedTaskWithPriority(taskPriority);
        if (currentTask != null) {
            assignedTasks.remove(currentTask);
            currentTask.setOvertimeTask(isOverTimeTask);
            completedTasks.add(currentTask);
            return true;
        }
        return false;
    }

    @Override
    public List<PositionsType> getPositions() {
        return null;
    }
}
