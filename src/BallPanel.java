import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BallPanel extends Panel implements Runnable, ActionListener {
    private List<Ball> ballList;

    public BallPanel() {
        ballList = new ArrayList<>();
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Ball ball : ballList) {
            ball.draw(g);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                repaint();
                Thread.sleep(33);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        ballList.add(new ThreadBall(x, y) {
            @Override
            public boolean collidingX() {
                return x + vx - 5 < 0 || x + vx + 5 > BallPanel.this.getWidth();
            }

            @Override
            public boolean collidingY() {
                return y + vy - 5 < 0 || y + vy + 5 > BallPanel.this.getHeight();
            }
        });

        ballList.add(new ScheduledBall(x, y) {
            @Override
            public boolean collidingX() {
                return x + vx - 5 < 0 || x + vx + 5 > BallPanel.this.getWidth();
            }

            @Override
            public boolean collidingY() {
                return y + vy - 5 < 0 || y + vy + 5 > BallPanel.this.getHeight();
            }
        });

        ballList.add(new VThreadBall(x, y) {
            @Override
            public boolean collidingX() {
                return x + vx - 5 < 0 || x + vx + 5 > BallPanel.this.getWidth();
            }

            @Override
            public boolean collidingY() {
                return y + vy - 5 < 0 || y + vy + 5 > BallPanel.this.getHeight();
            }
        });

        ballList.add(new VsThreadBall(x, y) {
            @Override
            public boolean collidingX() {
                return x + vx - 5 < 0 || x + vx + 5 > BallPanel.this.getWidth();
            }

            @Override
            public boolean collidingY() {
                return y + vy - 5 < 0 || y + vy + 5 > BallPanel.this.getHeight();
            }
        });
    }

    public void shake() {
        for (Ball ball : ballList) {
            ball.wakeUp();
        }
    }
}
