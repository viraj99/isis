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
package org.apache.isis.viewer.xhtml.viewer.authentication;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.runtimes.dflt.runtime.authentication.exploration.AuthenticationRequestExploration;
import org.apache.isis.runtimes.dflt.runtime.system.context.IsisContext;
import org.apache.isis.runtimes.dflt.webapp.auth.AuthenticationSessionLookupStrategyDefault;

public class AuthenticationSessionLookupStrategyTrusted extends AuthenticationSessionLookupStrategyDefault {

    @Override
    public AuthenticationSession lookup(final ServletRequest servletRequest, final ServletResponse servletResponse) {
        final AuthenticationSession session = super.lookup(servletRequest, servletResponse);
        if (session != null) {
            return session;
        }

        // will always succeed.
        final AuthenticationRequestExploration request = new AuthenticationRequestExploration();
        return IsisContext.getAuthenticationManager().authenticate(request);
    }
}
