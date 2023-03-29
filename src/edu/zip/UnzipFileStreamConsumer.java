package edu.zip;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipFileStreamConsumer {
    public static void main(String[] args) throws IOException {
        String pathFile = "/Users/emptytuple/sandbox/zip.zip";
        readZipStreamConsumer(pathFile);
    }

    private static void readZipStreamConsumer(String pathFile) throws IOException {
        ZipFile zipFile = new ZipFile(new File(pathFile), Charset.defaultCharset());
        zipFile.stream().forEach(new ZipConsumer(zipFile));
    }

    public static class ZipConsumer implements Consumer<ZipEntry> {
        private ZipFile zipFile;
        private String zipFileParentDir;

        public ZipConsumer(ZipFile zipFile) {
            this.zipFile = zipFile;
            zipFileParentDir = new File(zipFile.getName()).getParent() + "/";
        }

        @Override
        public void accept(ZipEntry ze) {
//            System.out.println(ze.getName() + " size: " + ze.getSize());
            if (ze.isDirectory()) {
                String entryName = ze.getName();
                File folderPath = new File(zipFileParentDir + entryName);
                folderPath.mkdir();
            }
            if (!ze.isDirectory()) {

            }

        }
    }
}