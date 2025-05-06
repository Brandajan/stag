package pro1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main6Test
{
    @Test
    void test01()
    {
        long result = Main6.idOfBestTeacher("KIKM", 2024);
        assertTrue(result > 0, "id nejlepšího učitele musí být kladné");
        ;
    }
}