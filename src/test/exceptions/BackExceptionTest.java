package exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackExceptionTest {

    private BackException be;

    @BeforeEach
    public void setup() {
        be = new BackException();
    }

    @Test
    public void testConstructor() {
        assertNull(be.getMessage());
    }
}
