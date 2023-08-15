package hexlet.code;


public class StringSchema {
    private boolean required = false;
    private Integer minLength = null;
    private String contains = null;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    public boolean isValid(String data) {
        if (required && (data == null || data.isEmpty())) {
            return false;
        }

        if (minLength != null && (data == null || data.length() < minLength)) {
            return false;
        }

        if (contains != null && (data == null || !data.contains(contains))) {
            return false;
        }

        return true;
    }

    public boolean isValid(int data) {
        return false;
    }
}
