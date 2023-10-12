package org.acme;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.DatatypeConverter;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    static {
            StringBuilder b = new StringBuilder("Security providers: \n");
            for (Provider p : Security.getProviders()) {
                b.append(String.format(" - %s/%s : configured: %s", p.getName(), p.getVersionStr(), p.isConfigured()));
                b.append("\n");
            }
            LOG.info(b.toString());
    }

    @GET
    @RolesAllowed("user")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(new byte[]{1, 2, 3});
            java.security.SecureRandom random = new java.security.SecureRandom();
            return String.format("Hello MD5: %s, SecureRandom provider: %s, MessageDigest provider: %s",
                DatatypeConverter.printHexBinary(digest).toUpperCase(),
                random.getProvider().getName(),
                MessageDigest.getInstance("MD5").getProvider());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
