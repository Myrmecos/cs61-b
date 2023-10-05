import org.junit.Test;
import org.junit.Assert;

public class GuitarHeroLiteTest {
    GuitarHeroLite ghl = new GuitarHeroLite();

    @Test
    public void getOrderTest(){
        int exp = 1;
        int ret = ghl.getOrder('2');
        Assert.assertEquals(exp, ret);
    }
}
