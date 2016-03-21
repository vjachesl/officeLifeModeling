package entity;

import java.util.*;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the company entity.
 */
public class Company {
    /**
     * The value is used for all company employees storing.
     */
    private List<Employee> employeeList;

    /**
     * The value is used for company employees storing, which is has assigned tasks.
     */
    private Map<Employee, List<Task>> employeesTaskMap;

    /**
     * The value is used for company assigned task storing.
     */
    private Queue<Task> taskQueue;

    /**
     * The value is used for actual date storing.
     */
    private Calendar actualDate;


    /**
     * This is one defined constructor for creating a company/
     */
    public Company() {
        employeeList = new ArrayList<Employee>();
        taskQueue = new LinkedList<Task>();
        employeesTaskMap = new HashMap<Employee, List<Task>>();
    }

    /**Set of getters for parameters * */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Map<Employee, List<Task>> getEmployeesTaskMap() {
        return employeesTaskMap;
    }

    public Queue<Task> getTaskQueue() {
        return taskQueue;
    }

    public Calendar getActualDate() {
        return actualDate;
    }


    /**Setter for actual date * */
    public void setActualDate(Calendar actualDate) {
        this.actualDate = actualDate;
    }
}
