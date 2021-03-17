import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel 
{
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck; 
	private JTextField taxField;
	private JLabel taxLabel;
	
	public FormPanel(){
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name : ");
		occupationLabel = new JLabel("Occupation : ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		
		//Set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
			
		});
		
		//Set up list box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0,"Under 18"));
		ageModel.addElement(new AgeCategory(1,"18 - 65"));
		ageModel.addElement(new AgeCategory(2,"65 or over"));
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 68));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1); // middle default
		
		//Set up combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0); //default employed
		//empCombo.setEditable(true); //ability to edit combobox fields
		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String name = nameField.getText();
			String occupation = occupationField.getText();
			AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
			String empCat = (String) empCombo.getSelectedItem();
			String taxId = taxField.getText();
			boolean usCitizen = citizenCheck.isSelected();
			
			
			FormEvent ev = new FormEvent(this, name, occupation, ageCat.toString(), empCat, taxId, usCitizen);
			
			if(formListener != null){
				formListener.formEventOccurred(ev);
			}
		}
	});
			
 
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		
	}
	
	public void layoutComponents(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///first row ///////
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // space between Name : and textlabel
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(nameField, gc);
		////second row///
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///third row///
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Age :"), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);
		
		//fourth row///
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Employment: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo, gc);
		
		//fifth row//
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("US Citizen: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);
		
		//sixth row//
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gc);
		
		
		////next row////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
	}
	
	public void setFormListener(FormListener listener){
		this.formListener = listener;
	}
}

class AgeCategory {
	private int id;
	private String text;
	public AgeCategory(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	public String toString(){
		return text;
	}
	
	public int getId(){
		return id;
	}
}
