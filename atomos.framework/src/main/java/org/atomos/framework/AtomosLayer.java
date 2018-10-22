/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.atomos.framework;

import java.lang.module.Configuration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.osgi.framework.BundleException;

/**
 * An Atomos Layer represents a {@link ModuleLayer} that was added to
 * a {@link AtomosRuntime} using the {@link AtomosRuntime#addLayer(Configuration) addLayer}
 * method or the Atomos Layer could represent the {@link AtomosRuntime#getBootLayer() boot layer}.
 * An Atomos Layer will contain one or more {@link AtomosBundleInfo atomos bundles} which can
 * then be used to {@link AtomosBundleInfo#install(String) install } them as OSGi bundles into the
 * {@link AtomosRuntime#createFramework(java.util.Map) framework}.
 */
public interface AtomosLayer {
	/**
	 * The {@link ModuleLayer} associated with this Atomos Layer. If not running
	 * in a module layer then the optional will have a null value.
	 * @return the ModuleLayer or null if not running in a module layer
	 */
	Optional<ModuleLayer> getModuleLayer();

	/**
	 * The Atomos Layer children of this layer
	 * @return The children of this layer
	 */
	Set<AtomosLayer> getChildren();

	/**
	 * The Atomos parents of this layer
	 * @return the parnets of this layer
	 */
	List<AtomosLayer> getParents();

	/**
	 * The Atomos bundles contained in this layer
	 * @return the Atomos Bundles
	 */
	Set<AtomosBundleInfo> getAtomosBundles();

	/**
	 * The name of the Atomos Layer.  By default the Atomos Layer
	 * name is the empty string.  Atomos Layer names are not
	 * required to be unique.  All Atomos bundles contained in a
	 * layer will have {@link AtomosBundleInfo#getLocation() locations}
	 * that use the layer name as a prefix.  If the layer
	 * name is not the empty string then the location prefix will be
	 * the layer name followed by a colon ({@code :}).
	 * This allows two different layers to load the same module in
	 * different layers.
	 * @return the name of the layer
	 */
	String getName();

	/**
	 * Uninstalls this Atomos Layer along with any {@link #getChildren() children}
	 * layers.
	 * @throws BundleException 
	 */
	void uninstall() throws BundleException;
}
