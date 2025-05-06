package pro1;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Main2Test
{
    @Test
    void test01() {

        long result = Main2.maxPersonsCount("KIKM", 2024);
        assertTrue(result >= 0, "Maximální počet přihlášených nesmí být záporný");
    }
}