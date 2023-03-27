package edu.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

public class AddFolderToZipRecursion {

    public static void main(String[] args) {
        String folder = "/Users/emptytuple/sandbox/archive_root";
        File[] rootFolderFiles = new File(folder).listFiles();

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(folder))) {
            addFolderToZipRecursion(zout, rootFolderFiles, "");

        } catch (IOException e) {
            e.getCause();
        }
    }

    public static void addFolderToZipRecursion(ZipOutputStream zout, File[] rootFiles, String base) {
        for (File file : rootFiles) {
            if (file.isFile()) {
                System.out.println(file.toString());
//                addFolderToZipRecursion(zout, file.listFiles(), base + file.getName() + File.pathSeparator);
            }
        }
    }
}
