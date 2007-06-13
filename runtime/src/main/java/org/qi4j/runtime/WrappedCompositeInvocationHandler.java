/*
 * Copyright 2007 Rickard Öberg
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.qi4j.runtime;

import java.lang.reflect.Method;

public final class WrappedCompositeInvocationHandler
    extends CompositeInvocationHandler
{
    private Object wrappedInstance;

    public WrappedCompositeInvocationHandler( Object wrappedInstance, CompositeContextImpl aContext )
    {
        super( aContext );
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance()
    {
        return wrappedInstance;
    }


    // InvocationHandler implementation ------------------------------
    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
    {
        if( Object.class.equals( method.getDeclaringClass() ) )
        {
            return method.invoke( wrappedInstance, args );
        }

        return super.invoke( proxy, method, args );
    }
}