import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledBall extends Ball {
    protected static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    protected ScheduledFuture<?> scheduledFuture;

    public ScheduledBall(int x, int y) {
        super(x, y);
        synchronized (this) {
            scheduledFuture = scheduler.scheduleAtFixedRate(
                    this, 0, speed, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        super.draw(g);
    }

    @Override
    public synchronized void run() {
        if (collidingX()) {
            vx = -vx;
            x += vx;
            scheduledFuture.cancel(true);
        }

        if (collidingY()) {
            vy = -vy;
            y += vy;
            scheduledFuture.cancel(true);
        }

        x += vx;
        y += vy;
    }

    @Override
    public synchronized void wakeUp() {
        if (scheduledFuture.isCancelled()) {
            scheduledFuture = scheduler.scheduleAtFixedRate(
                    this, 0, speed, TimeUnit.MILLISECONDS);
        }
    }
}
