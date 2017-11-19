package app.frontEnd.enums;

import java.awt.Color;

/**
 * Created by marty on November 2017
 */
public enum AvailabilityEditorStatus
{
	EMPTY(Color.WHITE), PENDING_ADD(Color.YELLOW), ADDED(Color.GREEN), PENDING_DELETE(Color.RED);
	
	private Color statusColor;
	
	AvailabilityEditorStatus(Color statusColor)
	{
		this.statusColor = statusColor;
	}
	
	public static AvailabilityEditorStatus parse(Color color)
	{
		if (color.equals(PENDING_ADD))
		{
			return PENDING_ADD;
		}
		if (color.equals(ADDED))
		{
			return ADDED;
		}
		if (color.equals(PENDING_DELETE))
		{
			return PENDING_DELETE;
		}
		
		return EMPTY;
	}
	
	public Color getColor()
	{
		return statusColor;
	}
	
	public boolean equals(Color color)
	{
		if (this.statusColor.equals(color))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		return name() + "[" + statusColor + "]";
	}
}
