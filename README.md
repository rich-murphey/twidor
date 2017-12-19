[//]: # (Copyright C 2017  Carey Richard Murphey. This file is released under the terms of the GNU General Public License version 2.)
<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/twiddler.jpg"
width="25%" align="right">

## Twidor - a typing tutor for the Twiddler keyboard
Twidor is a typing tutor for the [Twiddler chording keyboard](https://twiddler.tekgear.com/).   A chord is formed by pressing several keys together, like playing a "chord" on a piano. There are [downloads for Windows, Linux and OSX](https://github.com/rich-murphey/twidor/releases).  

Twidor was originally written by researchers studying
[chording keyboard learning and performance](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.4.3606&rep=rep1&type=pdf). Their
research suggests that learners may achieve faster typing speeds on single-handed chording keyboards than two-handed QWERTY keyboards given equal amount of practice.  In article [2] below, Lyons states, "faster typists would reach
60 WPM, the rate of our expert, after 10,000 phrases
(approximately 80 sessions or 27 hours) while the slower typists
could achieve 45 WPM."

## The Visual Key Map

<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/Twidor_SCC.png"
width="40%" align="right">Twidor shows a visual keymap and lesson text. Text entered by the user is shown below, in grey or red for correct or incorrect text, respectively.

The next letter of the lesson text and the buttons that send that letter are highlighted in green.

By default, Twidor loads the the
[Backspice keymap](https://raw.githubusercontent.com/AlexBravo/Twiddler/master/Backspice2%20cheat%20sheet.txt).
Users may select the Default, TabSpace, or Typemax layouts in the <kbd>Keymaps</kbd> menu, or may load a custom keymap generated by the 
the [Twiddler Tuner](https://twiddler.tekgear.com/tuner/edit.xhtml).

The 4x3 array of keys is composed of horizontal rows, for index finger on top, then middle, ring and pinky finger. Green, blue and red columns correspond to left, middle and right keys on the Twiddler.  Each large box shows the letter sent by a single key press.

The keymap shows the letters sent by each key.  Some letters require only a single key press, shown by the large boxes. Other letters reqire several keys pressed simultaneously,

## 2-Key Chords

<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/Twidor_screenshot.png"
width="33%" align="right">A "chord" is a keystroke using two or more keys pressed simultaneously.  The <kbd>View</kbd> menu has options to display chords.

Each 4x3 sub-array shows letters sent by two keys pressed simultaneously.  Button layout of the sub-arrray is the same: one row for each finger, and green/blue/red for left/middle/right columns.

The first key of the chord is the large box.  The location of the first key is relative to the larger 4x3 array.

The second key of the chord is a small box.  The position of the 2nd key is relative to the smaller 4x3 array.

<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/Twidor_chord_map.png"
width="25%" align="right"> Here are some two-key chords whose first key is <kbd>I</kbd>:
* <kbd>I + S</kbd> sends "is".
* <kbd>I + N</kbd> sends "in".
* <kbd>I + SP</kbd> sends "C".
* <kbd>I + H</kbd> sends "Y".

## Double and Longer letters

By default, Twidor limits training to chords that send a single letter.  This is controlled by the option <kbd>View | Single letters only</kbd>.

If you wish to learn both single and double letter chords, select only the option: <kbd>View | Double letters only</kbd>.

If you wish to learn all available chords, disable both <kbd>Single letters only</kbd> and <kbd>Double letters only</kbd>, in which case, Twidor will chooses the longest text that matches a chord.  Given the text, "the", Twidor will prompt for a macro that sends "the", rather than "th" or "t".

To display the expanded key map, select <kbd>View | Show chords</kbd>.

Hit <kbd>ENTER</kbd> at the end of a line to go to the next sentence. This also updates statistics for the session, including words per minute (WPM) and average error rate (AER).

Menu options include:
* <kbd>File | Load Keymap</kbd> imports a CSV keymap exported by the [Twiddler Tuner](https://twiddler.tekgear.com/tuner/).
* <kbd>File | Load Lesson</kbd> imports training text from a file.
* <kbd>Keyboard | Show..</kbd> toggles show/hide key labels and flips right-to-left orientation.
* <kbd>Hints | Highlight...</kbd> toggles show/hide which keys to press to send the next chord of the lesson.
* <kbd>Keymaps</kbd> loads certain keymaps: Backspice, Default_V5, TabSpace, or Typemax.

To load a custom keymap, users may:

* open https://twiddler.tekgear.com/tuner/edit.xhtml,
* import a keymap.cfg if not already present there.
* select 'Edit Configuration' for a given configuration file,
* with the configuration file open, select Export, and then CSV.
* download the CSV file.
* In Twidor, select <kbd>File | Load Keymap</kbd> and select the CSV file.

## How to Install

Download the releases from here:

[https://github.com/rich-murphey/twidor/releases](https://github.com/rich-murphey/twidor/releases)

On windows, download and run the Twidor-setup.exe installer.

On Linux and others, download Twidor.jar from the above link,
install Java, and invoke the application using javaw. For
example, on Debian or Ubuntu, one may use:

    sudo apt install openjdk-8-jre
    javaw -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -jar Twidor.jar Twidor

Feedback and reports of issues are
welcome [here](https://github.com/rich-murphey/twidor/issues).

## Limitations

* Labels for macro keys are shortened to the first three
  letters. This is not an issue for any of the popular keymaps, since they have no more than 2-letter macros in any 2-button chords.
* The visual keymap does not show the mouse buttons.

## How to Build

To compile and build Twidor.jar, one may use:

    sudo apt install git openjdk-8-jdk ant
    git clone https://github.com/rich-murphey/twidor.git
    cd twidor
    ant

## Other Relevant Projects

The [original Twidor source code and documentation](http://wearables.cc.gatech.edu/projects/twidor/) at Georgia Tech.

[Twidlit](https://github.com/pushkarkp/twidlit) is a a tutor and companion for The Twiddler keyboard. It
is intended to assist in all aspects of learning to twiddle and
developing a personal chord mapping. Like Twidor, Twidlit is
written in Java.

[twiddler_layout](https://github.com/ben-horner/twiddler_layout) is a command line tool for editing Twiddler keyboard layouts, also written in Java.

The [Backspice keymap](https://github.com/AlexBravo/Twiddler) by [Alex Bravo](https://plus.google.com/+AlexBravo).

The [TabSpace 3 layout](http://ivanwfr.github.io/Twiddler3-Layout/) discusses a range of layout issues, including ngram occurrence frequency and ranking the dexterity required for various multi-key chords.

The [Typemax layout](https://github.com/lancegatlin/typemax)
maximizes typing speed by emphasizing efficent single character
chord transitions (stride). The Typemax github repo has discussions of
[Basic Layout Design](https://github.com/lancegatlin/typemax/blob/master/basic_layout_design.md),
including issues of stride and ngram clustering, stutter,
separation of vowels and consonants on separate fingers, using
mouse buttons as letter keys, and many other desing issues.

The [Twiddler 3 Google Group](https://groups.google.com/forum/?hl=en#!forum/twiddler-3) has links to various resources.

## References

[1] Expert Chording Text Entry on the Twiddler One-Handed Keyboard,
Kent Lyons, Thad Starner, Daniel Plaisted,
James Fusia, Amanda Lyons, Aaron Drew, E. W. Looney, 
2012 16th International Symposium on Wearable Computers (2004)
Arlington, Virginia, Oct. 31, 2004 to Nov. 3, 2004.
[full text.](https://www.researchgate.net/publication/27521297_Twiddler_Typing_One-Handed_Chording_Text_Entry_for_Mobile_Phones)

[2] Twiddler Typing: One-Handed Chording Text Entry for Mobile
Phones, Kent Lyons, Thad Starner, Daniel Plaisted, James Fusia,
Amanda Lyons, Aaron Drew, E. W. Looney. Proceeding CHI '04
Proceedings of the SIGCHI Conference on Human Factors in
Computing Systems, Pages
671-678. [full text](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.4.3606&rep=rep1&type=pdf)

[3] Experimental Evaluations of the Twiddler One-Handed Chording
Mobile Keyboard. Kent Lyons, Brian Gane, and Thad
Starner. Journal Human-Computer Interaction archive Volume 21
Issue 4, November 2006 Pages
343-392. [full text](https://pdfs.semanticscholar.org/70c3/995e2487921b8a08bec07977f8cc161655fa.pdf)

[4] The Impacts of Limited Visual Feedback on Mobile Text Entry for
the Twiddler and Mini-QWERTY Keyboards. James Clawson, Kent
Lyons, Thad Starner, and Edward Clarkson. Proceedings of
ISWC 2005. Osaka, Japan. November 2005.
[full text.](https://pdfs.semanticscholar.org/70c3/995e2487921b8a08bec07977f8cc161655fa.pdf)

[5] Mobile Text Entry, Kent Loyons, [Lecture slides.](https://wiki.cc.gatech.edu/ccg/_media/classes/7470/7470-f06/mobile-text-entry.pdf?id=classes%3A7470%3A7470-f06%3Apowerpoint_slides&cache=cache)

## Acknowledgements

[James Gibson Fusia](http://wearables.cc.gatech.edu/people/james/)
is the original author of Twidor.

[Evan Dower](https://github.com/evantd) made
the source code available on github.

[Rich Murphey](https://github.com/rich-murphey) modified Twidor
to load [Twiddler v3 keyboard layouts](https://twiddler.tekgear.com/tuner/edit.xhtml),
released a simplified windows installer, added build files and
documentation, rewrote keypad display, rewrote layout to support
macros and alternate keymaps, and added options such as thumbpad
orientation and thumbpad labels visibility.
