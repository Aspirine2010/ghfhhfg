import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuDemo implements ActionListener {
    JLabel label;
    JMenuBar menuBar;
    JToolBar toolBar;
    JPopupMenu popupMenu;
   public DebugAction setAct;
    DebugAction clearAct;
    DebugAction resumeAct;
    MenuDemo(){
        JFrame frame = new JFrame("Complite menu demo");
        frame.setSize(360,200);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        label = new JLabel();
        menuBar = new JMenuBar();
        makeFileMenu();
        makeActions();
        makeToolBar();
        makeOptionMenu();
        makeHelpMenu();
        makeEditMenu();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.show(e.getComponent(),e.getX(),e.getY());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                popupMenu.show(e.getComponent(),e.getX(),e.getY());
            }
        });
        frame.add(label,SwingConstants.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
String comStr  = e.getActionCommand();
if(comStr.equals("exit"))System.exit(0);
label.setText(comStr+" selected");
    }
    class DebugAction extends AbstractAction{
        DebugAction(String name, Icon image , int mnem,int accel,String tTip){
            super(name,image);
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, new Integer(mnem));
            putValue(SHORT_DESCRIPTION,tTip);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String comStr = e.getActionCommand();
            label.setText(comStr+" selected");
            if(comStr.equals("Set Breakpoint")){
                clearAct.setEnabled(true);
                setAct.setEnabled(false);
            }
            else if(comStr.equals("Clear Breakpont")){
                clearAct.setEnabled(false);
                setAct.setEnabled(true);

            }
        }
    }
    void makeFileMenu(){
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        JMenuItem jmiOpen = new JMenuItem("Open",KeyEvent.VK_0);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiClose = new JMenuItem("Close",KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiSava =  new JMenuItem("Save",KeyEvent.VK_S);
        jmiSava.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit",KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSava);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        menuBar.add(jmFile);
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSava.addActionListener(this);
        jmiExit.addActionListener(this);
    }
    void makeOptionMenu(){
        JMenu jmOptions = new JMenu("Options");
        JMenu jmColors = new JMenu("Colors");
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);
        JMenu jmPriority = new JMenu("Priority");
        JRadioButtonMenuItem jmiHight = new JRadioButtonMenuItem("Hight",true);
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
        jmPriority.add(jmiHight);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jmiHight);
        buttonGroup.add(jmiLow);
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmSetBP = new JMenuItem(setAct);
        JMenuItem jmClearBP = new JMenuItem(clearAct);
        JMenuItem jmResume  = new JMenuItem(resumeAct);
        jmDebug.add(jmSetBP);
        jmDebug.add(jmClearBP);
        jmDebug.add(jmResume);
        jmOptions.add(jmDebug);
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);
        menuBar.add(jmOptions);
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHight.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
    }
    void makeHelpMenu(){
        JMenu jmHelp = new JMenu("Help");
        ImageIcon imageIcon = new ImageIcon("AboutIcon.gif");
        JMenuItem jmiAbout = new JMenuItem("About", imageIcon);
        jmiAbout.setToolTipText("Info about the MenuDemo program.");
        jmHelp.add(jmiAbout);
        menuBar.add(jmHelp);
        jmiAbout.addActionListener(this);
    }
    void makeActions(){
        ImageIcon setIcon = new ImageIcon("setBP.gif");
        ImageIcon clearIcon = new ImageIcon("clearBP.gif");
        ImageIcon resumeIcon = new ImageIcon("resume.gif");
        setAct = new DebugAction("Set Breakpoint",setIcon,KeyEvent.VK_S,KeyEvent.VK_B,"set a break point.");
        clearAct = new DebugAction("Clear Breakpoint",clearIcon,KeyEvent.VK_C,KeyEvent.VK_L,"Clear a break point.");
        resumeAct = new DebugAction("Resume", resumeIcon,KeyEvent.VK_R,KeyEvent.VK_R,"Resume exception after breakpoint.");
        clearAct.setEnabled(false);
    }
    void makeToolBar(){
        JButton jbtnSet = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);
        toolBar = new JToolBar("Breakpounts");
        toolBar.add(jbtnSet);
        toolBar.add(jbtnClear);
        toolBar.add(jbtnResume);
    }
    void makeEditMenu(){
        popupMenu = new JPopupMenu();
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");
        popupMenu.add(jmiCut);
        popupMenu.add(jmiCopy);
        popupMenu.add(jmiPaste);
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuDemo();
            }
        });
    }


}
