
import org.junit.Test;
import org.junit.Assert;
public class GuitarHeroLiteTest {
    @Test
    public void findCharOrderTest(){
        int exp = 1;
        int ret = GuitarHeroLite.findCharOrder('2');
        Assert.assertEquals(exp, ret);
    }

    @Test
    public void testExecTest(){
        GuitarHeroLite.testExec();
    }
}
