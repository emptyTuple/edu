package edu.zip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ReadZipFiles {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile("/Users/emptytuple/sandbox/zip/archive_root.zip");
        readAllFiles(zf);
    }

    private static void readAllFiles(ZipFile zf) {
        /*
        reads all files from zip file and print them out
         */
        for (Enumeration<? extends ZipEntry> iterator = zf.entries(); iterator.hasMoreElements();) {
            ZipEntry ze = iterator.nextElement();
            if (!ze.isDirectory()) {
                System.out.printf("file: %s; size: %s\n", ze.getName(), ze.getSize());
                System.out.println("file content:");
                try (BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)))) {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
