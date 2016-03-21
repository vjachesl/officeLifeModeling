package service;

import entity.*;
import types.EmployeeType;
import types.PositionsType;
import types.TaskPriority;
import types.TaskStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 21.03.16.
 */
public class EmployeeService {

    /**
     * In this class is defined one method for returning an instance of the new employee
     * depended on incoming String value.
     *
     * @param employee - define which employee will be returned from the method
     * @return new employee instance or null, if wrong input was made.
     */
    public static Employee getEmployee(Enum<PositionsType> employee) {
        switch (employee.toString()) {
            case "entity.Director":
                return new Director();
            case "entity.Manager":
                return new Manager();
            case "entity.Accountant":
                return new Accountant();
            case "entity.Designer":
                return new Designer();
            case "entity.Programmer":
                return new Programmer();
            case "entity.Tester":
                return new Tester();
            case "entity.Cleaner":
                return new Cleaner();
            case "Freelancer":
                return new UniversalEmployee(EmployeeType.FreelanceHourlyPayd);
        }
        return null;
    }

    /**
     * Method checks if the employee has time to complete the task. Also it's invokes another method
     * for checks if an employee has needed position for task.
     *
     * @param task -  the task instance.
     * @return {@code true} - returns true if has.
     */

    public boolean isSuitableForTask(Employee employee, Task task) {
        return employee.getTimeLeftToWorkAtDay() > task.getTaskDuration() &&
                employee.getTimeLeftToWorkAtMonth() > task.getTaskDuration() &&
                employee.ifEmplHasNeededPosition(task.getTaskType().getPositionsType());
    }

    /**
     * Method add the task to the employee assigned task with the previous checking, if the employee can do it.
     *
     * @param task -  the task instance.
     * @return {@code true} - returns true all checks was successful and task was added.
     */

    public boolean addTaskToEmployee(Company company, Employee employee, Task task) {
        if (isSuitableForTask(employee, task)) {
            employee.setTimeLeftToWorkAtDay(employee.getTimeLeftToWorkAtDay() - task.getTaskDuration());
            employee.setTimeLeftToWorkAtMonth(employee.getTimeLeftToWorkAtMonth() - task.getTaskDuration());
            List<Task> employeeTask = company.getEmployeesTaskMap().get(employee);
            if (employeeTask == null) {
                employeeTask = new ArrayList<>();
                company.getEmployeesTaskMap().put(employee, employeeTask);
            }
            task.setTaskStatus(TaskStatus.Assigned);
            employeeTask.add(task);
            return true;
        }
        return false;
    }

    /**
     * Method invokes another methods, for choosing the maximum priority or payd task and do it.
     * if one of these methods returned true, method will return true as result of compleing the task.
     *
     * @param isOverTimeTask -  the task instance overtime flag. Used for additional payd counting.
     * @return {@code true} - returns true if one task was completed.
     */

    public boolean doTheTask(Company company, Employee employee, boolean isOverTimeTask) {
        List<Task> tasks = company.getEmployeesTaskMap().get(employee);
        if (tasks != null && tasks.size() > 0) {
            if (!completingTask(employee, tasks, TaskPriority.HighPriority, isOverTimeTask)) {
                if (!completingTask(employee, tasks, TaskPriority.NormalPriority, isOverTimeTask)) {
                    if (!completingTask(employee, tasks, TaskPriority.LowPriority, isOverTimeTask)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean completingTask(Employee employee, List<Task> tasks, TaskPriority taskPriority, boolean isOverTimeTask) {
        Task currentTask = findingAllAssignedTaskWithPriority(tasks, taskPriority);
        if (currentTask != null) {
            tasks.remove(currentTask);
            currentTask.setOvertimeTask(isOverTimeTask);
            currentTask.setTaskStatus(TaskStatus.Completed);
            tasks.add(currentTask);
            return true;
        }
        return false;
    }

    private Task findingAllAssignedTaskWithPriority(List<Task> tasks, TaskPriority taskPriority) {
        List<Task> resultList = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTaskPriority().equals(taskPriority) || t.getTaskStatus().equals(TaskStatus.Assigned))
                resultList.add(t);
        }
        if (resultList.size() == 1) return resultList.get(1);
        else {
            Task taskToReturn = resultList.get(0);
            for (Task t : tasks) {
                if (t.getTaskType().getPositionsType().getPositionRate() >
                        taskToReturn.getTaskType().getPositionsType().getPositionRate()) {
                    taskToReturn = t;
                }
                return taskToReturn;
            }

        }
        return null;
    }

}
