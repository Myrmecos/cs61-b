package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }
    @Test
    public void enqueueTest(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(3);
        for (int i = 0; i < 3; i++){
            arb.enqueue(i);
        }
        arb.print();
        arb.dequeue();
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
