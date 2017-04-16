
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Drawable{
	public abstract void draw( Graphics g, ImageObserver observer );
}
