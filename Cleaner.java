import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Cleaner extends UniversalEmployee {
    public Cleaner() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Cleaner);
    }

    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

}
