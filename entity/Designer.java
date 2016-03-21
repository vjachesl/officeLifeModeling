package entity;

import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the entity.Designer entity.
 * It extends from Universal entity.Employee.
 */
public class Designer extends UniversalEmployee {
    /**
     * This is one defined constructor for creating a entity.Designer/
     * *
     */
    public Designer() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Designer);
    }


}
