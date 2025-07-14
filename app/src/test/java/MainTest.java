import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void checkMainDependencies() {
        assertNotNull(Solution.br);
        assertNotNull(Solution.bw);
    }
}
