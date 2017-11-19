package app.frontEnd.dialogs;

import app.frontEnd.customComponents.AvailabilityEditorTable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AvailabilityEditorDialog extends JDialog
{
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private AvailabilityEditorTable availabilityTable;
	private JRadioButton radioDeleting;
	private JRadioButton radioAdding;
	private JLabel labelTotal;
	private ButtonGroup radioGroupEditMode;
	
	public AvailabilityEditorDialog()
	{
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		setTitle("Availability Editor");
		
		buttonOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onOK();
			}
		});
		
		buttonCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onCancel();
			}
		});
		
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				onCancel();
			}
		});
		
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener()
										   {
											   public void actionPerformed(ActionEvent e)
											   {
												   onCancel();
											   }
										   }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ItemListener listener1 = new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				boolean inDeleteMode = true;
				if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals(radioAdding))
				{
					inDeleteMode = false;
				}
				availabilityTable.setInDeleteMode(inDeleteMode);
			}
		};
		radioDeleting.addItemListener(listener1);
		radioAdding.addItemListener(listener1);
		setupActionMap();
		contentPane.setFocusable(true);
		contentPane.requestFocus();
		availabilityTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				super.mouseReleased(e);
				labelTotal.setText(Integer.toString(availabilityTable.getTotalHours()));
			}
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				super.mouseReleased(e);
				labelTotal.setText(Integer.toString(availabilityTable.getTotalHours()));
			}
		});
		labelTotal.setText(Integer.toString(availabilityTable.getTotalHours()));
	}
	
	private void setupActionMap()
	{
		
		String modeChangeKey = "changeMode";
		Action modeChangeAction = new AbstractAction(modeChangeKey)
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (radioGroupEditMode.getSelection() == radioAdding.getModel())
				{
					radioGroupEditMode.setSelected(radioDeleting.getModel(), true);
				}
				else
				{
					radioGroupEditMode.setSelected(radioAdding.getModel(), true);
				}
			}
		};
		contentPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), modeChangeKey);
		contentPane.getActionMap().put(modeChangeKey, modeChangeAction);
	}
	
	private void onOK()
	{
		// add your code here
		dispose();
	}
	
	private void onCancel()
	{
		// add your code here if necessary
		dispose();
	}
}
