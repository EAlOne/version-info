package ru.ealone.gradle.plugins.versioninfo.task;

import org.gradle.api.tasks.TaskAction;
import ru.ealone.gradle.plugins.versioninfo.version.Version;

import java.io.IOException;

public class IncBuildNumberTask extends VersionTask {
    @TaskAction
    public void incBuildNumber() throws IOException {
        Version version = this.getVersion();
        version.incBuild();
        this.setVersion(version);
    }
}
