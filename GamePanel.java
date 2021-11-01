import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	private final long MEGA = 1000000;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	private int FPS = 30;
	private int targetTime = 1000 / FPS;

	private TileMap tileMap;
  private Player player;

	public GamePanel()
	{
		super( );
		setPreferredSize( new Dimension( WIDTH, HEIGHT ) );
		setFocusable( true );
		requestFocus( );
	}

	@Override
	public void addNotify()
	{
		super.addNotify( );
		if ( thread == null )
		{
			thread = new Thread( this );
			thread.start( );
		}

		addKeyListener(this);
	}

	@Override
	public void run()
	{
		init( );

		long startTime;
		long urdTime;
		long waitTime;

		while ( running )
		{
			startTime = System.nanoTime( );

			update( );
			render( );
			draw( );

			urdTime = ( System.nanoTime( ) - startTime ) / MEGA;
			waitTime = targetTime - urdTime;

			try
			{
				Thread.sleep( waitTime );
			}
			catch ( Exception ex )
			{
			}
		}
	}

	public void init() {
		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		tileMap = new TileMap("testmap2.txt", 32);
		tileMap.loadTiles("graphics/tileset.gif");

		player = new Player(tileMap);
		player.setx(50);
		player.sety(85);
	}

	//////////////////////////////////////////////////////////////////////////

	private void update()
	{
		tileMap.update( );
    player.update();
	}

	private void render()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		tileMap.draw( g );
    player.draw(g);
	}

	private void draw()
	{
		Graphics g2 = getGraphics( );
		g2.drawImage( image, 0, 0, null );
		g2.dispose( );
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if (code == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if (code == KeyEvent.VK_SPACE) {
			player.setJumping();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if (code == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
