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
 * InfoPanel.java, a piece of the screen that's there for displaying
 * messages to the user.
 *
 * Revisions:
 * 			0.1	15 August 2005
 * 			Created class InfoPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.1; 15 August 2005
 */
import java.awt.*;
import javax.swing.*;
import java.lang.String;
public class InfoPanel extends TwiddlerSubPanel implements TwidorConstants {

	private JLabel myTitle;

	public InfoPanel () {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		myTitle = new JLabel();
                myTitle.setFont(FONT_LABEL);
		add(myTitle);
		setVisible(true);
		if (bDEBUG) System.out.println("InfoPanel: Created");
	}

	public InfoPanel (String title) {
		this();
		setTitle(title);
	}

	/**
	 * gets the title that this panel is displaying
	 * @return String the title we're showing
	 */
	public String getTitle () {
		return myTitle.getText();
	}

	/**
	 * sets the title that this panel displays
	 * @param String the title to show
	 */
	public void setTitle (String title) {
		myTitle.setText(title);
	}

}
