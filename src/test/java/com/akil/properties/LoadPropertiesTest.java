package com.akil.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LoadPropertiesTest {

    @Test
    public void shouldLoadJdbcUrlWithoutQuotes() {
        String url = LoadProperties.getProperty("db.url");
        assertNotNull(url);
        assertEquals("jdbc:mysql://127.0.0.1:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true", url.trim());
    }
}
