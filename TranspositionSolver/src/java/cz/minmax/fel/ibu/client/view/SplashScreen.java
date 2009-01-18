package cz.minmax.fel.ibu.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Jan Suchan
 */
public class SplashScreen extends DialogBox
{
  public static final String TITLE = "Welcome to Transposition Solver";
  public static final String BODY =
          "This software helps to decipher given text. Main part of program is a table interpretation of the input text.<br>" +
          "You can change parameters of the table in the <b>right settings panel</b>. Green highlighted are column and row<br>" +
          "indexes. Blue highlighted are vowel counts in rows and columns.<br><br>" +
          "If you are not sure, what some function do, just move cursor over a button or other control element and see<br>" +
          "the tooltip.<br><br>" +
          "<b>Tip 1:</b> To start, paste your input text without spaces into text area and click the button \"Create table\".<br>" +
          "<b>Tip 2:</b> For better experience use Firefox 3 internet browser.<br>" +
          "<b>Tip 3:</b> Press F1 to show this help again.<br><br>" +
          "Check for a new version and support at <a href=\"http://www.minmax.cz/projekty/transposition-solver\">www.minmax.cz/projects/transposition-solver</a> (in czech).<br><br>" +
          "<center>Program developed by <b>Petr Horský &amp; <a href=\"http://www.minmax.cz\">Jan Suchan</a></b>.<br>" +
          "Version 1.0, powered by Google Web Toolkit.<br><br></center>";

  public SplashScreen()
  {
    this.setText(TITLE);
    this.setAnimationEnabled(true);

    // Create a table to layout the content
    VerticalPanel dialogContents = new VerticalPanel();
    dialogContents.setSpacing(4);
    this.setWidget(dialogContents);

    // Add some text to the top of the dialog
    dialogContents.add(new HTML(BODY));

    // Add a close button at the bottom of the dialog
    Button closeButton = new Button("Close",
            new ClickListener()
            {
              public void onClick(Widget sender)
              {
                hide();
              }
            });
    dialogContents.add(closeButton);
    //this.setWidth("60%");
    this.center();
    this.show();
  }
}
