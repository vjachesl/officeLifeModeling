package entity;

import types.EmployeeType;
import types.PositionsType;

/**
 * Created by viacheslav on 17.03.16.
 * Class modelating the Sales entity.Manager entity.
 * It extends from Universal entity.Employee.
 */
public class Manager extends UniversalEmployee {

    /**
     * This is one defined constructor for creating Sales entity.Manager/
     * *
     */
    public Manager() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Manager);
    }

    /**
     * It's overrided method to do nothing, because Sales entity.Manager can't share positions with any alse
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
