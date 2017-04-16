
import java.awt.Image;
import java.awt.Dimension;

import java.awt.geom.Point2D;

public class Player extends Character{
	
	private Point2D.Float 	currentVelocity = new Point2D.Float( 0.0f, 0.0f );
	private final float 	MOVE_SMOOTH		= 0.25f;
	private final float 	MOVE_SPEED 		= 10.0f;
	private int 			horizontalMove 	= 0;
	private int 			verticalMove	= 0;
	
	private CellAnimation[] animations;
	private int 			animationState;
	public static final int ANIMATION_FORWARD 		= 0;
	public static final int ANIMATION_RIGHT 		= 1;
	public static final int ANIMATION_LEFT 			= 2;
	public static final int ANIMATION_CLIP_COUNT	= 3;
	
	private static final int IMAGE_SEPARATE_COUNT_X = 4;
	private static final int IMAGE_SEPARATE_COUNT_Y = 3;
	
	private static final float	ANIM_PERIOD = 0.5f;
	
	public Player( ){
		loadImage( "./res/images/char/0.png" );
		size = new Dimension( 60, 60 );
		halfSize = new Dimension( size.width/2, size.height/2 );
		
		position.x = 400;
		position.y = 450;
		
		/*   separate source image   */
		final int cellW = srcImage.getWidth( ) / IMAGE_SEPARATE_COUNT_X;
		final int cellH = srcImage.getHeight( ) / IMAGE_SEPARATE_COUNT_Y;
	
		Image[][] separated = new Image[IMAGE_SEPARATE_COUNT_Y][IMAGE_SEPARATE_COUNT_X];
		for( int i=0; i<IMAGE_SEPARATE_COUNT_Y; ++i ){
			for( int j=0; j<IMAGE_SEPARATE_COUNT_X; ++j ){
				separated[i][j] = srcImage.getSubimage( j*cellW, i*cellH, cellW, cellH );
			}
		}
		
		/*   setup animations   */
		animations = new CellAnimation[ANIMATION_CLIP_COUNT];
		for( int i=0; i<ANIMATION_CLIP_COUNT; ++i )
			animations[i] = new CellAnimation( separated[i], ANIM_PERIOD );
	}
	
	public void update( ){
		/*   move   */
		velocity.x = MOVE_SPEED * horizontalMove;
		velocity.y = MOVE_SPEED * verticalMove;
		if( horizontalMove == 0 ) animationState = ANIMATION_FORWARD;
		
		position.x += (int)currentVelocity.x;
		position.y += (int)currentVelocity.y;
		
		position.x = Math.max( halfSize.width, Math.min(position.x, STG.CLIENT_WIDTH-halfSize.width) );
		position.y = Math.max( halfSize.height, Math.min(position.y, STG.CLIENT_HEIGHT-halfSize.height) );
		
		currentVelocity.x += (velocity.x-currentVelocity.x) * MOVE_SMOOTH;
		currentVelocity.y += (velocity.y-currentVelocity.y) * MOVE_SMOOTH;

		/*   animation   */
		animations[animationState].update( 0.017f );
		
		/*   reset state   */
		horizontalMove 	= 0;
		verticalMove 	= 0;
	}
	
	public void moveRight( ){
		horizontalMove += 1;
		animationState = ANIMATION_RIGHT;
	}
	public void moveLeft( ){
		horizontalMove -= 1;
		animationState = ANIMATION_LEFT;
	}
	public void moveUp( ){
		verticalMove -= 1;
	}
	public void moveDown( ){
		verticalMove += 1;
	}
	
	@Override
	public void draw( java.awt.Graphics g ){
		int x = position.x - halfSize.width;
		int y = position.y - halfSize.height;
		g.drawImage( animations[animationState].getCurrentCell(), x, y, size.width, size.height, null );
	}
}