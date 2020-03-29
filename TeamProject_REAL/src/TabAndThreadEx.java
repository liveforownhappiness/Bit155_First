import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TabAndThreadEx extends JFrame {

    ChargingBar bar = new ChargingBar(100);
    JLabel limitLabel = new JLabel("���ѽð� : ");
    JLabel second = new JLabel();
    JLabel secondLabel = new JLabel("��");
    JLabel finishLabel = new JLabel("!!!Game Over!!!!");

    TabAndThreadEx(int sec) {

        this.setTitle("������ ä���");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        limitLabel.setBounds(200, 10, 80, 25);
        this.add(limitLabel);

        second.setBounds(260, 10, 80, 25);
        second.setText(String.valueOf(sec));
        this.add(second);

        secondLabel.setBounds(300, 10, 80, 25);
        this.add(secondLabel);

        bar.setBackground(Color.orange);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300, 20);
        this.add(bar);

        finishLabel.setBounds(130, 100, 100, 50);
        this.add(finishLabel);
        finishLabel.setVisible(false);

        this.setLocationRelativeTo(null);
        this.setSize(350, 200);
        this.setVisible(true);
        this.requestFocus();

        ConsumerThread th = new ConsumerThread(this, second,finishLabel, sec, bar);
        th.start();
    }
}
