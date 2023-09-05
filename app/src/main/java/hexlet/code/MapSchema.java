package hexlet.code;


import java.util.Map;

public class MapSchema extends BaseSchema {

    private int sizeLimit = -1;

    private boolean isRequired = false;

    public MapSchema(Validator validator) {
        super(validator);
    }
    @Override
    public MapSchema required() {
        isRequired = true;
        return this;
    }
    public MapSchema sizeof(int size) {
        this.sizeLimit = size;
        return this;
    }
    @Override
    public boolean isValid(Object data) {
        if (isRequired && isNullMap(data)) {
            return false;
        }

        if (!(data instanceof Map)) {
            return false;
        }

        if (sizeLimit >= 0) {
            Map<?, ?> map = (Map<?, ?>) data;
            return map.size() == sizeLimit;
        }

        return true;
    }
    private static boolean isNullMap(Object obj) {
        if (obj instanceof Map<?, ?> map) {
            return map.isEmpty();
        }
        return false;
    }
}
