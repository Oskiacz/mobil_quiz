package com.example.quiz;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Question[] questions = {
            new Question("The original god of destruction... One of whom is the very prime ingredient for existence... Like shadow to light... Who is his counter-part?", "Gro-goroth", "Sylvian", "Rher", "Vinushka", "Sylvian"),
            new Question("Among us... the new gods... Who is torment...?", "Chambara", "Valteil", "FrancÃ³is", "Nilvan", "Chambara"),
            new Question("We, the new gods... While still walking among men...Our fellowship, when did we embark on our journey to ascension?", "In the year 809", "In the year 2000 BC", "In the year 862", "In the year 1760", "In the year 809"),
            new Question("As Valteil the enlightened one... Who preceded me here at the grand libraries?", "Alll-mer", "Nosramus", "Nas'hrah", "Enki", "Nas'hrah"),
            new Question("Alll-mer the ascended one... The last of the older gods. What year marks the birth of his new self?", "In the year 0", "In the year 809", "In the year 203", "In the year 1500 BC", "In the year 0"),
            new Question("The dark continent.... whence the darkness slowly leaks to the western world... Where the day only shines..... eternal darkness and grey gloom.... What is it called among the people of Europa?", "Vinland", "Europa", "Bohemia", "Abyssonia", "Vinland"),
            new Question("Rher the god from the unknown beyond the blue skies... He has an effect to feeble humans, what is it called?", "It's called scarlet cancer", "It's called anxiety", "It's called scarlet rot", "It's called moonlight cancer", "It's called moonlight cancer"),
            new Question("The character who walks among men... Called the Pocketcat. Is he the servant of which older god?", "The god of creation", "The Trickster moon god", "The god of destruction", "The god of nature", "The Trickster moon god"),
            new Question("Among the gods... Which is the child of creation and descrution?", "Rher", "Vinushka", "The God of Fear and Hunger", "The Sulfur God", "Vinushka"),
            new Question("In the farthest corners of the Eastern Sanctuaries... Warriors seek to combat a revered an ancient warrior deity... What is it's name?", "Iki Turso", "Per'kele", "Vitruvia", "Yggaegetsu", "Yggaegetsu")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.question_list);
        listView.setAdapter(new QuestionAdapter());
    }

    private class QuestionAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return questions.length;
        }

        @Override
        public Object getItem(int position) {
            return questions[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.question_layout, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final Question question = questions[position];
            viewHolder.questionText.setText(question.getQuestion());
            viewHolder.answer1.setText(question.getAnswer1());
            viewHolder.answer2.setText(question.getAnswer2());
            viewHolder.answer3.setText(question.getAnswer3());
            viewHolder.answer4.setText(question.getAnswer4());

            GradientDrawable border = new GradientDrawable();
            border.setCornerRadius(10f);
            border.setStroke(5, Color.BLACK);
            viewHolder.container.setBackground(border);

            viewHolder.answersGroup.setOnCheckedChangeListener(null);
            if (question.getSelectedAnswer() != -1) {
                viewHolder.answersGroup.check(question.getSelectedAnswer());
                RadioButton selected = convertView.findViewById(question.getSelectedAnswer());
                if (selected != null && selected.getText().equals(question.getCorrectAnswer())) {
                    border.setStroke(5, Color.GREEN);
                } else {
                    border.setStroke(5, Color.RED);
                }
                viewHolder.container.setBackground(border);
            } else {
                viewHolder.answersGroup.clearCheck();
            }

            viewHolder.answersGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    question.setSelectedAnswer(checkedId);
                    RadioButton selected = group.findViewById(checkedId);
                    GradientDrawable border = new GradientDrawable();
                    border.setCornerRadius(10f);

                    if (selected != null) {
                        border.setStroke(5, selected.getText().equals(question.getCorrectAnswer()) ? Color.GREEN : Color.RED);
                    } else {
                        border.setStroke(5, Color.BLACK);
                    }

                    viewHolder.container.setBackground(border);
                }
            });

            return convertView;
        }
    }
    private static class ViewHolder {
        View container;
        TextView questionText;
        RadioGroup answersGroup;
        RadioButton answer1, answer2, answer3, answer4;

        ViewHolder(View view) {
            container = view;
            questionText = view.findViewById(R.id.question);
            answersGroup = view.findViewById(R.id.answers);
            answer1 = view.findViewById(R.id.answer1);
            answer2 = view.findViewById(R.id.answer2);
            answer3 = view.findViewById(R.id.answer3);
            answer4 = view.findViewById(R.id.answer4);
        }
    }
    private static class Question {
        private String question;
        private String answer1;
        private String answer2;
        private String answer3;
        private String answer4;
        private String correctAnswer;
        private int selectedAnswer = -1; // Add this field to store the selected answer

        public Question(String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
            this.question = question;
            this.answer1 = answer1;
            this.answer2 = answer2;
            this.answer3 = answer3;
            this.answer4 = answer4;
            this.correctAnswer = correctAnswer;
        }
        public String getQuestion() {
            return question;
        }
        public String getAnswer1() {
            return answer1;
        }
        public String getAnswer2() {
            return answer2;
        }
        public String getAnswer3() {
            return answer3;
        }
        public String getAnswer4() {
            return answer4;
        }
        public String getCorrectAnswer() {
            return correctAnswer;
        }
        public int getSelectedAnswer() {
            return selectedAnswer;
        }
        public void setSelectedAnswer(int selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }
    }
}
