package com.codepractice;

import java.util.Scanner;
import java.util.Stack;

public class TwoStacksForQueue {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();

            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }

    static class MyQueue<T> {
        Stack<T> inputQueue = new Stack<>();
        Stack<T> outQueue = new Stack<>();

        public void enqueue(T next) {
            transferIfNeeded();
            inputQueue.push(next);
        }

        public void dequeue() {
            transferIfNeeded();
            outQueue.pop();
        }

        public T peek() {
            transferIfNeeded();
            return outQueue.peek();
        }

        private void transferIfNeeded(){
            if(outQueue.isEmpty()){
                while(!inputQueue.isEmpty()){
                    outQueue.push(inputQueue.pop());
                }
            }
        }
    }
}


