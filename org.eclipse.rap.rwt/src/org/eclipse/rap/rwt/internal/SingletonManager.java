/*******************************************************************************
 * Copyright (c) 2011, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.internal.util.ClassUtil;
import org.eclipse.rap.rwt.service.UISession;
import org.eclipse.swt.internal.SerializableCompatibility;


public class SingletonManager implements SerializableCompatibility {

  private static final String ATTR_SINGLETON_MANAGER
    = SingletonManager.class.getName() + "#instance";

  public static void install( UISession uiSession ) {
    uiSession.setAttribute( ATTR_SINGLETON_MANAGER, new SingletonManager() );
  }

  public static SingletonManager getInstance( UISession uiSession ) {
    return ( SingletonManager )uiSession.getAttribute( ATTR_SINGLETON_MANAGER );
  }

  private final Map<Class,Object> singletons;

  private SingletonManager() {
    singletons = Collections.synchronizedMap( new HashMap<Class,Object>() );
  }

  @SuppressWarnings("unchecked")
  public <T> T getSingleton( Class<T> type ) {
    T result = ( T )singletons.get( type );
    if( result == null ) {
      result = ClassUtil.newInstance( type );
      singletons.put( type, result );
    }
    return result;
  }

}
