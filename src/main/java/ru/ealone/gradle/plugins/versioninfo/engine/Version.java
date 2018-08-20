package ru.ealone.gradle.plugins.versioninfo.engine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

public class Version {
    static final String MAJOR_PROP = "major";
    static final String MIDDLE_PROP = "middle";
    static final String MINOR_PROP = "minor";
    static final String SUFFIX_PROP = "suffix";
    static final String BUILD_PROP = "build";

    static final int DEFAULT_INT_VALUE = 0;
    static final String DEFAULT_STR_VALUE = "";

    private int major;
    private int middle;
    private int minor;
    private String suffix;
    private int build;

    public Version(int major, int middle, int minor, @Nullable String suffix, int build){
        this.major = major;
        this.middle = middle;
        this.minor = minor;
        this.suffix = suffix == null ? DEFAULT_STR_VALUE : suffix;
        this.build = build;
    }

    public Version(@NotNull Properties properties){
        this.major = Version.convertToIntOrDefault(properties.getProperty(MAJOR_PROP), DEFAULT_INT_VALUE);
        this.middle = Version.convertToIntOrDefault(properties.getProperty(MIDDLE_PROP), DEFAULT_INT_VALUE);
        this.minor = Version.convertToIntOrDefault(properties.getProperty(MINOR_PROP), DEFAULT_INT_VALUE);
        this.suffix = properties.getProperty(SUFFIX_PROP, DEFAULT_STR_VALUE);
        this.build = Version.convertToIntOrDefault(properties.getProperty(BUILD_PROP), DEFAULT_INT_VALUE);
    }

    public String version(){
        String version = String.format("%d.%d.%d", this.major, this.middle, this.minor);
        if(!this.suffix.equals(DEFAULT_STR_VALUE)){
            version = version + "-" + this.suffix;
        }
        return version;
    }

    public String message(){
        return this.version() + " build " + this.build;
    }

    public void incBuild(){
        this.build++;
    }

    public Properties saveToProperties(){
        Properties properties = new Properties();
        properties.setProperty(MAJOR_PROP, Integer.toString(this.major));
        properties.setProperty(MIDDLE_PROP, Integer.toString(this.middle));
        properties.setProperty(MINOR_PROP, Integer.toString(this.minor));
        properties.setProperty(SUFFIX_PROP, this.suffix);
        properties.setProperty(BUILD_PROP, Integer.toString(this.build));

        return properties;
    }

    private static int convertToIntOrDefault(String value, int defValue){
        int retValue = defValue;
        try{
            retValue = Integer.valueOf(value);
        }
        catch (NumberFormatException ignore){}

        return retValue;
    }
}
