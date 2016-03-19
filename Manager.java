import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Manager extends UniversalEmployee {
    public Manager() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Manager);
    }

    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

}
