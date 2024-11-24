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

    // Chuyển List<String> thành chuỗi, mỗi phần tử cách nhau bằng dấu phẩy
    String msg = String.join(",", list);

    return msg;
}


    // Hàm tạo danh sách ngẫu nhiên (chứa chữ cái hoặc số)
    static List<String> randomList() {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        // Tạo dãy số ngẫu nhiên
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(100); // Tạo số từ 0 đến 99
            list.add(String.valueOf(number));
        }

      

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
