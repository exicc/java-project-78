package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    @Test
    public void isValid() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(4);

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(10));
        assertTrue(schema.isValid("hexlet"));
    }
}
