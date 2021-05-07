package _Test.InputOutput;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedInputStream(System.in));
        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = s.nextInt();
//            System.out.print(arr[i] + " ");
//        }

        int m = 1 << 5;
        int n = 5 << 1;
        System.out.println(m);
        System.out.println(n);

//        String[] str = s.nextLine().split(" ");
//
//        for (int i = 0; i < 10; i++) {
//            arr[i] = Integer.valueOf(str[i]);
//        }
//        System.out.println(Arrays.toString(arr));

//        int a;
//        int b;
//        while (s.hasNext()) {
//            a = s.nextInt();
//            if (a == 99999)
//                break;
//            b = s.nextInt();
//            System.out.print(a+b);
//            System.out.println(" -");
//        }
        String str;
        str = s.nextLine();
        str = s.next();
        System.out.println(str);
        String path = new File("abs").getAbsolutePath();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "test.txt")));
            for (int i = 0; i < 10; i++) {
                String temp = s.nextLine();
                bw.write(temp);
                bw.flush();
            }
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
