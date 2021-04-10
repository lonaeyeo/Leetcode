package _Test.Interviews;

public interface Interface1 {

    //
    int inDo1();

    // 如果有default就得要实现方法体
    default void inDo2(){
        System.out.println("inDo2");
    }

}
