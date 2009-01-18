package cz.minmax.fel.ibu.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class, which cares of matrix instances used for 'browsing' in action history
 * 
 * @author Jan Suchan
 */
public class HistoryCollector
{
  /** Constant for undo operation */
  public static final int UNDO = -1;
  /** Constant for redo operation */
  public static final int REDO = 1;
  private List<StringMatrix> instances;
  private int maxInstances;
  private int currentInstance;

  /**
   * Basic constructor.
   */
  public HistoryCollector()
  {
    this(0);
  }

  /**
   * Constructor with specification of maximum number
   * of matrix instances to be saved.
   * 
   * @param maxInstances Maximum matrix instances to be saved.
   */
  public HistoryCollector(int maxInstances)
  {
    this.maxInstances = maxInstances;
    instances = new ArrayList<StringMatrix>();
    currentInstance = -1;
  }

  /**
   * Add a new matrix instance to history.
   * 
   * @param matrix New matrix instance to be saved
   * @return <code>true</code>, if there was enough space to place new instance<br />
   * <code>false</code>, if first instance in history had to be deleted
   */
  public boolean addInstance(StringMatrix matrix)
  {
    if (currentInstance < instances.size())
    {
      for (int i = currentInstance + 1; i < instances.size(); i++)
      {
        instances.remove(i);
      }
    }
    instances.add(matrix);
    if ((maxInstances != 0) && (instances.size() >= maxInstances))
    {
      instances.remove(0);
      return false;
    }

    moveCurrentInstance(REDO);
    return true;
  }

  /**
   * Get matrix instance specified by instance number
   * 
   * @param number Number of instance in history
   * @return <code>Matrix</code> object
   */
  public StringMatrix getInstance(int number)
  {
    if (!isInstanceNumberValid(number))
    {
      return null;
    }
    return instances.get(number);
  }

  /**
   * Get current matrix instance in history.
   * 
   * @return Current matrix instance in history
   */
  public StringMatrix getCurrentInstance()
  {
    if (currentInstance != -1)
    {
      return instances.get(currentInstance);
    }
    return null;
  }

  /**
   * Used for UNDO and REDO operation.
   * 
   * @param direction Direction of moving (use HistoryCollector.UNDO and
   * HistoryCollector.REDO constants)
   * @return Current matrix instance in history (after operation)
   */
  public StringMatrix moveCurrentInstance(int direction)
  {
    if (isInstanceNumberValid(currentInstance + direction))
    {
      currentInstance += direction;
    }
    return getCurrentInstance();
  }

  private boolean isInstanceNumberValid(int number)
  {
    if (maxInstances != 0)
    {
      return ((number >= 0) && (number < maxInstances) && (number < instances.size()));
    }
    return ((number >= 0) && (number < instances.size()));
  }

  /**
   * Get maximum of matrix instances, which can be saved in history
   * 
   * @return Maximum of matrix instances, zero for infinity
   */
  public int getMaxInstances()
  {
    return maxInstances;
  }

  /**
   * Get current instance number.
   * 
   * @return Current instance number
   */
  public int getCurrentInstanceNumber()
  {
    return currentInstance;
  }
}
