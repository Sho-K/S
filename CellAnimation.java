
import java.util.ArrayList;
import java.util.ListIterator;

import java.awt.Image;


public class CellAnimation{
	private ArrayList< Image > cell = new ArrayList< Image >( );
	private ListIterator< Image > iterator;
	private Image currentCell;
	private float count 	= 0.0f;
	private float period 	= 1.0f;
	private float interval	= 0.0f;
	
	public CellAnimation( Image[] cell, float period ){
		addCell( cell );
		setPeriod( period );
	}
	
	public CellAnimation( ){
		initParam( );
	}
	
	public Image getCurrentCell( ){
		return currentCell;
	}
	
	public void update( float deltaTime ){
		count += deltaTime;
		if( count >= interval ){
			if( iterator.hasNext() ) {
				currentCell = iterator.next( );
			}else{
				iterator = cell.listIterator( );
				currentCell = iterator.next( );
			}
			count = 0.0f;
		}
	}
	
	public void setPeriod( float period ){
		this.period = period;
		initParam( );
	}
	
	public void addCell( Image image ){
		cell.add( image );
		initParam( );
	}
	
	public void addCell( Image[] image ){
		for( Image i : image ) cell.add( i );
		initParam( );
	}
	
	void initParam( ){
		count = 0.0f;
		iterator = cell.listIterator( );
		if( iterator.hasNext() ) {
			interval = period / cell.size( );
			currentCell = iterator.next( );
		}else{
			interval = 0.0f;
		}
	}
}
