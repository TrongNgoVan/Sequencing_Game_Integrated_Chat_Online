package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ngọ Văn Trọng
 */
public class Dayso{

    public static String renQuestion() {
        // Tạo một dãy số hoặc chữ cái ngẫu nhiên
        List<String> list = randomList(); // List ngẫu nhiên
        List<String> correctAnswer = new ArrayList<>(list); // Đáp án đúng

        // Sắp xếp danh sách theo thứ tự tăng dần
        Collections.sort(correctAnswer);

        // Tạo danh sách chứa các đáp án sai
        List<List<String>> wrongAnswers = generateWrongAnswers(list);

        // Gộp đáp án đúng và đáp án sai
        wrongAnswers.add(correctAnswer);

        // Trộn thứ tự các đáp án (để đảm bảo đáp án đúng không luôn ở vị trí cố định)
        Collections.shuffle(wrongAnswers);

        // Xây dựng chuỗi thông báo câu hỏi và các đáp án
        String msg = String.join(",", list) + ";" ;
        int i = 1;
        for (List<String> answer : wrongAnswers) {
            msg +=  String.join(",", answer)+ ";" ;
            i++;
        }

        return msg;
    }

    // Hàm tạo danh sách ngẫu nhiên (chứa chữ cái hoặc số)
    static List<String> randomList() {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        // Tạo dãy số ngẫu nhiên
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(100); // Tạo số từ 0 đến 99
            list.add(String.valueOf(number));
        }

        // Hoặc nếu muốn tạo dãy chữ cái thay vì số, có thể thay thế bằng:
        // for (int i = 0; i < 5; i++) {
        //     char letter = (char) ('A' + random.nextInt(26)); // Tạo chữ cái ngẫu nhiên từ A-Z
        //     list.add(String.valueOf(letter));
        // }

        return list;
    }

    // Hàm tạo các đáp án sai bằng cách xáo trộn danh sách ban đầu
    static List<List<String>> generateWrongAnswers(List<String> list) {
        List<List<String>> wrongAnswers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<String> wrongAnswer = new ArrayList<>(list);
            Collections.shuffle(wrongAnswer); // Xáo trộn thứ tự để tạo đáp án sai
            wrongAnswers.add(wrongAnswer);
        }
        return wrongAnswers;
    }

    public static void main(String[] args) {
        // Test tạo câu hỏi
        String question = renQuestion();
        System.out.println(question);
    }
}
