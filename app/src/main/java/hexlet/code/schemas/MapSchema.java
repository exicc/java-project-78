package hexlet.code.schemas;


import java.util.Map;

public class MapSchema extends BaseSchema {


    public MapSchema required() {
        addCheck(
                "required",
                value -> value instanceof Map<?, ?> map && !map.isEmpty()
        );

        return this;
    }
    public MapSchema sizeof(int size) {
        addCheck(
                "sizeof",
                value -> value instanceof Map<?, ?> map && map.size() == size
        );
        return this;
    }
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(
                "shape",
                value -> schemas.entrySet().stream().allMatch(e -> {
                    Object v = ((Map<?, ?>) value).get(e.getKey());
                    return e.getValue().isValid(v);
                })
        );
        return this;
    }
}
