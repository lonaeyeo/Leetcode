package _Test.Interviews;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Tencent {

    static void solve(PriorityQueue<Node> Q, int t[], int k) {
        int count = k;
        Node tmp;
        while (count-- > 0) {
            tmp = Q.poll();
            System.out.println(tmp.id);
            Q.add(new Node(tmp.time + t[tmp.id], tmp.id));
        }
    }

    public static void main(String args[]) {
        Scanner jin = new Scanner(System.in);

        int n = jin.nextInt(), k = jin.nextInt();
        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        int t[] = new int[n];
        for (int i = 0; i < n; ++i) {
            t[i] = jin.nextInt();
            Q.add(new Node(t[i], i));
        }
        solve(Q, t, k);

        jin.close();
    }
}

class Node implements Comparable<Node> {
    int time, id;

    Node() {
    }

    Node(int t, int _id) {
        this.time = t;
        this.id = _id;
    }

    @Override
    public int compareTo(Node rhs) {
        return this.time == rhs.time ? this.id - rhs.id : this.time - rhs.time;
    }
}