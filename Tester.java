import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 18.03.16.
 */
public class Tester extends UniversalEmployee {
    public Tester() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Tester);
    }


}
