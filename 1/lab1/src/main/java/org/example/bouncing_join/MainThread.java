package org.example.bouncing_join;

import java.awt.*;

public class MainThread extends Thread {
    private int count;
    private BallCanvas canvas;

    public MainThread(int count, BallCanvas canvas) {
        this.count = count;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        int redBallsCount = count / 2;

        if (count > 0) {
            BallThread[] threads = new BallThread[redBallsCount];
            for (int i = 0; i < threads.length; i++) {
                Ball ball = new Ball(Color.red, canvas);
                canvas.add(ball);

                BallThread thread = new BallThread(ball);
                threads[i] = thread;
            }

            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }

            for (int i = 0; i < threads.length; i++) {
                try {
                    threads[i].join();
                    System.out.println(threads[i].getName() + " has finished!!!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            threads = new BallThread[count - redBallsCount];
            for (int i = 0; i < threads.length; i++) {
                Ball ball = new Ball(Color.blue, canvas);
                canvas.add(ball);

                BallThread thread = new BallThread(ball);
                threads[i] = thread;
            }

            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        }
    }
}
