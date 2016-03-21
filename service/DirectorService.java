package service;

import entity.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by viacheslav on 21.03.16.
 * In this class is defined method for task generating.
 */
public class DirectorService {

    /**
     * It's method to generating new tasks. The tasks quantity is limited from 0 to 50
     * and it's exact quantity ramdomly generated.
     *
     * @param date - used as parameter in new entity.Task constructor
     * @return List<entity.Task> - new tasks as list.
     */
    public List<Task> generateTasks(Calendar date) {
        List<Task> toReturn = new ArrayList<>();
        int taskQuantity = new Random().nextInt(50);
        for (int i = 1; i < taskQuantity; i++) {
            toReturn.add(new Task(date));
        }
        return toReturn;
    }
}
