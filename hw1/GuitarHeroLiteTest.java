<<<<<<< HEAD

import org.junit.Test;
import org.junit.Assert;
=======
import org.junit.Test;
import org.junit.Assert;

>>>>>>> temp_hw1
public class GuitarHeroLiteTest {
    GuitarHeroLite ghl = new GuitarHeroLite();

    @Test
<<<<<<< HEAD
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
=======
    public void getOrderTest(){
        int exp = 1;
        int ret = ghl.getOrder('2');
        Assert.assertEquals(exp, ret);
    }
>>>>>>> temp_hw1
}
