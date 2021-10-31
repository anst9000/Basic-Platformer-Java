import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;

public class TileMap
{
	private int x;
	private int y;

	private int tileSize;
	private int[ ][ ] map;
	private int mapWidth;
	private int mapHeight;
	private BufferedReader br;

	public TileMap( String fileName, int tileSize )
	{
		this.tileSize = tileSize;

		try
		{
			br = new BufferedReader( new FileReader( fileName ) );

			mapWidth = Integer.parseInt( br.readLine( ) );
			mapHeight = Integer.parseInt( br.readLine( ) );
			map = new int[ mapHeight ][ mapWidth ];

			String delimiters = " ";
			for ( int row = 0; row < mapHeight; row++ )
			{
				String line = br.readLine( );
				String[ ] tokens = line.split( delimiters );

				for ( int col = 0; col < mapWidth; col++ )
				{
					map [ row ] [ col ] = Integer.parseInt( tokens [ col ] );
				}
			}
		}
		catch ( Exception ex )
		{
		}
	}

  public int getx() { return x; }
  public int gety() { return y; }

  public void setx(int i) { x = i; }
  public void sety(int i) { y = i; }

  public int getColTile(int i) {
    return x / tileSize;
  }
  public int getRowTile(int i) {
    return y / tileSize;
  }
  //////////////////////////////////////////////////

  public void update()
	{

	}

	public void draw( Graphics2D g )
	{
		for ( int row = 0; row < mapHeight; row++ )
		{
			for ( int col = 0; col < mapWidth; col++ )
			{
				int rc = map [ row ] [ col ];

				if ( rc == 0 )
				{
					g.setColor( Color.BLACK );
				}
				if ( rc == 1 )
				{
					g.setColor( Color.WHITE );
				}

				g.fillRect( x + col * tileSize, y + row * tileSize, tileSize, tileSize );
			}
		}

	}
}
