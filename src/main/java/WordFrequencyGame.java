import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String DELIMITER_NEWLINE = "\n";

    public String getResult(String sentence) {
        try{
            return calculateWordFrequency(sentence).stream()
                    .sorted(Comparator.comparing(WordInfo::getCount).reversed())
                    .map(wordInfo -> String.format("%s %d",wordInfo.getWord(),wordInfo.getCount()))
                    .collect(Collectors.joining(DELIMITER_NEWLINE));
        }catch(Exception e){
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        return distinctWords.stream()
                .map(distinctWord -> new WordInfo(distinctWord,Collections.frequency(words,distinctWord)))
                .collect(Collectors.toList());
    }

}
