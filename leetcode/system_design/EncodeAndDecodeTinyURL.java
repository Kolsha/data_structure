package exam.review.leetcode.system_design;

import java.util.ArrayList;


/**
 * It is also a system design problem. {@see <a href="http://stackoverflow.com/questions/742013/how-to-code-a-url-shortener">tiny url</a>}
 */
public class EncodeAndDecodeTinyURL {
    ArrayList<String> urls = new ArrayList<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl);
        return String.valueOf(urls.size() - 1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        final int index = Integer.valueOf(shortUrl);
        return (index < urls.size()) ? urls.get(index) : "";
    }
}
