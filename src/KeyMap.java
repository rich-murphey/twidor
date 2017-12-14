/*  -*- indent-tabs-mode: t; tab-width: 4; c-basic-offset: 4 -*-
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
 * KeyMap.java, a class for loading and storing of a KeyMap from the
 * 'standard' Twiddler1 format.
 *
 * Revisions:
 * 		0.5	17 July 2003
 * 		Completed Tutor
 * 		0.2	06 June 2003
 * 		Need to implement keymap file parsing.
 *		0.1	23 May 2003
 * 		Created class KeyMap
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.io.*;
import java.util.*;
public class KeyMap implements TwidorConstants {

	/**
	 * the keymap, as we see it. a Vector of KeyElements (buttons + map)
	 */
	private Vector <KeyElement> keylist;

	/**
	 *  a map from html tag key names in input file to ASCII caracter values.
	 */
	private HashMap<String, Integer> keyTags;
	private HashMap<String, Integer> letterToKeyIndex;

	/**
	 * default constructor
	 */
	private KeyMap () {
		keylist = new Vector <KeyElement> ();

                keyTags = new HashMap<String, Integer>();
		keyTags.put("<Backspace>",	8);
		keyTags.put("<Delete>",		127);
		keyTags.put("<Return>",		10); // note: should be 13, but keyboard emit 10?
		keyTags.put("<Tab>",		9);
		keyTags.put("<Escape>",		27);
		// "<LeftArrow>",		0,
		// "<DownArrow>",		0,
		// "<RightArrow>",		0,
		// "<UpArrow>",			0,
		// "<PageDown>",		0,
		// "<PageUp>",			0,
		// "<End>",			0,
		// "<Home>",			0,
		// "<Insert>",			0,
		// "<NumLock>",			0
		// <F1>..<F12>

                letterToKeyIndex = new HashMap<String, Integer>();
	}// end KeyMap

	/**
	 * default constructor
	 * @param String the file to read the keymap from
	 */
	public KeyMap (String source) {
		this();
		readKeyMap(source);
	}// end KeyMap (String)

	/**
	 * Gets the Keys matching the requested letter
	 * @param String the letter to match
	 * @return KeyElement the Key that matches the desired String
	 */
	public KeyElement getKey (String key) {
		KeyElement toReturn = null;
		int i = getKeylist().indexOf(new KeyElement(key));

		if (i >= 0) {
			toReturn = getKeylist().elementAt(i);
		} else {
                        toReturn = getKeyByLetter( key );
                }
		return toReturn;
	}// end getKey (String)

	/**
	 * Gets the Key matching the KeyElement button vector set
	 * @param Vector of KeyStatus to match against
	 * @return KeyElement the requested KeyElement
	 */
	public KeyElement getKey (Vector <KeyStatus> buttons) {
		KeyElement toReturn = null;
		int i = getKeylist().indexOf(new KeyElement(buttons));

		if (i >= 0) {
			toReturn = (KeyElement) getKeylist().elementAt(i);
		}
		return toReturn;
	}// end getKey (Vector)

	/**
	 * Function for testing whether or not the KeyMap appears valid
	 * @return boolean the validity of the keymap
	 */
	public boolean appearsValid () {
		if (getKeylist().size() >= 26) {
			return true;
		}
		return false;
	}// end appearsValid ()

	/**
	 * gets the internal keylist
	 * @return Vector the known keymap
	 */
	private Vector <KeyElement> getKeylist () {
		return keylist;
	}// end getKeylist ()

	/**
	 * matches the largest chunk out of the given string to a keyelement
	 * in the keymap
	 * @param String sentence/phrase to match
	 * @return String the key that matches the most
	 */
	public KeyElement matchLargestChunk (String sentence) {
		KeyElement temp;
		KeyElement match = null;
		int i;
		for (i = 0; i < getKeylist().size(); i++) {
			temp = (KeyElement)(getKeylist().elementAt(i));
			try {
			if (sentence.regionMatches(0, temp.getLetter(), 0, temp.getLetter().length())) {
				if (match == null) {
					match = temp;
				} else if (temp.getLetter().length() > match.getLetter().length()) {
					match = temp;
				}
			}
			} catch (Exception e) {
				System.out.println("Error with KeyElement " + i + " " + temp.getLetter());
			}
		}
		return match;
	}// end matchLargestChunk (String)

	/**
	 * Adds the KeyElement to the KeyMap
	 * @param KeyElement the desired key to add
	 * @return boolean the success or failure of the add
	 */
	public boolean addKey (KeyElement key) {
		return keylist.add(key);
	}// end addKey (KeyElement)

	/**
	 * Set up a hashmap of letter to key index.
	 */
        private void setupLetterMap () {
		int i;
		for (i = 0; i < getKeylist().size(); i++) {
			KeyElement key = getKeylist().elementAt(i);
			// try {
                            if ( ( key.getLetter() != null ) &&
                                 ( key.getLetter().length() > 0) ) {
                                letterToKeyIndex.put(key.getLetter(), i);
                            }
			// } catch (Exception e) {
			// 	System.out.println("Error in setupLetterMap " + i + " " + key.getLetter());
			// }
		}
	}// end setupLetterMap ()

	/**
	 * returns the index in the keymap of a given letter
	 * @param letter the keyboard letter of the given key
	 * @return the index of the letter in the keymap
	 */
        public KeyElement getKeyByLetter (String letter) {
                Integer keyIndex = letterToKeyIndex.get( letter );
                if ( keyIndex == null ) {
			return null;
		}
		return getKeylist().elementAt( keyIndex );
	}// end getKeyByLetter (String)

	/**
	 * sets the new keymap
	 * @param String the source file
	 */
	public void readKeyMap (String source) {
		FileReader frFile = null;
		InputStream iStream = null;
		InputStreamReader isReader = null;
		BufferedReader brBuffer = null;
		boolean finished = false;
		String temp;
		int test;

		if (bDEBUG) System.out.println("KeyMap: reading file " + source);
		// gotta open it
		try {
			frFile = new FileReader(source);
			brBuffer = new BufferedReader(frFile);
		} catch (FileNotFoundException e) {
			iStream = this.getClass().getResourceAsStream(source);
			if (iStream != null) {
				isReader = new InputStreamReader(iStream);
				brBuffer = new BufferedReader(isReader);
			} else {
				System.out.println("Could not load keymap. (" + source + " not found)");
				System.exit(1);
			}
		} catch (Exception e) {
		}
		try {
			while (brBuffer.ready()) {
				temp = brBuffer.readLine();
				temp = remove_comment(temp, "#");
				temp = remove_comment(temp, ";");
				temp = remove_comment(temp, "!");
				temp = remove_comment(temp, "//");
				/* Comments removed */
				test = temp.indexOf(',');
				if (test == -1) {
					if (bDEBUG) System.out.println("Discarding line (=)");
				}
				else {
					parseLine(temp);
				}
			}
		}
		catch (IOException e) {
			if (bDEBUG) System.out.println("KeyMap: IO Error while reading " + source);
		}
		catch (Exception e) {
			if (bDEBUG) System.out.println("KeyMap: General Error: " + source);
		}

		try {
			brBuffer.close();
			if (iStream == null) {
				frFile.close();
			} else {
				isReader.close();
				iStream.close();
			}
		} catch (Exception e) {
		}
                setupLetterMap();
		if (bDEBUG) System.out.println("KeyMap: file read");
	}// end setKeyMap (String)

	/**
	 * function to remove comments
	 * @param String the line to parse
	 * @param String the comment String
	 * @return String the string sans comment
	 */
	private String remove_comment (String toParse, String comment) {
		int test;
		if ((test = toParse.indexOf(comment)) != -1) {
			if (bDEBUG) System.out.println("Removing Comment (" + comment + ")");
			return toParse.substring(0,test);
		}
		return toParse;
	}// end remove_comment (String, String)

	/**
	 * function to quickly convert letters to their appropriate button
	 * @param char the character to look at
	 * @return int the value of this character
	 */
	private int finger_button (char toCompare) {
		switch (toCompare) {
			case 'L':	case 'l':
				return B_LEFT;
			case 'M':	case 'm':
				return B_MIDDLE;
			case 'R':	case 'r':
				return B_RIGHT;
			default:
				return -1;
		}
	}// end finger_button (char)

	/**
	 * parses a keymap line and attempts to make a button/letter
	 * association out of it.
	 * @param String the string to parse
	 */
	private void parseLine(String line) {
	    String token;
	    int pos1, pos2;
	    KeyElement newKey = new KeyElement();
	    
	    try {
		String column[] = line.split("\",\"", 2);
		if ( (column.length != 2) ||
			 (column[0].charAt(0) != '"')
			 (column[1].indexOf('"') == -1)
		     (column.length != 2) )
			{
				if (bDEBUG) {
					System.out.println("Not a valid entry");
				}
				return;
			}
		
		// line: "  NA RMOO","<Right Alt><Shift><UpArrow></Shift></Right Alt>"
		// chord:  NA RMOO
		String chord = column[0].substring(1, column[0].length());
		// keystrokes: <Right Alt><Shift><UpArrow></Shift></Right Alt>
		// Note: this ignores anything after the trailing double quote.
		String keystrokes = column[1].substring(0, column[1].lastIndexOf('"') - 1);
		if( chord.compareTo("Chord") == 0 ) {
		    return;
		}

		// modifiers:  NA
		String modifiers = chord.substring(0, 4);
		// keys:RMOO
		String keys = chord.substring(5, 9);

		if (modifiers.indexOf('N') > 0) {
		    newKey.setButton(THUMB_OFFSET + B_NUM, true);
		}
		if (modifiers.indexOf('A') > 0) {
		    newKey.setButton(THUMB_OFFSET + B_ALT, true);
		}
		if (modifiers.indexOf('C') > 0) {
		    newKey.setButton(THUMB_OFFSET + B_CTRL, true);
		}
		if (modifiers.indexOf('S') > 0) {
		    newKey.setButton(THUMB_OFFSET + B_SHIFT, true);
		}

		/* Finger Modifiers */
		for (int finger = 0; finger < 4; finger++) {
		    int ftemp = finger_button(keys.charAt(finger));
		    if (ftemp != -1) {
			newKey.setButton(finger * FINGER_OFFSET + ftemp, true);
		    }
		}

		/* And what the chord produces */
		String macro = ""; // keystrokes after converting tags to ASCII characters
		int begin = 0;
		while( ( begin < keystrokes.length() ) &&
		       (! keystrokes.startsWith("</", begin) )) {

		    if( keystrokes.startsWith("<Left Ctrl>", begin)) {
			newKey.setButton(THUMB_OFFSET + B_CTRL, true);
			begin += "<Left Ctrl>".length();
		    }
		    if( keystrokes.startsWith("<Shift>", begin)) {
			newKey.setButton(THUMB_OFFSET + B_CTRL, true);
			begin += "<Shift>".length();
		    }
		    // todo:
		    // note: these are not contained in current Twidor lessons.
		    // <Left Ctrl>
		    // <Left GUI>
		    // <CapsLock>
		    // <Right Alt>
		    int end = keystrokes.indexOf('>', begin);
		    if( ( keystrokes.charAt(begin) == '<' ) &&
			( (keystrokes.length() - begin) > 3 ) &&
			( end > begin ) ) {
			String keyName = keystrokes.substring(begin, end + 1);
			if( keyTags.containsKey( keyName ) ) {
			    macro += (char)(int)(keyTags.get(keyName));
			}
			begin = end + 1;
		    } else {
			macro += keystrokes.charAt(begin);
			begin += 1;
		    }
                }
		if( ( macro.length() == 1 ) &&
                    ( (int)macro.charAt(0) <= 177 ) ) {
                        newKey.setNumberAndLetter((int)macro.charAt(0), macro);
                } else {
		        newKey.setLetter(macro);
                }
                if (newKey.displayLetter() != null) {
                    if ( ! addKey(newKey)) {
						System.out.println("duplicate key:" + chord + "\t" + macro);
                    } else {
						System.out.println("add key:      " + chord + "\t" + macro);
					}
                }
	    }
	    catch (Exception e) {
		//	if (bDEBUG) System.out.println("KeyMap: " + e.toString());
	    }

	}// end parseLine (String)

	/**
	 * debugging main
	 * @param String [] the arguments from the command line
	 */
	public static void main (String[] argv) {
		KeyMap test = new KeyMap(DEFAULT_KEYMAP);
                System.out.println(test.getKeylist().size());
		for (int i = 0; i < test.getKeylist().size(); i++) {
			System.out.println(((KeyElement)test.getKeylist().elementAt(i)).toString());
		}

		System.out.println("getKey test");

		KeyElement check = test.getKey(new String("a"));
		if (check == null) {
			System.out.println("Go fix the getKey software");
			return;
		}
		System.out.println(check.toString());

		Vector <KeyStatus> temp = new Vector <KeyStatus> ();
		for (int i = 0; i < 16; i++) {
			if (check.getButton(i)) {
				temp.add(new KeyStatus(true));
			}
			else {
				temp.add(new KeyStatus(false));
			}
		}

		KeyElement doublecheck = test.getKey(temp);
		if (doublecheck == null) {
			System.out.println("Go fix the getKey software");
			return;
		}
		System.out.println(doublecheck.toString());
	}// end main

}// end class KeyMap
