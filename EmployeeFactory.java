import types.EmployeeType;

/**
 * Created by viacheslav on 18.03.16.
 */
public class EmployeeFactory {
    public Employee getEmployee(String employee) {
        switch (employee) {
            case "Director":
                return new Director();
            case "Manager":
                return new Manager();
            case "Accountant":
                return new Accountant();
            case "Designer":
                return new Designer();
            case "Programmer":
                return new Programmer();
            case "Tester":
                return new Tester();
            case "Cleaner":
                return new Cleaner();
            case "Freelancer":
                return new UniversalEmployee(EmployeeType.FreelanceHourlyPayd);
        }
        return null;
    }
}
