package search;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AnyWordSearchStrategy implements SearchStrategy {
    @Override
    public List<String> find(Dictionary dictionary, String[] words) {
        return Arrays.stream(words)
                .map(dictionary::getLinesByWord)
                .flatMap(Collection::stream)
                .distinct()
                .map(dictionary::getLine)
                .collect(toList());
    }
}
