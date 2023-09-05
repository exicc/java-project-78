package hexlet.code;


public class Validator {
    public StringSchema string() {
        return new StringSchema(this);
    }

    public NumberSchema number() {
        return new NumberSchema(this);
    }

    public MapSchema map() {
        return new MapSchema(this);
    }
}
