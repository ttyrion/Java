package mysp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class HexLoader {
    public String loadHexFile(String file_path) {
        byte[] data = loadFile(file_path);
        int lines = data.length / 16;
        int builder_size = lines * 56;
        if (data.length % 16 != 0) {
            builder_size += 7 + data.length % 16 * 3 + 1; // 1 for '\n'
        }

        StringBuilder hex = new StringBuilder(builder_size);
        int n = 0;
        for (byte b : data) {
            if(n % 16 == 0) {
                hex.append(String.format("%05X: ", n));
            }
            hex.append(String.format("%02X ", b));
            ++n;
            if(n % 16 == 0 || n == data.length) {
                hex.append("\n");
            }
        }

        return hex.toString();
    }

    private byte[] loadFile(String file_path) {
        byte[] data = new byte[0];
        File file = new File(file_path);
        try {
            InputStream input = FileUtils.openInputStream(file);
            data = IOUtils.toByteArray(input);
        }
        catch(IOException exp) {
            data = new byte[0];
        }

        return data;
    }
}