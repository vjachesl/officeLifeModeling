import types.PositionsType;
import types.TaskType;

import java.util.*;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Company {
    private List<Employee> employeeList;
    private Queue<Task> taskQueue;
    Calendar actualDate;
    EmployeeFactory employeeFactory;
    String[] employeesInd = {"Director", "Manager", "Accountant", "Designer", "Programmer", "Tester", "Cleaner", "Freelancer"};

    public Company() {
        employeeList = new ArrayList<>();
        taskQueue = new LinkedList<>();

        //Generate random(up to 100) Employees quantity
        employeeFactory = new EmployeeFactory();
        int workersQuantity = new Random().nextInt(100);
        if (workersQuantity < 10) workersQuantity = workersQuantity + 10;
        for (int i = 1; i <= workersQuantity; i++) {
            employeeList.add(employeeFactory.getEmployee(employeesInd[new Random().nextInt(6)]));
        }

        //Checks if company has atleast one Director, Manager, Accountant, Cleaner.
        if (!checkRestrictedPositions(PositionsType.Director))
            employeeList.add(employeeFactory.getEmployee("Director"));
        if (!checkRestrictedPositions(PositionsType.Manager)) employeeList.add(employeeFactory.getEmployee("Manager"));
        if (!checkRestrictedPositions(PositionsType.Accountant))
            employeeList.add(employeeFactory.getEmployee("Accountant"));
        if (!checkRestrictedPositions(PositionsType.Cleaner)) employeeList.add(employeeFactory.getEmployee("Cleaner"));

        // toDo add more positions to all workers;

    }

    public void operate() {
        Company modelingCompany = new Company();
        actualDate = new GregorianCalendar(2016, 10, 01, 00, 00, 00);
        Calendar finishDate = new GregorianCalendar(2016, 11, 01, 00, 00, 00);
        for (int i = 1; actualDate.before(finishDate); actualDate.add(Calendar.DAY_OF_MONTH, i)) {
            for (int j = 0; j < 23; ++j) {
                // setting working hours for each day;
                for (Employee e : employeeList) {
                    e.setDayHours();
                }
                //Doing reglament cleaning & accounting tasks
                if (j == 9) taskQueue.offer(new Task(actualDate, TaskType.Cleaning));
                if (j == 17) {
                    taskQueue.offer(new Task(actualDate, TaskType.Cleaning));
                    for (Employee e : employeeList) {
                        if (e instanceof Accountant) {
                            ((Accountant) e).doTheTask(employeeList, actualDate);
                            break;
                        }
                    }
                }

                //During operating time generating and assigning tasks:
                if (i >= 8 & i <= 16) {

                    // try to find all directors and ask for new tasks;
                    for (Employee e : employeeList) {
                        if (e instanceof Director) {
                            taskQueue.addAll(((Director) e).generateTasks(actualDate));
                        }
                    }
                    // Try to assign new task to relevant employee///
                    while (!taskQueue.isEmpty()) {
                        for (Employee e : employeeList) {
                            if (e.isSuitableForTask(taskQueue.peek())) e.addTaskToEmployee(taskQueue.poll());
                        }
                        // if No suitable fulltime worker - need a freelancer
                        Task taskForFreelancer = taskQueue.poll();
                        //Making needed freelancer and assign the task to him
                        Employee newFreelancer = employeeFactory.getEmployee(taskForFreelancer.getTaskType().getPositionsType().toString());
                        newFreelancer.addPosition(taskForFreelancer.getTaskType().getPositionsType());
                        newFreelancer.addTaskToEmployee(taskForFreelancer);
                        employeeList.add(newFreelancer);
                    }
                }
                //Asks fo all employee to do their tasks, while assigned tasks is not empty
                //Actually, in this block employee chose priority task and put it into completed list.
                // It can be done without time restriction. But in the offtime was setted overtime flag
                for (Employee e : employeeList) e.doTheTask(((i<9 | i>18)? true : false));
            }


        }
    }


    private boolean checkRestrictedPositions(PositionsType positionsType) {
        for (Employee e : employeeList) {
            if (e.ifEmplHasNeededPosition(positionsType)) return true;
        }
        return false;
    }


}
