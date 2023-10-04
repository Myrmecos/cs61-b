package synthesizer;
public interface BoundedQueue<T>{
    /*return size of queue*/
    int capacity();

    /* return number of items in queue*/
    int fillCount();

    /* add item to the back*/
    void enqueue(T x);

    /* return and delete item from the front*/
    T dequeue();

    /* return item in the front*/
    T peek();

    void printRb();

    default boolean isEmpty(){return (fillCount() == 0);}
        
    default boolean isFull(){
        return (fillCount() == capacity());
    }
}