package slidetoImageConvertor;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
public class ScreenshotComparison {
public static void main(String[] args) {
 
	for (int i = 0; i < 50; i++) {
try {
// Load the reference screenshot from file
BufferedImage expectedImage = ImageIO.read(new File("ExpectedImages//ppt_image" + i + ".png"));
BufferedImage actualScreenshot = ImageIO.read(new File("ActualImages//ppt_image" + i + ".png"));
// Get the actual screenshot image
 
// Compare the screenshots using ImageDiffer
ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot);
 
// Save the diff image (optional)
ImageIO.write(diff.getMarkedImage(), "PNG", new File("DifferenceImage//diff_image" + i + ".png"));
 
// Check if the screenshots are similar
if (diff.hasDiff()) {
System.out.println(i +"th" + "Screenshot is different");
} else {
System.out.println(i +"th" + "Screenshot is identical");
}
} catch (IOException e) {
e.printStackTrace();
} finally {
 
}
}
}
}