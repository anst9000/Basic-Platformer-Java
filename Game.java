import javax.swing.JFrame;

public class Game
{
	public static void main( String[ ] args )
	{
		JFrame window = new JFrame( "Basic Platformer" );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setContentPane( new GamePanel( ) );
		window.pack( );
		window.setLocationRelativeTo( null );
		window.setVisible( true );
	}
}
