import types.EmployeeType;
import types.PositionsType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by viacheslav on 17.03.16.
 */
public class Director extends UniversalEmployee {
    public Director() {
        super(EmployeeType.FullTimeFixed);
        super.addPosition(PositionsType.Director);
    }

    @Override
    public boolean addPosition(PositionsType positionsType) {
        //can't add any other positions;
        return false;
    }

    public List<Task> generateTasks(Calendar date) {
        List<Task> toReturn = new ArrayList<>();
        int taskQuantity = new Random().nextInt(50);
        for (int i = 1; i < taskQuantity; i++) {
            toReturn.add(new Task(date));
        }
        return toReturn;
    }

    @Override
    public boolean doTheTask(boolean isOverTimeTask) {
        return false;
    }


}
