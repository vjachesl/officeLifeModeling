package entity;

import service.AccountantService;
import types.EmployeeType;
import types.PositionsType;

/**Created by Viacheslav on 17.03.16.
 * Class modelating the accountant entity.
 * It extends from Universal entity.Employee.
 */
public class Accountant extends UniversalEmployee {
    AccountantService accountantService;

    /** This is one defined constructor for creating an accountant/
     */
    public Accountant() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Accountant);
        accountantService = new AccountantService();
    }

    /** It's overrided method to do nothing, because an accountant can't share positions with any alse
     *
     * @param positionsType position to add
     * @return false in any case.
     */
    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

    /** This method returns an instance of Accountant Service class
     * @return AccountantService - instance of service method class.
     */
    public AccountantService getAccountantService() {
        return accountantService;
    }
}
