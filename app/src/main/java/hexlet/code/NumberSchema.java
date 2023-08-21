package hexlet.code;


public class NumberSchema extends BaseSchema {
    private boolean positive = false;
    private int minRange = Integer.MIN_VALUE;
    private int maxRange = Integer.MAX_VALUE;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
    public NumberSchema(Validator validator) {
        super(validator);
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        minRange = min;
        maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!super.isValid(data)) {
            return false;
        }

        if (!(data instanceof Number)) {
            return false;
        }

        int numData = ((Number) data).intValue();

        if (positive && numData <= 0) {
            return false;
        }

        return numData >= minRange && numData <= maxRange;
    }
}
