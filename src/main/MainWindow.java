package main;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import angrybird.view.AngryBirds;

public class MainWindow {

	public static void main(String[] args) {
		Frame frame = new Frame("Oiseau pas content");
        final AngryBirds obj = new AngryBirds();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(obj);
        frame.pack();
        frame.setVisible(true);
    }

	}


