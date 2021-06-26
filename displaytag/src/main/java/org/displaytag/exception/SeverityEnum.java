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
package org.displaytag.exception;

/**
 * Enumeration for logging severities.
 * @author Fabrizio Giustina
 * @version $Revision$ ($Author$)
 */
public enum SeverityEnum {

    /** The fatal. */
    FATAL("fatal"),

    /** The error. */
    ERROR("error"),

    /** The warn. */
    WARN("warn"),

    /** The info. */
    INFO("info"),

    /** The debug. */
    DEBUG("debug");

    /**
     * D1597A17A6.
     */
    private static final long serialVersionUID = 899149338534L;

    /** The severity. */
    private String severity;

    /**
     * private constructor. Use only constants
     * @param severity Severity name as String
     */
    private SeverityEnum(String severity)
    {
        this.severity = severity;
    }

    /**
     * Gets the severity.
     *
     * @return the severity
     */
    public String getSeverity() {
      return severity;
    }

}