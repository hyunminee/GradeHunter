package GradeHunter;


import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 퀴즈 분야 선택을 담당하는 클래스입니다.
 * @author 서보경
 */
public class QuizSelectPanel {
    public static void main(String[] args) {

        // 프레임 생성
        JFrame frm = new JFrame("GradeHunter");

        // 프레임 크기 설정
        frm.setSize(1080, 720);

        // 프레임을 화면 가운데에 배치
        frm.setLocationRelativeTo(null);

        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 레이아웃 설정
        frm.getContentPane().setLayout(null);

        // 버튼 생성
        JButton btn1 = new JButton("국어");
        JButton btn2 = new JButton("수학");
        JButton btn3 = new JButton("영어");
        JButton btn4 = new JButton("지리");
        JButton btn5 = new JButton("역사");
        JButton btn6 = new JButton("과학");



        // ★ 버튼 위치와 크기 설정
        btn1.setBounds(40, 100, 300, 200);
        btn2.setBounds(380, 100, 300, 200);
        btn3.setBounds(720, 100, 300, 200);
        btn4.setBounds(40, 360,300, 200);
        btn5.setBounds(380, 360,300, 200);
        btn6.setBounds(720, 360,300, 200);


        // ★ 프레임에다가 버튼 추가
        frm.getContentPane().add(btn1);
        frm.getContentPane().add(btn2);
        frm.getContentPane().add(btn3);
        frm.getContentPane().add(btn4);
        frm.getContentPane().add(btn5);
        frm.getContentPane().add(btn6);



        // 프레임이 보이도록 설정
        frm.setVisible(true);

    }
}




