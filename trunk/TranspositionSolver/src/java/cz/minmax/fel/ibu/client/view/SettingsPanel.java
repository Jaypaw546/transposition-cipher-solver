package cz.minmax.fel.ibu.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import cz.minmax.fel.ibu.client.library.MatrixOperator;
import cz.minmax.fel.ibu.client.model.StringMatrix;

/**
 * Panel with main settings of aplication.
 * 
 * @author Petr Horsky
 */
public class SettingsPanel extends VerticalPanel
{
  private MainView mainView;
  private int rows;
  private int cols;
  private ListBox matrixSizeLB;
  private TextBox columnOneTB;
  private TextBox columnTwoTB;
  private TextBox rowOneTB;
  private TextBox rowTwoTB;
  private Button rowsPermuteButton;
  private Button transposeClockButton;
  private Button transposeCounterClockButton;
  private Button columnPermuteButton;

  public SettingsPanel(MainView mainView, StringMatrix stringMatrix)
  {
    this.mainView = mainView;
    this.setStylePrimaryName("gwt-SettingsPanel");

    rows = stringMatrix.getRowSize() - 1;
    cols = stringMatrix.getColumnSize() - 1;

    matrixSizeLB = new ListBox(false);
    columnOneTB = new TextBox();
    columnTwoTB = new TextBox();
    rowOneTB = new TextBox();
    rowTwoTB = new TextBox();

    columnOneTB.setWidth("200px");
    columnTwoTB.setWidth("200px");
    rowOneTB.setWidth("200px");
    rowTwoTB.setWidth("200px");

    final HTML labelMatrixSizeLB = new HTML("<h3>Select table size (rows x cols):</h3>");
    final HTML labelColumnPermute = new HTML("<h3><br/>Permute columns:</h3>");
    final HTML labelColumnOneTB = new HTML("First column:");
    final HTML labelColumnTwoTB = new HTML("Second column:");
    final HTML labelRowsPermute = new HTML("<h3>Permute rows:</h3>");
    final HTML labelRowOneTB = new HTML("First row:");
    final HTML labelRowTwoTB = new HTML("Second row:");

    setMatrixSizeLB(stringMatrix);
    setButtons();

    this.add(labelMatrixSizeLB);
    this.add(matrixSizeLB);

    this.add(labelColumnPermute);

    this.add(labelColumnOneTB);
    this.add(columnOneTB);
    this.add(labelColumnTwoTB);
    this.add(columnTwoTB);
    this.add(columnPermuteButton);

    this.add(labelRowsPermute);

    this.add(labelRowOneTB);
    this.add(rowOneTB);
    this.add(labelRowTwoTB);
    this.add(rowTwoTB);
    this.add(rowsPermuteButton);
    this.add(transposeClockButton);
    this.add(transposeCounterClockButton);

  }

  private void setButtons()
  {
    rowsPermuteButton = new Button("Permute rows");
    columnPermuteButton = new Button("Permute columns");
    transposeClockButton = new Button("Transpose clock");
    transposeCounterClockButton = new Button("Transponse counterclock");

    rowsPermuteButton.setTitle("Permutes two rows. You have to define row numbers in textinputs above.");
    columnPermuteButton.setTitle("Permutes two cols. You have to define col numbers in textinputs above.");
    transposeClockButton.setTitle("Rotates the table in clockwise direction.");
    transposeCounterClockButton.setTitle("Rotates the table in a counterclockwise direction.");

    rowsPermuteButton.setWidth("200px");
    columnPermuteButton.setWidth("200px");
    transposeClockButton.setWidth("200px");
    transposeCounterClockButton.setWidth("200px");

    rowsPermuteButton.addClickListener(new ClickListener()
    {
      public void onClick(Widget w)
      {
        permuteRows();
      }
    });

    columnPermuteButton.addClickListener(new ClickListener()
    {
      public void onClick(Widget w)
      {
        permuteColumns();
      }
    });

    transposeClockButton.addClickListener(new ClickListener()
    {
      public void onClick(Widget w)
      {
        mainView.setStringMatrix(MatrixOperator.transposeClockwise(mainView.getStringMatrix()));
      }
    });

    transposeCounterClockButton.addClickListener(new ClickListener()
    {
      public void onClick(Widget w)
      {
        mainView.setStringMatrix(MatrixOperator.transposeCounterClockwise(mainView.getStringMatrix()));
      }
    });
  }

  private void setMatrixSizeLB(StringMatrix stringMatrix)
  {
    matrixSizeLB = new ListBox(false);
    matrixSizeLB.setTitle("Sets the possible table size. Sizes depend on input text length.");
    matrixSizeLB.setWidth("200px");
    int[] possibleRowCount = MatrixOperator.getPossibleRectangleSizes(stringMatrix);
    for (int i = 0; i < possibleRowCount.length; i++)
    {
      matrixSizeLB.addItem(possibleRowCount[i] + " x " + (possibleRowCount[possibleRowCount.length - i - 1]) + "", possibleRowCount[i] + "");
    }
    matrixSizeLB.setSelectedIndex(possibleRowCount.length / 2);

    matrixSizeLB.addChangeListener(new ChangeListener()
    {
      public void onChange(Widget sender)
      {
        changeMatrixSize();
      }
    });
  }

  private void changeMatrixSize()
  {
    rows = Integer.parseInt(matrixSizeLB.getItemText(matrixSizeLB.getSelectedIndex()).split(" x ")[0]);
    StringMatrix sm = new StringMatrix(mainView.getInputOutputPanel().getInputText(), rows);
    cols = sm.getColumnSize() - 1;
    mainView.setStringMatrix(sm);
  }

  private void permuteRows()
  {
    int rowOne = 0;
    int rowTwo = 0;
    try
    {
      rowOne = Integer.parseInt(rowOneTB.getText());
      rowTwo = Integer.parseInt(rowTwoTB.getText());
    } catch (NumberFormatException numberFormatException)
    {
      DialogMessages.alert("Please enter row as number!");
      return;
    }
    if (((rowOne > 0) && (rowOne <= rows)) && ((rowTwo > 0) && (rowTwo <= rows)))
    {
      mainView.setStringMatrix(MatrixOperator.permuteTwoRows(mainView.getStringMatrix(), rowOne - 1, rowTwo - 1));
    }
    else
    {
      DialogMessages.alert("Row out of range!");
    }
  }

  private void permuteColumns()
  {
    int colOne = 0;
    int colTwo = 0;
    try
    {
      colOne = Integer.parseInt(columnOneTB.getText());
      colTwo = Integer.parseInt(columnTwoTB.getText());
    } catch (NumberFormatException numberFormatException)
    {
      DialogMessages.alert("Please enter column as number!");
      return;
    }
    if (((colOne > 0) && (colOne <= cols)) && ((colTwo > 0) && (colTwo <= cols)))
    {
      mainView.setStringMatrix(MatrixOperator.permuteTwoColumns(mainView.getStringMatrix(), colOne - 1, colTwo - 1));
    }
    else
    {
      DialogMessages.alert("Column out of range!");
    }
  }

  public void setNewMatrix(StringMatrix stringMatrix)
  {
    matrixSizeLB.clear();
    int[] possibleRowCount = MatrixOperator.getPossibleRectangleSizes(stringMatrix);
    for (int i = 0; i < possibleRowCount.length; i++)
    {
      matrixSizeLB.addItem(possibleRowCount[i] + " x " + (possibleRowCount[possibleRowCount.length - i - 1]) + "", possibleRowCount[i] + "");
    }
    matrixSizeLB.setSelectedIndex(possibleRowCount.length / 2);
  }
}
