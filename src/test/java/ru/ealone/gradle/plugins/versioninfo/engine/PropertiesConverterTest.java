package ru.ealone.gradle.plugins.versioninfo.engine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.Assert.assertEquals;

public class PropertiesConverterTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File temporaryFile;

    @Before
    public void createTemporaryFile() throws IOException {
        this.temporaryFile = temporaryFolder.newFile();
    }

    @Test
    public void get_withoutFile() {
        PropertiesConverter converter = new PropertiesConverter(new FilePropertiesSupplier(""));

        assertEquals("", converter.get());
    }

    @Test
    public void get_withFullSet() throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.temporaryFile))) {
            outputStreamWriter.write(
                    "major=2\n" +
                            "middle=2\n" +
                            "minor=57\n" +
                            "suffix=\n"
            );
        }
        PropertiesConverter converter = new PropertiesConverter(new FilePropertiesSupplier(temporaryFile.getAbsolutePath()));

        assertEquals("2.2.57", converter.get());
    }

    @Test
    public void get_withoutFullSet() throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.temporaryFile))) {
            outputStreamWriter.write(
                    "major=2\n" +
                            "minor=\n" +
                            "suffix=\n"
            );
        }
        PropertiesConverter converter = new PropertiesConverter(new FilePropertiesSupplier(temporaryFile.getAbsolutePath()));

        assertEquals("2.x.x", converter.get());
    }

    @Test
    public void get_withSuffix() throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.temporaryFile))) {
            outputStreamWriter.write(
                    "major=2\n" +
                            "middle=2\n" +
                            "minor=57\n" +
                            "suffix=test\n"
            );
        }
        PropertiesConverter converter = new PropertiesConverter(new FilePropertiesSupplier(temporaryFile.getAbsolutePath()));

        assertEquals("2.2.57-test", converter.get());
    }
}