package entity;

import service.EmployeeService;
import types.EmployeeType;
import types.PositionsType;

import java.util.List;

/**
 * Created by viacheslav on 17.03.16.
 */
public interface Employee {
    boolean addPosition(PositionsType positionsType);

    String getEmployeeNumberName();

    List<PositionsType> getPositions();

    EmployeeType getEmpType();

    int getTimeLeftToWorkAtDay();

    void setTimeLeftToWorkAtDay(int hours);

    int getTimeLeftToWorkAtMonth();

    void setTimeLeftToWorkAtMonth(int hours);

    EmployeeService getEmployeeService();

    boolean ifEmplHasNeededPosition(PositionsType positionsType);

}
