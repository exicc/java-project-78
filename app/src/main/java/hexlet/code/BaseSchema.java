package hexlet.code;


public class BaseSchema {
    protected Validator validator;
    protected boolean required = false;

    public BaseSchema(Validator validator) {
        this.validator = validator;
    }

    public BaseSchema required() {
        required = true;
        return this;
    }

    public boolean isValid(Object data) {
        return !required || (data != null && !data.toString().isEmpty());
    }
}
