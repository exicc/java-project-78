package hexlet.code.schemas;


public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addCheck(
                "required",
                value -> value != null && !isNullString(value) && value instanceof String
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(
                "minLength",
                value -> {
                    if (value == null || isNullString(value)) {
                        return false;
                    }
                    String strValue = (String) value;
                    return strValue.length() >= length;
                }
        );
        return this;
    }
    public StringSchema contains(String substring) {
        addCheck(
                "contains",
                value -> {
                    if (value == null || isNullString(value)) {
                        return false;
                    }
                    String strValue = (String) value;
                    return strValue.contains(substring);
                }
        );
        return this;
    }
    private static boolean isNullString(Object obj) {
        if (obj instanceof String str) {
            return str.isEmpty();
        }
        return false;
    }
}
