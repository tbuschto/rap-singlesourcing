/*******************************************************************************
 * Copyright (c) 2002, 2013 Innoopract Informationssysteme GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.internal.service;

import org.eclipse.rap.rwt.internal.application.ApplicationContextImpl;
import org.eclipse.rap.rwt.internal.application.UISessionImpl;
import org.eclipse.rap.rwt.service.UISession;


/**
 * Encapsulates access to the currently processed request, response and other status information.
 * After a request's lifecycle has expired the corresponding ServiceContext will be disposed and
 * throws IllegalStateException when accessed.
 */
public final class ServiceContext {

  private UISessionImpl uiSession;
  private ApplicationContextImpl applicationContext;
  private boolean disposed;

  public ServiceContext() {
    this.uiSession = new UISessionImpl();
    this.applicationContext = ( ApplicationContextImpl )uiSession.getApplicationContext();
  }


  public UISession getUISession() {
    checkState();
    return uiSession;
  }

  public ApplicationContextImpl getApplicationContext() {
    checkState();
    if( applicationContext != null ) {
      return applicationContext;
    }
    return null;
  }

  public void dispose() {
    disposed = true;
  }

  public boolean isDisposed() {
    return disposed;
  }

  private void checkState() {
    if( disposed ) {
      throw new IllegalStateException( "The context has been disposed." );
    }
  }

}
