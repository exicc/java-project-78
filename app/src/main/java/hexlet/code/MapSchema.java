package hexlet.code;


import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {

    private int sizeLimit = -1;

    private boolean isRequired = false;
    private Map<String, BaseSchema> propertySchemas;

    public MapSchema(Validator validator) {
        super(validator);
        this.propertySchemas = new HashMap<>();
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
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.propertySchemas = schemas;
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

        Map<?, ?> map = (Map<?, ?>) data;
        for (String property : propertySchemas.keySet()) {
            if (!map.containsKey(property)) {
                return false;
            }

            Object propertyValue = map.get(property);
            BaseSchema propertySchema = propertySchemas.get(property);

            if (propertyValue != null && !propertySchema.isValid(propertyValue)) {
                return false;
            }
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
