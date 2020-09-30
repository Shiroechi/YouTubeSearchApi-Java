package YouTubeSearchApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Class for creating YouTube url.
 */
public class UrlBuilder
{
    private final String _BaseUrl = "https://www.googleapis.com/youtube/v3";
    private String _BaseSearchUrl = this._BaseUrl + "/search/?key=";
    private String _BaseVideoDetailUrl = this._BaseUrl + "/videos?key=";
    private String _BasePlaylistUrl = this._BaseUrl + "/playlistItems?key=";
    private String _BaseCommentThreadsUrl = this._BaseUrl + "/commentThreads?key=";

    public UrlBuilder(String apiKey)
    {
        this._BaseSearchUrl += apiKey;
        this._BaseVideoDetailUrl += apiKey;
        this._BasePlaylistUrl += apiKey;
        this._BaseCommentThreadsUrl += apiKey;
    }

    /**
     * Create custom search.
     * @param query search keyword
     * @param part use "snippet" for default
     * @param type content type (video, playlist, etc)
     * @param maxResults number of search result
     * @return
     */
    public String BuildSearchUrl(String query, String part, String type, int maxResults)
    {
        String encodedQuery;

        try
        {
            encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        }
        catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex.getCause());
        }

        String searchUrl = this._BaseSearchUrl +
                           "&q=" + encodedQuery +
                           "&part=" + part +
                           "&type=" + type +
                           "&maxResults=" + maxResults;

        return searchUrl;
    }

    /**
     * Search related video based with other video.
     * @param videoId id for the YouTube video 
     * @param part use "snippet" for default
     * @param maxResults number of search result
     * @return
     */
    public String BuildSearchRelatedVideoUrl(String videoId, String part, int maxResults)
    {
        String encodedQuery;

        try
        {
            encodedQuery = URLEncoder.encode(videoId, StandardCharsets.UTF_8.toString());
        }
        catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex.getCause());
        }

        String searchUrl = this._BaseSearchUrl +
                           "&relatedToVideoId=" + encodedQuery +
                           "&part=" + part +
                           "&type=video" +
                           "&maxResults=" + maxResults;

        return searchUrl;
    }

    /**
     * Create video search url.
     * @param videoId id of the YouTube video.
     * @return
     */
    public String BuildVideoDetailUrl(String videoId)
    {
        return this._BaseVideoDetailUrl +
                "&id=" + videoId +
                "&part=contentDetails";
    }

    /**
     * Create video search url.
     * @param videoId id of the YouTube video.
     * @return
     */
    public String BuildVideoDetailUrl(String[] videoId)
    {
        String video = "";
        if (videoId.length > 1)
        {
            for (String id : videoId)
            {
                video += "&id=" + id;
            }
        }
        else
        {
            video = "&id=" + videoId[0];
        }

        return this._BaseVideoDetailUrl +
                video +
                "&part=contentDetails";
    }

    /**
     * Create video search url.
     * @param videoId id of the YouTube video.
     * @return
     */
    public String BuildVideoDetailUrl(List<String> videoId)
    {
        String video = "";
        if (videoId.size() > 1)
        {
            for (String id : videoId)
            {
                video += "&id=" + id;
            }
        }
        else
        {
            video = "&id=" + videoId.get(0);
        }

        return this._BaseVideoDetailUrl +
                video +
                "&part=contentDetails";
    }

    /**
     * Create playlist url.
     * @param playlistId id of the YouTube playlist
     * @param part use "snippet" for default
     * @param maxResults number of video to return.
     * @return
     */
    public String BuildPlaylistUrl(String playlistId, String part, int maxResults)
    {
        return this._BasePlaylistUrl +
                "&part=" + part +
                "&maxResults=" + maxResults +
                "&playlistId=" + playlistId;
    }

    /**
     * Create video comment thread url.
     * @param videoId id for the YouTube video
     * @param maxResults number of comment to return
     * @param textFormat use "plainText" for default
     * @param part use "snippet" for default
     * @return
     */
    public String BuildVideoCommentThreadsUrl(String videoId, int maxResults, String textFormat, String part)
    {
        return this._BaseCommentThreadsUrl +
                "&videoId=" + videoId +
                "&maxResults=" + maxResults +
                "&textFormat=" + textFormat +
                "&part=" + part;
    }

    /**
     * Create channel comment thread url.
     * @param channelId id for the YouTube channel
     * @param maxResults number of comment to return
     * @param textFormat use "plainText" for default
     * @param part use "snippet" for default
     * @return
     */
    public String BuildChannelCommentThreadsUrl(String channelId, int maxResults, String textFormat, String part)
    {
        return this._BaseCommentThreadsUrl +
                "&channelId=" + channelId +
                "&maxResults=" + maxResults +
                "&textFormat=" + textFormat +
                "&part=" + part;
    }
}
