package com.utc.api13.commun.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.utc.api13.commun.exceptions.TechnicalException;

public class ImageUtils {

    public static final String FILE_PREFIX = "file:///";

    private ImageUtils() {

    }

    public static byte[] extractBytes(String imagePath) throws TechnicalException {
        BufferedImage bufferedImage;
        try {
            if (imagePath != null) {
                if (imagePath.startsWith(FILE_PREFIX)) {
                    imagePath = imagePath.substring(FILE_PREFIX.length() - 1);
                }
                bufferedImage = ImageIO.read(new File(imagePath));
                DataBufferByte data = (DataBufferByte) bufferedImage.getRaster().getDataBuffer();
                return data.getData();
            }
        } catch (IOException e) {
            throw new TechnicalException("Error while reading image", e);
        }
        return null;
    }
}
