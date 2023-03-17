import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;

public class Unzip {

    private byte[] zipBytes;
    private String unzipTo = ""; // the base directory or place where to unzip at, default is current dir where the program is.

    public Unzip(byte[] byteStream) {
        this.zipBytes = byteStream;
    }

    public Unzip(byte[] byteStream, String unzipTo) {
        this.zipBytes = byteStream;
        if(unzipTo!=null && !unzipTo.equals("/")) {
            this.unzipTo = removeLeadingAndMultiSlashes(unzipTo);
        }
    }

    public void unzip () {
        ZipStream stream = new ZipStream(this.zipBytes);
        
        while (stream.hasMoreBytes()) {
            processRecord(stream.getNextRecord());
        }
    }

    private void processRecord(ZipRecord record) {
        
        if(this.unzipTo != null && this.unzipTo != "") {
            record.setPath(unzipTo.concat("/").concat(record.getPath()));
        }

        try {
            File file = new File(record.getPath());
            String[] parentAndLeaf = ZipUtil.splitParentAndLeaf(record.getPath());
            String parentDirs = parentAndLeaf[0];
            String leaf = parentAndLeaf[1];
            
            createDirsAndFiles(record, file, parentDirs, leaf);
        } catch (Exception e) {
            System.out.println("Exception while creating file" + e);
            e.printStackTrace();
        }

    }

    private void createDirsAndFiles(ZipRecord record, File file, String parentDirs, String leaf) throws IOException {
        if(parentDirs.length() !=0) {
            (new File(parentDirs)).mkdirs();
        }
        if(leaf.length() !=0) {
            Files.write(file.toPath(), record.getContent().getBytes(), new OpenOption[]{});
        }
    }

    private String removeLeadingAndMultiSlashes(String s) {
        return s.replaceAll("^/+", "").replaceAll("/{2,}", "/");
    }

}
