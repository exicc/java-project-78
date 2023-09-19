package hexlet.code.schemas;


public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addCheck(
                "required",
                value -> value instanceof String string && !string.isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(
                "minLength",
                value -> value instanceof String string && string.length() >= length
        );
        return this;
    }
    public StringSchema contains(String substring) {
        addCheck(
                "contains",
                value -> value instanceof String string && string.contains(substring)
        );
        return this;
    }
}
