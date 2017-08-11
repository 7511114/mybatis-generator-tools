package com.istock.generate.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class DBCommentGenerator extends DefaultCommentGenerator {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	   /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addTopLevelClassComment(org.mybatis.generator.api.dom.java.TopLevelClass, org.mybatis.generator.api.IntrospectedTable)
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
    	super.addModelClassComment(topLevelClass, introspectedTable);
        topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine(" * 上海尤恩信息技术有限公司:");
        topLevelClass.addJavaDocLine(" * (c) Copyright 2015-2020 uenpay");
        topLevelClass.addJavaDocLine(" * http://www.uenpay.com/");
        topLevelClass.addJavaDocLine(" * CREATE DATE  : 2"+sdf.format(new Date()));
        topLevelClass.addJavaDocLine(" * AUTHOR       :  "+System.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * MODIFIED BY  :");
        String[]  remarkLines = null;
        if(null != remarks){
        	remarkLines = remarks.split(System.getProperty("line.separator"));
        }else{
        	remarkLines = new String[0];
        }
        topLevelClass.addJavaDocLine(" * DESCRIPTION  :"+ (remarkLines.length >= 1 ? remarkLines[0] : ""));

        addJavadocTag(topLevelClass, false);

        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

	@Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn));
        field.addJavaDocLine(" * " + (introspectedColumn.getRemarks() != null ? introspectedColumn.getRemarks() : ""));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn));
        if (introspectedColumn.getRemarks() != null) {
            method.addJavaDocLine(" * 获得 " + introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn));
        if (introspectedColumn.getRemarks() != null) {
            method.addJavaDocLine(" * 设置 " + introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(" */");
    }

    private String getColumnDbTypeInfo(IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJdbcTypeName());
        if (introspectedColumn.getLength() > 0) {
            sb.append("(");
            sb.append(introspectedColumn.getLength());
            if (introspectedColumn.getScale() > 0) {
                sb.append(",").append(introspectedColumn.getScale());
            }
            sb.append(")");
        }
        if (introspectedColumn.getDefaultValue() != null) {
            sb.append(" 默认值[" + introspectedColumn.getDefaultValue() + "]");
        }
        if (!introspectedColumn.isNullable()) {
            sb.append(" 必填");
        }
        return sb.toString();
    }
}
