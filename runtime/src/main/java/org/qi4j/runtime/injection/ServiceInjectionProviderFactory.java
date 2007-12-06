/*
 * Copyright (c) 2007, Rickard �berg. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.qi4j.runtime.injection;

import java.util.Map;
import org.qi4j.spi.dependency.InjectionContext;
import org.qi4j.spi.dependency.InjectionProvider;
import org.qi4j.spi.dependency.InjectionProviderException;
import org.qi4j.spi.dependency.InjectionProviderFactory;
import org.qi4j.spi.dependency.InjectionResolution;
import org.qi4j.spi.dependency.InvalidInjectionException;
import org.qi4j.spi.dependency.ServiceProvider;

public class ServiceInjectionProviderFactory
    implements InjectionProviderFactory
{
    public InjectionProvider newInjectionProvider( InjectionResolution resolution ) throws InvalidInjectionException
    {
        return new ServiceInjectionProvider( resolution );
    }

    static class ServiceInjectionProvider
        implements InjectionProvider
    {
        private InjectionResolution injectionResolution;

        public ServiceInjectionProvider( InjectionResolution injectionResolution )
        {
            this.injectionResolution = injectionResolution;
        }

        public Object provideInjection( InjectionContext context ) throws InjectionProviderException
        {
            Map<Class, ServiceProvider> serviceProviders = context.getModuleBinding().getModuleResolution().getModuleModel().getServiceProviders();
            ServiceProvider provider = serviceProviders.get( injectionResolution.getInjectionModel().getInjectionClass() );
            Object service = provider.getService( injectionResolution, context );
            return service;
        }
    }
}