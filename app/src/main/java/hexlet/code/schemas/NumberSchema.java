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
                value -> value instanceof Integer obj && obj > 0 || value == null
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
