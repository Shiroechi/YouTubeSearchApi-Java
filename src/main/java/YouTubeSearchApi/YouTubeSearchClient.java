package main.java.YouTubeSearchApi;

import java.io.*;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class YouTubeSearchClient
{
    private String _DeveloperKey = "";
    private UrlBuilder _UrlBuilder;
    private HttpURLConnection _Client;

    /**
     * Create Youtube search client instance.
     */
    public YouTubeSearchClient()
    {
        this._DeveloperKey = "";
        this._UrlBuilder = new UrlBuilder(this._DeveloperKey);
    }

    /**
     * Create Youtube search client instance.
     * @param apiKey Your personal Api key or Developer key.
     */
    public YouTubeSearchClient(String apiKey)
    {
        this._DeveloperKey = apiKey;
        this._UrlBuilder = new UrlBuilder((this._DeveloperKey));
    }

    private String StreamToString(InputStream stream)
    {
        StringBuilder output = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader (stream, Charset.forName(StandardCharsets.UTF_8.name()))))
        {
            int c = 0;
            while ((c = reader.read()) != -1)
            {
                output.append((char) c);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return output.toString();
    }

    /**
     * YouTube Search
     * @param query search keyword
     * @param part use "snippet" for default
     * @param type use "video, playlist, etc"
     * @param maxResults number of search to return
     * @return
     */
    public String Search(String query, String part, String type, int maxResults)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildSearchUrl(query, part, type, maxResults);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Search related YouTube video
     * @param videoId id for YouTube video
     * @param part use "snippet" for default
     * @param maxResults number of search to return
     * @return
     */
    public String SearchRelatedVideo(String videoId, String part, int maxResults)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildSearchRelatedVideoUrl(videoId, part, maxResults);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get playlist
     * @param playlistId id for the YouTube playlist
     * @param maxResults number of video to return
     * @param part use "snippet" for default
     * @return
     */
    public String GetPlaylist(String playlistId, int maxResults, String part)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildPlaylistUrl(playlistId, part, maxResults);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get video detail
     * @param videoId id of the YouTube video
     * @return
     */
    public String GetVideoDetails(String videoId)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildVideoDetailUrl(videoId);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get video detail
     * @param videoId id of the YouTube video
     * @return
     */
    public String GetVideoDetails(String[] videoId)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildVideoDetailUrl(videoId);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get video detail
     * @param videoId id of the YouTube video
     * @return
     */
    public String GetVideoDetails(List<String> videoId)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildVideoDetailUrl(videoId);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get comment for the video
     * @param videoId id of the YouTube video
     * @param maxResults number of comment to return
     * @param textFormat use "plainText" for default
     * @param part use "snippet" for default
     * @return
     */
    public String GetVideoCommentThreads(String videoId, int maxResults, String textFormat, String part)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildVideoCommentThreadsUrl(videoId, maxResults, textFormat, part);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Get channel comments
     * @param channelId id of the YouTube channel
     * @param maxResults number of comments to return
     * @param textFormat use "plainText" for default
     * @param part use "snippet" for default
     * @return
     */
    public String GetChannelCommentThreads(String channelId, int maxResults, String textFormat, String part)
    {
        try
        {
            String requestUrl = this._UrlBuilder.BuildChannelCommentThreadsUrl(channelId, maxResults, textFormat, part);
            URL url = new URL(requestUrl);
            this._Client = (HttpURLConnection) url.openConnection();
            this._Client.setRequestProperty("accept", "application/json");
            String response = this.StreamToString(this._Client.getInputStream());
            return response;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
