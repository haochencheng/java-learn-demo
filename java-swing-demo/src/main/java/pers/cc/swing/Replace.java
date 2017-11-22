/**  
 * MDM主数据管理系统  
 * com.soa.mdm  
 * Replace.java  
 * 2017年3月29日-下午1:06:53  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import pers.cc.swing.controller.ReplaceFileNameController;
import pers.cc.swing.controller.ReplaceTextController;
import pers.cc.swing.util.StringUtil;

/**
 * 简单描述
 * 
 * @author 郝晨成
 * @createTime 2017年3月29日-下午1:06:53
 * @version 1.0.0
 */
public class Replace {

	private JFrame frmAa;
	private JTextField replaceTextPath;
	private JTextField replaceTextExcel;
	private JTextField replaceFileNamePath;
	private JTextField replaceFileNameParam;

	/**
	 * replaceTextController:批量替换
	 */
	private ReplaceTextController replaceTextController = new ReplaceTextController();

	/**
	 * replaceFileNameController:重命名
	 */
	private ReplaceFileNameController replaceFileNameController = new ReplaceFileNameController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Replace window = new Replace();
					window.frmAa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Replace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAa = new JFrame();
		frmAa.setTitle("VIP定制版(ZH)");
		frmAa.setBounds(100, 100, 607, 415);
		frmAa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAa.getContentPane().setLayout(null);

		replaceTextPath = new JTextField();
		replaceTextPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				// 获取桌面路径
				FileSystemView fsv = FileSystemView.getFileSystemView();
				chooser.setDialogTitle("请选择要文件夹路径");
				chooser.setApproveButtonText("确定");
				chooser.setCurrentDirectory(fsv.getHomeDirectory());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
					replaceTextPath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		replaceTextPath.setBounds(226, 22, 262, 21);
		frmAa.getContentPane().add(replaceTextPath);
		replaceTextPath.setColumns(10);

		JLabel label = new JLabel("操作路径");
		label.setBounds(86, 25, 54, 15);
		frmAa.getContentPane().add(label);

		JLabel lblExcel = new JLabel("excel");
		lblExcel.setBounds(86, 74, 54, 15);
		frmAa.getContentPane().add(lblExcel);

		replaceTextExcel = new JTextField();
		replaceTextExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 在当前目录下，创建文件选择器
				// JFileChooser fChooser = new JFileChooser(".");
				JFileChooser fChooser = new JFileChooser();
				// 设置桌面路径
				FileSystemView fsv = FileSystemView.getFileSystemView();
				fChooser.setCurrentDirectory(fsv.getHomeDirectory());
				// excel过滤器
				ExcelFileFilter excelFilter = new ExcelFileFilter();
				fChooser.setFileFilter(excelFilter);
				fChooser.addChoosableFileFilter(excelFilter);
				// 设置文件选择框的标题
				fChooser.setDialogTitle("请选择xls文件");
				// 打开文件选择器
				int returnVal = fChooser.showOpenDialog(null);
				// 如果是选择了文件
				if (JFileChooser.APPROVE_OPTION == returnVal) {
					// 输初路径到textField中
					replaceTextExcel.setText(fChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		replaceTextExcel.setColumns(10);
		replaceTextExcel.setBounds(226, 71, 262, 21);
		frmAa.getContentPane().add(replaceTextExcel);
		// 替换内容按钮
		JButton replaceTextConfirm = new JButton("替换内容");
		replaceTextConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(replaceTextPath.getText())) {
					JOptionPane.showMessageDialog(null, "请选择替换的文件路径！", "警告", JOptionPane.ERROR_MESSAGE);
				} else if (StringUtil.isEmpty(replaceTextExcel.getText())) {
					JOptionPane.showMessageDialog(null, "请选择替换的excel配置文件！", "警告", JOptionPane.ERROR_MESSAGE);
				} else {
					// 批量替换文本内容
					int i = JOptionPane.showConfirmDialog(null, "您确认替换吗？", "系统提示", JOptionPane.YES_NO_OPTION);
					// 如果选择是
					if (i == 0) {
						boolean flag = false;
						try {
							flag = replaceTextController.replace(replaceTextPath.getText(), replaceTextExcel.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "替换失败！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						if (!flag) {
							JOptionPane.showMessageDialog(null, "替换失败！", "错误", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "替换成功！");
						}
					}
				}
			}
		});
		replaceTextConfirm.setBounds(226, 136, 93, 23);
		frmAa.getContentPane().add(replaceTextConfirm);
		JButton replaceFileNameConfirm = new JButton("替换文件名");
		replaceFileNameConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(replaceFileNamePath.getText())) {
					JOptionPane.showMessageDialog(null, "请选择替换的文件路径！", "警告", JOptionPane.ERROR_MESSAGE);
				} else if (StringUtil.isEmpty(replaceFileNameParam.getText())) {
					JOptionPane.showMessageDialog(null, "请选择替换的参数！", "警告", JOptionPane.ERROR_MESSAGE);
				} else {
					// 批量替换文本内容
					int i = JOptionPane.showConfirmDialog(null, "您确认替换吗？", "系统提示", JOptionPane.YES_NO_OPTION);
					// 如果选择是
					if (i == 0) {
						boolean flag = replaceFileNameController.replaceFileName(replaceFileNamePath.getText(),
								replaceFileNameParam.getText());
						if (!flag) {
							JOptionPane.showMessageDialog(null, "替换失败！", "错误", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "替换成功！");
						}
					}
				}

			}
		});
		replaceFileNameConfirm.setBounds(226, 302, 93, 23);
		frmAa.getContentPane().add(replaceFileNameConfirm);

		JLabel label_1 = new JLabel("操作路径");
		label_1.setBounds(86, 202, 54, 15);
		frmAa.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("操作参数");
		label_2.setBounds(86, 264, 54, 15);
		frmAa.getContentPane().add(label_2);

		replaceFileNamePath = new JTextField();
		replaceFileNamePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				// 获取桌面路径
				FileSystemView fsv = FileSystemView.getFileSystemView();
				chooser.setDialogTitle("请选择要文件夹路径");
				chooser.setApproveButtonText("确定");
				chooser.setCurrentDirectory(fsv.getHomeDirectory());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
					replaceFileNamePath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		replaceFileNamePath.setColumns(10);
		replaceFileNamePath.setBounds(226, 199, 262, 21);
		frmAa.getContentPane().add(replaceFileNamePath);

		replaceFileNameParam = new JTextField();
		replaceFileNameParam.setColumns(10);
		replaceFileNameParam.setBounds(226, 261, 262, 21);
		frmAa.getContentPane().add(replaceFileNameParam);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(557, 157, -554, 2);
		frmAa.getContentPane().add(separator);

		JButton button = new JButton("关闭");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setBounds(395, 136, 93, 23);
		frmAa.getContentPane().add(button);

		JButton button_1 = new JButton("关闭");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(395, 302, 93, 23);
		frmAa.getContentPane().add(button_1);
	}
}
