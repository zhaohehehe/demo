package com.zhaohe.demo.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ActionListenerFor {
	Class<? extends ActionListener> listener();
}

class OKListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "单击确认按钮");
	}

}

class CancelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "单击取消按钮");
	}

}

class ActionListenerInstaller {
	public static void processAnnotation(Object obj)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class<?> cl = obj.getClass();
		for (Field f : cl.getDeclaredFields()) {
			f.setAccessible(true);
			ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
			Object fObj = f.get(obj);
			if (fObj instanceof AbstractButton) {
				Class<? extends ActionListener> listenerClz = a.listener();
				ActionListener al = listenerClz.newInstance();
				AbstractButton ab = (AbstractButton) fObj;
				ab.addActionListener(al);
			}
		}
	}
}

public class ActionListenerAnnotation {
	private JFrame mainWin = new JFrame("使用注解绑定事件监听器");
	@ActionListenerFor(listener = OKListener.class)
	private JButton ok = new JButton("确定");
	@ActionListenerFor(listener = CancelListener.class)
	private JButton cancel = new JButton("取消");

	public void init() {
		JPanel jp = new JPanel();
		jp.add(ok);
		jp.add(cancel);
		mainWin.add(jp);
		try {
			ActionListenerInstaller.processAnnotation(this);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);
	}

	public static void main(String[] args) {
		new ActionListenerAnnotation().init();
	}
}
