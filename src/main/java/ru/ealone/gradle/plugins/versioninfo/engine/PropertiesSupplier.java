package ru.ealone.gradle.plugins.versioninfo.engine;

import java.util.Properties;
import java.util.function.Supplier;

@FunctionalInterface
public interface PropertiesSupplier extends Supplier<Properties> {
}
