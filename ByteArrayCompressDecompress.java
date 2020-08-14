package com.cvjetkovic.fileupload.fileops;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
/**
 * @author Vladimir Cvjetkovic
 */
public class ByteArrayCompressDecompress {

    public byte[] compressByteArray(byte[] bytes) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(bytes);
        deflater.finish();
        byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!deflater.finished()) {
                int size = deflater.deflate(tmp);
                byteArrayOutputStream.write(tmp, 0, size);
            }
        } catch (Exception ex) {

        } finally {
            try {
                if (byteArrayOutputStream != null) byteArrayOutputStream.close();
            } catch (Exception ex) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] decompressByteArray(byte[] bytes) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        Inflater inflater = new Inflater();
        inflater.setInput(bytes);
        byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int size = inflater.inflate(tmp);
                byteArrayOutputStream.write(tmp, 0, size);
            }
        } catch (Exception ex) {

        } finally {
            try {
                if (byteArrayOutputStream != null) byteArrayOutputStream.close();
            } catch (Exception ex) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
