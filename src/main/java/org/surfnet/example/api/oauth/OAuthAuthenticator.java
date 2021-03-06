/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.surfnet.example.api.oauth;

import org.surfnet.example.api.model.AccessToken;
import org.surfnet.example.api.model.ClientDetails;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

/**
 * Responsible for looking up {@link ClientDetails} instances based on
 * {@link AccessToken} values
 * 
 */
public class OAuthAuthenticator implements Authenticator<String, ClientDetails> {

  private OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore;

  public OAuthAuthenticator(OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore) {
    super();
    this.tokenStore = tokenStore;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.yammer.dropwizard.auth.Authenticator#authenticate(java.lang.Object)
   */
  @Override
  public Optional<ClientDetails> authenticate(String bearer) throws AuthenticationException {
    /*
     * Hook for the application to enforce that a REST call can only access the
     * data for the student that authorized the client application (which we
     * currently don't do)
     */
    return tokenStore.getClientDetailsByAccessToken(bearer);
  }
}
