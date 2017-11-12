package frontEnd.customComponents;

import frontEnd.enums.AvailabilityEditorStatus;
import javafx.util.Pair;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by marty on November 2017
 */
public class AvailabilityEditorTable extends JTable
{
	private static final String[] columnHeaderNamesShort = new String[] { "", "Mon", "Tue", "Wed",
			"Thu", "Fri", "Sat", "Sun" };
	private static final String[] columnHeaderNamesFull = new String[] { "", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	
	public static boolean useFullDayNames = true;
	public static boolean showTimeAsIntervals = true;
	
	private AvailabilityEditorStatus[][] cellStatuses;
	private boolean isInDeleteMode = false;
	
	public AvailabilityEditorTable()
	{
		this(30);
	}
	
	public AvailabilityEditorTable(int minutesPerRow)
	{
		super(new DefaultTableModel(generateSampleData(minutesPerRow),
				useFullDayNames ? columnHeaderNamesFull : columnHeaderNamesShort));
		getColumnModel().getColumn(0).setCellRenderer(new RowHeaderRenderer());
		
		setCellSelectionEnabled(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		cellStatuses = new AvailabilityEditorStatus[getRowCount()][getColumnCount()];
		for (int row = 0; row < getRowCount(); row++)
		{
			for (int col = 0; col < getColumnCount(); col++)
			{
				cellStatuses[row][col] = AvailabilityEditorStatus.EMPTY;
			}
		}
		addListeners();
	}
	
	private static Object[][] generateSampleData(int minutesPerRow)
	{
		int colCount = 7;
		int rowCount = 24 * (60 / minutesPerRow);
		Object[][] data = new Object[rowCount][colCount];
		
		int hours = 0;
		int minutes = 0;
		for (int row = 0; row < rowCount; row++)
		{
			String hr = hours < 10 ? "0" + hours : String.valueOf(hours);
			String min = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
			String value = hr + ":" + min;
			
			minutes += minutesPerRow;
			if (minutes >= 60)
			{
				hours++;
				minutes = minutes - 60;
			}
			
			if (showTimeAsIntervals)
			{
				String hr1 = hours < 10 ? "0" + hours : String.valueOf(hours);
				String min1 = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
				value += " - " + hr1 + ":" + min1;
			}
			
			data[row][0] = value;
		}
		
		for (int col = 1; col < colCount; col++)
		{
			for (int row = 0; row < rowCount; row++)
			{
				data[row][col] = null;
			}
		}
		
		return data;
	}
	
	private void addListeners()
	{
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				Pair<Integer, Integer> startCell = new Pair<>(
						getSelectionModel().getAnchorSelectionIndex(),
						getColumnModel().getSelectionModel().getAnchorSelectionIndex());
				Pair<Integer, Integer> endCell = new Pair<>(
						getSelectionModel().getLeadSelectionIndex(),
						getColumnModel().getSelectionModel().getLeadSelectionIndex());
				
				for (int row = Math.min(startCell.getKey(), endCell.getKey());
					 row <= Math.max(startCell.getKey(), endCell.getKey()); row++)
				{
					for (int col = Math.min(startCell.getValue(), endCell.getValue());
						 col <= Math.max(startCell.getValue(), endCell.getValue()); col++)
					{
						AvailabilityEditorStatus status = cellStatuses[row][col];
						if (!isInDeleteMode)
						{
							if (status == AvailabilityEditorStatus.EMPTY)
							{
								cellStatuses[row][col] = AvailabilityEditorStatus.PENDING_ADD;
							}
							if (status == AvailabilityEditorStatus.PENDING_DELETE)
							{
								cellStatuses[row][col] = AvailabilityEditorStatus.ADDED;
							}
						}
						else
						{
							if (status == AvailabilityEditorStatus.ADDED)
							{
								cellStatuses[row][col] = AvailabilityEditorStatus.PENDING_DELETE;
							}
							if (status == AvailabilityEditorStatus.PENDING_ADD)
							{
								cellStatuses[row][col] = AvailabilityEditorStatus.EMPTY;
							}
						}
						
					}
				}
				getSelectionModel().clearSelection();
			}
		});
	}
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{
		Component component = super.prepareRenderer(renderer, row, column);
		int rendererWidth = component.getPreferredSize().width;
		TableColumn tableColumn = getColumnModel().getColumn(column);
		tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width,
				tableColumn.getPreferredWidth()));
		
		if (!isCellSelected(row, column) && column > 0)
		{
			component.setBackground(cellStatuses[row][column].getColor());
		}
		return component;
	}
	
	public int getTotalHours()
	{
		int total = 0;
		for (int r = 0; r < cellStatuses.length; r++)
		{
			for (int c = 1; c < cellStatuses[r].length; c++)
			{
				if (cellStatuses[r][c] != AvailabilityEditorStatus.EMPTY
						&& cellStatuses[r][c] != AvailabilityEditorStatus.PENDING_DELETE)
				{
					total++;
				}
			}
		}
		return total;
	}
	
	public void setInDeleteMode(boolean inDeleteMode)
	{
		isInDeleteMode = inDeleteMode;
	}
	
	class RowHeaderRenderer extends DefaultTableCellRenderer
	{
		public RowHeaderRenderer()
		{
			setHorizontalAlignment(JLabel.CENTER);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column)
		{
			if (table != null)
			{
				JTableHeader header = table.getTableHeader();
				
				if (header != null)
				{
					setForeground(header.getForeground());
					setBackground(header.getBackground());
					setFont(header.getFont());
				}
			}
			
			if (isSelected)
			{
				//				setFont(getFont().deriveFont(Font.BOLD));
			}
			
			setValue(value);
			return this;
		}
	}
}
