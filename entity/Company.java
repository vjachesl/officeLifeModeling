package entity;

import java.util.*;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Company {
    private List<Employee> employeeList;
    private Map<Employee, List<Task>> employeesTaskMap;
    private Queue<Task> taskQueue;
    private Calendar actualDate;

    public Company() {
        employeeList = new ArrayList<>();
        taskQueue = new LinkedList<>();
        employeesTaskMap = new HashMap<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Map<Employee, List<Task>> getEmployeesTaskMap() {
        return employeesTaskMap;
    }

    public void setEmployeesTaskMap(Map<Employee, List<Task>> employeesTaskMap) {
        this.employeesTaskMap = employeesTaskMap;
    }

    public Queue<Task> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(Queue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public Calendar getActualDate() {
        return actualDate;
    }

    public void setActualDate(Calendar actualDate) {
        this.actualDate = actualDate;
    }
}
