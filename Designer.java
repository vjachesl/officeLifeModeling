import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 18.03.16.
 */
public class Designer extends UniversalEmployee {
    public Designer() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Designer);
    }


}
