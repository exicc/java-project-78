package hexlet.code.schemas;



public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addCheck(
                "required",
                value -> value instanceof Integer
        );
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> {
                    if (value == null) {
                        return false;
                    }
                    return ((int) value) > 0;
                }
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> {
                    if (value == null) {
                        return false;
                    }
                    int intValue = (int) value;
                    return intValue >= min && intValue <= max;
                }
        );
        return this;
    }
}
