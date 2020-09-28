# YouTube Search API 

Simple lirary for searching YT video 

# How to use

String key = "api ley";
YouTubeSearchClient client = new YouTubeSearchClient(key);
String output = client.Search("anime", "snippet", "video", 10);
System.out.println(output);

the output will give 10 video with "anime" keyword in JSON format

# Build
* Build with JDK 8
