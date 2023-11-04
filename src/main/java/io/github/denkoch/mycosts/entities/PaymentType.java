package io.github.denkoch.mycosts.entities;

public enum PaymentType {
    EXPENSE("Expense"),
    INCOME("Income");

    public static final PaymentType[] ALL = {EXPENSE, INCOME};
    private final String value;

    private PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
