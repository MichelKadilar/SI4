package menuchoice;

public enum ChoiceValues {
    ONE(1), TWO(2);

    private int choiceValue;

    ChoiceValues(int choiceValue) {
        this.choiceValue = choiceValue;
    }

    public int getChoiceValue() {
        return choiceValue;
    }
}
