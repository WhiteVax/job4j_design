package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private static final Map<String, Integer> headerIndexMap = new HashMap<>();
    private static final List<String[]> rows = new ArrayList<>();
    private static final List<String> resultLines = new ArrayList<>();

    public static void handle(ArgsName args) {
        clearState();
        validateArgs(args);
        process(args);
    }

    private static void clearState() {
        headerIndexMap.clear();
        rows.clear();
        resultLines.clear();
    }

    private static void process(ArgsName args) {
        String path = args.get("path");
        String delimiter = args.get("delimiter");
        String filter = args.get("filter");
        String out = args.get("out");

        loadFile(path, delimiter);
        filterColumns(filter, delimiter);
        outputResult(out);
    }

    private static void validateArgs(ArgsName args) {
        Path filePath = Path.of(args.get("path"));
        String delimiter = args.get("delimiter");
        String out = args.get("out");
        String filter = args.get("filter");

        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            throw new IllegalArgumentException("Invalid file: " + filePath);
        }

        if (delimiter.length() != 1) {
            throw new IllegalArgumentException("Delimiter must be a single character.");
        }

        if (!"stdout".equals(out)) {
            try {
                Path outPath = Path.of(out);
                if (outPath.getParent() != null) {
                    Files.createDirectories(outPath.getParent());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid output path: " + out, e);
            }
        }

        if (!filter.matches("[\\w]+(,[\\w]+)*")) {
            throw new IllegalArgumentException("Filter must be comma-separated column names.");
        }
    }

    private static void loadFile(String path, String delimiter) {
        try (Scanner scanner = new Scanner(new FileReader(path, StandardCharsets.UTF_8))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Empty CSV file: " + path);
            }

            String[] headers = scanner.nextLine().split(delimiter);
            for (int i = 0; i < headers.length; i++) {
                headerIndexMap.put(headers[i], i);
            }

            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine().split(delimiter));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file: " + path, e);
        }
    }

    private static void filterColumns(String filterArg, String delimiter) {
        String[] filters = filterArg.split(",");
        List<Integer> selectedIndexes = Arrays.stream(filters)
                .map(headerIndexMap::get)
                .filter(Objects::nonNull)
                .toList();

        resultLines.add(String.join(delimiter, filters));

        for (String[] row : rows) {
            List<String> selected = new ArrayList<>();
            for (int index : selectedIndexes) {
                selected.add(index < row.length ? row[index] : "");
            }
            resultLines.add(String.join(delimiter, selected));
        }
    }

    private static void outputResult(String out) {
        if ("stdout".equals(out)) {
            resultLines.forEach(System.out::println);
        } else {
            writeToFile(out);
        }
    }

    private static void writeToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filePath), StandardCharsets.UTF_8)))) {
            for (String line : resultLines) {
                writer.println(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to write to file: " + filePath, e);
        }
    }

    public static void main(String[] args) {
        ArgsName parsedArgs = ArgsName.of(args);
        handle(parsedArgs);
    }
}