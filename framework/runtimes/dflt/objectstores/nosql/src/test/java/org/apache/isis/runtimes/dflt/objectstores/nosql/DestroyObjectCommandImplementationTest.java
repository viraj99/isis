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

package org.apache.isis.runtimes.dflt.objectstores.nosql;

import java.util.Date;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.adapter.version.SerialNumberVersion;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.runtimes.dflt.objectstores.dflt.testsystem.TestProxySystemII;
import org.apache.isis.runtimes.dflt.runtime.persistence.oidgenerator.simple.SerialOid;
import org.apache.isis.runtimes.dflt.runtime.system.context.IsisContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DestroyObjectCommandImplementationTest {

    @Before
    public void setup() {
        Logger.getRootLogger().setLevel(Level.OFF);
        final TestProxySystemII system = new TestProxySystemII();
        system.init();
    }

    @Test
    public void testname() throws Exception {
        final ObjectSpecification specification =
            IsisContext.getSpecificationLoader().loadSpecification(ExampleReferencePojo.class);
        final ObjectAdapter object = IsisContext.getPersistenceSession().createInstance(specification);
        IsisContext.getPersistenceSession().makePersistent(object);
        object.setOptimisticLock(new SerialNumberVersion(3, "username", new Date(1000)));

        final long id = ((SerialOid) object.getOid()).getSerialNo();
        final String key = Long.toString(id, 16);

        final Mockery context = new Mockery();
        final NoSqlCommandContext commandContext = context.mock(NoSqlCommandContext.class);
        context.checking(new Expectations() {
            {
                one(commandContext).delete(specification.getFullIdentifier(), key, "3");
            }
        });

        final NoSqlDestroyObjectCommand command =
            new NoSqlDestroyObjectCommand(new SerialKeyCreator(), new SerialNumberVersionCreator(), object);
        command.execute(commandContext);
    }
}
