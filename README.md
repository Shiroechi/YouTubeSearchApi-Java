# YouTube Search API 

Simple library for searching YT video 

## Badges
[![CodeFactor](https://www.codefactor.io/repository/github/shiroechi/youtubesearchapi-java/badge)](https://www.codefactor.io/repository/github/shiroechi/youtubesearchapi-java)

# How to use
```java
String key = "api key";

YouTubeSearchClient client = new YouTubeSearchClient(key);

String output = client.Search("anime", "snippet", "video", 10);

System.out.println(output);
```
the output will give 10 video with "anime" keyword in JSON format. 

or if you don't have `api key` use this:
```java
YoutubeClient client = new YoutubeClient();

List<YoutubeVideo> video = client.search("anime", 5);
```
this will give 5 video with "anime" keyword.

# Build
* Build with JDK 8

# Donation
[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/X8X81SP2L)
