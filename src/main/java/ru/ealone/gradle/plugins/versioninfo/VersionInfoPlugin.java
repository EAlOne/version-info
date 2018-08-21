package ru.ealone.gradle.plugins.versioninfo;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;
import ru.ealone.gradle.plugins.versioninfo.task.IncBuildNumberTask;
import ru.ealone.gradle.plugins.versioninfo.task.PrintBuildNumberTask;
import ru.ealone.gradle.plugins.versioninfo.task.UpdateProjectVersionTask;

public class VersionInfoPlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        // Add tasks
        project.getTasks().create("printBuildNumber", PrintBuildNumberTask.class);
        project.getTasks().create("incBuildNumber", IncBuildNumberTask.class);
        project.getTasks().create("updateProjectVersion", UpdateProjectVersionTask.class, task -> task.setProject(project));
    }
}
