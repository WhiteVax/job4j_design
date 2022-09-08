package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        var result = "";
        try (var reader = new BufferedReader(new FileReader(String.format(
                "%s%s", cachingDir, key)))) {
            var joiner = new StringJoiner("");
            reader.lines().filter(e -> !e.isEmpty()).forEach(joiner::add);
            result = joiner.toString();
            put(key, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
