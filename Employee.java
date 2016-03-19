import types.EmployeeType;
import types.PositionsType;

import java.util.List;

/**
 * Created by viacheslav on 17.03.16.
 */
interface Employee {
    boolean addPosition(PositionsType positionsType);

    boolean ifEmplHasNeededPosition(PositionsType positionsType);

    boolean isSuitableForTask(Task task);

    boolean addTaskToEmployee(Task task);

    boolean doTheTask(boolean isOverTimeTask);

    EmployeeType getEmpType();

    void setDayHours();

    String getEmployeeNumberName();

    List<Task> getCompletedTasks();

    List<PositionsType> getPositions();

}
