package pro1;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class Main1Test
{
    @Test
    void test01()
    {

        long result = Main1.emptyActionsCount("KIKM", 2024);
        assertTrue(result >= 0, "Počet prázdných akcí nesmí být záporný");
        ;
    }
}