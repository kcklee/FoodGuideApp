package exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateNameExceptionTest {

    private DuplicateNameException dne;

    @BeforeEach
    public void setup() {
        dne = new DuplicateNameException();
    }

    @Test
    public void testConstructor() {
        assertNull(dne.getMessage());
    }
}
