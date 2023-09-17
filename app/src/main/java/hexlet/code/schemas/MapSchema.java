package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {


    public MapSchema required() {
        addCheck(
                "required",
                value -> value != null && !isNullMap(value) && value instanceof Map<?, ?>
        );

        return this;
    }
    public MapSchema sizeof(int size) {
        addCheck(
                "sizeof",
                value -> {
                    if (value == null || isNullMap(value)) {
                        return false;
                    }
                    Map<?, ?> mapValue = (HashMap<?, ?>) value;
                    return mapValue.size() == size;
                }
        );
        return this;
    }
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(
                "shape",
                value -> {
                    if (value == null || isNullMap(value)) {
                        return false;
                    }
                    Map<?, ?> mapValue = (HashMap<?, ?>) value;
                    for (String property : schemas.keySet()) {
                        if (!mapValue.containsKey(property)) {
                            return false;
                        }

                        Object propertyValue = mapValue.get(property);
                        BaseSchema propertySchema = schemas.get(property);
                        if (propertyValue != null && !propertySchema.isValid(propertyValue)) {
                            return false;
                        }
                    }
                    return true;
                }
        );

        return this;
    }
    private static boolean isNullMap(Object obj) {
        if (obj instanceof Map<?, ?> map) {
            return map.isEmpty();
        }
        return false;
    }
}
