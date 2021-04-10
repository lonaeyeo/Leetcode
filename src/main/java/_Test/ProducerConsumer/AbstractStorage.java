package _Test.ProducerConsumer;

public interface AbstractStorage {
    void consume(int num);

    void produce(int num);
}
