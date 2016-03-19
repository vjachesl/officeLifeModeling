import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Programmer extends UniversalEmployee {
    public Programmer() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Programmer);
    }


}
