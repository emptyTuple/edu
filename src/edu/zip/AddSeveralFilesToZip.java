package edu.zip;
import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AddSeveralFilesToZip {
    public static void main(String[] args) {
        addFilesToZip("/Users/emptytuple/sandbox/test1.zip",
                "/Users/emptytuple/sandbox/forzip/9876.txt",
                "/Users/emptytuple/sandbox/forzip/12345.txt",
                "/Users/emptytuple/sandbox/forzip/54321.txt");
    }

    public static void addFilesToZip(String fileName, String... args) {
        /*
        adds files to fileName.zip
         */
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileName))) {
            for (String file : args) {
                String entryName = Path.of(file).getFileName().toString();
                System.out.printf("Adding %s to archive...\n", entryName);
                ZipEntry ze = new ZipEntry(entryName);
                zout.putNextEntry(ze);
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = fis.readAllBytes();
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
