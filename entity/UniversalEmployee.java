package entity;

import service.EmployeeService;
import types.EmployeeType;
import types.PositionsType;

import java.util.ArrayList;
import java.util.List;

import static types.Constants.WORK_HOURS_PER_DAY;
import static types.Constants.WORK_HOURS_PER_MONTH;

/**
 * Created by Viacheslav on 17.03.16.
 * Class modelating the entity.Employee entity. It's implements the entity.Employee interface
 * it incapsulate all info about the regular employee.
 */
public class UniversalEmployee implements Employee {

    /**
     * The class value is used for storing last employee number.
     */
    private static int empCounter = 0;

    /**
     * The value is used for unique employee number.
     */
    private int empNumber;

    /**
     * The value is used for employee Name.
     */
    private String empName;

    /** The value is used for employee Surname. */
    private String empSurName;

    /** The value is used for storing employee type as an instance Enum EmployeeType. */
    private EmployeeType empType;

    /** The value is used for storing employee positions as list. */
    private List<PositionsType> positionsTypeList;

    /** The value is used for storing employee working hours per day (normally 8 hours).
     * It's update every day. and decrease with each assigned task */
    private int timeLeftToWorkAtDay;

    /** The value is used for storing employee working hours per month (normally 40 hours).
     * It's update once per month and decrease with each assigned task */
    private int timeLeftToWorkAtMonth;

    /**
     * The value is used for storing employee service methods instance .
     * It's assigns only in constructor
     */
    private EmployeeService employeeService;


    /** Constructor for new entity.Employee with needed type
     * @param empType needed type of an employee as an instance of EmployeeType Enum
     * */
    public UniversalEmployee(EmployeeType empType) {
        empNumber = ++empCounter;
        empName = "empName" + empNumber;
        empSurName = "empSurName" + empNumber;
        timeLeftToWorkAtMonth = WORK_HOURS_PER_MONTH;
        timeLeftToWorkAtDay = WORK_HOURS_PER_DAY;
        positionsTypeList = new ArrayList<>();
        employeeService = new EmployeeService();
        this.empType = empType;
    }

    /** method implementation for adding new positions to the employee
     * @param positionsType position to be added to an employee as an instance of PositionType Enum
     * @return {@code true} - if is possible to add additional position to worker.
     * */
    public boolean addPosition(PositionsType positionsType) {
        if (positionsTypeList.size() == 0) {
            positionsTypeList.add(positionsType);
            return true;
        }
        for (PositionsType p : positionsTypeList) {
            if (p.isSharedPosition() && positionsType.isSharedPosition()) {
                positionsTypeList.add(positionsType);
                return true;
            }
        }
        return false;
    }


    /**Set of getters for parameters * */

    public EmployeeType getEmpType() {
        return empType;
    }

    public String getEmployeeNumberName() {
        return empNumber + "_" + empSurName;
    }

    public List<PositionsType> getPositions() {
        return positionsTypeList;
    }

    public int getTimeLeftToWorkAtMonth() {
        return timeLeftToWorkAtMonth;
    }

    public void setTimeLeftToWorkAtMonth(int timeLeftToWorkAtMonth) {
        this.timeLeftToWorkAtMonth = timeLeftToWorkAtMonth;
    }

    public int getTimeLeftToWorkAtDay() {
        return timeLeftToWorkAtDay;
    }

    /**
     * Set of setters
     */
    public void setTimeLeftToWorkAtDay(int hours) {
        timeLeftToWorkAtDay = hours;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    /** This method checks if employee has needed position
     *
     * @param positionsType position to check
     * @return {flag = true} if has.
     */
    public boolean ifEmplHasNeededPosition(PositionsType positionsType) {
        for (PositionsType p : positionsTypeList) {
            if (p.equals(positionsType)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversalEmployee)) return false;

        UniversalEmployee that = (UniversalEmployee) o;

        if (empNumber != that.empNumber) return false;
        if (!empName.equals(that.empName)) return false;
        if (!empSurName.equals(that.empSurName)) return false;
        return empType == that.empType;

    }

    @Override
    public int hashCode() {
        int result = empNumber;
        result = 31 * result + empName.hashCode();
        result = 31 * result + empSurName.hashCode();
        result = 31 * result + empType.hashCode();
        return result;
    }
}
