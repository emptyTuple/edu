package edu.zip;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AddFolderToZipRecursion {

    public static void main(String[] args) throws IOException {
        String pathString = "/Users/emptytuple/sandbox/archive_root";

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("/Users/emptytuple/sandbox/a.zip"));

        File[] pathLevelFiles = new File(pathString).listFiles();
        addFilesToZipRecursion(zos, pathLevelFiles, Path.of(pathString).getFileName().toString() + File.separator);

        zos.close();

    }

    private static void addFilesToZipRecursion(ZipOutputStream zos, File[] pathLevelFiles, String base) throws IOException {
        /*
        adds all the folder content to zip archive excluding the root directory and empty folders;
        recursion version
         */
        for (File f : pathLevelFiles) {
            if (f.isDirectory()) {
                addFilesToZipRecursion(zos, f.listFiles(), base + f.getName() + File.separator);
            }
            else {
                ZipEntry ze = new ZipEntry(base + f.getName());
                System.out.printf("Adding %s to ZIP file \n", f.getName());
                zos.putNextEntry(ze);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f.toString()));
                zos.write(bis.readAllBytes());
                zos.flush();
                zos.closeEntry();
            }
        }
    }
}