/*
 * GUI
 *
 */

package prea;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author j0ni
 *
 * @version %I%
 */
public class PREAUserInterface extends JFrame {


   
    private final JPanel mainpanel;
    private final JPanel txpanel;
    private final JPanel titlepanel;
    private JPanel spanel;
    private JPanel boxpanel;
    private final JTextArea terminal;
    //private final JTextField tx;
    private final JComboBox box;
    private static PropertyList p;
    private String file="data.txt";
    private SelectedProperty sp;
    private JLabel title;
    private final JMenuBar thismenu;
    private final JMenu fileMenu;
    private final JMenu helpMenu;
    private final JMenuItem fileOpen;
    private final JMenuItem fileSearch;
    private final JMenuItem filePrint;
    private final JMenuItem helpDoc;
    
    private JCheckBox checklist[];
    private JScrollBar VSelector;
    private final JLabel statuslbl;
    private final JMenu calcMenu;
    private final JMenu calcStat;
    private final JMenuItem saleMin;
    private final JMenuItem saleMax;
    private final JMenuItem saleAv;
    private final JMenuItem calcSold;
    private final JMenuItem calcCommission;
    private final JMenuItem calcToFile;
    private final JMenuItem perArea;
    private final JButton listbtn;
    private final JButton myListbtn;
    private final JButton toFilebtn;
    

/**
 * GUI Constructor
 */
    public PREAUserInterface(){
        super("PREA Application UI");
        setLayout(new BorderLayout());

        p=new PropertyList(file);
        sp=new SelectedProperty();

        mainpanel=new JPanel();
        mainpanel.setLayout(new FlowLayout());

        txpanel=new JPanel();
        txpanel.setLayout(new BoxLayout(txpanel,BoxLayout.Y_AXIS));
   
        spanel=new JPanel();
        spanel.setLayout(new BoxLayout(spanel,BoxLayout.Y_AXIS));

        titlepanel=new JPanel();
        titlepanel.setLayout(new FlowLayout());

        boxpanel=new JPanel();
        boxpanel.setLayout(new GridLayout(p.list.size(),1));
        statuslbl=new JLabel();
        boxpanel.add(statuslbl);

        //Create Menu and theirs Listeners
        thismenu=new JMenuBar();
        fileMenu=new JMenu("File");
        helpMenu=new JMenu("Help");
        calcMenu=new JMenu("PropertyCalculator");
        fileOpen=new JMenuItem("Open");
        fileSearch=new JMenuItem("Search");
        filePrint=new JMenuItem("Print");
        helpDoc=new JMenuItem("Documentation");
        calcStat=new JMenu("Statistics");
        saleMin=new JMenuItem("Minimum");
        saleMax=new JMenuItem("Maximum");
        saleAv=new JMenuItem("Average");
        calcSold=new JMenu("Sold");
        calcCommission=new JMenuItem("Commission");
        calcToFile=new JMenuItem("Save to File");
        perArea=new JMenuItem("per Area");

        thismenu.add(fileMenu);
        thismenu.add(helpMenu);
        thismenu.add(calcMenu);
        calcMenu.addMenuListener(new MenuListener(){

            public void menuSelected(MenuEvent e) {
             File pwfile=new File("passwd.txt");
                JLabel passwdlbl=new JLabel("enter password");
                JPasswordField passwd=new JPasswordField(15);
                Object[] passo={passwdlbl,passwd};
                //String user=null;
                String pass=null;
                char[] spellpass = null;
                Scanner sc;
                try {
                    sc = new Scanner(pwfile);
                    while(sc.hasNext()){
                        String wd=sc.next().trim();
                        spellpass=wd.toCharArray();
                        }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PREAUserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                JOptionPane.showConfirmDialog(null, passo, "Log In",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                        String mypassword=new String(passwd.getPassword());
                   char[] password=mypassword.toCharArray();
                         if( isPasswordCorrect(password,spellpass))
                    JOptionPane.showMessageDialog(null, "You re in","confirmation",JOptionPane.OK_OPTION);
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect Inputs","confirmation",JOptionPane.OK_OPTION);
                        calcMenu.setEnabled(false);
                    }
            }

            public void menuDeselected(MenuEvent e) {
                calcMenu.setArmed(false);
            }

            public void menuCanceled(MenuEvent e) {
                calcMenu.setEnabled(true);
            }
                

            });
        fileMenu.add(fileOpen);
        fileOpen.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               String anewfile=JOptionPane.showInputDialog(null,"type path to file","Open Property File",JOptionPane.PLAIN_MESSAGE);
               PropertyList thenewList=new PropertyList(anewfile);
               p=thenewList;
            }

        });
        fileMenu.add(fileSearch);
        fileMenu.add(filePrint);
        filePrint.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                try {
                   File itemfile=new File("myitems.txt");
                    PrintWriter filewriter = new PrintWriter(itemfile);
                    filewriter.println(sp.displayList());
                    filewriter.flush();
                    statuslbl.setText("List is saved in "+itemfile.toString());
                    validate();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PREAUserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        helpMenu.add(helpDoc);
        helpDoc.addActionListener(new ActionListener(){
            //the code of current ActionListener is a copy from http://johnbokma.com/mexit/2008/08/19/java-open-url-default-browser.html
            //just for the needs of design and the quick help.
            public void actionPerformed(ActionEvent e) {
                String link = "file:///home/j0ni/NetBeansProjects/PREA/dist/javadoc/index.html";
        if( !java.awt.Desktop.isDesktopSupported() ) {

            System.err.println( "Desktop is not supported (fatal)" );
            System.exit( 1 );
        }

        if ( link.length() == 0 ) {

            System.out.println( "Usage: OpenURI [URI [URI ... ]]" );
            System.exit( 0 );
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

            System.err.println( "Desktop doesn't support the browse action (fatal)" );
            System.exit( 1 );
        }

            try {

                java.net.URI uri = new java.net.URI( link );
                desktop.browse( uri );
            }
            catch ( Exception ex ) {

                System.err.println( ex.getMessage() );
            }

                //file:///home/j0ni/NetBeansProjects/PREA/dist/javadoc/index.html
            }

        });
        calcMenu.add(calcStat);
        calcMenu.addSeparator();
        calcMenu.add(calcSold);
        calcStat.add(saleMin);
        saleMin.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyCalculator pc=new PropertyCalculator(p);
                JOptionPane.showMessageDialog(null,pc.minimum(sp.wishList),"Minimum Price",JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        calcStat.add(saleMax);
        saleMax.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyCalculator pc=new PropertyCalculator(p);
                JOptionPane.showMessageDialog(null,pc.maximum(sp.wishList),"Maximum Price",JOptionPane.INFORMATION_MESSAGE);
            }

        });
        calcStat.add(saleAv);
        saleAv.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyCalculator pc=new PropertyCalculator(p);
                JOptionPane.showMessageDialog(null,pc.average(sp.wishList),"Average Price",JOptionPane.INFORMATION_MESSAGE);
            }

        });
        calcStat.add(perArea);
        perArea.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyList pl=new PropertyList();
                String[] locs=pl.getAreas();
                PropertyCalculator pc=new PropertyCalculator(p);
                String s=(String) JOptionPane.showInputDialog(null,"select area","Information Per Area",JOptionPane.PLAIN_MESSAGE,null,locs,null);
                terminal.setText(pc.statisticsPerArea(pl, s));
            }

        });

        calcSold.add(calcCommission);
        calcCommission.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyCalculator pc=new PropertyCalculator(p);
                JOptionPane.showMessageDialog(null,pc.commission(),"Commission Price",JOptionPane.INFORMATION_MESSAGE);
            }

        });
        calcMenu.addSeparator();
        calcMenu.add(calcToFile);
        calcToFile.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                PropertyCalculator pc=new PropertyCalculator(p);
                pc.saveStatistics(p.list);
            }

        });

        //Create Components(buttons,scrollbar,textarea,labels)
        //and incorporate on the panels
        listbtn=new JButton("Provide List");
        myListbtn=new JButton("To myList");
        toFilebtn = new JButton("save to file");
        terminal=new JTextArea(15,25);
        // VSelector = new JScrollBar();
        JScrollPane VScrollPane = new JScrollPane(terminal);
        VScrollPane.setViewportView(terminal);
        VScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        terminal.setBackground(Color.CYAN);
        terminal.setEditable(false);
  
        box=new JComboBox();

        title=new JLabel("PREA  \nAn application to seek Properties\n");
        title.setSize(10, 5);

        listbtn.setMinimumSize(toFilebtn.getSize());
        listbtn.setToolTipText("create and display the PropertyList");
        myListbtn.setSize(listbtn.getSize());
        myListbtn.setToolTipText("Add selections in your List");
        toFilebtn.setSize(listbtn.getSize());
        toFilebtn.setToolTipText("Copy your PropertyList in a file");
        spanel.add(listbtn);
        spanel.add(myListbtn);
        spanel.add(toFilebtn);

        setJMenuBar(thismenu);
        titlepanel.add(title);
        mainpanel.add(new JLabel("Selected Property Details"));
        mainpanel.add(VScrollPane);

//incorporate panels on frame
        getContentPane().add(titlepanel,BorderLayout.NORTH);
        getContentPane().add(txpanel,BorderLayout.WEST);
        getContentPane().add(spanel,BorderLayout.EAST);
        getContentPane().add(boxpanel,BorderLayout.CENTER);
        getContentPane().add(mainpanel,BorderLayout.SOUTH);

//ACTION OF MAKE LIST BUTTON
          listbtn.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e) {
                    //TODO avoid run more than once
                    makeList();
            }
        });

//ACTION OF ADD BUTTON
         myListbtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                   SelectedProperty sptmp=sp;
                        for(int i=0;i<checklist.length;i++){

                            if(checklist[i].isSelected()){
                                
                                Property prp=p.getProperty(checklist[i].getText());
                                if(!sptmp.wishList.contains(prp))
                                    sp.wishList.add(prp);
                                else JOptionPane.showMessageDialog(null,"Is already selected", prp.getID(),JOptionPane.INFORMATION_MESSAGE );
                                terminal.setText(sp.displayList());
                                 
                            }
                        }
                   sptmp=null;
                }

            });

//ACTION OF SAVE BUTTON
          toFilebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                   File itemfile=new File("myitems.txt");
                    PrintWriter filewriter = new PrintWriter(itemfile);
                    filewriter.println(sp.displayList());
                    filewriter.flush();
                    statuslbl.setText("List is saved in "+itemfile.toString());
                    validate();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PREAUserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, sp.cheaperis(),"your cheapiest selection is",JOptionPane.INFORMATION_MESSAGE);
            }

          })  ;


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        setVisible(true);
    }
    //end of constructor

    private void makeList(){
                checklist=new JCheckBox[p.list.size()];
            for(int i=0;i<p.list.size();i++){
                checklist[i]=new JCheckBox(p.list.get(i).propertyID);//+" "+p.list.get(i).propertyKind+" "+p.list.get(i).getLocation());
                checklist[i].addItemListener(new itemAction());
                boxpanel.add(checklist[i]);
        }            
                         validate();
    }

    public class itemAction implements ItemListener{

            public void itemStateChanged(ItemEvent e) {
                terminal.setText("  ");
                for(int i=0;i<checklist.length;i++){
                    if(checklist[i].isSelected()){
                       terminal.setText(checklist[i].getText()+"\n");
                    }
                }
            }
          }

    private static boolean isPasswordCorrect(char[] inputPassword,char[] mypass) {

		if (inputPassword.length != mypass.length)
			return false; // Return false if lengths are unequal
		for (int i = 0; i < inputPassword.length; i++)
			if (inputPassword[i] != mypass[i])
				return false;
		return true;
	}


}
