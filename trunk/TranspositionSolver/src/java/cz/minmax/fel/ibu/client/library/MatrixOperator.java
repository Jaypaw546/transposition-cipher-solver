package cz.minmax.fel.ibu.client.library;

import com.google.gwt.core.client.GWT;
import cz.minmax.fel.ibu.client.model.StringMatrix;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Suchan
 */
public class MatrixOperator
{
  /**
   * Returns array containing possible rectangle sizes (first numbers). The second
   * one is <code>length / first one</code>
   * 
   * @param length Length of cipher text
   * @return Array of first numbers in sizes
   * (<code><strong>1</strong></code> x 50, <code><strong>2</strong></code> x 25, ...)
   */
  public static int[] getPossibleRectangleSizes(int length)
  {
    List<Integer> widths = new ArrayList<Integer>();
    for (int i = 1; i <= length; i++)
    {
      if (length % i == 0)
      {
        widths.add(i);
      }
    }

    int[] widthsArray = new int[widths.size()];
    for (int j = 0; j < widths.size(); j++)
    {
      widthsArray[j] = widths.get(j);
    }
    return widthsArray;
  }

  public static int[] getPossibleRectangleSizes(StringMatrix matrix)
  {
    if (matrix.getText() != null)
    {
      return getPossibleRectangleSizes(matrix.getText().length());
    }
    return new int[0];
  }

  public static StringMatrix permuteTwoColumns(StringMatrix matrix, int first, int second)
  {
    if (matrix != null)
    {
      if ((matrix.getRowSize() != 0) && (matrix.getColumnSize() != 0))
      {
        if ((first >= 0) && (first < matrix.getColumnSize() - 1) && (second >= 0) &&
                (second < matrix.getColumnSize() - 1) && (first != second))
        {
          try
          {
            StringMatrix newMatrix = (StringMatrix) matrix.clone();
            for (int i = 0; i < newMatrix.getRowSize(); i++)
            {
              String temp = newMatrix.getMatrix()[i][first];
              newMatrix.getMatrix()[i][first] = newMatrix.getMatrix()[i][second];
              newMatrix.getMatrix()[i][second] = temp;
            }
            return newMatrix;
          } catch (Exception ex)
          {
            GWT.log("Clone", ex);
            return null;
          }
        }
      }

    }
    return null;
  }

  public static StringMatrix permuteTwoRows(StringMatrix matrix, int first, int second)
  {
    if (matrix != null)
    {
      if ((matrix.getRowSize() != 0) && (matrix.getColumnSize() != 0))
      {
        if ((first >= 0) && (first < matrix.getRowSize() - 1) && (second >= 0) &&
                (second < matrix.getRowSize() - 1) && (first != second))
        {
          try
          {
            StringMatrix newMatrix = (StringMatrix) matrix.clone();
            for (int i = 0; i < newMatrix.getColumnSize(); i++)
            {
              String temp = newMatrix.getMatrix()[first][i];
              newMatrix.getMatrix()[first][i] = newMatrix.getMatrix()[second][i];
              newMatrix.getMatrix()[second][i] = temp;
            }
            return newMatrix;
          } catch (Exception ex)
          {
            GWT.log("Clone", ex);
            return null;
          }
        }
      }
    }
    return null;
  }

  public static StringMatrix transposeClockwise(StringMatrix matrix)
  {
    if (matrix != null)
    {
      if ((matrix.getRowSize() != 0) && (matrix.getColumnSize() != 0))
      {
        try
        {
          StringMatrix newMatrix = (StringMatrix) matrix.clone();
          String[][] newArray =
                  new String[newMatrix.getColumnSize()][newMatrix.getRowSize()];
          for (int i = 0; i < newMatrix.getRowSize() - 1; i++)
          {
            for (int j = 0; j < newMatrix.getColumnSize() - 1; j++)
            {
              newArray[j][newMatrix.getRowSize() - 2 - i] = matrix.getMatrix()[i][j];
            }
            newArray[newMatrix.getColumnSize() - 1][newMatrix.getRowSize() - 2 - i] =
                    matrix.getMatrix()[i][matrix.getColumnSize() - 1];
          }
          for (int i = 0; i < newMatrix.getColumnSize() - 1; i++)
          {
            newArray[i][newMatrix.getRowSize() - 1] = matrix.getMatrix()[matrix.getRowSize() - 1][i];
          }
          newMatrix.setMatrix(newArray);
          return newMatrix;
        } catch (Exception ex)
        {
          return null;
        }
      }
    }
    return null;
  }

  public static StringMatrix transposeCounterClockwise(StringMatrix matrix)
  {
    if (matrix != null)
    {
      if ((matrix.getRowSize() != 0) && (matrix.getColumnSize() != 0))
      {
        try
        {
          StringMatrix newMatrix = (StringMatrix) matrix.clone();
          String[][] newArray =
                  new String[newMatrix.getColumnSize()][newMatrix.getRowSize()];
          for (int i = 0; i < newMatrix.getRowSize() - 1; i++)
          {
            for (int j = 0; j < newMatrix.getColumnSize() - 1; j++)
            {
              newArray[newMatrix.getColumnSize() - 2 - j][i] = matrix.getMatrix()[i][j];
            }
            newArray[newMatrix.getColumnSize() - 1][i] =
                    matrix.getMatrix()[i][matrix.getColumnSize() - 1];
          }
          for (int i = 0; i < newMatrix.getColumnSize() - 1; i++)
          {
            newArray[newMatrix.getColumnSize() - 2 - i][newMatrix.getRowSize() - 1] =
                    matrix.getMatrix()[matrix.getRowSize() - 1][i];
          }
          newMatrix.setMatrix(newArray);
          return newMatrix;
        } catch (Exception ex)
        {
          return null;
        }
      }
    }
    return null;
  }
}
