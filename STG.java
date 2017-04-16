
import java.awt.Graphics;
import java.awt.Image;

public class STG extends javax.swing.JPanel implements Runnable{
	
	public static void main( String[] args ){
		new STG( );
	}
	
	public static final int CLIENT_WIDTH 	= 640;
	public static final int CLIENT_HEIGHT	= 480;
	
	private javax.swing.JFrame	frame;
	
	private Image 				backBuffer;
	
	private Player				player;
	
	public STG( ){
		initFrame( );
		backBuffer = createImage( CLIENT_WIDTH, CLIENT_HEIGHT );
		
		player = new Player( );
		frame.addKeyListener( Input.getInstance() );

		Thread mainLoop = new Thread( this );
		mainLoop.start( );
	}
	
	@Override
	public void run( ){
		while( true ){
			//	Game loop
			Input input = Input.getInstance( );
			if( input.getKeyState(Input.KeyConfig.RIGHT).isPressed() ) 	player.moveRight( ); 
			if( input.getKeyState(Input.KeyConfig.LEFT).isPressed() ) 	player.moveLeft( ); 
			if( input.getKeyState(Input.KeyConfig.UP).isPressed() ) 		player.moveUp( ); 
			if( input.getKeyState(Input.KeyConfig.DOWN).isPressed() ) 	player.moveDown( ); 
			player.update( );
			
			input.update( );
			repaint( );
			try{ Thread.sleep(16); }catch( InterruptedException e ){ }
		}
	}
	
	@Override
	public void paint( java.awt.Graphics g ){
		Graphics bb = backBuffer.getGraphics( );
		/*   clear buffer with black color   */
		bb.setColor( java.awt.Color.BLACK );
		bb.fillRect( 0, 0, CLIENT_WIDTH, CLIENT_HEIGHT );
		
		if( player != null ) player.draw( bb, this );
		
		g.drawImage( backBuffer, 0, 0, this );
	}
	
	void initFrame( ){
		frame = new javax.swing.JFrame( );
		setPreferredSize( new java.awt.Dimension(CLIENT_WIDTH, CLIENT_HEIGHT) );
		frame.add( this );
		frame.pack( );
		
		frame.setLocationRelativeTo( null );
		
		frame.setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}