/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.atomos.service.impl.activator;

import java.util.Collections;
import java.util.Hashtable;

import org.atomos.service.contract.Echo;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

@org.osgi.annotation.bundle.Header(name = Constants.BUNDLE_ACTIVATOR, value = "${@class}")
@org.osgi.annotation.bundle.Requirement(namespace = "osgi.ee", filter = "(&(osgi.ee=JavaSE)(version=1.8))")
public class Activator implements BundleActivator
{

    @Override
    public void start(BundleContext context) throws Exception
    {
        Echo impl = (m) -> "impl.activator " + m;
        context.registerService(Echo.class, impl, new Hashtable<String, String>(
            Collections.singletonMap("type", "impl.activator")));
        System.out.println("Registered Echo service from activator.");
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        // Do nothing; unregistration happens automatically
    }

}
