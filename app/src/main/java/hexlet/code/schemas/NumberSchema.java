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
                        return true;
                    }
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        return intValue > 0;
                    }
                    return false;
                }
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> value instanceof Integer obj && obj >= min && obj <= max
        );
        return this;
    }
}
