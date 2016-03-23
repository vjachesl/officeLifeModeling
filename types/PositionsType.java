package types;

/**
 * Created by viacheslav on 17.03.16.
 */
public enum PositionsType {
    Director("Director", false, false, true, 10000),
    Manager("Manager", false, true, true, 1000),
    Accountant("Accountant", false, false, true, 500),
    Designer("Designer", true, true, false, 20),
    Programmer("Programmer", true, true, false, 40),
    Cleaner("Cleaner", false, false, false, 5),
    Tester("Tester", true, true, false, 10),
    Freelancer("Freelancer", true, true, false, 10);

    private String printName;
    private boolean sharedPosition;
    private boolean availableForFreelance;
    private boolean isFullTimePosition;
    private int positionRate;

    PositionsType(String printName, boolean sharedPosition, boolean availableForFreelance, boolean isFullTimePosition, int positionRate) {
        this.printName = printName;
        this.sharedPosition = sharedPosition;
        this.availableForFreelance = availableForFreelance;
        this.isFullTimePosition = isFullTimePosition;
        this.positionRate = positionRate;
    }

    public boolean isSharedPosition() {
        return sharedPosition;
    }

    public boolean isAvailableForFreelance() {
        return availableForFreelance;
    }

    public boolean isFullTimePosition() {
        return isFullTimePosition;
    }

    public int getPositionRate() {
        return positionRate;
    }

    @Override
    public String toString() {
        return printName;
    }
}

