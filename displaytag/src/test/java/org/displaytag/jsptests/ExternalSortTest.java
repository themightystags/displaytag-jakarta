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

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.test.DisplaytagCase;
import org.displaytag.util.ParamEncoder;
import org.junit.Assert;
import org.junit.Test;


/**
 * Basic tests for pagination.
 * @author Fabrizio Giustina
 * @version $Revision$ ($Author$)
 */
public class ExternalSortTest extends DisplaytagCase
{

    /**
     * Gets the jsp name.
     *
     * @return the jsp name
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    @Override
    public String getJspName()
    {
        return "externalSort.jsp";
    }

    /**
     * Verifies that the generated page contains the pagination links with the inupt parameter. Tests #917200 ("{}" in
     * parameters).
     *
     * @throws Exception any axception thrown during test.
     */
    @Override
    @Test
    public void doTest() throws Exception
    {

        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));
        ParamEncoder p1 = new ParamEncoder("table");
        ParamEncoder p2 = new ParamEncoder("table2");

        request.setParameter(p2.encodeParameterName(TableTagParameters.PARAMETER_SORT), "number");
        request.setParameter(p2.encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME), "1");

        WebResponse response = this.runner.getResponse(request);

        if (this.log.isDebugEnabled())
        {
            this.log.debug("RESPONSE: " + response.getText());
        }

        WebLink[] links = response.getLinks();

        Assert.assertEquals(
            "0",
            links[0].getParameterValues(p1.encodeParameterName(TableTagParameters.PARAMETER_SORT))[0]);
        Assert.assertEquals(
            "2",
            links[0].getParameterValues(p1.encodeParameterName(TableTagParameters.PARAMETER_ORDER))[0]);

        Assert.assertEquals(
            "buzz",
            links[1].getParameterValues(p1.encodeParameterName(TableTagParameters.PARAMETER_SORT))[0]);
        Assert.assertEquals(
            "2",
            links[1].getParameterValues(p1.encodeParameterName(TableTagParameters.PARAMETER_ORDER))[0]);

        // test that the column with sortName buzz was set as sorted and now has a link to sort desc
        Assert.assertEquals(
            "number",
            links[2].getParameterValues(p2.encodeParameterName(TableTagParameters.PARAMETER_SORT))[0]);
        Assert.assertEquals(
            "1",
            links[2].getParameterValues(p2.encodeParameterName(TableTagParameters.PARAMETER_ORDER))[0]);

        // now ensure that our data has not been sorted at all since we are doing it 'externally'
        WebTable[] tables = response.getTables();
        Assert.assertEquals("1", tables[1].getCellAsText(1, 0));
        Assert.assertEquals("4", tables[1].getCellAsText(2, 0));
        Assert.assertEquals("2", tables[1].getCellAsText(3, 0));
        Assert.assertEquals("3", tables[1].getCellAsText(4, 0));
    }
}