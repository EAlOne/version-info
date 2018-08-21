package ru.ealone.gradle.plugins.versioninfo.task;

import org.gradle.api.tasks.TaskAction;

public class PrintBuildNumberTask extends VersionTask {
    @TaskAction
    public void printBuildNumber() {
        System.out.println(this.getVersion().message());
    }
}
