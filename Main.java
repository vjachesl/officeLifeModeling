import entity.Company;
import service.CompanyService;

/**
 * Created by viacheslav on 18.03.16.
 */
public class Main {
    public static void main(String[] args) {
        Company modelingCompany = new Company();
        CompanyService.init(modelingCompany);
        CompanyService.operate(modelingCompany);
    }
}
