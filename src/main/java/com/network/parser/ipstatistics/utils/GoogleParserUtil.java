package com.network.parser.ipstatistics.utils;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class GoogleParserUtil {

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
    private static final int CONNECTION_TIMEOUT = 60000;
    private static final int READ_TIMEOUT = 60000;
    private static final String UNICODE = "UTF-8";
    private static final String SEARCH_PATTERN = "<div id=\"result-stats\">";

    public static long getResultsCount(String query) throws IOException {
        URLConnection connection = getConfiguredConnection(query);

        try (Scanner reader = new Scanner(connection.getInputStream(), UNICODE)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains(SEARCH_PATTERN)) {
                    return parseSearchResultCount(line);
                }
            }
        }
        return 0;
    }

    private static long parseSearchResultCount(String line) {
        return Long.parseLong(line.split(SEARCH_PATTERN)[1].split("<")[0].replaceAll("[^\\d]", ""));
    }

    private static URLConnection getConfiguredConnection(String query) throws IOException {
        URL url = new URL(GOOGLE_SEARCH_URL + URLEncoder.encode(query, UNICODE));
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.addRequestProperty("User-Agent", USER_AGENT);
        return connection;
    }

}
