package hexlet.code.schemas;


import hexlet.code.Validator;

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
