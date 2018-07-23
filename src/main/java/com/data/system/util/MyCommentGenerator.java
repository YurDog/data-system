package com.data.system.util;

import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.util.StringUtils;

/**
 * 失败了
 * 
 * @author 小二狗
 *
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
	private boolean suppressAllComments;
	private boolean addRemarkComments;

	public void addConfigurationProperties(Properties properties) {
		super.addConfigurationProperties(properties);
		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

		addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
	}

	public static boolean isTrue(String s) {
		return "true".equalsIgnoreCase(s); //$NON-NLS-1$
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		field.addJavaDocLine("/**");
		String remarks = introspectedColumn.getRemarks();
		if (addRemarkComments && !StringUtils.isEmpty(remarks)) {
			String[] remrakLines = remarks.split(System.getProperty("line. separator"));
			for (String remarkLine : remrakLines) {
				field.addJavaDocLine(" * " + remarkLine);
			}
		}
		field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
		field.addJavaDocLine(" */");
	}

	/**
	 * mapper接口注释关闭
	 */
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		return;
	}

	/**
	 * 映射文件注释关闭
	 */
	public void addComment(XmlElement xmlElement) {
		return;
	}
}
