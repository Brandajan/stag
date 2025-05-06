package pro1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main5Test
{
    @Test
    void test01()
    {

        String result = Main5.roomsSummary("KIKM", 2024);
        assertNotNull(result, "Výpis učeben nesmí být null");
        assertTrue(result.contains(","), "Výpis učeben by měl obsahovat čárku");
    }
}