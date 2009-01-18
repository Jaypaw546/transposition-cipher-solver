package cz.minmax.fel.ibu.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Wrapper class for DialogBox used for application alerts.
 * 
 * @author Petr Horsky
 */
public class AlertBox extends DialogBox
{
  public AlertBox(String header, String text)
  {
    this.setText(header);
    this.setAnimationEnabled(true);

    // Create a table to layout the content
    VerticalPanel dialogContents = new VerticalPanel();
    dialogContents.setSpacing(4);
    this.setWidget(dialogContents);

    // Add some text to the top of the dialog
    dialogContents.add(new HTML(text));

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
    this.center();
    this.show();
  }
}
