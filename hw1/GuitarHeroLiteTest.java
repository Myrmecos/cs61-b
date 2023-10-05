
import org.junit.Test;
import org.junit.Assert;
public class GuitarHeroLiteTest {
    GuitarHeroLite ghl = new GuitarHeroLite();

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

    @Test
    public void makeFreqListTest(){
        ghl.makeFreqList();
        ghl.printFreqList();

    }
    public void testMakeArray(){
        GuitarHeroLite ghl = new GuitarHeroLite();
        ghl.makeArray();

    }
}
