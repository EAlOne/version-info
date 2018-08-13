package ru.ealone.gradle.plugins.versioninfo.engine;

import org.jetbrains.annotations.NotNull;

import java.util.Properties;
import java.util.function.Supplier;

public class PropertiesConverter implements Supplier<String> {

    private final PropertiesSupplier propertiesSupplier;

    public PropertiesConverter(PropertiesSupplier propertiesSupplier) {
        this.propertiesSupplier = propertiesSupplier;
    }

    @Override
    public String get() {
        String result = "";

        if (this.propertiesSupplier.get() != null) {
            StringBuilder versionBuilder = new StringBuilder();
            versionBuilder.append(this.getProperty("major", "x")).append('.')
                    .append(this.getProperty("middle", "x")).append('.')
                    .append(this.getProperty("minor", "x"));

            String suffix = this.getProperty("suffix", "");
            if (!suffix.equals("")) {
                versionBuilder.append('-').append(suffix);
            }
            result = versionBuilder.toString();
        }
        return result;
    }

    private String getProperty(@NotNull String key, @NotNull String defValue) {
        Properties properties = this.propertiesSupplier.get();
        String value = properties.getProperty(key);
        return (value == null || value.equals("")) ? defValue : value;
    }
}
