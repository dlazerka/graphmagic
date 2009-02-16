package name.dlazerka.gc.ui;

import name.dlazerka.gc.GraphPanel;

import javax.swing.*;
import java.awt.event.*;

public class Main extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;

	public Main() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonCancel.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onCancel();
				}
			}
		);

// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					onCancel();
				}
			}
		);

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onCancel();
				}
			}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
		);
	}

	private void onCancel() {
// add your code here if necessary
		dispose();
	}

	public static void main(String[] args) {
		Model model = new Model();
		showUI(model);
	}

	private static void showUI(Model model) {
		Main dialog = new Main();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}

}
