package cz.minmax.fel.ibu.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for wrapping a matrix, which contains char groups organized to 2D matrix
 * 
 * @author Jan Suchan
 */
public class StringMatrix
{
  public static final List<String> VOWELS = new ArrayList<String>();
  private String[][] matrix;
  private String text;
  private int charGroupSize;

  public StringMatrix()
  {
    VOWELS.add("a");
    VOWELS.add("á");
    VOWELS.add("e");
    VOWELS.add("é");
    VOWELS.add("i");
    VOWELS.add("í");
    VOWELS.add("y");
    VOWELS.add("ý");
    VOWELS.add("o");
    VOWELS.add("ó");
    VOWELS.add("u");
    VOWELS.add("ú");
    VOWELS.add("ů");
    charGroupSize = 1;
  }

  /**
   * Constructor with specification of cipher text and number of matrix rows.
   * 
   * @param text Text of cipher
   * @param rows Number of matrix rows
   */
  public StringMatrix(String text, int rows)
  {
    this();
    createRowsMatrix(text, rows);
    this.text = text;
  }

  /**
   * Create new matrix, specified by number of rows.
   * 
   * @param text Text of cipher
   * @param rows Number of matrix rows
   */
  public void createRowsMatrix(String text, int rows)
  {
    int groupCount = text.length() / charGroupSize;
    int columns = groupCount / rows;
    if (groupCount % rows > 0)
    {
      columns++;
    }
    matrix = new String[rows + 1][columns + 1];
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < columns; j++)
      {
        int groupStart = (i * columns + j) * charGroupSize;
        int groupEnd = Math.min(groupStart + charGroupSize, text.length());
        matrix[i][j] = text.substring(groupStart, groupEnd);
      }
    }
    inicialize(text);
  }

  /**
   * Create new matrix, specified by number of columns.
   * 
   * @param text Text of cipher
   * @param cols Number of matrix columns
   */
  public void createColsMatrix(String text, int cols)
  {
    int groupCount = text.length() / charGroupSize;
    int rows = groupCount / cols;
    if (groupCount % cols > 0)
    {
      rows++;
    }

    createRowsMatrix(text, rows);
    inicialize(text);
  }

  private void inicialize(String text)
  {
    this.text = text;
    createRowVowels();
    createColumnVowels();
  }

  private void createRowVowels()
  {
    if (matrix != null)
    {
      for (int i = 0; i < matrix.length - 1; i++)
      {
        int vowels = 0;
        for (int j = 0; j < matrix[i].length - 1; j++)
        {
          if (VOWELS.contains(matrix[i][j].toLowerCase()))
          {
            vowels++;
          }
        }
        matrix[i][matrix[i].length - 1] = String.valueOf(vowels);
      }
    }
  }

  private void createColumnVowels()
  {
    if (matrix != null)
    {
      if (matrix[matrix.length - 1] != null)
      {
        for (int i = 0; i < matrix[matrix.length - 1].length - 1; i++)
        {
          Integer vowels = 0;
          for (int j = 0; j < matrix.length - 1; j++)
          {
            if (VOWELS.contains(matrix[j][i].toLowerCase()))
            {
              vowels++;
            }
          }
          matrix[matrix.length - 1][i] = String.valueOf(vowels);
        }
      }
    }
  }

  public String getText()
  {
    return text;
  }

  public String getTextRowsFromLeftTop(boolean eachFromLeft)
  {
    String s = "";
    if (eachFromLeft)
    {
      for (int i = 0; i < getRowSize() - 1; i++)
      {
        for (int j = 0; j < getColumnSize() - 1; j++)
        {
          s += getMatrix()[i][j];
        }
      }
    }
    else
    {
    //FIXME
    }
    return s;
  }

  public String getTextColumnsFromLeftTop(boolean eachFromTop)
  {
    String s = "";
    if (eachFromTop)
    {
      for (int i = 0; i < getColumnSize() - 1; i++)
      {
        for (int j = 0; j < getRowSize() - 1; j++)
        {
          s += getMatrix()[j][i];
        }
      }
    }
    return s;
  }

  public int getCharCount()
  {
    return text.length();
  }

  public int getCharGroupSize()
  {
    return charGroupSize;
  }

  public void setCharGroupSize(int charGroupSize)
  {
    this.charGroupSize = charGroupSize;
  }

  public String[][] getMatrix()
  {
    return matrix;
  }

  public int getRowSize()
  {
    if (matrix != null)
    {
      return matrix.length;
    }
    return 0;
  }

  public int getColumnSize()
  {
    if (getRowSize() != 0)
    {
      return matrix[0].length;
    }
    return 0;
  }

  public void setMatrix(String[][] matrix)
  {
    this.matrix = matrix;
  }

  @Override
  public String toString()
  {
    String s = "[StringMatrix\n";
    for (String[] row : matrix)
    {
      for (String letter : row)
      {
        s += letter + " ";
      }
      s += "\n";
    }
    return s;
  }

  public StringMatrix clone()
  {
    StringMatrix newMatrix = new StringMatrix();
    String[][] arrayMatrix = new String[this.getRowSize()][this.getColumnSize()];
    for (int i = 0; i < this.getRowSize(); i++)
    {
      for (int j = 0; j < this.getColumnSize(); j++)
      {
        arrayMatrix[i][j] = this.getMatrix()[i][j];
      }
    }
    newMatrix.setCharGroupSize(this.charGroupSize);
    newMatrix.text = this.text;
    newMatrix.matrix = arrayMatrix;
    return newMatrix;
  }
}
