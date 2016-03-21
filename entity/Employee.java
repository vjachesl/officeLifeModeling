package entity;

import service.EmployeeService;
import types.EmployeeType;
import types.PositionsType;

import java.util.List;

/**
 * Created by viacheslav on 17.03.16.
 * Used for creating joint interface for all employees hierarhy
 */
public interface Employee {

    /**
     * method declaring for adding new positions to the employee
     *
     * @param positionsType position to be added to an employee as an instance of PositionType Enum
     * @return {@code true} - if is possible to add additional position to worker.
     */
    boolean addPosition(PositionsType positionsType);

    /**Set of getters for employee instance variables * */
    String getEmployeeNumberName();

    List<PositionsType> getPositions();

    EmployeeType getEmpType();

    int getTimeLeftToWorkAtDay();

    /**Set of setters */
    void setTimeLeftToWorkAtDay(int hours);

    int getTimeLeftToWorkAtMonth();

    void setTimeLeftToWorkAtMonth(int hours);

    EmployeeService getEmployeeService();

    /** This method checks if employee has needed position
     *
     * @param positionsType position to check
     * @return {flag = true} if has.
     */
    boolean ifEmplHasNeededPosition(PositionsType positionsType);

}
