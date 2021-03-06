/*
 * Copyright 2008 Niclas Hedhman. All rights Reserved.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package org.qi4j.bootstrap;

import org.qi4j.api.structure.Application;

/**
 * An application assembly. This can be used by Assemblers to programmatically
 * set the name of the application and create new layers.
 */
public interface ApplicationAssembly
{
    /**
     * Create a new layer assembly
     *
     * @param name of the new layer
     *
     * @return a LayerAssembly instance
     */
    LayerAssembly layer( String name );

    /**
     * Get the currently set name of the application
     *
     * @return the name of the application
     */
    String name();

    /**
     * Get the currently set mode of the application
     *
     * @return the application mode
     */
    Application.Mode mode();

    /**
     * Set the name of the application
     *
     * @param name of the application
     *
     * @return the assembly
     */
    ApplicationAssembly setName( String name );

    /**
     * Set the version of the application. This can be in any format, but
     * most likely will follow the Dewey format, i.e. x.y.z.
     *
     * @param version of the application
     *
     * @return the assembly
     */
    ApplicationAssembly setVersion( String version );

    /**
     * Set the application mode. This will be set to "production" by default. You can
     * set the system property "mode" to either "development", "satisfiedBy" or "production"
     * to explicitly set the mode. If that is not an option, then call this method
     * during assembly to set the mode. The mode may then be queried by assemblers,
     * and they may assemble the application differentlly depending on this setting.
     *
     * @param mode the application mode
     *
     * @return the current application mode
     */
    ApplicationAssembly setMode( Application.Mode mode );

    ApplicationAssembly setMetaInfo( Object info );

    <ThrowableType extends Throwable> void visit( AssemblyVisitor<ThrowableType> visitor )
        throws ThrowableType;
}
