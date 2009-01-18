package cz.minmax.fel.ibu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import cz.minmax.fel.ibu.client.model.StringMatrix;

/**
 * Main panel view with layout definition and any
 * other components inclusion.
 * 
 * @author Petr Horsky
 */
public class MainView extends HorizontalPanel
{
  private MatrixGrid matrixGrid;
  private InputOutputPanel inputOutputPanel;
  private SettingsPanel settingsPanel;
  private StringMatrix stringMatrix;

  public MainView(StringMatrix stringMatrix)
  {
    this.stringMatrix = stringMatrix;
    this.matrixGrid = new MatrixGrid(this, stringMatrix);
    this.inputOutputPanel = new InputOutputPanel(this);
    this.settingsPanel = new SettingsPanel(this, stringMatrix);

    this.setSize("100%", "100%");
    this.setStyleName("gwt-MainView");

    VerticalSplitPanel vSplit = new VerticalSplitPanel();

    HorizontalSplitPanel hSplit = new HorizontalSplitPanel();
    hSplit.setSize("100%", "100%");
    hSplit.setSplitPosition("72%");

    // Add content to hSplit
    hSplit.setRightWidget(settingsPanel);
    hSplit.setLeftWidget(vSplit);

    vSplit.setSize("100%", "100%");
    vSplit.setSplitPosition("63%");


    // Add content to vSplit
    vSplit.setTopWidget(matrixGrid);
    vSplit.setBottomWidget(inputOutputPanel);

    this.add(hSplit);
    enableKeyShorcuts();
  }

  private void enableKeyShorcuts()
  {
    this.sinkEvents(Event.ONKEYDOWN);
    EventPreview keyBoardShorcuts = new EventPreview()
    {
      public boolean onEventPreview(Event event)
      {
        int type = DOM.eventGetType(event);
        int keyCode = DOM.eventGetKeyCode(event);
        if (type == Event.ONKEYUP)
        {
          switch (keyCode)
          {
            case 112:
              SplashScreen splashScreen = new SplashScreen();
              return false;
            default:
              return true;
          }
        }
        else if (keyCode == 112)
        {
          return false;
        }
        else
        {
          return true;
        }
      }
    };
    DOM.addEventPreview(keyBoardShorcuts);
  }

  public StringMatrix getStringMatrix()
  {
    return stringMatrix;
  }

  public void setStringMatrixToAll(StringMatrix stringMatrix)
  {
    this.stringMatrix = stringMatrix;
    this.getMatrixGrid().setNewMatrix(stringMatrix);
    this.getSettingsPanel().setNewMatrix(stringMatrix);
  }

  public void setStringMatrix(StringMatrix stringMatrix)
  {
    this.stringMatrix = stringMatrix;
    this.matrixGrid.setNewMatrix(stringMatrix);
  }

  public InputOutputPanel getInputOutputPanel()
  {
    return inputOutputPanel;
  }

  public MatrixGrid getMatrixGrid()
  {
    return matrixGrid;
  }

  public SettingsPanel getSettingsPanel()
  {
    return settingsPanel;
  }
}
