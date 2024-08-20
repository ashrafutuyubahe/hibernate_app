package com.ashify;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class AppInitializer {

    public static void main(String[] args) {
        // Create the service registry
        StandardServiceRegistry standardServiceRegistry = getStandardServiceRegistryBuilder().build();

        // Create MetadataSources
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);

        // Create Metadata
        Metadata metadata = metadataSources.getMetadataBuilder().build();

        // Create SessionFactory
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // Use try-with-resources to automatically close the session
        try (Session session = sessionFactory.openSession()) {
            // Execute a query to test the connection
            Object obj = session.createNativeQuery("SELECT NOW()").getSingleResult();
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the SessionFactory
            sessionFactory.close();
        }
    }

    private static StandardServiceRegistryBuilder getStandardServiceRegistryBuilder() {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> databaseConfiguration = new HashMap<>();

        // Database configuration settings
        databaseConfiguration.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_app?createDatabaseIfNotExist=true&useSSL=false");
        databaseConfiguration.put(Environment.USER, "root");
        databaseConfiguration.put(Environment.PASS, "123");
        databaseConfiguration.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        databaseConfiguration.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
        databaseConfiguration.put(Environment.SHOW_SQL, "true");
        databaseConfiguration.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        databaseConfiguration.put(Environment.HBM2DDL_AUTO, "update");

        standardServiceRegistryBuilder.applySettings(databaseConfiguration);
        return standardServiceRegistryBuilder;
    }
}
