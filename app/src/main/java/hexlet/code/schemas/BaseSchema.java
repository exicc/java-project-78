package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Map;
import java.util.HashMap;

public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }

    public final boolean isValid(Object object) {
        return checks.values().stream().allMatch(check -> check.test(object));
    }
}
