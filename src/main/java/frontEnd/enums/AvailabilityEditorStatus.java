package frontEnd.enums;

import java.awt.Color;

/**
 * Created by marty on November 2017
 */
public enum AvailabilityEditorStatus
{
	EMPTY(Color.WHITE),
	PENDING_ADD(Color.YELLOW),
	ADDED(Color.GREEN),
	PENDING_DELETE(Color.RED);
	
	private Color statusColor;
	
	AvailabilityEditorStatus(Color statusColor)
	{
		this.statusColor = statusColor;
	}
	
	@Override
	public String toString()
	{
		return name() + "[" + statusColor + "]";
	}
}
