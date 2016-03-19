import types.EmployeeType;
import types.PositionsType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Accountant extends UniversalEmployee {
    public Accountant() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Accountant);
    }

    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

    @Override
    public boolean doTheTask(boolean isOverTimeTask) {
        return false;
    }



    public void doTheTask(List<Employee> employeeList, Calendar date) {
        doFreelanceReport(employeeList, date);
        doHourlyPaydEmpeeReports(employeeList, date);
        doMonthlyReport(employeeList, date);

    }

    public Boolean doFreelanceReport(List<Employee> employeeList, Calendar date) {
        List<Employee> freelanceList = new ArrayList<>();
        for (Employee e : employeeList) {
            if (e.getEmpType().equals(EmployeeType.FreelanceHourlyPayd)) freelanceList.add(e);
        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMMdd");
        for (Employee e : freelanceList) {
            int dayPaydAmount = 0;
            try (PrintStream out = new PrintStream(new FileOutputStream("" + e.getEmployeeNumberName() + simpleFormat.format(date.getTime() + "filename.txt")))) {
                out.println("Report on Date" + simpleFormat.format(date.getTime() + "for employee " + e.getEmployeeNumberName()));
                out.println("The following task was completed:");
                out.println("Task #   Task Type   Task priority Task Duration  TaskPay");
                for (Task t : e.getCompletedTasks()) {
                    dayPaydAmount = dayPaydAmount + t.getTaskType().getPositionsType().getPositionRate();
                    out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + " " + t.getTaskType().getPositionsType().getPositionRate());
                }
                out.println(" Total amount to pay:" + dayPaydAmount);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    public Boolean doHourlyPaydEmpeeReports(List<Employee> employeeList, Calendar date) {
        if (date.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY || date.get(Calendar.DAY_OF_MONTH) != date.getActualMaximum(Calendar.DAY_OF_MONTH))
            return false;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMM");
        for (Employee e : employeeList) {
            int WeekPaydAmount = 0;
            try (PrintStream out = new PrintStream(new FileOutputStream("" + e.getEmployeeNumberName() + simpleFormat.format(date.getTime()) + date.get(Calendar.WEEK_OF_MONTH) + "filename.txt"))) {
                out.println("Report on Week oof month" + date.get(Calendar.WEEK_OF_MONTH) + "for employee " + e.getEmployeeNumberName());
                out.println("The following task was completed:");
                out.println("Task #   Task Type   Task priority Task Duration  IsOwertimetask TaskPay");
                //selecting weekly task from the whole tasklist
                for (Task t : e.getCompletedTasks()) {
                    if (date.get(Calendar.WEEK_OF_YEAR) == t.getWeekN()) {
                        WeekPaydAmount = WeekPaydAmount + (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate());
                        out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + t.isOvertimeTask() + " " + (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate()));
                    }
                }
                out.println(" Total amount to pay:" + WeekPaydAmount);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

    public Boolean doMonthlyReport(List<Employee> employeeList, Calendar date) {
        if (date.get(Calendar.DAY_OF_MONTH) != date.getActualMaximum(Calendar.DAY_OF_MONTH)) return false;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMM");
        int totalAmount = 0;
        for (Employee e : employeeList) {
            int MonthPaydAmount = 0;
            try (PrintStream out = new PrintStream(new FileOutputStream("JointReport for " + simpleFormat.format(date.getTime()) + "filename.txt"))) {
                out.println("Report on month" + date.get(Calendar.MONTH) + "for employee " + e.getEmployeeNumberName() + e.getEmpType());
                if (e.getEmpType().equals(EmployeeType.FullTimeFixed)) {
                    MonthPaydAmount = e.getPositions().get(0).getPositionRate();
                    System.out.println(" Was earned without time counting " + MonthPaydAmount);
                    totalAmount = totalAmount + MonthPaydAmount;
                } else {
                    out.println("The following task was completed:");
                    out.println("Task #   Task Type   Task priority Task Duration  IsOwertimetask TaskPay");
                    for (Task t : e.getCompletedTasks()) {
                        int currentTaskAmount = (t.isOvertimeTask() ? t.getTaskType().getPositionsType().getPositionRate() * 2 : t.getTaskType().getPositionsType().getPositionRate());
                        MonthPaydAmount = MonthPaydAmount + currentTaskAmount;
                        out.println("" + t.getTaskNumber() + " " + t.getTaskType() + " " + t.getTaskDuration() + t.isOvertimeTask() + " " + currentTaskAmount);
                    }
                    out.println(" Monthly amount to pay:" + MonthPaydAmount);
                }

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }


}
