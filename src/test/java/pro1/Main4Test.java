package pro1;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main4Test
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void test01()
    {
        Main4.printShortestEmails("KIKM",5);

        Main4.printShortestEmails("KIKM",5);
        assertTrue(outContent.toString().split("\n").length <= 5, "Mělo by být vypsáno maximálně 5 e-mailů");
        assertTrue(outContent.toString().contains("@"), "Výstup musí obsahovat znak '@' (v e-mailech)");
        ;
    }
}