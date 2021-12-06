import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String DELIMITER_NEWLINE = "\n";

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_PATTERN).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);

                wordInfoList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                StringJoiner joiner = new StringJoiner(DELIMITER_NEWLINE);
                for (WordInfo wordInfo : wordInfoList) {
                    joiner.add(wordInfo.getWord() + " " + wordInfo.getCount());
                }
                return joiner.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordInfo>calculateWordFrequency(String sentence){
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach( distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, frequency)) ;
        });
        return wordInfos;
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return map;
    }


}
