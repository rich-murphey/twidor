## Twidor - a typing tutor for the Twiddler mobile keyboard
<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/Twidor_screenshot.png"
width="50%" align="right">
Twidor is a typing tutor for the [Twiddler one-handed keyboard](https://twiddler.tekgear.com/).

Twidor was originally written by researchers studying
[Twiddler learning rates and performance](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.4.3606&rep=rep1&type=pdf). Their
research suggests that learners may achive faster typing speeds on single-handed chording keyboards than two-handed QWERTY keyboards given equal amount of practice.  In article [2] below, Lyons states, "faster typists would reach
60 WPM, the rate of our expert, after 10,000 phrases
(approximately 80 sessions or 27 hours) while the slower typists
could achieve 45 WPM."

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

## Usage

Hit <kbd>ENTER</kbd> at the end of a line to go to the next sentence.

Labels within the boxes on the keyboard show the letters
sent by single-button keystrokes.

To the right of each button, are labels for two-button chords. Each two-button chord keymap is oriented
the same as the single-button boxes: one horizonal row for each finger, index to pinky
, and three vertical columns of red-blue-green labels corresponding to the red-blue-green button columns.  

<img src="https://raw.githubusercontent.com/rich-murphey/twidor/master/web.assets/Twidor_chord_map.png"
width="30%" align="right"> Here are all two-button chords that use the <kbd>I</kbd> key.

They include:
* <kbd>I + S</kbd> sends "is".
* <kbd>I + N</kbd> sends "in".
* <kbd>I + SP</kbd> sends "C".
* <kbd>I + H</kbd> sends "Y".

By default, Twidor loads the the
[Backspice keymap](https://raw.githubusercontent.com/AlexBravo/Twiddler/master/Backspice2%20cheat%20sheet.txt).
Users may select others in the <kbd>Keymaps</kbd> menu, or load  custom keymaps generated by the 
the [official Twiddler layout editor](https://twiddler.tekgear.com/tuner/edit.xhtml).

Menu options include:
* <kbd>File | Load Keymap</kbd> imports a CSV keymap exported by the Twiddler Tuner.
* <kbd>File | Load Lesson</kbd> imports a arbitrary ascii text file, displayed one line at a time.
* <kbd>Keyboard | Show..</kbd> to show/hide key labels or flip right-to-left orientation.
* <kbd>Hints | Highlight...</kbd> to show/hide buttons for the next letter of the lesson.
* <kbd>Keymaps</kbd> to load Backspice, Default_V5, TabSpace, or Typemax.

To load a custom keymap, users may:

* open https://twiddler.tekgear.com/tuner/edit.xhtml,
* load the kemmap.cfg if not alreay present there.
* select 'Edit Configuration' for a given configuration file,
* with the configuration file open, select Export, and then CSV.
* download the CSV file.
* In Twidor, select <kbd>File | Load Keymap</kbd> and select the CSV file.

## Limitations

* Labels for macro keys are shortened to the first three
  letters. This is currently not an issue because current
  keymaps have no more than 2-letter macros in 2-button chords.
* Up/down/right/left arrows key labels are not shown.  current lessons do not use them.
* For Typemax, while the keybaords does not show mouse buttons,
  mapped mouse buttons do work for training purposes.

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

The [Tabspace 3 layout](http://ivanwfr.github.io/Twiddler3-Layout/) discusses a range of layout issues, including ngram occurrence frequency and ranking the dexterity required for various multi-key chords.

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

[Evan Dower](https://github.com/evantd) collaborated and made
the source code available on github.

[Rich Murphey](https://github.com/rich-murphey) modified Twidor
to load [Twiddler v3 keyboard layouts](https://twiddler.tekgear.com/tuner/edit.xhtml),
released a simplified windows installer, added build files and
documentation, rewrote keypad display, rewrote layout to support
macros and alternate keymaps, and added options such as thumbpad
orientation and thumbpad labels visibility.
