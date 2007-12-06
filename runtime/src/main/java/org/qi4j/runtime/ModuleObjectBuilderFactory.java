/*
 * Copyright (c) 2007, Rickard Öberg. All Rights Reserved.
 * Copyright (c) 2007, Niclas Hedhman. All Rights Reserved.
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
package org.qi4j.runtime;

import org.qi4j.composite.InvalidApplicationException;
import org.qi4j.composite.ObjectBuilder;
import org.qi4j.composite.ObjectBuilderFactory;
import org.qi4j.runtime.composite.ObjectContext;
import org.qi4j.runtime.structure.ModuleContext;

/**
 * Default implementation of ObjectBuilderFactory
 */
public final class ModuleObjectBuilderFactory
    implements ObjectBuilderFactory
{
    private ModuleContext moduleContext;

    public ModuleObjectBuilderFactory( ModuleContext moduleContext )
    {
        this.moduleContext = moduleContext;
    }

    public ObjectBuilder newObjectBuilder( Class objectType )
    {
        // Get the Object context
        ObjectContext objectContext = moduleContext.getObjectContext( objectType );

        // Check if this Composite has been registered properly
        if( objectContext == null )
        {
            throw new InvalidApplicationException( "Trying to create unregistered object of type " + objectType.getName() + " in module " + moduleContext.getModuleBinding().getModuleResolution().getModuleModel().getName() );
        }

        // Create a builder
        ObjectBuilder builder = new ObjectBuilderImpl( moduleContext, objectContext );
        return builder;
    }
}