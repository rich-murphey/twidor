/*  -*- indent-tabs-mode: t; tab-width: 8; c-basic-offset: 8 -*-
/*
Twidor: the twiddler typing tutor.
Copyright (C) 2005	James Fusia

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
USA.
 */
/**
 * CCG: Twidor- The Twiddler Tutor!
 * <pre>
 * TypingPanel.java, because I figured "Everything else has it's own class,
 * why not this one, too!".
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.1	10 June 2003
 * 		Created class TypingPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.lang.String;
/**
 * @author rich
 *
 */
public class TypingPanel extends JPanel implements TwidorConstants {

	/**
	 * internal variables
	 */
	Vector Sentence;
	String sentenceText;

	Vector Typed;           // characters typed by user
	int current;            // cursor on typed text
	boolean highlightErrors;
	boolean allowErrors;
	boolean finished;
	int min_display_length = 40;

	/**
	 * default constructor
	 */
	public TypingPanel () {
		Sentence = new Vector();
                String sentenceText = "";
		Typed = new Vector();
		setCurrent(0);
		setEntered(false);
		setHighlightErrors(HIGHLIGHT_ERRORS);
		//setAllowErrors(ALLOW_ERRORS);
		setAllowErrors(true);
		setBackground(TEXT_BACKGROUND);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(margin);
		setVisible(true);
		if (bDEBUG) System.out.println("TypingPanel: Created New Panel");
	}// end TypingPanel ()

	/**
	 * Modifier for whether or not we highlight on error
	 * @param boolean the new status of highlighting
	 */
	public void setHighlightErrors (boolean status) {
		highlightErrors = status;
	}// end setHighlightErrors (boolean)

	/**
	 * Modifier for whether or not we allow errors
	 * @param boolean the new status of errors
	 */
	public void setAllowErrors (boolean status) {
		allowErrors = status;
	}// end setAllowErrors (boolean)

	/**
	 * Modifier for the current JLabel in the typing panel
	 * @param int the new value to use as 'current'
	 */
	public void setCurrent (int thing) {
		if (thing <= 0) {
			current = 0;
		}
		else {
			current = thing;
		}
	}// end setCurrent (int)

	/**
	 * Private function to determine some whacky stupid things
	 * @param boolean the new value to set
	 */
	private void setEntered (boolean value) {
		finished = value;
	}// end setEntered (boolean)

	/**
	 * Accessor for whether or not we highlight on error
	 * @return boolean the status of highlighting
	 */
	public boolean getHighlightErrors () {
		return highlightErrors;
	}// end getHighlightErrors ()

	/**
	 * Accessor for whether or not we allow errors
	 * @return boolean the status of errors
	 */
	public boolean getAllowErrors () {
		return allowErrors;
	}// end getAllowErrors ()

	/**
	 * Accessor for the current JLabel in the typing panel
	 * @return int the value of the current JLabel
	 */
	public int getCurrent () {
		return current;
	}// end getCurrent ()

	/**
	 * Accessor for the finished variable
	 * @return boolean the finished variable
	 */
	private boolean getEntered () {
		return finished;
	}// end getEntered ()

	public String getSentenceText() {
		return sentenceText;
	}

	private void setSentenceText(String sentenceText) {
		this.sentenceText = sentenceText;
	}
	/**
	 * Called when we want to display a message
	 */
	public void displayMessage (String message) {
		setVisible(false);
		removeAll();
		JLabel myMessage = new JLabel(message);
		JPanel temp = new JPanel();
		temp.setAlignmentY(Component.CENTER_ALIGNMENT);
		temp.add(myMessage);
		temp.setBackground(TEXT_BACKGROUND);
		add(temp);
		setBackground(TEXT_BACKGROUND);
		setVisible(true);
	}

	/**
	 * Called when a new sentence is to be displayed
	 * @param String the new sentence to display
	 */
	public void displaySentence (String sentence) {
		setVisible(false);
		setSentenceText(sentence);
		JPanel sentence_panel = new JPanel();
		sentence_panel.setAlignmentY(CENTER_ALIGNMENT);

		if (bDEBUG) System.out.println("TypingPanel: Displaying new sentence");
		/* Empty everything in this JPanel, and clear all associated variables */
		Sentence.clear();
		Typed.clear();
		setCurrent(0);
		setEntered(false);
		removeAll();

		if (sentence == null) {
			if (bDEBUG) System.out.println("TypingPanel: Got sent a null sentence");
			return;
		}
                min_display_length = java.lang.Math.max(min_display_length, sentence.length());
		int pad = java.lang.Math.max( 3, min_display_length - sentence.length());
                
		FontMetrics metrics = getGraphics().getFontMetrics(FONT_TEXT);
		int hgt = metrics.getHeight();
		int wid = metrics.stringWidth("m"); // M=14 x 26 m=17x26
		Dimension size = new Dimension(wid - 2, hgt);
//		System.out.println("font: " + size);

		sentence_panel.setLayout(new GridLayout(2, sentence.length() + pad, 0, 0));
		sentence_panel.setBackground(TEXT_BACKGROUND);
//		int maxWid = 0;
		for (int i = 0; i < sentence.length(); i++) {
			String letter = String.valueOf(sentence.charAt(i));
			JLabel newLetter = letterLabel(letter, size);
			Sentence.add(newLetter);
			sentence_panel.add(newLetter);
//			int w = metrics.stringWidth(letter);
//			if( w > maxWid ) { maxWid = w; }
		}
//		System.out.println("maxWid: " + maxWid);
		/* text alignment. We allow typers to go +/- 3 characters before hitting enter. */
		for (int i = sentence.length();  i < sentence.length() + pad; i++) {
			JLabel newLetter = letterLabel("", size);
			Sentence.add(newLetter);
			sentence_panel.add(newLetter);
		}
		for (int i = 0; i < sentence.length() + pad; i++) {
			JLabel newLetter = letterLabel("", size);
			if (i == 0) {
				newLetter.setText(CURSOR);
				newLetter.setForeground(TEXT_CURSOR);
			}
			Typed.add(newLetter);
			sentence_panel.add(newLetter);
		}
		sentence_panel.setMaximumSize(sentence_panel.getPreferredSize());
		// add(Box.createVerticalGlue());
                // add(Box.createRigidArea(new Dimension(0,pad)));
		add(sentence_panel);
                validate();
		setVisible(true);
		if (bDEBUG) System.out.println("TypingPanel: New sentence displayed");
	}// end displaySentence ()

	private JLabel letterLabel (String letter) {
		return letterLabel( letter, null);
	}

	private JLabel letterLabel (String letter, Dimension size) {
		JLabel newLetter = new JLabel(letter, javax.swing.SwingConstants.CENTER);
		newLetter.setFont(FONT_TEXT);
		newLetter.setForeground(TEXT_DEFAULT);
		newLetter.setBackground(TEXT_BACKGROUND);
		newLetter.setBorder(noBorder);
		if (size != null) {
//			newLetter.setMinimumSize( size );
			newLetter.setMaximumSize( size );
		}
		return newLetter;
	}

	/**
	 * Called when a character was typed
	 * @param KeyElement the character that was typed
	 */
	public void charTyped (KeyElement typed) {
		/* Make sure we're in valid character range */
		if (getCurrent() < 0) {
			setCurrent(0);
		}
		if (getCurrent() > getSentenceText().length()) {
			if (typed.getNumber() == KEY_EOL || typed.getNumber() == KEY_ENTER || typed.getLetter().equals("\n")) {
				if (getAllowErrors()) {
					setEntered(true);
				}
			}
			else if (typed.getNumber() == KEY_BACKSPACE || typed.getNumber() == KEY_DELETE) {
				setCurrent(getCurrent() - 1);
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
				((JLabel)Typed.elementAt(getCurrent())).setForeground(TEXT_CURSOR);
				((JLabel)Typed.elementAt(getCurrent())).setText(CURSOR);
			}
			else {
				if (bDEBUG) System.out.println("TypingPanel: Not accepting other input.");
			}
			return;
		}

		setVisible(false);
		if (typed.getNumber() == KEY_EOL || typed.getNumber() == KEY_ENTER || typed.getLetter().equals("\n")) {
			/* Enter only matters if we're allowing errors and the sentence is finished */
			if (getCurrent() >= getSentenceText().length()) {
				setEntered(true);
			}
		}
		else if ((typed.getNumber() == KEY_BACKSPACE) || (typed.getNumber() == KEY_DELETE)) {
			/* Erase */
			if (getCurrent() <= 0) {
				if (bDEBUG) System.out.println("TypingPanel: Can't delete what's not there");
			}
			else {
				((JLabel)Typed.elementAt(getCurrent())).setText("");
				((JLabel)Typed.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
				setCurrent(getCurrent() - 1);
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
			}
		}
		else {
			/* Treat it like a normal character */
			String toMatch = ((JLabel)Sentence.elementAt(getCurrent())).getText();
			((JLabel)Typed.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
			((JLabel)Typed.elementAt(getCurrent())).setText(typed.displayLetter());
			if (typed.displayLetter().equals(toMatch)) {
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_GOOD);
				setCurrent(getCurrent() + 1);
			}
			else {
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_ERROR);
				if (getAllowErrors()) {
					setCurrent(getCurrent() + 1);
				}
			}
		}
		if (getCurrent() < Typed.size()) {
			((JLabel)Typed.elementAt(getCurrent())).setForeground(TEXT_CURSOR);
			((JLabel)Typed.elementAt(getCurrent())).setText(CURSOR);
		}
		setVisible(true);
	}// end charTyped (KeyElement)

	/**
	 * Called to check and see if the sentence is complete
	 */
	public boolean sentenceComplete () {
		if (getAllowErrors()) {
			return getEntered();
		}
		return (getCurrent() >= getSentenceText().length());
	}// end sentenceComplete ()

}// end class TypingPanel
