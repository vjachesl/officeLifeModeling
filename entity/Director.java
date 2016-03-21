package entity;

import service.DirectorService;
import types.EmployeeType;
import types.PositionsType;

/**
 * Created by Viacheslav on 17.03.16.
 * Class modelating the director entity.
 * It extends from Universal entity.Employee.
 */
public class Director extends UniversalEmployee {
    DirectorService directorService;

    /**
     * This is one defined constructor for creating a entity.Director/
     * *
     */
    public Director() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Director);
        directorService = new DirectorService();
    }

    /**
     * It's overrided method to do nothing, because director can't share positions with any alse
     *
     * @param positionsType position to add
     * @return false in any case.
     */
    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

    public DirectorService getDirectorService() {
        return directorService;
    }
}
