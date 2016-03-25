package audio.test01;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.*;

public class AudioRecorder extends JFrame {

	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	public CaptureThread CaptureThread;
	final JButton ErfassungBtn = new JButton("Erfassung");
	final JButton stopBtn = new JButton("Stop");
	final JPanel btnPanel = new JPanel();
	final ButtonGroup btnGroup = new ButtonGroup();
	final JRadioButton waveBtn = new JRadioButton("WAVE");

	public AudioRecorder() {

		ErfassungBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		ErfassungBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErfassungBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				captureAudio();
			}
		});

		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErfassungBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				targetDataLine.stop();
				targetDataLine.close();
			}
		});

		getContentPane().add(ErfassungBtn);
		getContentPane().add(stopBtn);

		btnGroup.add(waveBtn);
		btnPanel.add(waveBtn);
		getContentPane().add(btnPanel);
		getContentPane().setLayout(new FlowLayout());

		setTitle("Sprecheridentifikation, Li Zongke");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 100);
		setVisible(true);
	}

	private void captureAudio() {
		try {
			// Get things set up for capture
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(
					TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			new CaptureThread().start();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private AudioFormat getAudioFormat() {
		float sampleRate = 16000.0F;

		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}

	// Inner class to capture data from microphone and write it to an output
	// audio file.
	class CaptureThread extends Thread {

		public void run() {
			AudioFileFormat.Type fileType = null;
			File audioFile = null;

			if (waveBtn.isSelected()) {
				fileType = AudioFileFormat.Type.WAVE;
				audioFile = new File("Beispiel.wav");
			}

			try {
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				AudioSystem.write(new AudioInputStream(targetDataLine),
						fileType, audioFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	public static void main(String args[]){
		AudioRecorder ar=new AudioRecorder();
	}
	
}
