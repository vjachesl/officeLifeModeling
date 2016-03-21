package entity;

import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the entity.Tester entity.
 * It extends from Universal entity.Employee.
 */
public class Tester extends UniversalEmployee {
    /**
     * This is one defined constructor for creating a entity.Tester/
     */
    public Tester() {
        super(EmployeeType.FullTimeHourlyPayd);
        super.addPosition(PositionsType.Tester);
    }


}
