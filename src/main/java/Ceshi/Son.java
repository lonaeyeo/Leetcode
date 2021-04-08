package Ceshi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Son extends Father {
    final List<Integer> list = new ArrayList<>();

    public Son() {

    }

    public void myPrint() {
        System.out.println();
    }

    static void do1() {
        return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split("\\*|-|/|\\+");
        for (String str : ss) {
            System.out.print(str + " ");
        }
        //
//        Son son = new Son();
//        son.myPrint();
//        System.out.println(son.list.size());
//        son.list.add(1);
//        System.out.println(son.list.size());
//        System.out.println(Math.round(-1.5));
//        double a = -1.5;
//        System.out.println((int) a / 1);
    }


    // 翻转字符串
    @Test
    public void sbTest() {
        StringBuilder sb = new StringBuilder("iloveyou");
        sb.reverse();
        System.out.println(sb);
        String str = "love";
        System.out.println(str.replace('o', '0'));
    }

    //
    @Test
    public void test() {

    }
}

