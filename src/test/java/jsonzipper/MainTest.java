package jsonzipper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
@Test(suiteName = "main", groups = {"integration"})
public class MainTest {
    private File file;

    @BeforeClass
    @AfterClass
    public void clean() {
        file = new File(Main.ZIP_FILE_NAME);
        if (file.exists())
            file.delete();

        file = new File(Main.GZIP_FILE_NAME);
        if (file.exists())
            file.delete();
    }

    @Test
    public void testZipCompress() throws IOException {
        Main.compressZip();

        file = new File(Main.ZIP_FILE_NAME);
        assert file.exists();
    }

    @Test
    public void testGZipCompress() throws IOException {
        Main.compressGZip();

        file = new File(Main.GZIP_FILE_NAME);
        assert file.exists();
    }

}
