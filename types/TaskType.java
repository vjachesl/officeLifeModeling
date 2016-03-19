package types;

/**
 * Created by viacheslav on 17.03.16.
 */
public enum TaskType {
    GenerateTasks(PositionsType.Director),
    SaleServises(PositionsType.Manager),
    Coding(PositionsType.Programmer),
    Testing(PositionsType.Tester),
    MakingDesign(PositionsType.Designer),
    Cleaning(PositionsType.Cleaner),
    MakeReporting(PositionsType.Accountant);
    PositionsType positionsType;

    TaskType(PositionsType positionsType) {
        this.positionsType = positionsType;
    }

    public PositionsType getPositionsType() {
        return positionsType;
    }
}
