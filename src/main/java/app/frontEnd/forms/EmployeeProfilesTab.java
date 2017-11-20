package app.frontEnd.forms;

import app.dao.DaoManager;
import app.frontEnd.dialogs.AvailabilityEditorDialog;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mpetuska on November 2017
 */
public class EmployeeProfilesTab
{
	private JButton newButton;
	private JButton deleteButton;
	private JList lsEmployees;
	private JButton editButton;
	private JList list2;
	private JButton addButton;
	private JButton editButton1;
	private JButton deleteButton1;
	private JButton openEditorButton;
	private JList list3;
	private JPanel mainPanel;
	private JLabel lbDailyAverage;
	private JLabel lbTotalAvailability;
	private JLabel lbEmployeeCount;
	
	private DefaultListModel<String> employeesModel = new DefaultListModel<>();
	
	public EmployeeProfilesTab()
	{
		openEditorButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AvailabilityEditorDialog aed = new AvailabilityEditorDialog();
				aed.pack();
				aed.setVisible(true);
			}
		});
		populateEmployees();
		lsEmployees.setModel(employeesModel);
	}
	
	private void populateEmployees()
	{
		if (employeesModel == null)
		{
			employeesModel = new DefaultListModel<>();
		}
		employeesModel.clear();
		for (String employee : DaoManager.getEmployeeDao().getEmployeeNames())
		{
			employeesModel.addElement(employee);
		}
	}
}
