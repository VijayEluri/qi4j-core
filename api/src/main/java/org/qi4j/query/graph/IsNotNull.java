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
package org.qi4j.query.graph;

/**
 * Constraints that a {@link org.qi4j.property.Property} is not null (is set).
 *
 * @author Alin Dreghiciu
 * @since March 25, 2008
 */
public class IsNotNull<T>
    extends AbstractUnaryOperator<PropertyExpression<T>>
    implements BooleanExpression
{

    /**
     * Constructor.
     *
     * @param property property expression; cannot be null
     * @throws IllegalArgumentException - If property is null
     */
    public IsNotNull( final PropertyExpression<T> property )
    {
        super( property );
    }

    @Override public String toString()
    {
        return new StringBuilder( )
            .append( "( " )
            .append( getArgument() )
            .append( " IS NOT NULL )" )
            .toString();
    }

}