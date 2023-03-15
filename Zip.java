import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Zip {

    public final static String ZIPRECORD_PATH_PREFIX = "PATH:";
    public final static String ZIPRECORD_DATA_PREFIX = "DATA:";

    
    public static byte[] createZipRecordNew(File file) {

        StringBuffer buffer = new StringBuffer();
        String path = file.getPath();
        int pathLength = path.length();

        try {
            buffer.append(ZIPRECORD_PATH_PREFIX); //path prefix
            buffer.append(new String(ZipUtil.convertIntTo4Bytes(pathLength))); //path length
            buffer.append(path); //path

            buffer.append(ZIPRECORD_DATA_PREFIX); //data prefix
            buffer.append(new String(ZipUtil.convertIntTo4Bytes((int) file.length()))); // data length, change later to long or something
            buffer.append(new String(Files.readAllBytes(file.toPath()))); //data
        } catch (Exception e) {
            System.err.println("Somee error happened" + e);
        }

        return buffer.toString().getBytes();
    }

    public static byte[] createZipRecordNew(List<File> files) {
        return files.stream()
            .map(file -> createZipRecordNew(file))
            .reduce((a,b) -> ZipUtil.appendBytes(a, b))
            .get();
        
    }
}
