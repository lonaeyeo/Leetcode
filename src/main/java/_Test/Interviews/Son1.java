package _Test.Interviews;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Son1 extends Father1 implements Interface1 {

    // 父类方法是static，那么子类重写必须要static
    public static void meow() {
        System.out.println("son meow");
    }

    public static void main(String[] args) {
        Son1 son1 = new Son1();
        son1.meow();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(null, null);
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.print(entry.getKey());
            System.out.println(entry.getValue());
        }
//        String path = new File(son1.getClass().getResource("").getPath()).getAbsolutePath();
//        System.out.println(path);

        String path = new File("src\\main\\java").getAbsolutePath();
        System.out.println(path);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "\\test.txt")));
            for (int i = 0; i < 10; i++) {
                String temp = "sss";
                bw.write(temp);
                bw.flush();
            }
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(Files.exists(Paths.get("D:\\Work\\IDEAProjects\\AlgorithmProblems\\src\\main\\java\\Ceshi\\Father1.java")));
    }

    @Override
    public int inDo1() {
        return 0;
    }

    @Test
    public void test() {

    }
}
