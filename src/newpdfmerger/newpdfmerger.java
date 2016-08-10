package newpdfmerger;
import javax.swing.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.awt.Font;

public class newpdfmerger {

	private static String filenames[];
	private static int no;
	
	public static void main(String[] args) {
		
		try {
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			
			JOptionPane.showMessageDialog(null, "ERROR: "+e1);
		}
		
		PDFMergerUtility ut = new PDFMergerUtility();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
			
		JFrame frame = new JFrame("PDF Merger by Niteen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(443, 190);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblPdfMerger = new JLabel("PDF Merger");
		lblPdfMerger.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPdfMerger.setBounds(27, 4, 101, 22);
		frame.getContentPane().add(lblPdfMerger);
		
		
		JLabel lblNewLabel = new JLabel("Step 1");
		lblNewLabel.setBounds(27, 37, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setEditable(false);
		textArea.setBounds(27, 84, 170, 22);
		frame.getContentPane().add(textArea);
		textArea.setVisible(false);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					for(String s:filenames){
						System.out.println(s);
						ut.addSource(s);
					}
					
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setDialogTitle("Select the directory in which you want to save the merged file: ");
					chooser.showOpenDialog(frame);
					File temp = chooser.getSelectedFile();
					
					String fileName = JOptionPane.showInputDialog(frame,
							"Enter a name to save the merged file as(without .pdf): ");
					while(fileName.isEmpty()==true){
						JOptionPane.showMessageDialog(frame, "No Name was entered.Try again");
						fileName = JOptionPane.showInputDialog(frame, "Enter a name to save the merged file as(without .pdf): ");
					}
					
					ut.setDestinationFileName(temp + "\\" + fileName + ".pdf");
					//System.out.println(temp + "\\" + fileName + ".pdf");
					ut.mergeDocuments();
					JOptionPane.showMessageDialog(null, "PDF's Successfully Merged");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: "+e);
				}
				
				
			}
		});
		btnMerge.setBounds(237, 101, 89, 23);
		btnMerge.setVisible(false);
		frame.getContentPane().add(btnMerge);
		
		JLabel lblStep = new JLabel("Step 2");
		lblStep.setBounds(237, 84, 46, 14);
		lblStep.setVisible(false);
		frame.getContentPane().add(lblStep);
		
		
		JButton btnEnterNumberOf = new JButton("Enter Number of PDF's to merge & then Select the pdf's");
		btnEnterNumberOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = JOptionPane.showInputDialog(frame, "Enter the number of pdf's to merge: ");
				
				try {
					while (temp.isEmpty() == true) {
						JOptionPane.showMessageDialog(frame, "No Integer was entered.Try again");
						temp = JOptionPane.showInputDialog(frame, "Enter the number of pdf's to merge: ");
					}
					no = Integer.parseInt(temp);
					filenames = new String[no];
					frame.setSize(443, 140 + 25 * no + 25);
					frame.setLocationRelativeTo(null);
					textArea.setBounds(27, 84, 170, 22 * no);
					textArea.setVisible(true);
					lblStep.setVisible(true);
					btnMerge.setVisible(true);
					frame.revalidate();
					for (int i = 1; i <= no; i++) {
						try {
							chooser.setDialogTitle("Select PDF number: " + i);
							chooser.showOpenDialog(frame);
							File selectedFile = chooser.getSelectedFile();
							textArea.append(selectedFile.getName() + "\n");

							filenames[i - 1] = selectedFile.getAbsolutePath();

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"Aye Aye! You forgot to select your file. Lets try that again!");
						}
					} 
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		btnEnterNumberOf.setBounds(27, 54, 377, 23);
		frame.getContentPane().add(btnEnterNumberOf);
		
		JLabel lblIfYouFind = new JLabel("If you find any bugs,mail me (niteenautade@gmail.com)");
		lblIfYouFind.setBounds(138, 7, 279, 16);
		frame.getContentPane().add(lblIfYouFind);
		
		frame.revalidate();
		frame.setVisible(true);
		
		
	}
}
