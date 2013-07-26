package org.eclipse.rap.swthelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class SnippetRunner {


  public static void main( String[] args ) {
    Class<?> snippet = null;
    // TODO : support features.json or own bundle
    if( args.length > 0 && args[ 0 ].endsWith( ".java" ) ) {
      String[] parts = args[ 0 ].split( "[\\/\\\\]" );
      String file = parts[ parts.length - 1 ];
      parts[ parts.length - 1 ] = file.substring( 0, file.length() - 5 );
      for( int i = 0; i < parts.length && snippet == null; i++ ) {
        String name = join( parts, ".", i  );
        System.out.println( name );
        try {
          ClassLoader classLoader = ClassLoader.getSystemClassLoader();
          snippet = classLoader.loadClass( name );
        } catch( ClassNotFoundException e ) {
        }
      }
      if( snippet == null ) {
        throw new IllegalArgumentException( "Java class not found" );
      }
      System.out.println( "success : " + snippet.getName() );
    } else {
      throw new IllegalArgumentException( "No java file given" );
    }
    try {
      createContents( snippet );
    } catch( InstantiationException e ) {
      throw new RuntimeException( e );
    } catch( IllegalAccessException e ) {
      throw new RuntimeException( e );
    } catch( InvocationTargetException e ) {
      throw new RuntimeException( e );
    } catch( NoSuchMethodException e ) {
      throw new RuntimeException( e );
    }
  }

  private static void createContents( Class<?> clazz )
    throws InstantiationException, IllegalAccessException, InvocationTargetException,
    NoSuchMethodException
  {
    Display display = new Display();
    Shell shell = new Shell( display );
    shell.setLayout( new GridLayout( 1, false ) );
    Object instance = clazz.newInstance();
    Class<?> params[] = { Composite.class };
    Object paramsObj[] = { shell };
    Method declaredMethod = clazz.getDeclaredMethod( "createContents", params );
    declaredMethod.setAccessible( true );
    declaredMethod.invoke( instance, paramsObj );
    shell.setSize( 640, 480 );
    shell.open();
    shell.layout();
    while( !shell.isDisposed() ) {
      if( !display.readAndDispatch() )
        display.sleep();
    }
    display.dispose();
  }

  private static String join( String[] parts, String glue, int start ) {
    StringBuilder result = new StringBuilder();
    for( int i = start; i < parts.length; i++ ) {
      result.append( parts[ i ] );
      if( i != parts.length -1 ) {
        result.append( "." );
      }
    }
    return result.toString();
  }

}
