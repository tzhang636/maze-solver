package mazeSolver;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @author Tom Zhang
 * 
 * The ImageReadWrite class is responsible for reading from and 
 * writing to files. For some reason, creating a new BufferedImage
 * using its constructor does not allow me to write it to a file if
 * I use TYPE_INT_ARGB instead of TYPE_INT_RGB.
 */
public class ImageReadWrite {

    /**
     * Reads a file into a BufferedImage.
     * @param readFile - the file to read from
     * @return - a BufferedImage representing the file
     * @throws Exception
     */
    public static BufferedImage Read(String readFile) throws Exception {
        File input = new File(readFile);

        // ImageIO.read might throw an IOException
        // The BufferedImage returned by ImageIO.read is gray-scale
        BufferedImage readImage = ImageIO.read(input);

        // create a newImage and copy all the pixel values from readImage into newImage
        BufferedImage newImage = new BufferedImage(readImage.getWidth(), 
                readImage.getHeight(), BufferedImage.TYPE_INT_RGB);              
        for (int x = 0; x < readImage.getWidth(); ++x) {
            for (int y = 0; y < readImage.getHeight(); ++y) {
                newImage.setRGB(x, y, readImage.getRGB(x, y));
            }
        }
        return newImage;
    }

    /**
     * Writes a BufferedImage into a file.
     * @param writeFile - the file to write to
     * @param image - the image that will be written
     * @throws Exception
     */
    public static void Write(String writeFile, BufferedImage image)
            throws Exception {
        File output = new File(writeFile);
        ImageIO.write(image, "bmp", output);
    }
}
