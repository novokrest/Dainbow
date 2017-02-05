package com.oneuse.core.parsers;

import com.oneuse.core.ByteArrayEx;
import com.oneuse.core.DataUrlUtils;
import com.oneuse.dainbow.image.ImageType;
import org.junit.Assert;
import org.junit.Test;

public class DataUrlParserTest {
    @Test
    public void test_Given_ValidFullFilledDataUrl_Expect_ParsingSuccessfully() {
        for (ImageType imageType: ImageType.values()) {
            test_Given_ValidFullFilledDataUrl_Expect_ParsingSuccessfully(imageType, ByteArrayEx.generate(100000));
        }
    }

    private void test_Given_ValidFullFilledDataUrl_Expect_ParsingSuccessfully(ImageType expectedImageType, byte[] expectedData) {
        String testedDataUrl = createDataUrl(expectedImageType, expectedData);
        DataUrlParser parser = new DataUrlParser(testedDataUrl);

        Assert.assertTrue(parser.parse());
        Assert.assertEquals(expectedImageType, parser.getImageType());
        Assert.assertArrayEquals(expectedData, parser.getData());
    }

    private String createDataUrl(ImageType imageType, byte[] data) {
        return DataUrlUtils.encode(imageType, data);
    }
}
