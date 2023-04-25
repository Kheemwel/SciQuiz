package com.CrimsonKnightBlood.SciQuiz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class QAParser {
    private final Context context;
    private final int resource;
    private final String splitter;
    private final int answerIndex;
    private final List<String> questions;
    private final Map<String, List<String>> choices;
    private final Map<String, String> answers;

    public QAParser(Context context, int resource, String splitter, int answerIndex) {
        this.context = context;
        this.resource = resource;
        this.splitter = splitter;
        this.answerIndex = answerIndex;

        questions = new ArrayList<>();
        choices = new HashMap<>();
        answers = new HashMap<>();

        parseCSV();
    }

    private void parseCSV() {
        try (InputStream inputStream = context.getResources().openRawResource(resource) ) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String csvLine;
                while ((csvLine = bufferedReader.readLine()) != null) {
                    String[] parts = csvLine.split(splitter);
                    String question = parts[0];
                    String answer = parts[answerIndex];

                    questions.add(question);
                    answers.put(question, answer);

                    if (answerIndex > 2) {
                        String[] choiceArr = Arrays.copyOfRange(parts, 1, answerIndex);
                        choices.put(question, new ArrayList<>(Arrays.asList(choiceArr)));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Error in reading CSV file: " + e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while closing input stream: " + e);
        }
    }

    public List<String> getQuestions() {
        return questions;
    }

    public Map<String, List<String>> getChoices() {
        return choices;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }
}
