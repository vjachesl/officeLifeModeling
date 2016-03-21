package service;

import entity.*;
import types.PositionsType;
import types.TaskType;

import java.util.*;

import static types.Constants.*;

/**
 * Created by viacheslav on 21.03.16.
 */
public class CompanyService {
    public void init(Company company) {

        List<Enum> employeesInd = Arrays.asList(PositionsType.values());

        //Generate random(up to 100) Employees quantity
        int workersQuantity = new Random().nextInt(100);
        if (workersQuantity < 10) workersQuantity = workersQuantity + 10;
        for (int i = 1; i <= workersQuantity; i++) {
            company.getEmployeeList().add(EmployeeService.getEmployee(employeesInd.get(new Random().nextInt(employeesInd.size()))));
        }

        //Checks if company has atleast one entity.Director, entity.Manager, entity.Accountant, entity.Cleaner.
        if (!checkRestrictedPositions(company, PositionsType.Director))
            company.getEmployeeList().add(EmployeeService.getEmployee(PositionsType.Director));
        if (!checkRestrictedPositions(company, PositionsType.Manager))
            company.getEmployeeList().add(EmployeeService.getEmployee(PositionsType.Manager));
        if (!checkRestrictedPositions(company, PositionsType.Accountant))
            company.getEmployeeList().add(EmployeeService.getEmployee(PositionsType.Accountant));
        if (!checkRestrictedPositions(company, PositionsType.Cleaner))
            company.getEmployeeList().add(EmployeeService.getEmployee(PositionsType.Cleaner));

        // toDo add more positions to all workers;
    }

    public void operate(Company company) {
        company.setActualDate(new GregorianCalendar(2016, 10, 1, 0, 0, 0));
        Calendar finishDate = new GregorianCalendar(2016, 11, 1, 0, 0, 0);
        Queue<Task> companyTaskQueue = company.getTaskQueue();
        for (int i = 1; company.getActualDate().before(finishDate); i++) {
            for (int j = 0; j < 23; ++j) {
                // setting working hours for each day;
                for (Employee e : company.getEmployeeList()) {
                    e.setTimeLeftToWorkAtDay(WORK_HOURS_PER_DAY);
                }
                //Doing reglament cleaning & accounting tasks
                if (j == WORKS_START_HOUR) companyTaskQueue.offer(new Task(company.getActualDate(), TaskType.Cleaning));
                if (j == (WORKS_END_HOUR - 1)) {
                    companyTaskQueue.offer(new Task(company.getActualDate(), TaskType.Cleaning));
                    for (Employee e : company.getEmployeeList()) {
                        if (e instanceof Accountant) {
                            ((Accountant) e).getAccountantService().doFreelanceReport(company);
                            ((Accountant) e).getAccountantService().doHourlyPaydEmpeeReports(company);
                            ((Accountant) e).getAccountantService().doMonthlyReport(company);


                            break;
                        }
                    }
                }

                //During operating time generating and assigning tasks:
                if (i >= WORKS_START_HOUR & i <= (WORKS_END_HOUR - 2)) {

                    // try to find all directors and ask for new tasks;
                    for (Employee e : company.getEmployeeList()) {
                        if (e instanceof Director) {
                            companyTaskQueue.addAll((((Director) e).getDirectorService().generateTasks(company.getActualDate())));
                        }
                    }
                    // Try to assign new task to relevant employee///
                    while (!companyTaskQueue.isEmpty()) {
                        for (Employee e : company.getEmployeeList()) {
                            if (e.getEmployeeService().isSuitableForTask(e, companyTaskQueue.peek()))
                                e.getEmployeeService().addTaskToEmployee(company, e, companyTaskQueue.poll());
                        }
                        // if No suitable fulltime worker - need a freelancer
                        Task taskForFreelancer = companyTaskQueue.poll();
                        //Making needed freelancer and assign the task to him
                        Employee newFreelancer = EmployeeService.getEmployee(taskForFreelancer.getTaskType().getPositionsType());
                        newFreelancer.addPosition(taskForFreelancer.getTaskType().getPositionsType());
                        newFreelancer.getEmployeeService().addTaskToEmployee(company, newFreelancer, taskForFreelancer);
                        company.getEmployeeList().add(newFreelancer);
                    }
                }
                //Asks fo all employee to do their tasks, while assigned tasks is not empty
                //Actually, in this block employee chose priority task and put it into completed list.
                // It can be done without time restriction. But in the offtime was setted overtime flag
                for (Employee e : company.getEmployeeList())
                    e.getEmployeeService().doTheTask(company, e, ((i < WORKS_START_HOUR | i > WORKS_END_HOUR));
            }
            Calendar tempDate = company.getActualDate();
            tempDate.add(Calendar.DAY_OF_MONTH, i);
            company.setActualDate(tempDate);
        }
    }


    private boolean checkRestrictedPositions(Company company, PositionsType positionsType) {
        for (Employee e : company.getEmployeeList()) {
            if (e.ifEmplHasNeededPosition(positionsType)) return true;
        }
        return false;
    }

}
