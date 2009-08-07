/*
 * Copyright (c) 2009, Rickard Öberg. All Rights Reserved.
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

package org.qi4j.spi.entity.helpers;

import org.qi4j.api.concern.ConcernOf;
import org.qi4j.api.injection.scope.Service;
import org.qi4j.spi.entity.EntityState;
import org.qi4j.spi.entity.StateCommitter;
import org.qi4j.spi.unitofwork.StateChangeListener;

/**
 * JAVADOC
 */
public abstract class StateChangeNotificationConcern
    extends ConcernOf<EntityStoreSPI>
    implements EntityStoreSPI
{
    @Service Iterable<StateChangeListener> listeners;

    public StateCommitter apply(final Iterable<EntityState> state, String identity)
    {
        final StateCommitter committer = next.apply(state, identity);
        return new StateCommitter()
        {
            public void commit()
            {
                for (StateChangeListener listener : listeners)
                {
                    listener.notifyChanges(state);
                }
                committer.commit();
            }

            public void cancel()
            {
                committer.cancel();
            }
        };
    }

}