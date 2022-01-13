package net.mingsoft.cms.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SensitiveWord {

    private Object syncRoot = new Object();
    public static HashMap<String, Object> sensitiveWordMap;

    public static int minMatchType = 1;

    public static int maxMatchType = 2;

    public SensitiveWord(String path) throws IOException {
        if (sensitiveWordMap == null) {
            synchronized(syncRoot) {
                if (sensitiveWordMap == null) {
                    this.initKeyWord(path);
                }
            }
        }
    }
    
    public void initKeyWord(String sensitiveWordPath) throws IOException {
        String sContent = Files.readString(Paths.get(sensitiveWordPath));
        String[] words = sContent.split("，");
        Set<String> keyWordSet = new HashSet<String>();
        for (String s : words) {
            keyWordSet.add(s.trim());
        }
        addSensitiveWordToHashMap(keyWordSet);
    }

    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap<>(keyWordSet.size());
        String key = null;
        Map<String, Object> nowMap = null;
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);

                Object wordMap = nowMap.get(String.valueOf(keyChar));
                if (wordMap != null) {
                    nowMap = (Map<String, Object>) wordMap;
                } else {
                    Map<String, Object> newWorMap = new HashMap<>();
                    newWorMap.put("isEnd", "0");
                    nowMap.put(String.valueOf(keyChar), newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    public int getWordSize() {
        return (sensitiveWordMap == null) ? 0 : sensitiveWordMap.size();
    }

    public boolean isContaintSensitiveWord(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveWord(txt, i, matchType);
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }

    public Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();

        for (int i = 0; i < txt.length(); i++) {
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    public String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }
        return resultReplace;
    }

    public int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;
        char word = 0;
        Map<String, Object> nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++){
            word = txt.charAt(i);
            nowMap = (Map<String, Object>) nowMap.get(String.valueOf(word));
            if (nowMap == null) {
                break;
            }
            matchFlag++;
            if ("1".equals(nowMap.get("isEnd"))) {
                flag = true;
                if (minMatchType == matchType) {
                    break;
                }
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
}