/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.core;

/**
 *
 * @author AE08464
 */
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Res {

    private static final String[] DEFAULT_RESOURCE_PATHS = {null, "src/main/resources", "src/test/resources"};

    public static URL getResource(String propFile) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(propFile);

        if (resource == null) {
            for (String resourcePath : DEFAULT_RESOURCE_PATHS) {
                File resFile = new File(resourcePath, propFile);
                if (resFile.isFile() || resFile.isDirectory()) {
                    try {
                        resource = resFile.toURI().toURL();
                        break;
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException("Unable to locate the csv file");
                    }
                }
            }
        }
        return resource;
    }
}
