package jsonzipper;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.lang.ClassLoader;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static final File jsonMessageFile = new File(
            ClassLoader.getSystemResource("parsedMessage.json").getFile());
    public static final String ZIP_FILE_NAME = "outputResult.zip";
    public static final String GZIP_FILE_NAME = "outputResult.gz";
    public static final String FILE_NAME = "outputResult";

    public static void main( String[] args ) throws IOException {
        compressZip();
        compressGZip();
    }

    public static void compressGZip() throws IOException {
        if (Objects.isNull(jsonMessageFile) || !jsonMessageFile.exists()) {
            System.err.println("No such file");
            return;
        }

        File gzipFile = new File(GZIP_FILE_NAME);

        GZIPOutputStream gzos =
                new GZIPOutputStream(new FileOutputStream(gzipFile));

        FileInputStream in =
                new FileInputStream(jsonMessageFile);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            gzos.write(buffer, 0, len);
        }

        in.close();

        gzos.finish();
        gzos.close();
    }

    public static void compressZip() throws IOException {

        if (Objects.isNull(jsonMessageFile) || !jsonMessageFile.exists()) {
            System.err.println("No such file");
            return;
        }

        File outputFile = new File(ZIP_FILE_NAME);
        ZipEntry zipEntry = new ZipEntry(FILE_NAME);

        BufferedReader bufferedReader = new BufferedReader( new FileReader(jsonMessageFile));

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));

        BufferedWriter zipWriter = new BufferedWriter(new OutputStreamWriter(zipOutputStream));

        zipOutputStream.putNextEntry(zipEntry);

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            zipWriter.append(line).append('\n');
        }

        zipWriter.flush();

        zipOutputStream.closeEntry();
        zipOutputStream.finish();

        bufferedReader.close();
        zipWriter.close();
    }

}
