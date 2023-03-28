package edu.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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
        files.add("/Users/emptytuple/sandbox/archive_root");

        addFilesToZipVisitor(zipFilePath, files);
    }

    private static void addFilesToZipVisitor(String zipFilePath, List<String> files) throws IOException {
        /*
        Adds any files or directories with all the context including empty folders
        from List<String> to zip file
         */
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
        for (String file : files) {
            Path filePath = Path.of(file);
            if (Files.isRegularFile(filePath)) {
                ZipEntry ze = new ZipEntry(filePath.getFileName().toString());
                zos.putNextEntry(ze);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int size = -1;
                while((size = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, size);
                }
                fis.close();
                zos.closeEntry();
            }
            if (Files.isDirectory(filePath)) {
                Files.walkFileTree(filePath, new SimpleFileVisitor<>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        ZipEntry ze = new ZipEntry(filePath.getFileName().resolve(filePath.relativize(dir)) + "/");
                        zos.putNextEntry(ze);
                        zos.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        ZipEntry ze = new ZipEntry(filePath.getFileName().resolve(filePath.relativize(file)).toString());
                        zos.putNextEntry(ze);
                        Files.copy(file, zos);
                        zos.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }
        zos.close();
    }
}
