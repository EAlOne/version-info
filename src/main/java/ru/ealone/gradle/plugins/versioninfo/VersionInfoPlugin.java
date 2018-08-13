package ru.ealone.gradle.plugins.versioninfo;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;
import ru.ealone.gradle.plugins.versioninfo.engine.FilePropertiesSupplier;
import ru.ealone.gradle.plugins.versioninfo.engine.PropertiesConverter;

public class VersionInfoPlugin implements Plugin<Project> {
    public static final String DEFAULT_FILE_NAME = "version.properties";

    @Override
    public void apply(@NotNull Project project) {

        PropertiesConverter propertiesConverter = new PropertiesConverter(new FilePropertiesSupplier(DEFAULT_FILE_NAME));

        String version = propertiesConverter.get();
        project.setVersion(version);
    }
}
