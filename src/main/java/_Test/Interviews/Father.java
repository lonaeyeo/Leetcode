package _Test.Interviews;

public abstract class Father {
    // 抽象类的抽象方法必须在子类实现，否则子类不能编译通过

    private int v;

    public Father() {

    }

    public Father(int v) {
        this.v = v;
    }

    public void myPrint() {
        System.out.println(v);
    }

    public void fDo1() {
        System.out.println("fdo1");
    }

    abstract void fDo2();
}

abstract class Father1 {
    public static void meow() {
        System.out.println("meow");
    }
}
