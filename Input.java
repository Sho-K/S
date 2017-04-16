
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input implements java.awt.event.KeyListener{

	public enum KeyConfig{
		RIGHT( 0, KeyEvent.VK_D ),
		LEFT( 1, KeyEvent.VK_A ), 
		UP( 2, KeyEvent.VK_W ),
		DOWN( 3, KeyEvent.VK_S ),
		FIRE( 4, KeyEvent.VK_SPACE ),
		DECIDE( 5, KeyEvent.VK_ENTER ),
		CANCEL( 6, KeyEvent.VK_ESCAPE ),
		PAUSE( 7, KeyEvent.VK_P );
		
		public final int index;
		private int keycode;
		
		private KeyConfig( int index, int keycode ){
			this.index 	= index;
			this.keycode 	= keycode;
		}
		
		public int getKeyCode( ){
			return keycode;
		}
		
		public void setKeyCode( int keycode ){
			if( !contains(keycode) )
				this.keycode = keycode;
		}
		
		public static int getKeyCount( ){
			return KeyConfig.values( ).length;
		}
	
		public static boolean contains( int keycode ){
			for( KeyConfig key : KeyConfig.values() )
				if( key.getKeyCode() == keycode ) return true;
			
			return false;
		}
	}
	
	public class KeyState{
		public static final int NONE 		= 0;
		public static final int TYPED 		= 1;
		public static final int PRESSED 	= 2;
		public static final int RELEASED	= 3;
		
		private int state = NONE;
		
		public int getState( ){
			return state;
		}
		public void setState( int state ){
			this.state = state;
		}
		
		public boolean isPressed( ){
			return state == PRESSED || state == TYPED;
		}
		public boolean isReleased( ){ 
			return state == RELEASED || state == NONE;
		}
	}
	private KeyState[] keyStates;

	private static Input instance = new Input( );
	
	private Input( ){
		keyStates = new KeyState[KeyConfig.getKeyCount()];
		for( int i=0; i<keyStates.length; ++i ){
			keyStates[i] = new KeyState( );
		}
	}
	
	public static Input getInstance( ){
		if( instance == null ) instance = new Input( );
		return instance;
	}
	
	public KeyState getKeyState( KeyConfig key ){
		return keyStates[key.index];
	}
	public KeyState[] getAllKeyState( ){
		return keyStates;
	}
	
	public void update( ){
		for( KeyState keyState : keyStates ){
			if( keyState.getState() == KeyState.TYPED ) keyState.setState( KeyState.PRESSED );
			if( keyState.getState() == KeyState.RELEASED ) keyState.setState( KeyState.NONE );
		}
	}
	
	@Override
	public void keyPressed( KeyEvent e ){
		int keycode = e.getKeyCode( );
		for( KeyConfig key : KeyConfig.values() ){
			if( keycode != key.getKeyCode() ) continue;
			KeyState state = keyStates[key.index];
			if( state.getState() == KeyState.NONE ) state.setState( KeyState.TYPED );
		}
	}
	
	@Override
	public void keyReleased( KeyEvent e ){
		int keycode = e.getKeyCode( );
		for( KeyConfig key : KeyConfig.values() ){
			if( keycode != key.getKeyCode() ) continue;
			KeyState state = keyStates[key.index];
			state.setState( KeyState.RELEASED );
		}
	}
	
	@Override
	public void keyTyped( KeyEvent e ){
		
	}
}