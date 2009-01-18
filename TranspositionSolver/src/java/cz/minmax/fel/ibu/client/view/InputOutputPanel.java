package cz.minmax.fel.ibu.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import cz.minmax.fel.ibu.client.library.MatrixOperator;
import cz.minmax.fel.ibu.client.model.StringMatrix;

/**
 * Panel for definition and confirmation of text input.
 * 
 * @author Petr Horsky
 */
public class InputOutputPanel extends AbsolutePanel
{
  private MainView mainView;
  private TextArea inputText;
  private TextArea outputText;

  public InputOutputPanel(MainView mainView)
  {
    this.mainView = mainView;

    HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();

    inputText = new TextArea();
    inputText.setWidth("100%");
    inputText.setTitle("Paste your input text.");

    outputText = new TextArea();
    outputText.setWidth("100%");
    outputText.setReadOnly(true);
    outputText.setTitle("Here you can print table by rows or columns.");

    VerticalPanel inputPanel = new VerticalPanel();
    inputPanel.setStylePrimaryName("gwt-InputOutputPanel");
    inputPanel.add(new HTML("<h3>Input text:</h3>"));
    inputPanel.add(inputText);

    VerticalPanel outputPanel = new VerticalPanel();
    outputPanel.setStylePrimaryName("gwt-InputOutputPanel");
    outputPanel.add(new HTML("<h3>Output text:</h3>"));
    outputPanel.add(outputText);

    splitPanel.setLeftWidget(inputPanel);
    splitPanel.setRightWidget(outputPanel);
    this.add(splitPanel);

    addInputButtons(inputPanel);
    addOutputButtons(outputPanel);
  }

  private void addInputButtons(Panel inputPanel)
  {
    Button createTable = new Button("Create table");
    createTable.setTitle("Creates table from input text.");
    createTable.addClickListener(
            new ClickListener()
            {
              public void onClick(Widget sender)
              {
                sendTextAsMatrix(getInputText());
              }
            });

    Button deleteSpaces = new Button("Delete spaces");
    deleteSpaces.setTitle("Deletes all white spaces from input text area.");
    deleteSpaces.addClickListener(
            new ClickListener()
            {
              public void onClick(Widget sender)
              {
                inputText.setText(deleteSpaces(getInputText()));
              }
            });

    HorizontalPanel panel = new HorizontalPanel();
    panel.setSpacing(10);
    panel.add(createTable);
    panel.add(deleteSpaces);
    inputPanel.add(panel);
  }

  private void addOutputButtons(Panel outputPanel)
  {
//    Button printMatrix = new Button("Print matrix");
    Button printMatrixRows = new Button("Print by rows");
    printMatrixRows.setTitle("Prints table row by row, from top left.");
    Button printMatrixColumns = new Button("Print by columns");
    printMatrixColumns.setTitle("Prints table column by column from top left.");

    final ListBox listBox = new ListBox(false);
    listBox.addItem("Rows from left top", "0");
    listBox.addItem("Columns from left top", "1");

    HorizontalPanel panel = new HorizontalPanel();
    panel.setSpacing(10);
    panel.add(printMatrixRows);
    panel.add(printMatrixColumns);
//    panel.add(listBox);

//    printMatrix.addClickListener(
//            new ClickListener()
//            {
//              public void onClick(Widget sender)
//              {
//                outputText.setText(printMatrix(listBox.getValue(listBox.getSelectedIndex())));
//              }
//            });
    printMatrixRows.addClickListener(
            new ClickListener()
            {
              public void onClick(Widget sender)
              {
                outputText.setText(printMatrix("0"));
              }
            });
    
    printMatrixColumns.addClickListener(
            new ClickListener()
            {
              public void onClick(Widget sender)
              {
                outputText.setText(printMatrix("1"));
              }
            });

    outputPanel.add(panel);
  }

  private String printMatrix(String choice)
  {
    if (mainView.getStringMatrix() == null)
    {
      AlertBox alert = new AlertBox("Matrix is empty",
              "Create new matrix forst, please.");
      return "";
    }
    int intChoice = Integer.parseInt(choice);
    switch (intChoice)
    {
      case 1:
        return mainView.getStringMatrix().getTextColumnsFromLeftTop(true);
      default:
        return mainView.getStringMatrix().getTextRowsFromLeftTop(true);
    }
  }

  public String getInputText()
  {
    return inputText.getText();
  }

  private void sendTextAsMatrix(String text)
  {
    if ((text == null) || (text.isEmpty()))
    {
      AlertBox alert = new AlertBox("Text area is empty",
              "You didn't paste any text into input text area");
      return;
    }
    int[] sizes = MatrixOperator.getPossibleRectangleSizes(text.length());
    StringMatrix matrix = new StringMatrix(text, sizes[sizes.length / 2]);
    mainView.setStringMatrixToAll(matrix);
  }

  private String deleteSpaces(String text)
  {
    return text.replaceAll(" ", "").trim().replaceAll("\n", "");
  }
}
