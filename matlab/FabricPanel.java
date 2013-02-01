import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Sat Jul 14 21:15:09 EDT 2012
 */



/**
 * @author David Ing
 */
public class FabricPanel extends JPanel {
	public FabricPanel() {
		initComponents();
	}

	/*private void goMouseClicked(MouseEvent e) throws IOException {
		// TODO add your code here
		Runtime run = Runtime.getRuntime();
		
		if(cbHeatMap.isSelected())
		{
			Process proc = run.exec("C:\\Users\\David\\Dropbox\\FYDP\\Teensy\\HeatMap.bat");
		}
		else if (cbTouchPad.isSelected())
		{
			Process proc = run.exec("C:\\Users\\David\\Dropbox\\FYDP\\Teensy\\TouchPad.bat");
		}
		else if (cbMusic.isSelected())
		{
			Process proc = run.exec("C:\\Users\\David\\Dropbox\\FYDP\\Teensy\\Music.bat");
		}
	}*/
	
	public int[] getStatus()
	{
		//HeatMapEnabled TouchPadEnabled MusicEnabled InterpolationEnabled HeatColorEnabled TouchPadRadio MusicRadio ClickThreshold PlayThreshold Instrument
		
		int [] status = new int [10];
		status[0] = cbHeatMap.isSelected() ? 1 : 0;
		status[1] = cbTouchPad.isSelected() ? 1 : 0;
		status[2] = cbMusic.isSelected() ? 1 : 0;
		status[3] = cbInterpolate.isSelected() ? 1 : 0;
		status[4] = cbHeatColor.isEnabled() && cbHeatColor.isSelected() ? 1 : 0; 
		status[5] = rbTPFull.isSelected() ? 1 : 0;
		status[6] = rbMCont.isSelected() ? 1 : 0;
		status[7] = clickThreshold.getValue();
		status[8] = playThreshold.getValue();
		status[9] = comboBox1.getSelectedIndex();
						
		return status;
	}

	private void cbMusicStateChanged(ChangeEvent e) {
		if(!cbMusic.isSelected()){
			cbHeatColor.setEnabled(false);
		}
		else
		{
			cbHeatColor.setEnabled(true);
		}
	}

	private void initComponents() {
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - David Ing
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		separator1 = compFactory.createSeparator("HeatMap");
		cbHeatMap = new JCheckBox();
		separator2 = compFactory.createSeparator("TouchPad");
		cbTouchPad = new JCheckBox();
		hSpacer1 = new JPanel(null);
		hSpacer2 = new JPanel(null);
		cbInterpolate = new JCheckBox();
		vSpacer1 = new JPanel(null);
		rbTPFull = new JRadioButton();
		rbTPRel = new JRadioButton();
		label1 = compFactory.createLabel("Click Threshold:");
		clickThreshold = new JSlider();
		vSpacer2 = new JPanel(null);
		vSpacer3 = new JPanel(null);
		separator3 = compFactory.createSeparator("Music Application");
		cbMusic = new JCheckBox();
		cbHeatColor = new JCheckBox();
		rbMDisc = new JRadioButton();
		rbMCont = new JRadioButton();
		label3 = compFactory.createLabel("Play Threshold:");
		playThreshold = new JSlider();
		label2 = compFactory.createLabel("Instrument:");
		comboBox1 = new JComboBox();
		vSpacer4 = new JPanel(null);

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new FormLayout(
			"7*(default, $lcgap), default",
			"21*(default, $lgap), default"));
		add(separator1, CC.xywh(3, 1, 7, 1, CC.FILL, CC.DEFAULT));

		//---- cbHeatMap ----
		cbHeatMap.setText("Enabled");
		add(cbHeatMap, CC.xy(3, 3));
		add(separator2, CC.xywh(3, 7, 7, 1));

		//---- cbTouchPad ----
		cbTouchPad.setText("Enabled");
		add(cbTouchPad, CC.xy(3, 9));
		add(hSpacer1, CC.xywh(1, 1, 1, 33));
		add(hSpacer2, CC.xywh(11, 1, 1, 33));

		//---- cbInterpolate ----
		cbInterpolate.setText("Use Interpolation");
		add(cbInterpolate, CC.xy(7, 3));
		add(vSpacer1, CC.xywh(3, 5, 13, 2));

		//---- rbTPFull ----
		rbTPFull.setText("Full Screen");
		add(rbTPFull, CC.xy(3, 11));

		//---- rbTPRel ----
		rbTPRel.setText("Relative");
		add(rbTPRel, CC.xy(7, 11));
		add(label1, CC.xy(3, 13));
		add(clickThreshold, CC.xywh(5, 13, 4, 1, CC.FILL, CC.DEFAULT));
		add(vSpacer2, CC.xywh(3, 14, 13, 5));
		add(vSpacer3, CC.xywh(3, 17, 13, 2));
		add(separator3, CC.xywh(3, 19, 7, 1));

		//---- cbMusic ----
		cbMusic.setText("Enabled");
		cbMusic.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cbMusicStateChanged(e);
			}
		});
		add(cbMusic, CC.xy(3, 21));

		//---- cbHeatColor ----
		cbHeatColor.setText("Modify HeatMap Color");
		add(cbHeatColor, CC.xy(7, 21));

		//---- rbMDisc ----
		rbMDisc.setText("Discrete");
		add(rbMDisc, CC.xy(3, 23));

		//---- rbMCont ----
		rbMCont.setText("Continuous");
		add(rbMCont, CC.xy(7, 23));
		add(label3, CC.xy(3, 25));
		add(playThreshold, CC.xywh(5, 25, 4, 1));
		add(label2, CC.xy(3, 27));
		add(comboBox1, CC.xy(7, 27));
		add(vSpacer4, CC.xywh(3, 29, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	
		comboBox1.addItem("Acoustic Grand Piano");
		comboBox1.addItem("Bright Acoustic Piano");
		comboBox1.addItem("Electric Grand Piano");
		comboBox1.addItem("Honky-tonk Piano");
		comboBox1.addItem("Electric Piano 1");
		comboBox1.addItem("Electric Piano 2");
		comboBox1.addItem("Harpsichord");
		comboBox1.addItem("Clavi");
		comboBox1.addItem("Celesta");
		comboBox1.addItem("Glockenspiel");
		comboBox1.addItem("Music Box");
		comboBox1.addItem("Vibraphone");
		comboBox1.addItem("Marimba");
		comboBox1.addItem("Xylophone");
		comboBox1.addItem("Tubular Bells");
		comboBox1.addItem("Dulcimer");
		comboBox1.addItem("Drawbar Organ");
		comboBox1.addItem("Percussive Organ");
		comboBox1.addItem("Rock Organ");
		comboBox1.addItem("Church Organ");
		comboBox1.addItem("Reed Organ");
		comboBox1.addItem("Accordion");
		comboBox1.addItem("Harmonica");
		comboBox1.addItem("Tango Accordion");
		comboBox1.addItem("Acoustic Guitar (nylon)");
		comboBox1.addItem("Acoustic Guitar (steel)");
		comboBox1.addItem("Electric Guitar (jazz)");
		comboBox1.addItem("Electric Guitar (clean)");
		comboBox1.addItem("Electric Guitar (muted)");
		comboBox1.addItem("Overdriven Guitar");
		comboBox1.addItem("Distortion Guitar");
		comboBox1.addItem("Guitar harmonics");
		comboBox1.addItem("Acoustic Bass");
		comboBox1.addItem("Electric Bass (finger)");
		comboBox1.addItem("Electric Bass (pick)");
		comboBox1.addItem("Fretless Bass");
		comboBox1.addItem("Slap Bass 1");
		comboBox1.addItem("Slap Bass 2");
		comboBox1.addItem("Synth Bass 1");
		comboBox1.addItem("Synth Bass 2");
		comboBox1.addItem("Violin");
		comboBox1.addItem("Viola");
		comboBox1.addItem("Cello");
		comboBox1.addItem("Contrabass");
		comboBox1.addItem("Tremolo Strings");
		comboBox1.addItem("Pizzicato Strings");
		comboBox1.addItem("Orchestral Harp");
		comboBox1.addItem("Timpani");
		comboBox1.addItem("String Ensemble 1");
		comboBox1.addItem("String Ensemble 2");
		comboBox1.addItem("SynthStrings 1");
		comboBox1.addItem("SynthStrings 2");
		comboBox1.addItem("Choir Aahs");
		comboBox1.addItem("Voice Oohs");
		comboBox1.addItem("Synth Voice");
		comboBox1.addItem("Orchestra Hit");
		comboBox1.addItem("Trumpet");
		comboBox1.addItem("Trombone");
		comboBox1.addItem("Tuba");
		comboBox1.addItem("Muted Trumpet");
		comboBox1.addItem("French Horn");
		comboBox1.addItem("Brass Section");
		comboBox1.addItem("SynthBrass 1");
		comboBox1.addItem("SynthBrass 2");
		comboBox1.addItem("Soprano Sax");
		comboBox1.addItem("Alto Sax");
		comboBox1.addItem("Tenor Sax");
		comboBox1.addItem("Baritone Sax");
		comboBox1.addItem("Oboe");
		comboBox1.addItem("English Horn");
		comboBox1.addItem("Bassoon");
		comboBox1.addItem("Clarinet");
		comboBox1.addItem("Piccolo");
		comboBox1.addItem("Flute");
		comboBox1.addItem("Recorder");
		comboBox1.addItem("Pan Flute");
		comboBox1.addItem("Blown Bottle");
		comboBox1.addItem("Shakuhachi");
		comboBox1.addItem("Whistle");
		comboBox1.addItem("Ocarina");
		comboBox1.addItem("Lead 1 (square)");
		comboBox1.addItem("Lead 2 (sawtooth)");
		comboBox1.addItem("Lead 3 (calliope)");
		comboBox1.addItem("Lead 4 (chiff)");
		comboBox1.addItem("Lead 5 (charang)");
		comboBox1.addItem("Lead 6 (voice)");
		comboBox1.addItem("Lead 7 (fifths)");
		comboBox1.addItem("Lead 8 (bass + lead)");
		comboBox1.addItem("Pad 1 (new age)");
		comboBox1.addItem("Pad 2 (warm)");
		comboBox1.addItem("Pad 3 (polysynth)");
		comboBox1.addItem("Pad 4 (choir)");
		comboBox1.addItem("Pad 5 (bowed)");
		comboBox1.addItem("Pad 6 (metallic)");
		comboBox1.addItem("Pad 7 (halo)");
		comboBox1.addItem("Pad 8 (sweep)");
		comboBox1.addItem("FX 1 (rain)");
		comboBox1.addItem("FX 2 (soundtrack)");
		comboBox1.addItem("FX 3 (crystal)");
		comboBox1.addItem("FX 4 (atmosphere)");
		comboBox1.addItem("FX 5 (brightness)");
		comboBox1.addItem("FX 6 (goblins)");
		comboBox1.addItem("FX 7 (echoes)");
		comboBox1.addItem("FX 8 (sci-fi)");
		comboBox1.addItem("Sitar");
		comboBox1.addItem("Banjo");
		comboBox1.addItem("Shamisen");
		comboBox1.addItem("Koto");
		comboBox1.addItem("Kalimba");
		comboBox1.addItem("Bag pipe");
		comboBox1.addItem("Fiddle");
		comboBox1.addItem("Shanai");
		comboBox1.addItem("Tinkle Bell");
		comboBox1.addItem("Agogo");
		comboBox1.addItem("Steel Drums");
		comboBox1.addItem("Woodblock");
		comboBox1.addItem("Taiko Drum");
		comboBox1.addItem("Melodic Tom");
		comboBox1.addItem("Synth Drum");
		comboBox1.addItem("Reverse Cymbal");
		comboBox1.addItem("Guitar Fret Noise");
		comboBox1.addItem("Breath Noise");
		comboBox1.addItem("Seashore");
		comboBox1.addItem("Bird Tweet");
		comboBox1.addItem("Telephone Ring");
		comboBox1.addItem("Helicopter");
		comboBox1.addItem("Applause");
		comboBox1.addItem("Gunshot");
		comboBox1.setSelectedIndex(0);
		
		ButtonGroup bgTouchPad = new ButtonGroup();
		bgTouchPad.add(rbTPFull);
		bgTouchPad.add(rbTPRel);
		ButtonGroup bgMusic = new ButtonGroup();
		bgMusic.add(rbMCont);
		bgMusic.add(rbMDisc);
		
		cbHeatMap.setSelected(true);
		cbTouchPad.setSelected(false);
		cbMusic.setSelected(true);
		cbHeatColor.setSelected(true);
		rbTPRel.setSelected(true);
		rbMDisc.setSelected(true);
		rbTPFull.setEnabled(false);
		//rbMCont.setEnabled(false);
		
		clickThreshold.setMaximum(500);
		clickThreshold.setMinimum(50);
		clickThreshold.setMajorTickSpacing(150);
		clickThreshold.setPaintTicks(true);
		clickThreshold.setPaintLabels(true);
		clickThreshold.setValue(275);
		
		playThreshold.setMaximum(500);
		playThreshold.setMinimum(100);
		playThreshold.setMajorTickSpacing(100);
		playThreshold.setPaintTicks(true);
		playThreshold.setPaintLabels(true);
		playThreshold.setValue(200);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - David Ing
	private JComponent separator1;
	private JCheckBox cbHeatMap;
	private JComponent separator2;
	private JCheckBox cbTouchPad;
	private JPanel hSpacer1;
	private JPanel hSpacer2;
	private JCheckBox cbInterpolate;
	private JPanel vSpacer1;
	private JRadioButton rbTPFull;
	private JRadioButton rbTPRel;
	private JLabel label1;
	private JSlider clickThreshold;
	private JPanel vSpacer2;
	private JPanel vSpacer3;
	private JComponent separator3;
	private JCheckBox cbMusic;
	private JCheckBox cbHeatColor;
	private JRadioButton rbMDisc;
	private JRadioButton rbMCont;
	private JLabel label3;
	private JSlider playThreshold;
	private JLabel label2;
	private JComboBox comboBox1;
	private JPanel vSpacer4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
