package service;

import entity.Company;
import entity.Employee;
import entity.Task;
import types.EmployeeType;
import types.TaskStatus;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static types.Constants.WORK_HOURS_PER_MONTH;

/**
 * Created by viacheslav on 21.03.16.
 * It's define methods for report creating.
 * All reports creation are invoked in different methods with parameters.
 */
public class AccountantService {

    /**
     * This method is to do reports for freelancers each day.
     * Firstly it picks freelansers from whole employee list, count dayly pay in accordance with the current date
     * and save all data in the file with the name mask "EmployeeNumberNameyyyyMMMdd.txt"
     *
     * @param company - incapsulate current company instance
     * @return {@code true} if all was made ok
     */
    public Boolean doFreelanceReport(Company company) {
        List<Employee> freelanceList = new ArrayList<>();
        for (Employee e : ((Employee[]) company.getEmployeesTaskMap().keySet().toArray())) {
            if (e.getEmpType().equals(EmployeeType.FreelanceHourlyPayd)) freelanceList.add(e);
        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMMdd");
        for (Employee e : freelanceList) {
            int dayPaydAmount = 0;
            Calendar date = company.getActualDate();
            try (PrintStream out = new PrintStream(new FileOutputStream("" + e.getEmployeeNumberName() + simpleFormat.format(date.getTime() + ".txt")))) {
                out.println("Report on Date" + simpleFormat.format(date.getTime() + "for employee " + e.getEmployeeNumberName()));
                out.println("The following task was completed:");
                out.println("entity.Task #   entity.Task Type   entity.Task priority entity.Task Duration  TaskPay");
                for (Task t : company.getEmployeesTaskMap().get(e)) {
                    if (t.getTaskStatus().equals(TaskStatus.Completed)) {
                        dayPaydAmount = dayPaydAmount + t.getTaskType().getPositionsType().getPositionRate();
                        out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + " " + t.getTaskType().getPositionsType().getPositionRate());
                    }

                }
                out.println(" Total hours:" + (WORK_HOURS_PER_MONTH - e.getTimeLeftToWorkAtDay()));
                out.println(" Total amount to pay:" + dayPaydAmount);

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    /**
     * This method is to do reports for hourly payd employee each week or each last day of month.
     * Firstly it checks if it is friday or last day of month and picks hourlyPaydEmployee
     * from whole employee list, count weekly pay in accordance with the current date
     * and save all data in the file with the name mask "EmployeeNumberNameyyyyMMMW.txt"
     *
     * @param company - incapsulate current company instance
     * @return {@code true} if all was made ok
     */
    public Boolean doHourlyPaydEmpeeReports(Company company) {
        Calendar date = company.getActualDate();
        if (date.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY ||
                date.get(Calendar.DAY_OF_MONTH) != date.getActualMaximum(Calendar.DAY_OF_MONTH))
            return false;
        List<Employee> hourlyPaydList = new ArrayList<>();
        for (Employee e : (Employee[]) company.getEmployeesTaskMap().keySet().toArray()) {
            if (e.getEmpType().equals(EmployeeType.FullTimeHourlyPayd)) hourlyPaydList.add(e);
        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMM");
        for (Employee e : hourlyPaydList) {
            int WeekPaydAmount = 0;
            try (PrintStream out = new PrintStream(new FileOutputStream("" + e.getEmployeeNumberName() + simpleFormat.format(date.getTime()) + date.get(Calendar.WEEK_OF_MONTH) + ".txt"))) {
                out.println("Report on Week oof month" + date.get(Calendar.WEEK_OF_MONTH) + "for employee " + e.getEmployeeNumberName());
                out.println("The following task was completed:");
                out.println("entity.Task #   entity.Task Type   entity.Task priority entity.Task Duration  IsOvertimeTask TaskPay");
                //selecting weekly task from the whole tasklist
                for (Task t : company.getEmployeesTaskMap().get(e)) {
                    if (date.get(Calendar.WEEK_OF_YEAR) == t.getWeekN() && t.getTaskStatus().equals(TaskStatus.Completed)) {
                        WeekPaydAmount = WeekPaydAmount + (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate());
                        out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + t.isOvertimeTask() + " " + (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate()));
                    }
                }
                out.println(" Total hours:" + (40 - e.getTimeLeftToWorkAtDay()));
                out.println(" Total amount to pay:" + WeekPaydAmount);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    /**
     * This method is to do whole month report for all employee.
     * Firstly it picks employee from whole employee list, count pay in accordance with complete tasks / or just
     * print monthly amount for fixed payd positions. Method saves all data in the file with the name mask "JointReportForyyyyMMM.txt"
     *
     * @param company - incapsulate current company instance
     * @return {@code true} if all was made ok
     */
    public Boolean doMonthlyReport(Company company) {
        Calendar date = company.getActualDate();
        if (date.get(Calendar.DAY_OF_MONTH) != date.getActualMaximum(Calendar.DAY_OF_MONTH)) return false;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMM");
        int totalAmount = 0;
        try (PrintStream out = new PrintStream(new FileOutputStream("JointReportFor" + simpleFormat.format(date.getTime()) + "filename.txt"))) {
            for (Employee e : company.getEmployeeList()) {
                int MonthPaydAmount;
                if (e.getEmpType().equals(EmployeeType.FullTimeFixed)) {
                    out.println("Report on month" + date.get(Calendar.MONTH) + "for employee " + e.getEmployeeNumberName() + e.getEmpType());
                    MonthPaydAmount = e.getPositions().get(0).getPositionRate();
                    System.out.println(" Was earned without time counting " + MonthPaydAmount);
                    totalAmount = totalAmount + MonthPaydAmount;
                }
            }

            for (Employee e : (Employee[]) company.getEmployeesTaskMap().keySet().toArray()) {
                int MonthPaydAmount = 0;
                out.println("Report on month" + date.get(Calendar.MONTH) + "for employee " + e.getEmployeeNumberName() + e.getEmpType());
                out.println("The following task was completed:");
                out.println("entity.Task #   entity.Task Type   entity.Task priority entity.Task Duration  IsOwertimetask TaskPay");
                for (Task t : company.getEmployeesTaskMap().get(e)) {
                    if (t.getTaskStatus().equals(TaskStatus.Completed)) {
                        int currentTaskAmount = (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate());
                        MonthPaydAmount = MonthPaydAmount + currentTaskAmount;
                        out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + t.isOvertimeTask() + " " + currentTaskAmount);
                    }
                }
                out.println(" Total hours:" + (40 - e.getTimeLeftToWorkAtDay()));
                out.println(" Monthly amount to pay:" + MonthPaydAmount);
                totalAmount = totalAmount + MonthPaydAmount;
            }


        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return true;
    }
}
