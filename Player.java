import java.awt.*;

public class Player {

  private double x;
  private double y;
  private double dx;
  private double dy;

  private int width;
  private int height;

  private boolean left;
  private boolean right;
  private boolean jumping;
  private boolean falling;

  private double moveSpeed;
  private double maxSpeed;
  private double maxFallingSpeed;
  private double stopSpeed;
  private double jumpStart;
  private double gravity;

  private TileMap tileMap;

  public Player(TileMap tm)
  {
    tileMap = tm;

    width = 20;
    height = 20;

    moveSpeed = 0.6;
    maxSpeed = 4.2;
    maxFallingSpeed = 12.0;
    stopSpeed = 0.30;
    jumpStart = -11.0;
    gravity = 0.64;
  }

  public void setLeft(boolean b) { left = b; }
  public void setRight(boolean b) { right = b; }
  public void setJumping(boolean b)
  {
    if (!falling) {
      jumping = b;
    }
  }

  //////////////////////////////////////////////////

  public void update()
  {
    // Determine next position
    if (left) {
      dx -= moveSpeed;
      if (dx < -maxSpeed) {
        dx = -maxSpeed;
      }
    } else if(right) {
      dx += moveSpeed;
      if (dx > maxSpeed) {
        dx = maxSpeed;
      }
    } else {
      if (dx > 0) {
        dx -= stopSpeed;
        if (dx < 0) {
          dx = 0;
        }
      } else if (dx < 0) {
        dx += stopSpeed;
        if (dx > 0) {
          dx = 0;
        }
      }
    }

    if (jumping) {
      dy = jumpStart;
      falling = true;
      jumping = false;
    }

    if (falling) {
      dy += gravity;
      if (dy > maxFallingSpeed) {
        dy = maxFallingSpeed;
      }
    } else {
      dy = 0;
    }

    // Check collisions
    int currCol = tileMap.getColTile((int) x);
    int currRow = tileMap.getRowTile((int) y);

    double tox = x + dx;
    double toy = y + dy;

    double tempx = x;
    double tempy = y;


  }

  public void draw(Graphics2D g)
  {
    int tx = tileMap.getx();
    int ty = tileMap.gety();

    g.setColor(Color.RED);
    g.fillRect(
      (int) (tx + x - width / 2),
      (int) (ty + y - height / 2),
      width, height
    );
  }
}
