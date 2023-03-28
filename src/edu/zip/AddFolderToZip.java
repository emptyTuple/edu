package edu.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AddFolderToZip {

    public static void main(String[] args) {
        addFolderContentToZip("/Users/emptytuple/sandbox/folder.zip",
                "/Users/emptytuple/sandbox/archive_root");
    }

    public static void addFolderContentToZip(String fileName, String rootDir) {
        /*
        adds folder <rootDir> with all the content to zip file <fileName>
        rootDir is the full path to directory
        fileName is the full path to file
        excludes empty folders
         */
        Path rootPath = Path.of(rootDir);
        List<Path> files = new ArrayList<>();
        try {
            files = Files.walk(rootPath).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileName))) {
            for (Path file : files) {
                ZipEntry ze = new ZipEntry(rootPath.relativize(file).toString());
                zout.putNextEntry(ze);
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zout.write(bis.readAllBytes());
                    zout.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
