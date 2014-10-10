package mazeSolver;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.junit.Test;

public class ImageReadWriteTest {

    @Test(expected = IOException.class)
    public void testRead() throws Exception {

        BufferedImage image = ImageReadWrite.Read("c:/temp/maze0.bmp");
        ImageReadWrite.Write("c:/temp/hello.bmp", image);

        image = ImageReadWrite.Read("Hello"); // should throw an IOException

    }
}
