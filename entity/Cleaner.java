package entity;

import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the cleaner entity.
 * It extends from Universal entity.Employee.
 */
public class Cleaner extends UniversalEmployee {
    /**
     * This is one defined constructor for creating a cleaner/
     * *
     */
    public Cleaner() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Cleaner);
    }

    /**
     * It's overrided method to do nothing, because cleaner can't share positions with any alse
     *
     * @param positionsType position to add
     * @return false in any case.
     */
    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

}
