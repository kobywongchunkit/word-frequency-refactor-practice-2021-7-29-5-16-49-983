import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String DELIMITER_NEWLINE = "\n";

    public String getResult(String sentence) {
        if (sentence == null) return CALCULATE_ERROR;
        StringJoiner sentenceJoiner = new StringJoiner(DELIMITER_NEWLINE);
        calculateWordFrequency(sentence).stream()
                .sorted(Comparator.comparing(WordInfo::getCount).reversed()).forEach(wordInfo ->
                        sentenceJoiner.add(wordInfo.getWord() + " " + wordInfo.getCount())
                );
        return sentenceJoiner.toString();
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, frequency));
        });
        return wordInfos;
    }

}
