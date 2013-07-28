/*******************************************************************************
 * Copyright (c) 2012, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.widgets;

import org.eclipse.rap.rwt.internal.util.ParamCheck;
import org.eclipse.swt.browser.Browser;

/**
 * Utility class to work with non-blocking browser script execution.
 *
 * @see Browser
 * @since 2.0
 */
public final class BrowserUtil {

  /**
   * Executes the given script in a non-blocking way. The <code>browserCallback</code> is notified
   * when the result from the operation is available.
   * <p>
   * Use this method instead of the <code>execute()</code> or <code>evaluate()</code> methods from
   * the respective <code>Browser</code> widget when running a <em>life cycle</em> without a
   * dedicated UI thread where <code>evaluate()</code> does not block the program execution flow.
   * </p>
   *
   * @param browser the browser to execute the script, must not be <code>null</code>.
   * @param script the script to execute, must not be <code>null</code>.
   * @param browserCallback the callback to be notified when the result from the script execution is
   * available, must not be <code>null</code>.
   *
   * @see Browser
   * @see BrowserCallback
   * @see org.eclipse.rap.rwt.application.Application.OperationMode
   */
  public static void evaluate( final Browser browser, final String script, BrowserCallback browserCallback ) {
    ParamCheck.notNull( browser, "browser" );
    ParamCheck.notNull( script, "script" );
    ParamCheck.notNull( browserCallback, "browserCallback" );
    browser.getDisplay().asyncExec( new Runnable() {
      public void run() {
        browser.evaluate( script );
      }
    } );
  }

  private BrowserUtil() {
    // prevent instantiation
  }
}