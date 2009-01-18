package cz.minmax.fel.ibu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Grid;
import cz.minmax.fel.ibu.client.model.StringMatrix;

/**
 * Wrapper class of GWT Grid.
 * Allows loading data from StringMatrix 
 * 
 * @author Petr Horsky
 */
public class MatrixGrid extends Grid
{
  private MainView mainView;

  /**
   * Basic constructor for grid
   * @param view Link to MainView panel
   */
  public MatrixGrid(MainView view)
  {
    this.mainView = view;
    this.setStylePrimaryName("gwt-MatrixGrid");
  }

  /**
   * Basic constructor for grid
   * @param view Link to MainView panel
   * @param matrix Input matrix which will be displayed in grid
   */
  public MatrixGrid(MainView view, StringMatrix matrix)
  {
    super(matrix.getRowSize() + 1, matrix.getColumnSize() + 1);
    this.mainView = view;
    this.setStylePrimaryName("gwt-MatrixGrid");
    this.setMatrix(matrix);
  }

  public void setNewMatrix(StringMatrix matrix)
  {
    if (matrix == null)
    {
      GWT.log("nullMatrix", null);
      return;
    }
    this.setTitle("Table interpretation of the input text. You can change parameters of the table in the left settings panel. Green highlighted are column and row indexes. Blue highlighted are vowel counts in rows and columns.");
    this.resize(matrix.getRowSize() + 1, matrix.getColumnSize() + 1);
    this.setMatrix(matrix);
  }

  private void setMatrix(StringMatrix matrix)
  {
    if (matrix == null)
    {
      //FIXME message
      GWT.log("nullMatrix", null);
      return;
    }
    this.setGridBackground();
    this.setColumnCaptions();
    this.setRowCaptions();
    this.setVolwelInfo();

    if ((mainView.getStringMatrix().getRowSize() == 0) || (mainView.getStringMatrix().getColumnSize() == 0))
    {
      this.setHTML(0, 0, "TABLE IS UNAVAILIBLE! <br/> You have to set an input text (panel in the bottom left corner) and click the button Create table.");
    }
    for (int row = 1; row < this.numRows; row++)
    {
      for (int col = 1; col < this.numColumns; col++)
      {
        this.setHTML(row, col, (String) matrix.getMatrix()[row - 1][col - 1]);
      }
    }
  }

  private void setColumnCaptions()
  {
    for (int col = 0; col < this.numColumns - 1; col++)
    {
      this.getCellFormatter().setStylePrimaryName(0, col, "gwt-Grid-captionHighlight");
      if (col == 0)
      {
        this.setHTML(0, col, "");
      }
      else
      {
        this.setHTML(0, col, col + "");
      }
    }
  }

  private void setRowCaptions()
  {
    for (int row = 0; row < this.numRows - 1; row++)
    {
      this.getCellFormatter().setStylePrimaryName(row, 0, "gwt-Grid-captionHighlight");
      if (row == 0)
      {
        this.setHTML(0, row, "");
      }
      else
      {
        this.setHTML(row, 0, row + "");
      }
    }
  }

  private void setVolwelInfo()
  {
    for (int row = 0; row < this.numRows; row++)
    {
      this.getCellFormatter().setStylePrimaryName(row, this.numColumns - 1, "gwt-Grid-vowelHighlight");
    }
    for (int col = 0; col < this.numColumns; col++)
    {
      this.getCellFormatter().setStylePrimaryName(this.numRows - 1, col, "gwt-Grid-vowelHighlight");
    }
    this.setHTML(0, this.numColumns - 1, "V");
    this.setHTML(this.numRows - 1, 0, "V");
  }

  private void setGridBackground()
  {
    for (int i = 0; i < this.numRows; i++)
    {
      for (int j = 0; j < this.numColumns; j++)
      {
        this.getCellFormatter().setStyleName(i, j, "gwt-Grid-background");
      }
    }
  }

  public void visibleVowelInfo(boolean bool)
  {
    this.getRowFormatter().setVisible(this.numRows - 1, bool);
    for (int row = 0; row < this.numRows; row++)
    {
      this.getCellFormatter().setVisible(this.numRows, this.numColumns - 1, bool);
    }
  }

  public void visibleColumnCaption(boolean bool)
  {
    for (int row = 0; row < this.numRows; row++)
    {
      this.getCellFormatter().setVisible(this.numRows, 0, bool);
    }
  }

  public void visibleRowCaption(boolean bool)
  {
    this.getRowFormatter().setVisible(0, bool);
  }
}
