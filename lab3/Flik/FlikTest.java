//import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FlikTest {
    @Test
    public void isSameNumberTest() {
        int a0 = 100;
        int b0 = 127;
        int a1 = 100;
        int b1 = 127;
        int c0 = 128;
        int c1 = 128;
        Integer d1 = 128;
        Integer d2 = 128;
        System.out.println(Flik.isSameNumber(a0, a1));
        System.out.println(Flik.isSameNumber(b0, b1));
        System.out.println(Flik.isSameNumber(c0, c1));
        System.out.println(d1 == d2);
    }
}
