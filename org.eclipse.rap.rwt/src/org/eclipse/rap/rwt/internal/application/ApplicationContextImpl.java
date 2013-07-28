/*******************************************************************************
 * Copyright (c) 2011, 2013 Frank Appel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Frank Appel - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package org.eclipse.rap.rwt.internal.application;

import org.eclipse.rap.rwt.internal.resources.ResourceDirectory;
import org.eclipse.rap.rwt.internal.resources.ResourceManagerImpl;
import org.eclipse.rap.rwt.internal.service.ApplicationStoreImpl;
import org.eclipse.rap.rwt.service.ApplicationContext;
import org.eclipse.rap.rwt.service.ResourceManager;


public class ApplicationContextImpl implements ApplicationContext {

  // TODO [fappel]: this allows to set a fake double of the resource manager for testing purpose.
  //                Think about a less intrusive solution.
  // [rst] made public to allow access from testfixture in OSGi (bug 391510)
  public static ResourceManager testResourceManager;

  // TODO [fappel]: this flag is used to skip resource registration. Think about
  //                a less intrusive solution.
  // [rst] made public to allow access from testfixture in OSGi (bug 391510)
  public static boolean skipResoureRegistration;

  // TODO [fappel]: this flag is used to skip resource deletion. Think about
  //                a less intrusive solution.
  // [rst] made public to allow access from testfixture in OSGi (bug 391510)
  public static boolean skipResoureDeletion;

  private final ApplicationStoreImpl applicationStore;
  private final ResourceManager resourceManager;
  private final ResourceDirectory resourceDirectory;

  public ApplicationContextImpl() {
    applicationStore = new ApplicationStoreImpl();
    resourceDirectory = new ResourceDirectory();
    resourceDirectory.configure( System.getProperty( "java.io.tmpdir" ) );
    resourceManager = new ResourceManagerImpl( resourceDirectory );
  }

  public void setAttribute( String name, Object value ) {
    applicationStore.setAttribute( name, value );
  }

  public Object getAttribute( String name ) {
    return applicationStore.getAttribute( name );
  }

  public void removeAttribute( String name ) {
    applicationStore.removeAttribute( name );
  }

//  public ResourceDirectory getResourceDirectory() {
//    return resourceDirectory;
//  }
//
  public ResourceManager getResourceManager() {
    return resourceManager;
  }

//  public SettingStoreManager getSettingStoreManager() {
//    return settingStoreManager;
//  }
//
//  public PhaseListenerRegistry getPhaseListenerRegistry() {
//    return phaseListenerRegistry;
//  }
//
//  public LifeCycleAdapterFactory getLifeCycleAdapterFactory() {
//    return lifeCycleAdapterFactory;
//  }
//
//  public ResourceRegistry getResourceRegistry() {
//    return resourceRegistry;
//  }
//

}
