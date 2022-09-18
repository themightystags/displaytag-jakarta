/*
 * Copyright (C) 2002-2014 Fabrizio Giustina, the Displaytag team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.displaytag.jsptests;

import org.apache.commons.lang3.StringUtils;
import org.displaytag.properties.MediaTypeEnum;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.test.DisplaytagCase;
import org.displaytag.test.KnownValue;
import org.displaytag.util.ParamEncoder;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * Tests for "media" attribute support.
 *
 * @author Fabrizio Giustina
 *
 * @version $Revision$ ($Author$)
 */
public class MediaSupportXmlTest extends DisplaytagCase {

    /**
     * Gets the jsp name.
     *
     * @return the jsp name
     *
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    @Override
    public String getJspName() {
        return "media.jsp";
    }

    /**
     * Test as Xml.
     *
     * @throws Exception
     *             any exception thrown during test.
     */
    @Override
    @Test
    public void doTest() throws Exception {

        final ParamEncoder encoder = new ParamEncoder("table");
        final String mediaParameter = encoder.encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);

        final WebRequest request = new GetMethodWebRequest(this.getJspUrl(this.getJspName()));
        request.setParameter(mediaParameter, "" + MediaTypeEnum.XML.getCode());

        final WebResponse response = this.runner.getResponse(request);

        if (this.log.isDebugEnabled()) {
            this.log.debug("RESPONSE: " + response.getText());
        }

        // we are really testing an xml output?
        Assert.assertEquals("Expected a different content type.", "text/xml", response.getContentType());

        final String output = response.getText();

        Assert.assertTrue("Expected value [" + KnownValue.BEE + "] missing",
                StringUtils.contains(output, KnownValue.BEE));
        Assert.assertTrue("Expected value [" + KnownValue.CAMEL + "] missing",
                StringUtils.contains(output, KnownValue.CAMEL));
        Assert.assertTrue("Unexpected value [" + KnownValue.ANT + "] found",
                !StringUtils.contains(output, KnownValue.ANT));

    }

}