package search;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class NoneWordsSearchStrategy implements SearchStrategy {
    @Override
    public List<String> find(Dictionary dictionary, String[] words) {
        Set<Integer> contains = Arrays.stream(words)
                .map(dictionary::getLinesByWord)
                .flatMap(Collection::stream)
                .collect(toSet());
        return IntStream.range(0, dictionary.getLineCount())
                .filter(i -> !contains.contains(i))
                .mapToObj(dictionary::getLine)
                .collect(toList());
    }
}
