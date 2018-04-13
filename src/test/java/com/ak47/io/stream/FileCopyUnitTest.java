package com.ak47.io.stream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileCopyUnitTest {

    @Test
    public void whenUsingStream_thenCopyFile() throws IOException {
        final File src = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "src" + File.separator + "test_stream.txt");
        final File dest = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "dest" + File.separator + "test_stream.txt");
        FileCopy.copyFileUsingStream(src, dest);

        assertTrue(dest.exists());
        dest.delete();
    }

    @Test
    public void whenUsingFiles_thenCopyFile() throws IOException {
        final File src = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "src" + File.separator + "test_files.txt");
        final File dest = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "dest" + File.separator + "test_files.txt");
        FileCopy.copyFileUsingJavaFiles(src, dest);

        assertTrue(dest.exists());
        dest.delete();
    }

    @Test
    public void whenUsingChannel_thenCopyFile() throws IOException {
        final File src = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "src" + File.separator + "test_channel.txt");
        final File dest = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "dest" + File.separator + "test_channel.txt");
        FileCopy.copyFileUsingChannel(src, dest);

        assertTrue(dest.exists());
        dest.delete();
    }

    @Test
    public void whenUsingApache_thenCopyFile() throws IOException {
        final File src = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "src" + File.separator + "test_apache.txt");
        final File dest = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "copyTest" + File.separator + "dest" + File.separator + "test_apache.txt");
        FileCopy.copyFileUsingApacheCommonsIO(src, dest);

        assertTrue(dest.exists());
        dest.delete();
    }

}
