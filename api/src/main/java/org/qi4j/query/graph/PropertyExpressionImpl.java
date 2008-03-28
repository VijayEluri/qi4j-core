/*
 * Copyright 2008 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.qi4j.query.graph;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Default implementation of {@link PropertyExpression}.
 *
 * @author Alin Dreghiciu
 * @since March 25, 2008
 */
public class PropertyExpressionImpl
    implements PropertyExpression
{

    /**
     * Property name.
     */
    private final String name;
    /**
     * Interface that declared the property.
     */
    private final Class declaringType;
    /**
     * Property type.
     */
    private final Class type;
    /**
     * Traversed association.
     */
    private final AssociationExpression traversed;

    /**
     * Constructor.
     *
     * @param name          property name; cannot be null
     * @param declaringType type that declared the property; cannot be null
     * @param type;         property type
     */
    public PropertyExpressionImpl( final String name,
                                   final Class declaringType,
                                   final Class type )
    {
        this( name, declaringType, type, null );
    }

    /**
     * Constructor.
     *
     * @param name          property name; cannot be null
     * @param declaringType type that declared the property; cannot be null
     * @param type;         property type
     * @param traversed     traversed association
     */
    public PropertyExpressionImpl( final String name,
                                   final Class declaringType,
                                   final Class type,
                                   final AssociationExpression traversed )
    {
        this.name = name;
        this.declaringType = declaringType;
        this.type = type;
        this.traversed = traversed;
    }

    /**
     * Constructor.
     *
     * @param propertyMethod method that acts as property
     */
    public PropertyExpressionImpl( final Method propertyMethod )
    {
        this( propertyMethod, null );
    }


    /**
     * Constructor.
     *
     * @param propertyMethod method that acts as property
     * @param traversed      traversed association
     */
    public PropertyExpressionImpl( final Method propertyMethod,
                                   final AssociationExpression traversed )
    {
        name = propertyMethod.getName();
        declaringType = propertyMethod.getDeclaringClass();
        Type returnType = propertyMethod.getGenericReturnType();
        if( !( returnType instanceof ParameterizedType ) )
        {
            throw new UnsupportedOperationException( "Unsupported property type:" + returnType );
        }
        Type propertyTypeAsType = ( (ParameterizedType) returnType ).getActualTypeArguments()[ 0 ];
        if( !( propertyTypeAsType instanceof Class ) )
        {
            throw new UnsupportedOperationException( "Unsupported property type:" + propertyTypeAsType );
        }
        type = (Class) propertyTypeAsType;
        this.traversed = traversed;
    }

    /**
     * @see PropertyExpression#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * @see PropertyExpression#getDeclaringType()
     */
    public Class getDeclaringType()
    {
        return declaringType;
    }

    /**
     * @see PropertyExpression#getType()
     */
    public Class getType()
    {
        return type;
    }

    /**
     * @see PropertyExpression#getTraversedAssociation()
     */
    public AssociationExpression getTraversedAssociation()
    {
        return traversed;
    }

    @Override public String toString()
    {
        return new StringBuilder()
            .append( traversed == null ? "" : traversed.toString() + "." )
            .append( declaringType.getSimpleName() )
            .append( ":" )
            .append( name )
            .append( "()^^" )
            .append( type.getName() )
            .toString();
    }

}