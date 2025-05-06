package pro1;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class Main3Test
{
    @Test
    void test01()
    {

        String email = Main3.emailOfBestTeacher("KIKM", 2024);
        assertNotNull(email, "E-mail nejlepšího učitele nesmí být null");
        assertTrue(email.contains("@"), "E-mail musí obsahovat znak '@'");
        ;
    }
}