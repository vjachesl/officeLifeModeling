package types;

/**
 * Created by viacheslav on 17.03.16.
 */
public enum PositionsType {
    Director(false, false, true, 10000),
    Manager(false, true, true, 1000),
    Accountant(false, false, true, 500),
    Designer(true, true, false, 20),
    Programmer(true, true, false, 40),
    Cleaner(false, false, false, 5),
    Tester(true, true, false, 10);

    private boolean sharedPosition;
    private boolean availableForFreelance;
    private boolean isFullTimePosition;
    private int positionRate;

    PositionsType(boolean sharedPosition, boolean availableForFreelance, boolean isFullTimePosition, int positionRate) {
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

}

