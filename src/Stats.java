/*
Twidor: the twiddler typing tutor.
Copyright (C) 2005	James Fusia
Copyright (C) 2017	Carey Richard Murphey

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
 * Stats.java, what I expect to be the most heavily modified file here.
 * This file is meant to hold the stats for a user/session/whatever. For
 * however long. And automatically do the calculations. Etc.
 *
 * Revisions:
 * 			0.5	17 July 2003
 * 			Completed Tutor
 * 			0.1	22 May 2003
 * 			Created class Stats
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.io.*;
import java.util.Vector;
import java.lang.Math;
public class Stats implements TwidorConstants {

	/**
	 * internal variable that contains stats
	 */
	private String sentence;
	private String typed;
	private Vector <StatsElement> statArray; 
	private Vector <StatsElement> transcribedInput;

	/**
	 * default constructor
	 */
	private Stats () {
		sentence = "";
		typed = "";
		statArray = new Vector <StatsElement> ();
		transcribedInput = new Vector <StatsElement> ();
	}

	/**
	 * default constructor
	 */
	public Stats (String newSentence) {
		this();
		if (bDEBUG) System.out.println("Stats created");
		setSentence(newSentence);
	}

	/**
	 * Accessor to get the sentence for this stat
	 * @return String the sentence we contain
	 */
	public String getSentence () {
		return sentence;
	}

	/**
	 * Accessor to get the typed sentence for this stat
	 * @return String the sentence actually typed
	 */
	public String getTyped () {
		return typed;
	}

	/**
	 * Accessor to get the vector of keypresses
	 * @return Vector of StatsElement
	 */
	public Vector <StatsElement> getKeyPresses () {
		return statArray;
	}

	/**
	 * Accessor for the Transcribed Input
	 * @return Vector of StatsElements
	 */
	public Vector <StatsElement> getTranscribedInput () {
		return transcribedInput;
	}

	/**
	 * Modifier to set the current sentence
	 * @param String the sentence to set
	 */
	public void setSentence (String line) {
		sentence = line;
	}

	/**
	 * Modifier to set the KeyPress vector
	 * @param Vector the new vector to set
	 */
	public void setKeyPresses (Vector <StatsElement> poke) {
		statArray = poke;
	}

	/**
	 * Accessor to add a new StatsElement to the Stats
	 * @param KeyElement the key that was pressed
	 * @param long the time it took to press it (in ms)
	 */
	public void addPressed (KeyElement pressed, long time) {
		getKeyPresses().add(new StatsElement(pressed, time));
		if ( ( pressed.match(KEYCODE_BACKSPACE, UNICODE_BACKSPACE) ||
                       pressed.match(KEYCODE_DELETE, UNICODE_DELETE)
                       ) &&
                     ( transcribedInput.size() > 0 )
                     ) {
			int last = transcribedInput.size() - 1;
			transcribedInput.removeElementAt(last);
			typed = typed.substring(0, typed.length() - 1);
		} else {
			transcribedInput.add(new StatsElement(pressed, time));
			typed += pressed.getMacro();
		}
	}

	public double getCPS () {
		long totalTime = 0;
		StatsElement temp1, temp2;
		if (transcribedInput.isEmpty())
			return 0;
		temp1 = transcribedInput.elementAt(0);
		for (int i = 1; i < transcribedInput.size() - 1; i++) {
			temp2 = transcribedInput.elementAt(i);
			totalTime += (temp2.getTime() - temp1.getTime());
			temp1 = temp2;
		}
		return (double)((transcribedInput.size() - 2) * 1000) / (double)totalTime;
	}

	public double getAER () {
		double err;
		int[] stats = StatsCalc.statsALL(getSentence(), getTyped(), getKeyPresses());

		err = (double)(stats[INF] + stats[IF]) / (double)(stats[C] + stats[INF] + stats[IF]);
		err = 100 - Math.floor(err * 1000) / 10;
		return err;
	}

	/**
	 * Function for writing all stats to a file.
	 * @param String the name of the file to output to
	 */
	public void writeStats (String dest) {
		FileWriter fwFile;
		BufferedWriter bwBuffer;
		StatsElement temp;
		if (getKeyPresses().size() <= 0) {
			/* No need to bother saving */
			return;
		}
		try {
			fwFile = new FileWriter(dest);
			bwBuffer = new BufferedWriter(fwFile);
			
			bwBuffer.write(getSentence());
			bwBuffer.newLine();
			for (int i = 0; i < getKeyPresses().size(); i++) {
//				bwBuffer.write(((StatsElement)getKeyPresses().elementAt(i)).toString());
				bwBuffer.write(getKeyPresses().elementAt(i).toString());
				bwBuffer.newLine();
			}
			bwBuffer.close();
			fwFile.close();
		}
		catch (Exception e) {
			if (bDEBUG) System.out.println("Stats: Error writing to file " + dest);
		}
	}

}
