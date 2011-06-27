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

package org.apache.isis.viewer.wicket.ui.pages.entity;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.pages.PageAbstract;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;

/**
 * Web page representing an entity.
 */
@AuthorizeInstantiation("org.starobjects.wicket.roles.USER")
public class EntityPage extends PageAbstract {

    public EntityPage(final PageParameters pageParameters) {
        super(pageParameters, ComponentType.ENTITY);
        final IModel<?> model = new EntityModel(getPageParameters(), getOidStringifier());
        addChildComponents(model);
    }

    public EntityPage(final ObjectAdapter adapter) {
        super(new PageParameters(), ComponentType.ENTITY);
        final IModel<?> model = new EntityModel(adapter);
        addChildComponents(model);
    }

}
