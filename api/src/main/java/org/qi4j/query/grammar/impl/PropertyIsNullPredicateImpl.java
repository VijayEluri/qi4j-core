/*
 * Copyright 2008 Alin Dreghiciu.
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
package org.qi4j.query.grammar.impl;

import org.qi4j.query.grammar.PropertyIsNullPredicate;
import org.qi4j.query.grammar.PropertyReference;

/**
 * Default {@link org.qi4j.query.grammar.PropertyIsNullPredicate} implementation.
 *
 * @author Alin Dreghiciu
 * @since March 28, 2008
 */
public class PropertyIsNullPredicateImpl<T>
    extends PropertyNullPredicateImpl<T>
    implements PropertyIsNullPredicate<T>
{

    /**
     * Constructor.
     *
     * @param propertyReference property reference; cannot be null
     * @throws IllegalArgumentException - If property reference is null
     */
    public PropertyIsNullPredicateImpl( final PropertyReference<T> propertyReference )
    {
        super( propertyReference );
    }

    @Override public String toString()
    {
        return new StringBuilder()
            .append( "( " )
            .append( propertyReference() )
            .append( " IS NULL )" )
            .toString();
    }

}