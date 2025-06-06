/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.docker.compose.service.connection.ldap;

import org.springframework.boot.autoconfigure.ldap.LdapConnectionDetails;
import org.springframework.boot.docker.compose.service.connection.test.DockerComposeTest;
import org.springframework.boot.testsupport.container.TestImage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link LLdapDockerComposeConnectionDetailsFactory}.
 *
 * @author Eddú Meléndez
 */
class LLdapDockerComposeConnectionDetailsFactoryIntegrationTests {

	@DockerComposeTest(composeFile = "lldap-compose.yaml", image = TestImage.LLDAP)
	void runCreatesConnectionDetails(LdapConnectionDetails connectionDetails) {
		assertThat(connectionDetails.getUsername()).isEqualTo("cn=admin,ou=people,dc=springframework,dc=org");
		assertThat(connectionDetails.getPassword()).isEqualTo("somepassword");
		assertThat(connectionDetails.getBase()).isEqualTo("dc=springframework,dc=org");
		assertThat(connectionDetails.getUrls()).hasSize(1);
		assertThat(connectionDetails.getUrls()[0]).startsWith("ldap://");
	}

}
