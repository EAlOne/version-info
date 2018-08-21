package ru.ealone.gradle.plugins.versioninfo.task;

import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

public class UpdateProjectVersionTask extends VersionTask {

    private Project project;

    public void setProject(Project project) {
        this.project = project;
    }

    @TaskAction
    public void updateProjectVersion() {
        this.project.setVersion(this.getVersion());
    }
}
