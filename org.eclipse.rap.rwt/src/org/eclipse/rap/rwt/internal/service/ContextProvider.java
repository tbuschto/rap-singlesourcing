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
import org.eclipse.rap.rwt.service.UISession;


/**
 * This class provides application-wide access to the context of the currently processed request.
 * <p>
 * It is possible to register a context to a thread other than the request thread (i.e. not the
 * current thread). This is useful to enable background processes to access data stored in a UI
 * session in the same way as in the request thread.
 * </p>
 * <p>
 * Note: In case that a context was already added using the <code>setContext(ServiceContext)</code>
 * method and it's tried to add another context using <code>setContext(ServiceContext,Thread)</code>
 * no exception will be thrown. That's because a check is not possible due to implementation
 * details. In such a case the context added with <code>setContext(ServiceContext)</code> will be
 * preferred.
 * </p>
 */
public class ContextProvider {

  private static ServiceContext context;

  /**
   * Returns the service context mapped to the currently processed request.
   */
  public static ServiceContext getContext() {
    if( context == null ) {
      context = new ServiceContext();
    }
    return context;
  }

  /**
   * Returns whether the current thread has a mapped service context.
   */
  public static boolean hasContext() {
    return true;
  }

  /**
   * Returns the UI session that is associated with the currently processed request.
   */
  public static UISession getUISession() {
    return getContext().getUISession();
  }

  /**
   * Returns the application context that is associated with the currently processed request.
   */
  public static ApplicationContextImpl getApplicationContext() {
    return getContext().getApplicationContext();
  }

}
