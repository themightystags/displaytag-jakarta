displaytag-jakarta
==================

Forked from [here](http://hazendaz.github.io/displaytag/)

This is fork of the `displaytag` library with updated code and dependencies to use classes / interfaces in the newer `jakarta.servlet` package which has replaced `javax.servlet`.

These changes allow `displaytag` to be used with Servlet API v6, Spring 6 and Spring Boot 3.  The changes have been kept to the minimum required to make the code work.

The tests compile but fail owing to the `httpunit` testing library using the `javax.servlet` package.

The maven coordinates are

```xml
<dependency>
    <groupId>com.themightystags</groupId>
    <artifactId>displaytag-jakarta</artifactId>
    <version>2.5.0.2</version>
</dependency>
```

Building and Releasing
======================

See `build_and_deploy.sh`