import java.util.List;

public class ZipUtil {
    
    public static byte[] appendBytes(byte[] b1, byte[] b2) {
        byte[] res = new byte[b1.length + b2.length];
        int k = 0;
        for(int i=0; i<b1.length; i++) {
            res[k++] = b1[i];
        }
        for(int i=0; i<b2.length; i++) {
            res[k++] = b2[i];
        }
        return res;
    }

    public static int convert4BytesToInt(byte[] arr) {
        int result = 0;
        for(int i=3; i>=0; i--) {
            result = result + (arr[i] * pow(256, i-3));
        }
        return result;
    }

    public static byte[] convertIntTo4Bytes(int num) {

        byte[] result = new byte[4];
        int n = num;
        int k = 3;

        while(n != 0) {
            result[k] = (byte) (n%256);
            n = n/256;
        }
        return result;
    }

    private static int pow (int base, int pow) {
        int result = base;

        if(pow == 0) return 1;

        for(int i=0; i<pow-1; i++) {
            result *= base;
        }
        return result;
    }

    
    private static String stripPathPrefix(String s) {
        int i = s.length() - 1;
        while (s.charAt(i) != '/') {
            i--;
        }
        return s.substring(i+1);
    }

     private static String stripPathPrefix(List<String> paths) {
        // int i = s.length() - 1;
        // while (s.charAt(i) != '/') {
        //     i--;
        // }
        return "";
    }
}
