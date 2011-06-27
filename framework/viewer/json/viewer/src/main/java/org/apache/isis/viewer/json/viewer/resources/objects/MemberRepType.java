/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.isis.viewer.json.viewer.resources.objects;

import org.apache.isis.core.metamodel.spec.feature.ObjectMember;
import org.apache.isis.viewer.xhtml.applib.resources.DomainObjectResource;

/**
 * Whether the representation of an {@link ObjectMember} (as generated by
 * the subclasses of {@link AbstractMemberRepBuilder}) is to be a {@link #STANDALONE} (eg
 * for {@link DomainObjectResource#propertyDetails(String, String)}, 
 * {@link DomainObjectResource#accessCollection(String, String)}, or
 * {@link DomainObjectResource#actionPrompt(String, String)}), or is
 * {@link #INLINE} within the representation of the {@link DomainObjectResource#object(String)}).
 * 
 * <p>
 * {@link #STANDALONE Standalone} representations are more detailed (eg contain links to
 * describe how to modify the member), while {@link #INLINE inlined} representations
 * contain links to the {@link #STANDALONE} representation. 
 */
public enum MemberRepType {
    
    STANDALONE,
    INLINE;

    public boolean hasSelf() {
        return this == STANDALONE;
    }

    public boolean hasMutators() {
        return this == STANDALONE;
    }

    public boolean hasLinkToDetails() {
        return this == INLINE;
    }

    public boolean hasValue(MemberType memberType) {
        return memberType.isProperty() || memberType.isCollection() && isStandalone();
    }

    private boolean isStandalone() {
        return this == STANDALONE;
    }


}
