package edu.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AddFilesFromListToZipUsingVisitor {

    private static List<String> files = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String zipFilePath = "/Users/emptytuple/sandbox/zip.zip";

        files.add("/Users/emptytuple/sandbox/forzip/111.txt");
        files.add("/Users/emptytuple/sandbox/forzip/222.txt");
        files.add("/Users/emptytuple/sandbox/forzip/333.txt");

        addFilesToZipVisitor(zipFilePath, files);
    }

    private static void addFilesToZipVisitor(String zipFilePath, List<String> files) throws IOException {
        /*

         */
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
        for (String file : files) {
            if (Files.isRegularFile(Path.of(file))) {
                ZipEntry ze = new ZipEntry(Path.of(file).getFileName().toString());
                zos.putNextEntry(ze);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int size = -1;
                while((size = fis.read(buffer)) !=-1) {
                    zos.write(buffer, 0, size);
                }
                zos.closeEntry();
            }
        }

        zos.close();
    }
}
