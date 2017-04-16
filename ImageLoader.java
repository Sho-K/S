
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.HashMap;

public class ImageLoader{
	
	private static ImageLoader instance;
	private HashMap< Integer, BufferedImage > images = new HashMap< Integer, BufferedImage >( );
	
	private ImageLoader( ){}
	public static ImageLoader getInstance( ){
		return instance;
	}
	
	public int load( String filepath ){
		int key = filepath.hashCode( );
		
		if( images.containsKey(key) ) return key;
		try{
			File file = new File( filepath );
			BufferedImage image = ImageIO.read( file );
			images.put( key, image );
		}catch( IOException e ){
			
		}
		return key;
	}
	
	public BufferedImage get( int key ){
	}
}