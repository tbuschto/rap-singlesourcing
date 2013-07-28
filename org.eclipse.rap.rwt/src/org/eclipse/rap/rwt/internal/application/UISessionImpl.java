package org.eclipse.rap.rwt.internal.application;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.rap.rwt.client.Client;
import org.eclipse.rap.rwt.internal.util.ParamCheck;
import org.eclipse.rap.rwt.service.UISession;
import org.eclipse.rap.rwt.service.UISessionListener;


public final class UISessionImpl implements UISession {

  private final Map<String, Object> attributes;
//  private final Set<UISessionListener> listeners;
  private boolean bound;
  private transient ApplicationContextImpl applicationContext;

  public UISessionImpl() {
    applicationContext = new ApplicationContextImpl();
    attributes = new HashMap<String, Object>();
//    listeners = new HashSet<UISessionListener>();
  }

  public ApplicationContextImpl getApplicationContext() {
    return applicationContext;
  }

  public Object getAttribute( String name ) {
    ParamCheck.notNull( name, "name" );
    Object result = null;
    result = attributes.get( name );
    return result;
  }

  public boolean setAttribute( String name, Object value ) {
    ParamCheck.notNull( name, "name" );
    boolean result = false;
    if( bound ) {
      result = true;
      removeAttributeInternal( name );
      attributes.put( name, value );
    }
    return result;
  }

  public boolean removeAttribute( String name ) {
    ParamCheck.notNull( name, "name" );
    boolean result = false;
    if( bound ) {
      result = true;
      removeAttributeInternal( name );
    }
    return result;
  }

  public Enumeration<String> getAttributeNames() {
    return createAttributeNameEnumeration();
  }

  public Client getClient() {
    return null;
  }

  public Locale getLocale() {
    return Locale.getDefault();
  }

  public void setLocale( Locale locale ) {
    throw new UnsupportedOperationException();
  }

  public void exec( Runnable runnable ) {
    throw new UnsupportedOperationException();
  }

  public boolean addSessionStoreListener( UISessionListener listener ) {
    throw new UnsupportedOperationException();
  }

  public boolean addUISessionListener( UISessionListener listener ) {
    throw new UnsupportedOperationException();
  }

  public boolean removeSessionStoreListener( UISessionListener listener ) {
    throw new UnsupportedOperationException();
  }

  public boolean removeUISessionListener( UISessionListener listener ) {
    throw new UnsupportedOperationException();
  }

  private void removeAttributeInternal( String name ) {
    attributes.remove( name );
  }

  private Enumeration<String> createAttributeNameEnumeration() {
    Set<String> names = new HashSet<String>( attributes.keySet() );
    final Iterator<String> iterator = names.iterator();
    return new Enumeration<String>() {
      public boolean hasMoreElements() {
        return iterator.hasNext();
      }
      public String nextElement() {
        return iterator.next();
      }
    };
  }

}
