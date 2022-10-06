package facebook.positive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.base.BaseTest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import static constants.Constant.URLs.WEBSITE;

public class FacebookTest extends BaseTest {

    @Test
    @DisplayName(value = "Facebook Authentication")
    public void isFacebookLoggedIn() {
        driver.get(WEBSITE);
        driver.findElement(By.id("email")).sendKeys("eugeneghummer@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("WebSit!eCreator12##");
        driver.findElement(By.xpath("//button[contains(text(),\"Log In\")]")).click();

        int count = 1;
        List<WebElement> list = driver.findElements(By.tagName("img"));
        for (WebElement element : list) {

            String src = element.getAttribute("src");
            System.out.println(src);
            URL imageURL = null;
            try {
                imageURL = new URL(src);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedImage saveImage = null;
            try {
                assert imageURL != null;
                saveImage = ImageIO.read(imageURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File dir = new File("downloads");
            String newName = src.substring(src.lastIndexOf("/"));
            try {
                assert saveImage != null;
                ImageIO.write(saveImage, "png", new File(dir, count + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
