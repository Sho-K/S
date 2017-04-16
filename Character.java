	
import java.io.File;
import java.io.IOException;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.awt.geom.Point2D;

import javax.imageio.ImageIO;

public abstract class Character implements Drawable{
	protected BufferedImage 	srcImage;
	
 	protected Dimension 		size;
	protected Dimension 		halfSize;
	protected Point				position;
	protected Point.Float 		velocity;
	
	protected boolean 			moving = false;
	
	protected Character( ){
		position = new Point( 0, 0 );
		velocity = new Point2D.Float( 0.0f, 0.0f );
	}
	
	protected void loadImage( String imageFilepath ){	
		try{
			File file = new File( imageFilepath );
			srcImage = ImageIO.read( file );
		}catch( IOException e ){
			System.out.println( e.getMessage() );
		}
	}
	
	public void move( float vx, float vy ){
		velocity.x = vx;
		velocity.y = vy;
		moving = true;
	}
	
	public void stopMoving( ){
		velocity.x = 0.0f;
		velocity.y = 0.0f;
		moving = false;
	}
}
