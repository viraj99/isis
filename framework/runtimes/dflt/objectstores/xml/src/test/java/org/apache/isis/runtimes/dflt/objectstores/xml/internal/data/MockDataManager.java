/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.runtimes.dflt.objectstores.xml.internal.data;

import java.util.Iterator;
import java.util.Vector;

import junit.framework.Assert;

import org.apache.isis.runtimes.dflt.runtime.persistence.ObjectNotFoundException;
import org.apache.isis.runtimes.dflt.runtime.persistence.oidgenerator.simple.SerialOid;
import org.apache.isis.runtimes.dflt.runtime.transaction.ObjectPersistenceException;

public class MockDataManager implements DataManager {
    private final Vector actions = new Vector();

    public void assertAction(final int i, final String action) {
        if (i >= actions.size()) {
            Assert.fail("No such action " + action);
        }
        // Assert.assertEquals(action, actions.elementAt(i));
    }

    public MockDataManager() {
        super();
    }

    public SerialOid createOid() throws PersistorException {
        return null;
    }

    @Override
    public void insertObject(final ObjectData data) throws ObjectPersistenceException {
    }

    @Override
    public boolean isFixturesInstalled() {
        return true;
    }

    @Override
    public void remove(final SerialOid oid) throws ObjectNotFoundException, ObjectPersistenceException {
        final ObjectDataVector vector = new ObjectDataVector();
        final Iterator i = actions.iterator();
        while (i.hasNext()) {
            final ObjectData data = (ObjectData) i.next();
            if (data.getOid().equals(oid)) {
                actions.remove(data);
            }
        }
    }

    @Override
    public void save(final Data data) throws ObjectPersistenceException {
        actions.addElement(data);
    }

    @Override
    public void shutdown() {
    }

    @Override
    public ObjectDataVector getInstances(final ObjectData pattern) {
        final ObjectDataVector vector = new ObjectDataVector();
        final Iterator i = actions.iterator();
        while (i.hasNext()) {
            final ObjectData data = (ObjectData) i.next();
            if (pattern.getSpecification().equals(data.getSpecification())) {
                vector.addElement(data);
            }
        }

        return vector;
    }

    @Override
    public Data loadData(final SerialOid oid) {
        final ObjectDataVector vector = new ObjectDataVector();
        final Iterator i = actions.iterator();
        while (i.hasNext()) {
            final ObjectData data = (ObjectData) i.next();
            if (data.getOid().equals(oid)) {
                return data;
            }
        }
        return null;
    }

    @Override
    public int numberOfInstances(final ObjectData pattern) {
        return actions.size();
    }

    @Override
    public String getDebugData() {
        return null;
    }

}
