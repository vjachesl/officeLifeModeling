import entity.*;
import org.junit.Test;
import types.EmployeeType;
import types.PositionsType;
import types.TaskStatus;
import types.TaskType;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by viacheslav on 21.03.16.
 */
public class EntityTest {

    @Test
    public void AcountantInstanceCreation() {
        Employee accountant = new Accountant();
        Assert.assertEquals(false, accountant.addPosition(PositionsType.Director));
        Assert.assertEquals(false, accountant.addPosition(PositionsType.Accountant));
        Assert.assertEquals(EmployeeType.FullTimeFixed, accountant.getEmpType());
        Assert.assertEquals(1, accountant.getPositions().size());
        Assert.assertEquals(true, accountant.getPositions().contains(PositionsType.Accountant));
        Assert.assertEquals(true, accountant instanceof Accountant);
        Assert.assertEquals(true, accountant.ifEmplHasNeededPosition(PositionsType.Accountant));
    }

    @Test
    public void CleanerInstanceCreation() {
        Employee cleaner = new Cleaner();
        Assert.assertEquals(false, cleaner.addPosition(PositionsType.Director));
        Assert.assertEquals(false, cleaner.addPosition(PositionsType.Cleaner));
        Assert.assertEquals(EmployeeType.FullTimeFixed, cleaner.getEmpType());
        Assert.assertEquals(1, cleaner.getPositions().size());
        Assert.assertEquals(true, cleaner.getPositions().contains(PositionsType.Cleaner));
        Assert.assertEquals(true, cleaner instanceof Cleaner);
        Assert.assertEquals(true, cleaner.ifEmplHasNeededPosition(PositionsType.Cleaner));
    }

    @Test
    public void DesignerInstanceCreation() {
        Employee designer = new Designer();
        Assert.assertEquals(false, designer.addPosition(PositionsType.Director));
        Assert.assertEquals(false, designer.addPosition(PositionsType.Accountant));
        Assert.assertEquals(false, designer.addPosition(PositionsType.Cleaner));
        Assert.assertEquals(true, designer.addPosition(PositionsType.Tester));
        Assert.assertEquals(EmployeeType.FullTimeHourlyPayd, designer.getEmpType());
        Assert.assertEquals(2, designer.getPositions().size());
        Assert.assertEquals(true, designer.getPositions().contains(PositionsType.Tester));
        Assert.assertEquals(true, designer.getPositions().contains(PositionsType.Designer));
        Assert.assertEquals(true, designer instanceof Designer);
        Assert.assertEquals(false, designer.ifEmplHasNeededPosition(PositionsType.Director));
        Assert.assertEquals(true, designer.ifEmplHasNeededPosition(PositionsType.Designer));
    }

    @Test
    public void DirectorInstanceCreation() {
        Employee director = new Director();
        Assert.assertEquals(false, director.addPosition(PositionsType.Director));
        Assert.assertEquals(false, director.addPosition(PositionsType.Accountant));
        Assert.assertEquals(EmployeeType.FullTimeFixed, director.getEmpType());
        Assert.assertEquals(1, director.getPositions().size());
        Assert.assertEquals(true, director.getPositions().contains(PositionsType.Director));
        Assert.assertEquals(true, director instanceof Director);
        Assert.assertEquals(true, director.ifEmplHasNeededPosition(PositionsType.Director));
    }

    @Test
    public void ManagerInstanceCreation() {
        Employee manager = new Manager();
        Assert.assertEquals(false, manager.addPosition(PositionsType.Director));
        Assert.assertEquals(false, manager.addPosition(PositionsType.Cleaner));
        Assert.assertEquals(EmployeeType.FullTimeFixed, manager.getEmpType());
        Assert.assertEquals(1, manager.getPositions().size());
        Assert.assertEquals(true, manager.getPositions().contains(PositionsType.Manager));
        Assert.assertEquals(true, manager instanceof Manager);
        Assert.assertEquals(true, manager.ifEmplHasNeededPosition(PositionsType.Manager));
    }

    @Test

    public void TesterInstanceCreation() {
        Employee tester = new Tester();
        Assert.assertEquals(false, tester.addPosition(PositionsType.Director));
        Assert.assertEquals(false, tester.addPosition(PositionsType.Accountant));
        Assert.assertEquals(false, tester.addPosition(PositionsType.Cleaner));
        Assert.assertEquals(true, tester.addPosition(PositionsType.Programmer));
        Assert.assertEquals(EmployeeType.FullTimeHourlyPayd, tester.getEmpType());
        Assert.assertEquals(2, tester.getPositions().size());
        Assert.assertEquals(true, tester.getPositions().contains(PositionsType.Tester));
        Assert.assertEquals(true, tester.getPositions().contains(PositionsType.Programmer));
        Assert.assertEquals(true, tester instanceof Tester);
        Assert.assertEquals(false, tester.ifEmplHasNeededPosition(PositionsType.Director));
        Assert.assertEquals(true, tester.ifEmplHasNeededPosition(PositionsType.Programmer));
    }

    @Test
    public void ProgrammerInstanceCreation() {
        Employee programmer = new Programmer();
        Assert.assertEquals(false, programmer.addPosition(PositionsType.Director));
        Assert.assertEquals(false, programmer.addPosition(PositionsType.Accountant));
        Assert.assertEquals(false, programmer.addPosition(PositionsType.Cleaner));
        Assert.assertEquals(true, programmer.addPosition(PositionsType.Tester));
        Assert.assertEquals(EmployeeType.FullTimeHourlyPayd, programmer.getEmpType());
        Assert.assertEquals(2, programmer.getPositions().size());
        Assert.assertEquals(true, programmer.getPositions().contains(PositionsType.Tester));
        Assert.assertEquals(true, programmer.getPositions().contains(PositionsType.Programmer));
        Assert.assertEquals(true, programmer instanceof Programmer);
        Assert.assertEquals(false, programmer.ifEmplHasNeededPosition(PositionsType.Director));
        Assert.assertEquals(true, programmer.ifEmplHasNeededPosition(PositionsType.Programmer));
    }

    @Test
    public void UniversalEmployeeCreation() {
        Employee empl1 = new UniversalEmployee(EmployeeType.FreelanceHourlyPayd);
        Employee empl2 = new UniversalEmployee((EmployeeType.FullTimeHourlyPayd));
        Employee empl3 = new UniversalEmployee((EmployeeType.FullTimeFixed));
        Assert.assertEquals(EmployeeType.FreelanceHourlyPayd, empl1.getEmpType());
        Assert.assertEquals(EmployeeType.FullTimeHourlyPayd, empl2.getEmpType());
        Assert.assertEquals(EmployeeType.FullTimeFixed, empl3.getEmpType());
        Assert.assertEquals(0, empl1.getPositions().size());
        Assert.assertEquals(true, empl1.addPosition(PositionsType.Director));
        Assert.assertEquals(false, empl1.addPosition(PositionsType.Accountant));
        Assert.assertEquals(1, empl1.getPositions().size());
    }

    @Test
    public void TaskCreation() {
        Task task1 = new Task(GregorianCalendar.getInstance());
        Task task2 = new Task(GregorianCalendar.getInstance(), TaskType.Cleaning);
        Task task3 = new Task(GregorianCalendar.getInstance(), TaskType.MakeReporting);
        Assert.assertEquals(true, task1.getTaskStatus().equals(TaskStatus.New));
        Assert.assertEquals(true, task2.getTaskStatus().equals(TaskStatus.New));
        Assert.assertEquals(true, task3.getTaskType().equals(TaskType.MakeReporting));
        Assert.assertEquals(false, task3.getTaskType().getPositionsType().isSharedPosition());
        Assert.assertEquals(500, task3.getTaskType().getPositionsType().getPositionRate());
        Assert.assertEquals(false, task3.getTaskType().getPositionsType().isAvailableForFreelance());
    }


}
