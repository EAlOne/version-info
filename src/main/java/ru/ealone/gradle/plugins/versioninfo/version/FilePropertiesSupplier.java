package ru.ealone.gradle.plugins.versioninfo.version;

import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilePropertiesSupplier implements PropertiesSupplier {
    private final String filePath;

    public FilePropertiesSupplier(String filePath) {
        this.filePath = filePath;
    }

    @Override
    @Nullable
    public Properties get() {
        Properties properties;

        try (InputStream input = new FileInputStream(this.filePath)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            properties = null;
        }
        return properties;
    }
}
