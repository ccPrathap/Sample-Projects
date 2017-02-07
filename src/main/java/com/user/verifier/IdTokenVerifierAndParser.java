package com.user.verifier;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class IdTokenVerifierAndParser {
	
	private static final String GOOGLE_CLIENT_ID = "352911980755-le50v77c5ko7uhpm1b2m2smionut0sdt.apps.googleusercontent.com";

    public static GoogleIdToken.Payload getPayload (String tokenString) throws Exception {

        JacksonFactory jacksonFactory = new JacksonFactory();
        /*GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(newProxyTransport(), jacksonFactory);*/

        GoogleIdToken token = GoogleIdToken.parse(jacksonFactory, tokenString);

        /*if (googleIdTokenVerifier.verify(token)) {*/
            GoogleIdToken.Payload payload = token.getPayload();
            if (!GOOGLE_CLIENT_ID.equals(payload.getAudience())) {
            	System.out.println("Audience mismatch");
            } else if (!GOOGLE_CLIENT_ID.equals(payload.getAuthorizedParty())) {
            	System.out.println("Client ID mismatch");
            }
            return payload;
        /*} else {
        	System.out.println("Invalid ID token.");
        }
        
        return null;*/
    }
    
    static HttpTransport newProxyTransport() throws GeneralSecurityException, IOException {
        NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
        builder.trustCertificates(GoogleUtils.getCertificateTrustStore());
        builder.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.cognizant.com", 6050)));
        return builder.build();
      }

}
