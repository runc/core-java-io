package com.ak47.io.file;

import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FileOperationsManualTest {

    @Test
    public void givenFileName_whenUsingClassloader_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("fileTest.txt").getFile());
        InputStream inputStream = new FileInputStream(file);
        String data = readFromInputStream(inputStream);

        assertEquals(expectedData, data.trim());
    }

    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Class clazz = FileOperationsManualTest.class;
        InputStream inputStream = clazz.getResourceAsStream("/fileTest.txt");
        String data = readFromInputStream(inputStream);

        assertEquals(expectedData, data.trim());
    }

    @Test
    public void givenFileName_whenUsingJarFile_thenFileData() throws IOException {
        String expectedData = "BSD License";

        Class clazz = Matchers.class;
        InputStream inputStream = clazz.getResourceAsStream("/LICENSE.txt");
        String data = readFromInputStream(inputStream);

        assertThat(data.trim(), CoreMatchers.containsString(expectedData));
    }

    @Test
    public void givenURLName_whenUsingURL_thenFileData() throws IOException {
        String expectedData = "Example Domain";

        URL urlObject = new URL("http://www.example.com/");

        URLConnection urlConnection = urlObject.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        String data = readFromInputStream(inputStream);

        assertThat(data.trim(), CoreMatchers.containsString(expectedData));
    }

    @Test
    public void givenFileName_whenUsingFileUtils_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("fileTest.txt").getFile());
        String data = FileUtils.readFileToString(file);

        assertEquals(expectedData, data.trim());
    }

    @Test
    public void givenFilePath_whenUsingFilesReadAllBytes_thenFileData() throws IOException, URISyntaxException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI());

        byte[] fileBytes = Files.readAllBytes(path);
        String data = new String(fileBytes);

        assertEquals(expectedData, data.trim());
    }

    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() throws IOException, URISyntaxException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI());

        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> data.append(line).append("\n"));
        lines.close();

        assertEquals(expectedData, data.toString().trim());
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        return resultStringBuilder.toString();
    }
}