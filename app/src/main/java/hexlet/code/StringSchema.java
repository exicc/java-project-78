package hexlet.code;

public class StringSchema extends BaseSchema {
    private int minLength;
    private String containsSubstring;
    @Override
    public StringSchema required() {
        super.required();
        return this;
    }
    public StringSchema(Validator validator) {
        super(validator);
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        containsSubstring = substring;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!super.isValid(data)) {
            return false;
        }

        if (!(data instanceof String)) {
            return false;
        }

        String strData = (String) data;

        if (minLength > 0 && strData.length() < minLength) {
            return false;
        }

        return containsSubstring == null || strData.contains(containsSubstring);
    }
}
