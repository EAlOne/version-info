package ru.ealone.gradle.plugins.versioninfo.engine;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;
import static ru.ealone.gradle.plugins.versioninfo.engine.Version.*;

public class VersionTest {

    @Test
    public void constructor_withNullSuffix(){
        Version version = new Version(1, 1, 1, null, 100);

        assertEquals("1.1.1", version.version());
        assertEquals("1.1.1 build 100", version.message());
    }

    @Test
    public void constructor_withNotNullSuffix(){
        Version version = new Version(1, 1, 1, "suffix", 100);

        assertEquals("1.1.1-suffix", version.version());
        assertEquals("1.1.1-suffix build 100", version.message());
    }

    @Test
    public void constructor_withCorrectProperties(){
        Properties properties = new Properties();
        properties.setProperty(MAJOR_PROP, "1");
        properties.setProperty(MIDDLE_PROP, "1");
        properties.setProperty(MINOR_PROP, "1");
        properties.setProperty(SUFFIX_PROP, "suffix");
        properties.setProperty(BUILD_PROP, "100");

        Version version = new Version(properties);

        assertEquals("1.1.1-suffix", version.version());
        assertEquals("1.1.1-suffix build 100", version.message());
    }

    @Test
    public void constructor_withPropertiesWithoutSuffix(){
        Properties properties = new Properties();
        properties.setProperty(MAJOR_PROP, "1");
        properties.setProperty(MIDDLE_PROP, "1");
        properties.setProperty(MINOR_PROP, "1");
        properties.setProperty(BUILD_PROP, "100");

        Version version = new Version(properties);

        assertEquals("1.1.1", version.version());
        assertEquals("1.1.1 build 100", version.message());
    }

    @Test
    public void constructor_withPropertiesWithoutOneParameter(){
        Properties properties = new Properties();
        properties.setProperty(MAJOR_PROP, "1");
        properties.setProperty(MINOR_PROP, "1");
        properties.setProperty(BUILD_PROP, "100");

        Version version = new Version(properties);

        assertEquals("1." + Integer.toString(DEFAULT_INT_VALUE) + ".1", version.version());
        assertEquals("1." + Integer.toString(DEFAULT_INT_VALUE) + ".1 build 100", version.message());
    }

    @Test
    public void incBuild_shouldIncrementBuildNumber(){
        Version version = new Version(1, 1, 1, null, 100);
        version.incBuild();

        assertEquals("1.1.1", version.version());
        assertEquals("1.1.1 build 101", version.message());
    }
}