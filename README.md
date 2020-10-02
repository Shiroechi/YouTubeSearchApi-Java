# YouTube Search API 

Simple library for searching YT video 

# How to use
```
String key = "api key";

YouTubeSearchClient client = new YouTubeSearchClient(key);

String output = client.Search("anime", "snippet", "video", 10);

System.out.println(output);
```
the output will give 10 video with "anime" keyword in JSON format

# Build
* Build with JDK 8

# Donation
[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/X8X81SP2L)
