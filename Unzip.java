import java.util.ArrayList;
import java.util.List;

public class Unzip {

    private byte[] zipBytes;

    public Unzip(byte[] byteStream) {
        this.zipBytes = byteStream;
    }

    public void unzip () {
        // List<ZipRecord> records = new ArrayList<>();
        ZipStream stream = new ZipStream(this.zipBytes);
        
        while (stream.hasMoreBytes()) {
            // records.add( 
                System.out.println (
                    stream.getNextRecord()
                );
                // );
        }
    }

}
