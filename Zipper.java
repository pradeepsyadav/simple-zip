import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class Zipper {

    static Path zipPath = new File("multiFiles3.pkzip").toPath();
    static File inputFile = new File("abc.txt");
    static File inputFile2 = new File("data/foo.txt");
    static File inputFile3 = new File("data/barz.txt");

    public static byte[]  zipFiles(List<File> files) {
        return new byte[1];
    }

    public static void  zip() throws IOException {
        byte[] zippedBytes = Zip.createZipRecordNew(Arrays.asList(inputFile, inputFile2, inputFile3));
        // byte[] zippedBytes = Zip.createZipRecordNew(inputFile);

        Files.write(zipPath, zippedBytes, new OpenOption[]{});
    }

    public static void  unzip() throws IOException {
        Unzip unzip = new Unzip(Files.readAllBytes(zipPath));
        unzip.unzip();
    }

    public static void main(String[] args) {
        try {
            
            zip();
            unzip();
            
        } catch (Exception e) {
            System.err.println("Error " + e);
            e.printStackTrace();
        }
    }
}