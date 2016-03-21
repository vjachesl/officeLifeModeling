package entity;

import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the entity.Programmer entity.
 * It extends from Universal entity.Employee.
 */
public class Programmer extends UniversalEmployee {
    /**
     * This is one defined constructor for creating entity.Programmer/
     * *
     */
    public Programmer() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Programmer);
    }


}
