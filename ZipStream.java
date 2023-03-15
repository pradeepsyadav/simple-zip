public class ZipStream {

    private int ptr = 0;
    private byte[] byteStream;

    public final static int PREFIX_LENGTH = 5;
    public final static int PATH_LENGHT_BYTES = 4;
    public final static int CONTENT_LENGTH_BYTES = 4;

    public ZipStream (byte[] byteStream) {
        this.byteStream = byteStream;
    }

    public int getPointer() {
        return this.ptr;
    }

    public boolean hasMoreBytes() {
        return this.ptr < this.byteStream.length;
    }

    private void setPathSize(int pathSize, ZipRecord record) {
        this.ptr += PATH_LENGHT_BYTES;
        record.setPathSize(pathSize);
    }

    private void setPath(String path, ZipRecord record) {
        this.ptr += record.getPathSize();
        record.setPath(path);
    }

    private void setContentSize(int contentSize, ZipRecord record) {
        this.ptr += CONTENT_LENGTH_BYTES;
        record.setContentSize(contentSize);
    }

    private void setContent(String content, ZipRecord record) {
        this.ptr += record.getContentSize();
        record.setContent(content);
    }

    public ZipRecord getNextRecord () {
        ZipRecord record = new ZipRecord();
        skipBytes(PREFIX_LENGTH);
        setPathSize(ZipUtil.convert4BytesToInt(byteArraySlice(byteStream, ptr, PATH_LENGHT_BYTES)), record);
        setPath(new String(byteArraySlice(byteStream, ptr, record.getPathSize())), record);

        skipBytes(PREFIX_LENGTH);
        setContentSize(ZipUtil.convert4BytesToInt(byteArraySlice(byteStream, ptr, CONTENT_LENGTH_BYTES)), record);
        setContent(new String(byteArraySlice(byteStream, ptr, record.getContentSize())), record);
        return record;
    }

    private void skipBytes(int count) {
        this.ptr += count;
    }


    /**
     * eg. offset = 5, length = 4, so will stop at 8
     * @param byteStream
     * @param offset
     * @param len
     * @return
     */
    // 
    private byte[] byteArraySlice(byte[] byteStream, int offset, int len) {
        byte[] result = new byte[len];
        for (int i = offset, k = 0; i < offset + len; i++, k++) {
            result[k] = byteStream[i];
        }
        return result;
    }


}
